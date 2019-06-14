package it.maucel89.bigterminal.lateral.connection;

import it.maucel89.bigterminal.lateral.connection.storage.ConnectionService;
import it.maucel89.bigterminal.lateral.tree.BaseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mauro Celani
 */
@Component
public class ConnectionTree extends BaseTree<Connection> {

	public ConnectionTree() {

		super(new ConnectionTreeItem().getRootItem("Connessioni"));

		getRoot().getChildren().addAll(
			_loadConnections(DEFAULT_ROOT_ITEM_ID));
	}

	private Collection<ConnectionTreeItem> _loadConnections(long parentId) {

		Set<ConnectionTreeItem> connectionTreeItemSet = new HashSet<>();

		for (Connection connection :
			_connectionService.getConnectionsByParentId(parentId)) {

			ConnectionTreeItem connectionTreeItem = new ConnectionTreeItem();

			connectionTreeItem.getChildren().addAll(
				_loadConnections(connection.getId()));

			connectionTreeItemSet.add(connectionTreeItem);
		}

		return connectionTreeItemSet;
	}

	@Autowired
	private ConnectionService _connectionService;

}
