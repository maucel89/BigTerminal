package it.maucel89.bigterminal.lateral.connection.storage;

import it.maucel89.bigterminal.lateral.connection.Connection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Mauro Celani
 */
public interface ConnectionRepository extends CrudRepository<Connection, Long> {

	public Connection findByHost(String host);

	public Connection findByHostAndUser(String host, String user);

	public Collection<Connection> findByParentId(long parentId);

	@Override
	@Transactional(timeout = 8)
	public Collection<Connection> findAll();

}
