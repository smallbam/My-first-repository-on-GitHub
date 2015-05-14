package org.eclipse.wang.concurrent;

//jstack file pid_29412.log
public class DeadThread implements Runnable
{
	private Object monitorA = new Object();

	private Object monitorB = new Object();

	@Override
	public void run()
	{
		for (int i = 0; i < 1; i--)
		{
			methodA();
			methodB();
		}
	}

	public void methodA()
	{
		synchronized (monitorA)
		{
			synchronized (monitorB)
			{
				System.out.println(Thread.currentThread().getName() + " invoke method A");
			}
		}
	}

	public void methodB()
	{
		synchronized (monitorB)
		{
			synchronized (monitorA)
			{
				System.out.println(Thread.currentThread().getName() + " invoke method B");
			}
		}
	}

	public static void main(String[] args)
	{
		DeadThread t1 = new DeadThread();
		Thread ta = new Thread(t1, "A");
		Thread tb = new Thread(t1, "B");
		ta.start();
		tb.start();
	}
}
