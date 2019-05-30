package it.maucel89.bigterminal.lateral.connection;

import it.maucel89.bigterminal.lateral.tree.BaseTreeItem;

/**
 * @author Mauro Celani
 */
public class ConnectionTreeItem extends BaseTreeItem<Connection> {

	@Override
	protected Connection createRootValueItem() {
		return new Connection();
	}

}
