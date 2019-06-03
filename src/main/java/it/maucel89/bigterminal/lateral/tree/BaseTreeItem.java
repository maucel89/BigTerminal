package it.maucel89.bigterminal.lateral.tree;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.TreeItem;

/**
 * @author Mauro Celani
 */
public abstract class BaseTreeItem<ValueType extends BaseValue>
	extends TreeItem<ValueType> {

	public TreeItem<ValueType> getRootItem(String label) {

		ValueType rootConnectionTreeItem = createRootValueItem();
		rootConnectionTreeItem.setText(label);

		setFolder(true);

		setValue(rootConnectionTreeItem);

		setGraphic(GlyphsDude.createIcon(
			FontAwesomeIcon.FOLDER, "17px"));

		return this;
	}

	protected abstract ValueType createRootValueItem();

	public void setFolder(boolean folder) {
		_folder = folder;
	}

	private boolean _folder;
	private ValueType _value;

}
