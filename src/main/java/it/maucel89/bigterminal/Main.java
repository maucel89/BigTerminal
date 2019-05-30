package it.maucel89.bigterminal;

import com.liferay.petra.string.StringPool;
import it.maucel89.bigterminal.lateral.LateralTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        _primaryStage = primaryStage;
        _primaryStage.setTitle("Big Terminal");

        _initSpring();

        Font.loadFont(Main.class.getResource("/fonts/fa-brands-400.ttf").
            toExternalForm(), 12);

        TabPane lateralTabPane = new LateralTabPane();
        TabPane connectionTabPane = _createConnectionTabPane();

        SplitPane splitTrees = new SplitPane();
        splitTrees.getItems().addAll(lateralTabPane, connectionTabPane);
        splitTrees.setDividerPositions(0.2);

        BorderPane borderPane = new BorderPane();
        //borderPane.setTop(topBox);
        borderPane.setCenter(splitTrees);

        Scene scene = new Scene(borderPane, 900d, 700d, Color.WHITE);
        scene.getStylesheets().add(
            getClass().getResource("main.css").toExternalForm());

        _primaryStage.setScene(scene);

        _primaryStage.show();
    }

    private TabPane _createConnectionTabPane() {
        TabPane connectionTabPane = new TabPane();

        Tab addConnectionTab = new Tab();
        addConnectionTab.setClosable(false);
        addConnectionTab.getStyleClass().addAll("tab");

        Label plusLabel = new Label();
        plusLabel.setText(StringPool.PLUS);

        plusLabel.setOnMouseClicked(event ->
            connectionTabPane.getTabs().addAll(
                new ConnectionTab()));

        addConnectionTab.setGraphic(plusLabel);

        connectionTabPane.getTabs().addAll(addConnectionTab);

        return connectionTabPane;
    }

    private void _initSpring() {

    }

    private Stage _primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

}
