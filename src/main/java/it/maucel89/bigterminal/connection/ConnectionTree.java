package it.maucel89.bigterminal.connection;

import javafx.scene.control.TreeView;

/**
 * @author Mauro Celani
 */
public class ConnectionTree extends TreeView<ConnectionTreeItem> {

	public ConnectionTree() {
		super(ConnectionTreeItem.getRootItem());
	}

}
