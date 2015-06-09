package org.eclipse.wang.db;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.eclipse.wang.db.manager.DBCfg;
import org.eclipse.wang.db.model.PlayerModel;

public class DBCache
{
	private static Cache playerModelCache;

	static
	{
		CacheManager singletonManager;
		try
		{
			singletonManager = CacheManager.create(Config.getConfigRoot() + "ehcache.xml");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			singletonManager = CacheManager.create();
		}
		playerModelCache = singletonManager.getCache("playerModelCache");
	}

	public static PlayerModel getPlayerModel(String playerId)
	{
		if (DBCfg.isUseApplicationCache() == true)
		{
			Element element = playerModelCache.get(playerId);
			if (element != null)
			{
				return (PlayerModel) element.getObjectValue();
			}
		}
		return null;
	}

	public static void setPlayerModel(PlayerModel player)
	{
		if (player != null && DBCfg.isUseApplicationCache() == true)
		{
			Element element = new Element(player.getId(), player);
			playerModelCache.put(element);
		}
	}

	public static synchronized void removePlayerModel(PlayerModel player)
	{
		if (DBCfg.isUseApplicationCache() == false)
		{
			return;
		}
		if (player != null)
		{
			playerModelCache.remove(player.getId());
		}
	}

	public static synchronized void removePlayerModel(String playerId)
	{
		if (DBCfg.isUseApplicationCache() == false)
		{
			return;
		}
		playerModelCache.remove(playerId);
	}

	public static synchronized void clearPlayerModelCache()
	{
		if (DBCfg.isUseApplicationCache() == false)
		{
			return;
		}
		playerModelCache.removeAll();
	}

	public static void main(String[] str)
	{
		new DBCache();
	}
}
