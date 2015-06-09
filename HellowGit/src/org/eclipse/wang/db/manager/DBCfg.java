package org.eclipse.wang.db.manager;

import java.io.FileInputStream;
import java.util.Properties;

import org.eclipse.wang.db.Config;

public class DBCfg
{
	private static String filePath = Config.getConfigRoot() + "DBConfig.properties";

	private static Properties prop = new Properties();

	static
	{
		FileInputStream fis;
		try
		{
			fis = new FileInputStream(filePath);
			prop.load(fis);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Properties getProp()
	{
		return prop;
	}

	public static boolean isUseApplicationCache()
	{
		return Boolean.valueOf(prop.getProperty("UseApplicationCache"));
	}
}
