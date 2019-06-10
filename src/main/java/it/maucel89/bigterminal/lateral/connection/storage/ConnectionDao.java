package it.maucel89.bigterminal.lateral.connection.storage;

import it.maucel89.bigterminal.lateral.connection.Connection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * @author Mauro Celani
 */
public interface ConnectionDao extends CrudRepository<Connection, Long> {

	@Query("select c from Connection c where c.host = :name")
	public Connection getConnectionByName(@Param("name") String name);

	@Query("select c from Connection c")
	public Collection<Connection> getAll();

	@Query("select c from Connection c where parentId = :parentId")
	public Collection<Connection> getConnectionsByParentId(
		@Param("parentId") long parentId);

}
