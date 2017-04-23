package org.eclipse.wang.aspectj;

public aspect HelloWorldAspect {
	public pointcut pointcut1() :
		call (public String org.eclipse.wang.aspectj.HelloWorld.getName());
	
	public pointcut pointcut2(String str) :
		call (public void org.eclipse.wang.aspectj.HelloWorld.setName(String))
		   && args (str);
	
	public pointcut pointcut3(String str) :
		call (public boolean org.eclipse.wang.aspectj.HelloWorld.changeName(String))
		  && args (str);
	
	// capture method return value
	after() returning (String returnValue) : pointcut1()
	{
		System.out.println("pointcut1 return value is:" + returnValue);
	}

	// capture method parameter
	after(String str) : pointcut2(str)
	{
		System.out.println("pointcut2 set value is:" + str);
	}
	
	// capture method return value and method parameter
	after(String str) returning (boolean returnValue) : pointcut3(str)
	{
		if (returnValue)
		{
			System.out.println("pointcut3 return value is:" + returnValue + ",changeName is:" + str);
		}
	}
	
	// anonymous
	after(String str) returning (boolean returnValue) :
		call (public boolean org.eclipse.wang.aspectj.HelloWorld.changeName(String))
		  && args (str)
    {
		if (returnValue)
		{
			System.out.println("I am a anonymous after advice!!!");
		}
    }
}
