package it.maucel89.bigterminal.lateral.connection;

import it.maucel89.bigterminal.lateral.connection.storage.ConnectionDao;
import it.maucel89.bigterminal.lateral.tree.BaseTree;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mauro Celani
 */
public class ConnectionTree extends BaseTree<Connection> {

	public ConnectionTree(
		ConnectionDao connectionDao) {

		super(new ConnectionTreeItem().getRootItem("Connessioni"));

		getRoot().getChildren().addAll(
			loadConnections(connectionDao, DEFAULT_ROOT_ITEM_ID));
	}

	private Collection<ConnectionTreeItem> loadConnections(
		ConnectionDao connectionDao, long parentId) {

		Set<ConnectionTreeItem> connectionTreeItemSet = new HashSet<>();

		for (Connection connection :
			connectionDao.getConnectionsByParentId(parentId)) {

			ConnectionTreeItem connectionTreeItem = new ConnectionTreeItem();

			connectionTreeItem.getChildren().addAll(
				loadConnections(connectionDao, connection.getId()));

			connectionTreeItemSet.add(connectionTreeItem);
		}

		return connectionTreeItemSet;
	}

}
