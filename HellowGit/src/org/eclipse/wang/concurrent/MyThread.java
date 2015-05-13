package org.eclipse.wang.concurrent;

public class MyThread implements Runnable
{

	public static void main(String[] args)
	{
		MyThread t1 = new MyThread();
		//		t1.run();
		Thread ta = new Thread(t1, "A");
		//		Thread tb = new Thread(t1, "B");
		//		ta.run();
		ta.start();
		System.out.println("do you know diff run() and start() in order?");
		//				tb.start();
	}

	@Override
	public void run()
	{
		synchronized (this)
		{
			for (int i = 0; i < 10; i++)
			{
				System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
				try
				{
					Thread.sleep(1000L);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
