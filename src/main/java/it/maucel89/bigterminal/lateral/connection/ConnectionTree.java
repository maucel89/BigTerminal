package it.maucel89.bigterminal.lateral.connection;

import it.maucel89.bigterminal.lateral.tree.BaseTree;

/**
 * @author Mauro Celani
 */
public class ConnectionTree extends BaseTree<Connection> {

	public ConnectionTree() {
		super(new ConnectionTreeItem().getRootItem("Connessioni"));
	}

}
