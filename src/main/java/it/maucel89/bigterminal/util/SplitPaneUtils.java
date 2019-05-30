package it.maucel89.bigterminal.util;

import it.maucel89.bigterminal.lateral.LateralTabPane;
import it.maucel89.bigterminal.lateral.tree.BaseTree;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;

/**
 * @author Mauro Celani
 */
public class SplitPaneUtils {

	public static SplitPane getSplitPane(
		BaseTree baseTree, Node scene) {

		TabPane lateralTabPane = new LateralTabPane(baseTree);

		SplitPane splitPane = new SplitPane();
		splitPane.getItems().addAll(lateralTabPane, scene);
		splitPane.setDividerPositions(0.2);

		return splitPane;
	}
}
