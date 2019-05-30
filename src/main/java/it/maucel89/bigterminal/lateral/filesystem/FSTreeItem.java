package it.maucel89.bigterminal.lateral.filesystem;

import it.maucel89.bigterminal.lateral.tree.BaseTreeItem;

/**
 * @author Mauro Celani
 */
public class FSTreeItem extends BaseTreeItem<FSItem> {

	@Override
	protected FSItem createRootValueItem() {
		return new FSItem();
	}

}
