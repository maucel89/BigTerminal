package it.maucel89.bigterminal.lateral.tree;

import javafx.scene.control.TreeItem;

/**
 * @author Mauro Celani
 */
public abstract class BaseTreeItem<ValueType> extends TreeItem<ValueType> {

	public TreeItem<ValueType> getRootItem(String label) {

		ValueType rootConnectionTreeItem = createRootValueItem();

		setFolder(true);
		setText(label);

		setValue(rootConnectionTreeItem);

		//GlyphView icon = new GlyphView();
		//icon.setF

		//root.setGraphic();

		return this;
	}

	protected abstract ValueType createRootValueItem();

	public void setFolder(boolean folder) {
		_folder = folder;
	}

	public void setText(String text) {
		_text = text;
	}

	@Override
	public String toString() {
		return _text;
	}

	private boolean _folder;
	private ValueType _value;
	private String _text;

}
