package org.eclipse.wang.junit4;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * I cannot reproduce this issue. How do you run your tests? Do you have a
 * stacktrace?
 * 
 * @author super
 * @created on 2015年7月28日
 */
public class Snippet
{
	@Test
	public void testJunit()
	{
		assertArrayEquals(new Boolean[] { true }, new Boolean[] { false });
	}

	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(Snippet.class);
		result.getFailures().get(0).getException().printStackTrace(System.out);
	}
}
