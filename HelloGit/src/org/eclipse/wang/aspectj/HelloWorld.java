package org.eclipse.wang.aspectj;

public class HelloWorld
{
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void sayHello()
	{
		System.out.println("Hello World!");
	}

	public boolean changeName(String str)
	{
		setName("whoami");
		return true;
	}
}
