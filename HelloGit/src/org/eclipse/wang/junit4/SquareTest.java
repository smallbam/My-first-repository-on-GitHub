package org.eclipse.wang.junit4;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SquareTest
{
	private static Calculator calculator = new Calculator();

	private int param;

	private int result;

	@Parameters
	public static Collection data()
	{
		return Arrays.asList(new Object[][] { { 2, 4 }, { 0, 0 }, { -3, 9 } });
	}

	public SquareTest(int param, int result)
	{
		this.param = param;
		this.result = result;
	}

	@Before
	public void setUp() throws Exception
	{
		calculator.clear();
	}

	@Test
	public void test()
	{
		calculator.square(param);
		assertEquals(result, calculator.getResult());
	}

}
