package it.maucel89.bigterminal.lateral.connection;

import it.maucel89.bigterminal.lateral.tree.BaseValue;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Mauro Celani
 */
@Data
public class Connection extends BaseValue implements Comparable<Connection> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String host;
	private String user;
	private String pass;

	@Override
	public int compareTo(@NotNull Connection connection) {
		return 0;
	}

}
