package it.maucel89.bigterminal;

import com.kodedu.terminalfx.TerminalTab;
import com.liferay.petra.string.StringPool;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import it.maucel89.bigterminal.lateral.connection.ConnectionTree;
import it.maucel89.bigterminal.lateral.connection.storage.ConnectionRepository;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { ConnectionRepository.class })
public class BigTerminal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        _log.debug("Start BigTerminal");

        _initSpring();

        _primaryStage = primaryStage;
        _primaryStage.setTitle("BigTerminal");

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

    private void _initSpring() {
        _springContext = SpringApplication.run(BigTerminal.class);
    }

    @Override
    public void stop() throws Exception {
        _log.debug("Stop BigTerminal");

        _connectionTabPane.getTabs().forEach(tab -> {
            if (tab instanceof TerminalTab) {
               ((TerminalTab)tab).destroy();
            }
        });

        _springContext.stop();
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
                new ConnectionTree(), homePane));

        return homeTab;
    }

    private Stage _primaryStage;
    private TabPane _connectionTabPane;

    private ConfigurableApplicationContext _springContext;

    public static void main(String[] args) {
        launch(args);
    }

    private static final Logger _log = Logger.getLogger(BigTerminal.class);

}
