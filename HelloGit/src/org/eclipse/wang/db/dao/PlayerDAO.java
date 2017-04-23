package org.eclipse.wang.db.dao;

import org.eclipse.wang.db.DBCache;
import org.eclipse.wang.db.manager.HibernateManager;
import org.eclipse.wang.db.model.PlayerModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class PlayerDAO extends BasicDAO
{
	private static final String modelName = PlayerModel.class.getName();

	public static PlayerModel loadByPlayerId(String playerId)
	{
		Session session = HibernateManager.getSession();
		PlayerModel playerModel = DBCache.getPlayerModel(playerId);
		if (playerModel != null)
		{
			return playerModel;
		}
		String sql = "FROM " + modelName + " players WHERE players.id=:ID";
		Query query = session.createQuery(sql);
		query.setParameter("ID", playerId);
		query.setMaxResults(1);
		//		query.setCacheable(true);
		playerModel = (PlayerModel) query.uniqueResult();
		return playerModel;
	}

	public static void save(PlayerModel playerModel)
	{
		if (playerModel == null)
		{
			return;
		}
		Session session = HibernateManager.getSession();
		try
		{
			session.saveOrUpdate(playerModel);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			playerModel = (PlayerModel) session.merge(playerModel);
		}
		DBCache.setPlayerModel(playerModel);
	}
}
