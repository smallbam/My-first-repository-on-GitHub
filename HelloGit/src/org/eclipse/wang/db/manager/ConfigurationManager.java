package org.eclipse.wang.db.manager;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.eclipse.wang.db.model.PlayerModel;
import org.hibernate.cfg.Configuration;

public class ConfigurationManager
{
	private static Set<Class<?>> gameSet = new HashSet<Class<?>>();

	static
	{
		initHibernateConfiguration();
	}

	public static Configuration getHibernateConfiguration(String propertyPrefix)
	{
		Configuration config = new Configuration();
		for (Class<?> clazz : gameSet)
		{
			if (clazz != null)
			{
				config.addAnnotatedClass(clazz);
			}
		}
		applyDBConfigProperties(propertyPrefix, config);
		return config;
	}

	// http://www.cnblogs.com/elleniou/archive/2012/12/01/2797546.html
	private static Configuration applyDBConfigProperties(String prefix, Configuration config)
	{
		testApplyConfigProperty(config, prefix, "hibernate.connection.url");
		testApplyConfigProperty(config, prefix, "hibernate.connection.username");
		testApplyConfigProperty(config, prefix, "hibernate.connection.password");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.timeout");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.idle_test_period");
		testApplyConfigProperty(config, prefix, "hibernate.cache.use_query_cache");
		testApplyConfigProperty(config, prefix, "hibernate.cache.provider_class");

		testApplyConfigProperty(config, prefix, "hibernate.dialect");
		testApplyConfigProperty(config, prefix, "hibernate.connection.driver_class");
		testApplyConfigProperty(config, prefix, "hibernate.hbm2ddl.auto");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.max_size");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.min_size");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.max_statements");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.acquire_increment");
		testApplyConfigProperty(config, prefix, "hibernate.c3p0.validate");
		testApplyConfigProperty(config, prefix, "hibernate.statement_cache.size");
		testApplyConfigProperty(config, prefix, "hibernate.jdbc.fetch_size");
		testApplyConfigProperty(config, prefix, "hibernate.jdbc.batch_size");
		testApplyConfigProperty(config, prefix, "hibernate.use_outer_join");
		testApplyConfigProperty(config, prefix, "hibernate.show_sql");
		testApplyConfigProperty(config, prefix, "hibernate.connection.autoReconnect");
		testApplyConfigProperty(config, prefix, "hibernate.cache.use_second_level_cache");
		testApplyConfigProperty(config, prefix, "hibernate.cache.region.factory_class");

		return config;
	}

	private static Configuration testApplyConfigProperty(Configuration config, String prefix,
															String propertyName)
	{
		final String DEFAULT_PREFIX = "DefaultDB.";

		Properties prop = DBCfg.getProp();

		String strVal = prop.getProperty(prefix + propertyName);
		if (strVal == null)
		{
			strVal = prop.getProperty(DEFAULT_PREFIX + propertyName);
		}
		if (strVal != null)
		{
			config.setProperty(propertyName, strVal);
		}
		return config;
	}

	private static void initHibernateConfiguration()
	{
		gameSet.add(PlayerModel.class);
	}

	public static void addHibernate(Class<?> clazz)
	{
		gameSet.add(clazz);
	}
}
