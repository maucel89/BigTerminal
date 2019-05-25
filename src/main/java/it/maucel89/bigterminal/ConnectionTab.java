package it.maucel89.bigterminal;

import it.maucel89.bigterminal.util.KeyCodeUtils;
import javafx.event.Event;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @author maucel89
 */

public class ConnectionTab extends Tab {

	private TextArea textArea;

	public ConnectionTab() {
		textArea = new TextArea();
		textArea.setFont(Font.font("Monospaced", 14));

		setClosable(false);
		setContent(textArea);
	}

	public void setPrintStream(PrintStream printStream) {

		textArea.addEventHandler(KeyEvent.KEY_RELEASED, Event::consume);

		textArea.addEventHandler(KeyEvent.KEY_TYPED, Event::consume);

		textArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

			byte[] code = KeyCodeUtils.getCodeByEvent(event);

			if (code != null) {
				try {
					printStream.write(code);
				}
				catch (IOException e) {
					//logger.warn("failed to write to stream", e);
				}
			}
			else {
				printStream.print(event.getText());
			}

			event.consume();
		});
	}

	public TextArea getTextArea() {
		return textArea;
	}

	//private static final Logger logger = LoggerFactory.getLogger(
		//ConnectionTab.class);

}