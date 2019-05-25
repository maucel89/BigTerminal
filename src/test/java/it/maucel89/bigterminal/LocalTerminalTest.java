package it.maucel89.bigterminal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

/**
 * @author Mauro Celani
 */
public class LocalTerminalTest {

	@Override
	public void start (Stage stage) throws Exception {
		Parent mainNode = FXMLLoader.load(Main.class.getResource("sample.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}

	@Test
	public void testLocalTerminal() {



		LocalTerminal localTerminal = new LocalTerminal();
	}

}
