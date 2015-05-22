package org.eclipse.wang.db.manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateManager
{
	private static final String HIBERNATE_CFG_PREFIX = "GameDB.";

	private static Configuration configuration;

	private static SessionFactory sessionFactory;

	private static ServiceRegistry serviceRegistry;

	private static ThreadLocal<Session> session = new ThreadLocal<Session>();

	private static ThreadLocal<Transaction> transaction = new ThreadLocal<Transaction>();

	public static Session getSession() throws HibernateException
	{
		Session s = session.get();
		if (s == null)
		{
			try
			{
				if (sessionFactory == null)
				{
					try
					{
						configuration = ConfigurationManager
								.getHibernateConfiguration(HIBERNATE_CFG_PREFIX);
						serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
							configuration.getProperties()).build();
						sessionFactory = configuration.buildSessionFactory(serviceRegistry);
					}
					catch (HibernateException e)
					{
						e.printStackTrace();
					}
				}
				s = sessionFactory.openSession();
			}
			catch (HibernateException e)
			{
				e.printStackTrace();
				throw e;
			}

			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException
	{
		Session s = session.get();
		session.set(null);
		transaction.set(null);
		if (s != null)
		{
			try
			{
				s.close();
			}
			catch (HibernateException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	}

	public static void txBegin() throws HibernateException
	{
		try
		{
			Session s = getSession();
			Transaction t = s.beginTransaction();
			transaction.set(t);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw e;
		}

	}

	public static boolean txCommit() throws HibernateException
	{
		Session s = session.get();
		Transaction t = transaction.get();
		if (s == null || t == null)
		{
			return false;
		}
		else
		{
			try
			{
				t.commit();
			}
			catch (HibernateException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
		return true;
	}

	public static boolean txRollback() throws HibernateException
	{
		Session s = session.get();
		Transaction t = transaction.get();
		if (s == null || t == null)
		{
			return false;
		}
		else
		{
			try
			{
				t.rollback();
			}
			catch (HibernateException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
		return true;
	}

	public static void clearSession()
	{
		Session session = getSession();
		session.clear();
	}

	public static void flushSession()
	{
		Session session = getSession();
		session.flush();
	}

	// just for test
	public static void mian(String[] str)
	{
		Runnable r1 = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(2000L);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				HibernateManager.getSession();
			}
		};

		Runnable r2 = new Runnable()
		{
			@Override
			public void run()
			{
				HibernateManager.getSession();
				try
				{
					Thread.sleep(1000L);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		};

		// Each thread holds an implicit reference to its copy of a thread-local variable 
		// as long as the thread is alive and the ThreadLocal instance is accessible; 
		// after a thread goes away, all of its copies of thread-local instances 
		// are subject to garbage collection (unless other references to these copies exist).
		Thread t1 = new Thread(r1, "Thread_A");
		Thread t2 = new Thread(r1, "Thread_B");
		Thread t3 = new Thread(r2, "Thread_C");
		t1.start();
		t2.start();
		t3.start();
	}
}
