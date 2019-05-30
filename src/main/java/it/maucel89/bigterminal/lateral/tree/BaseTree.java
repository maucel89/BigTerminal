package it.maucel89.bigterminal.lateral.tree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * @author Mauro Celani
 */
public class BaseTree<ValueType> extends TreeView<ValueType> {

	public BaseTree(TreeItem<ValueType> rootTreeItem) {
		super(rootTreeItem);
	}

}
