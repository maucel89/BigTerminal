package it.maucel89.bigterminal.lateral.connection.storage;

import it.maucel89.bigterminal.lateral.connection.Connection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Mauro Celani
 */
public interface ConnectionDao extends CrudRepository<Connection, Long> {

	@Query("select c from Connection c where c.host = :name")
	public Connection getHostByName(@Param("name") String name);

	@Query("select c from Connection c")
	public List<Connection> getAll();

	@Query("select c.host from Connection c")
	public List<String> getHost();

}
