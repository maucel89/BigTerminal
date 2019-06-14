package it.maucel89.bigterminal.lateral.connection.storage;

import it.maucel89.bigterminal.lateral.connection.Connection;

import java.util.Collection;

/**
 * @author Mauro Celani
 */
public interface ConnectionService {

	Collection<Connection> getAllArticles();

	Connection getConnectionById(long connectionId);

	boolean addConnection(Connection connection);

	void updateConnection(Connection connection);

	void deleteConnection(int connectionId);

	Iterable<? extends Connection> getConnectionsByParentId(long parentId);

}
