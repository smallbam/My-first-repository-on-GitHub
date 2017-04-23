package org.eclipse.wang.db;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Config
{
	private static final String SERVER_VERSION = "1";

	public static boolean debugMode = false;

	public static final String DEFAULT_CONFIG_ROOT = "./config/";

	private static String configRoot = Config.DEFAULT_CONFIG_ROOT;

	private static boolean isConfigRootAlreadySetup = false;

	public static String getConfigRoot()
	{
		return Config.configRoot;
	}

	private static void printVersionNumber()
	{
		try
		{
			String filePath = configRoot + "version.ini";
			FileOutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(SERVER_VERSION);
			osw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean loadConfig(String pConfigRoot)
	{
		if (isConfigRootAlreadySetup)
		{
			return false;
		}
		else
		{
			isConfigRootAlreadySetup = true;
			configRoot = pConfigRoot;
			printVersionNumber();
			return true;
		}
	}
}
