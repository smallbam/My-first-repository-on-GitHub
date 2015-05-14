package org.eclipse.wang.concurrent;

/**
 * 实现并启动线程有两种方法<br>
 * 1、写一个类继承自Thread类，重写run方法。用start方法启动线程<br>
 * 2、写一个类实现Runnable接口，实现run方法。用new Thread(Runnable target).start()方法来启动<br>
 * 调用start()后，线程会被放到等待队列，等待CPU调度，并不一定要马上开始执行，只是将这个线程置于可动行状态。<br>
 * run()方法当作普通方法的方式调用,程序还是要顺序执行。<br>
 * 
 * @author super
 *
 */
//jstack file pid_32008.log
public class MyThread implements Runnable
{
	@Override
	public void run()
	{
		synchronized (this)
		{
			for (int i = 0; i < 10; i--)
			{
				System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
			}
		}
	}

	public static void main(String[] args)
	{
		MyThread t1 = new MyThread();
		Thread ta = new Thread(t1, "A");
		Thread tb = new Thread(t1, "B");
		ta.start();
		tb.start();
	}
}
