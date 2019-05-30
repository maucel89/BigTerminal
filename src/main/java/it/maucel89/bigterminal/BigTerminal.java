package it.maucel89.bigterminal;

import com.liferay.petra.string.StringPool;
import it.maucel89.bigterminal.lateral.LateralTabPane;
import it.maucel89.bigterminal.terminal.Terminal;
import it.maucel89.bigterminal.terminal.TerminalTab;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

public class BigTerminal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        _log.debug("Start BigTerminal");

        _primaryStage = primaryStage;
        _primaryStage.setTitle("BigTerminal");

        _initSpring();

        Font.loadFont(BigTerminal.class.getResource("/fonts/fa-brands-400.ttf")
            .toExternalForm(), 12);

        TabPane lateralTabPane = new LateralTabPane();
        TabPane connectionTabPane = _createConnectionTabPane();

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(lateralTabPane, connectionTabPane);
        splitPane.setDividerPositions(0.2);

        BorderPane borderPane = new BorderPane();
        //borderPane.setTop(topBox);
        borderPane.setCenter(splitPane);

        Scene scene = new Scene(
            borderPane, 900d, 700d, Color.WHITE);

        scene.getStylesheets().add(
            getClass().getResource("main.css").toExternalForm());

        _primaryStage.setScene(scene);

        _primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        _log.debug("Stop BigTerminal");

        _connectionTabPane.getTabs().forEach(tab -> {
            if (tab instanceof TerminalTab) {
               ((TerminalTab)tab).close();
            }
        });
    }

    private TabPane _createConnectionTabPane() {
        _connectionTabPane = new TabPane();

        Tab homeTab = new Tab();
        homeTab.setClosable(false);
        homeTab.setText("Home");

        Tab addConnectionTab = new Tab();
        addConnectionTab.setClosable(false);
        addConnectionTab.setText(StringPool.PLUS);

        ObservableList<Tab> tabs = _connectionTabPane.getTabs();

        tabs.addAll(homeTab, addConnectionTab);

        SingleSelectionModel<Tab> selectionModel =
            _connectionTabPane.getSelectionModel();

        selectionModel
            .selectedItemProperty()
            .addListener((observable, oldTab, newTab) -> {
                if (newTab == addConnectionTab) {
                    try {
                        TerminalTab terminalTab = new TerminalTab();
                        tabs.add(tabs.size() - 1, terminalTab);
                        selectionModel.select(terminalTab);
                    }
                    catch (IOException e) {
                        _log.error(e, e);
                    }
                }
            });

        return _connectionTabPane;
    }

    private void _initSpring() {

    }

    private Stage _primaryStage;
    private TabPane _connectionTabPane;

    public static void main(String[] args) {
        launch(args);
    }

    private static final Logger _log = Logger.getLogger(BigTerminal.class);

}
