package org.eclipse.wang.junit4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorTest.class, AdvancedTest.class, SquareTest.class })
public class AllCalculatorTest
{}
