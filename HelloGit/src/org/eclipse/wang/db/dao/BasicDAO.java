package org.eclipse.wang.db.dao;

import org.eclipse.wang.db.manager.HibernateManager;
import org.hibernate.Query;
import org.hibernate.Session;

public class BasicDAO
{
	public static void save(Object entity)
	{
		if (entity == null)
		{
			return;
		}
		Session session = HibernateManager.getSession();
		session.save(entity);
	}

	public static void update(Object entity)
	{
		Session session = HibernateManager.getSession();
		session.update(entity);
	}

	public static void saveOrUpdate(Object entity)
	{
		Session session = HibernateManager.getSession();
		session.saveOrUpdate(entity);
	}

	public static void delete(Object entity)
	{
		Session session = HibernateManager.getSession();
		session.delete(entity);
	}

	public static int execute(String command)
	{
		Session session = HibernateManager.getSession();
		Query query = session.createSQLQuery(command);
		return query.executeUpdate();
	}
}
