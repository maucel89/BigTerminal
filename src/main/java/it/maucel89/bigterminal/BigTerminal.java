package it.maucel89.bigterminal;

import com.kodedu.terminalfx.TerminalTab;
import com.liferay.petra.string.StringPool;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import it.maucel89.bigterminal.lateral.connection.ConnectionTree;
import it.maucel89.bigterminal.lateral.connection.storage.ConnectionDao;
import it.maucel89.bigterminal.terminal.TerminalTabBuilder;
import it.maucel89.bigterminal.util.SplitPaneUtils;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BigTerminal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        _log.debug("Start BigTerminal");

        _primaryStage = primaryStage;
        _primaryStage.setTitle("BigTerminal");

        _initSpring();

        TabPane connectionTabPane = _createConnectionTabPane();

        BorderPane borderPane = new BorderPane();
        //borderPane.setTop(topBox);
        borderPane.setCenter(connectionTabPane);

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
               ((TerminalTab)tab).destroy();
            }
        });

        _context.stop();
    }

    private TabPane _createConnectionTabPane() {

        _connectionTabPane = new TabPane();

        Tab homeTab = _initHomeTab();

        Tab addConnectionTab = new Tab();
        addConnectionTab.setClosable(false);
        addConnectionTab.setGraphic(GlyphsDude.createIcon(
            FontAwesomeIcon.PLUS, "15px"));

        ObservableList<Tab> tabs = _connectionTabPane.getTabs();

        tabs.addAll(homeTab, addConnectionTab);

        SingleSelectionModel<Tab> selectionModel =
            _connectionTabPane.getSelectionModel();

        selectionModel
            .selectedItemProperty()
            .addListener((observable, oldTab, newTab) -> {
                if (newTab == addConnectionTab) {
                    Tab terminalTab =
                        TerminalTabBuilder.createLocalTerminalTab();

                    tabs.add(tabs.size() - 1, terminalTab);
                    selectionModel.select(terminalTab);
                }
            });

        return _connectionTabPane;
    }

    private Tab _initHomeTab() {

        Tab homeTab = new Tab();
        homeTab.setClosable(false);
        homeTab.setGraphic(GlyphsDude.createIcon(
            FontAwesomeIcon.HOME, "15px"));

        Pane homePane = new Pane();

        homeTab.setContent(
            SplitPaneUtils.getSplitPane(
                new ConnectionTree(_connectionDao), homePane));

        return homeTab;
    }

    private void _initSpring() {

        _context = new AnnotationConfigApplicationContext(BigTerminal.class);

        _connectionDao = _context.getBean(ConnectionDao.class);
    }

    private Stage _primaryStage;
    private TabPane _connectionTabPane;

    private AnnotationConfigApplicationContext _context;
    private ConnectionDao _connectionDao;

    public static void main(String[] args) {
        launch(args);
    }

    private static final Logger _log = Logger.getLogger(BigTerminal.class);

}
