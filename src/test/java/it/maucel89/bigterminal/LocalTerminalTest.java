package it.maucel89.bigterminal;

import it.maucel89.bigterminal.terminal.LocalTerminal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

/**
 * @author Mauro Celani
 */
public class LocalTerminalTest extends ApplicationTest {

	@Override
	public void start (Stage stage) throws Exception {
		Parent mainNode = FXMLLoader.load(BigTerminal.class.getResource("sample.fxml"));
		stage.setScene(new Scene(mainNode));
		stage.show();
		stage.toFront();
	}

	@Test
	public void testLocalTerminal() {

		try {
			LocalTerminal localTerminal = new LocalTerminal();

		}
		catch (IOException e) {

		}

	}


	private static final Logger _log = LoggerFactory.getLogger(
		LocalTerminalTest.class);

}
