package org.eclipse.wang.db.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BasicModel implements java.io.Serializable
{
	private static final long serialVersionUID = 6214841902800227112L;

	private String db_id;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getDb_Id()
	{
		return db_id;
	}

	public void setDb_Id(String db_id)
	{
		this.db_id = db_id;
	}
}
