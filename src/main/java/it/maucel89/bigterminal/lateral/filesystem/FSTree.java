package it.maucel89.bigterminal.lateral.filesystem;

import it.maucel89.bigterminal.lateral.tree.BaseTree;

/**
 * @author Mauro Celani
 */
public class FSTree extends BaseTree<FSItem> {

	public FSTree() {
		super(new FSTreeItem().getRootItem("root"));
	}

}
