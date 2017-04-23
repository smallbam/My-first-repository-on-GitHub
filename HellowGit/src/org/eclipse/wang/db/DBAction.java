package org.eclipse.wang.db;

import org.eclipse.wang.db.dao.PlayerDAO;
import org.eclipse.wang.db.manager.HibernateManager;
import org.eclipse.wang.db.model.PlayerModel;

/**
 * DB operation interface
 * 
 * @author super
 * @created on 2015年6月2日
 */
public class DBAction
{
	static
	{
		System.out.println("DBAction loaded.");
	}

	public static PlayerModel loadPlayerModelById(String playerId)
	{
		PlayerModel playerModel = null;
		playerModel = PlayerDAO.loadByPlayerId(playerId);
		if (playerModel != null)
		{
			DBCache.setPlayerModel(playerModel);
		}
		else
		{
			System.out.println("LoadPlayerById " + playerId + " got null.");
		}
		HibernateManager.clearSession();
		return playerModel;
	}

	public static void savePlayerModel(PlayerModel playerModel)
	{
		HibernateManager.txBegin();
		try
		{
			PlayerDAO.save(playerModel);
			HibernateManager.txCommit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			HibernateManager.txRollback();
			HibernateManager.clearSession();
		}
		finally
		{
			HibernateManager.clearSession();
		}
	}

	public static void deletePlayerModel(PlayerModel playerModel)
	{
		HibernateManager.txBegin();
		try
		{
			PlayerDAO.delete(playerModel);
			HibernateManager.txCommit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			HibernateManager.txRollback();
		}
		finally
		{
			HibernateManager.clearSession();
			DBCache.removePlayerModel(playerModel.getId());
		}
	}
}
