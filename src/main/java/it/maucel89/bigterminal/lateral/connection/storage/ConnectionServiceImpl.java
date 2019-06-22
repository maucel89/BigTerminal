package it.maucel89.bigterminal.lateral.connection.storage;

import it.maucel89.bigterminal.lateral.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Override
	public Collection<Connection> getAllArticles() {
		return _connectionRepository.findAll();
	}

	@Override
	public Connection getConnectionById(long connectionId) {

		return _connectionRepository
			.findById(connectionId)
			.get();
	}

	@Override
	public synchronized boolean addConnection(
		Connection connection) {

		Connection exists = _connectionRepository.findByHostAndUser(
			connection.getHost(), connection.getUser());

		if (exists != null) {
			return true;
		}

		updateConnection(connection);

		return false;
	}

	@Override
	public void updateConnection(
		Connection connection) {

		_connectionRepository.save(connection);
	}

	@Override
	public void deleteConnection(int connectionId) {

		_connectionRepository.delete(getConnectionById(connectionId));
	}

	@Override
	public Iterable<? extends Connection> getConnectionsByParentId(
		long parentId) {

		return _connectionRepository.findByParentId(parentId);
	}

	@Autowired
	private ConnectionRepository _connectionRepository;

}
