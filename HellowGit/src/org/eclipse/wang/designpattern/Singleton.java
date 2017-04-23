package org.eclipse.wang.designpattern;

public class Singleton
{
	private Singleton()
	{};

	public static Singleton getInstance()
	{
		return instance;
	}

	private static Singleton instance = new Singleton();
}
