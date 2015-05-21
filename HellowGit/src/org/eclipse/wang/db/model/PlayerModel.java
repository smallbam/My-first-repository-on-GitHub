package org.eclipse.wang.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "players", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }),
		@UniqueConstraint(columnNames = { "name" }) })
public class PlayerModel extends CreatureModel
{
	private static final long serialVersionUID = 8474195481281460853L;
}
