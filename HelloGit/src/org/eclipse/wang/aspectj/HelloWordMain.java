package org.eclipse.wang.aspectj;

/**
 * after advice and cross point
 * 
 * @author super
 * @created on 2015年7月24日
 */
public class HelloWordMain
{
	public static void main(String[] args)
	{
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setName("WhoAmI?");
		String name = helloWorld.getName();
		helloWorld.sayHello();
		helloWorld.changeName(name + name);
		helloWorld.getName();
	}
}
