package it.maucel89.bigterminal.lateral;

import it.maucel89.bigterminal.connection.ConnectionTree;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author Mauro Celani
 */
public class LateralTabPane extends TabPane {

	public LateralTabPane() {
		super();

		setSide(Side.LEFT);

		_initTabs();
	}

	private void _initTabs() {

		Tab tab1 = new Tab("tab1");
		tab1.setClosable(false);
		tab1.setContent(new ConnectionTree());

		Tab tab2 = new Tab("tab2");
		tab2.setClosable(false);

		Tab tab3 = new Tab("tab3");
		tab3.setClosable(false);

		getTabs().addAll(tab1, tab2, tab3);
	}

}
