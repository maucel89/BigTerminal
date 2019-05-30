package it.maucel89.bigterminal.connection;

import com.sun.javafx.font.Glyph;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import javax.swing.text.GlyphView;

/**
 * @author Mauro Celani
 */
public class ConnectionTreeItem {

	public ConnectionTreeItem() {
	}

	public static TreeItem<ConnectionTreeItem> getRootItem() {

		TreeItem<ConnectionTreeItem> root = new TreeItem();

		ConnectionTreeItem rootConnectionTreeItem = new ConnectionTreeItem();

		rootConnectionTreeItem.setFolder(true);
		rootConnectionTreeItem.setText("Connessioni");

		//GlyphView icon = new GlyphView();
		//icon.setF

		//root.setGraphic();
		root.setValue(rootConnectionTreeItem);

		return root;
	}

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
	private Connection _connection;
	private String _text;

}
