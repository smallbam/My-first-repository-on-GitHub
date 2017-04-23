package org.eclipse.wang.junit4;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// import junit.internal.runners.TestClassRunner;
// This is never existed.

/**
 * @see http://junit.org/javadoc/latest/org/junit/runners/package-summary.html
 * 
 * @author super
 * @created on 2015年7月29日
 */
@RunWith(JUnit4.class)
// Aliases the current default JUnit 4 class runner, for future-proofing.
public class AdvancedTest
{
	private static Calculator calculator = new Calculator();

	@Before
	public void clearCalculator()
	{
		calculator.clear();
	}

	@Test
	public void square1()
	{
		calculator.square(2);
		assertEquals(4, calculator.getResult());
	}

	@Test
	public void square2()
	{
		calculator.square(0);
		assertEquals(0, calculator.getResult());
	}

	@Test
	public void square3()
	{
		calculator.square(-3);
		assertEquals(9, calculator.getResult());
	}
}
