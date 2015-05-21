package org.eclipse.wang.db.dao;

import org.eclipse.wang.db.manager.HibernateManager;
import org.eclipse.wang.db.model.PlayerModel;
import org.hibernate.Query;
import org.hibernate.Session;

public class PlayerDAO extends BasicDAO
{
	private static final String modelName = PlayerModel.class.getName();

	public static PlayerModel loadByPlayerId(String id)
	{
		Session session = HibernateManager.getSession();
		String sql = "FROM " + modelName + " players WHERE players.id=:ID";
		Query query = session.createQuery(sql);
		query.setParameter("ID", id);
		query.setMaxResults(1);
		//		query.setCacheable(true);
		PlayerModel playerModel = (PlayerModel) query.uniqueResult();
		return playerModel;
	}
}
