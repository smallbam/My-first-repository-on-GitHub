package org.eclipse.wang.db.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CreatureModel extends BasicModel
{
	private static final long serialVersionUID = 6194152364211187380L;

	protected String id;

	protected String name;

	protected int level;

	protected int unitType;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getUnitType()
	{
		return unitType;
	}

	public void setUnitType(int unitType)
	{
		this.unitType = unitType;
	}
}
