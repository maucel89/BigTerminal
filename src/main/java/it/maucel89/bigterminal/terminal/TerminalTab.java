package it.maucel89.bigterminal.terminal;

import com.kodedu.terminalfx.TerminalBuilder;
import it.maucel89.bigterminal.lateral.filesystem.FSTree;
import it.maucel89.bigterminal.util.KeyCodeUtils;
import it.maucel89.bigterminal.util.SplitPaneUtils;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Mauro Celani
 */

public class TerminalTab extends Tab {

	public TerminalTab() throws IOException {

		this(new LocalTerminal());
	}

	public TerminalTab(Terminal terminal) {

		_terminal = terminal;

		_initTextArea();

		//_initTerminal();

		setText(tabCount++ + " - " + _terminal.getTitle());
	}

	private void _initTerminal() {

		_setOutputStream(_terminal.getOutputStream());

		Runnable run = new TerminalWatcher(
			_terminal.getInputStream(), _textArea);

		new Thread(run)
			.start();

		this.setOnCloseRequest(event -> close());
	}

	public void close() {
		_log.debug("Closing tab " + _terminal.getTitle());
		_terminal.close();
	}

	private void _initTextArea() {

		_textArea = new TextArea();

		_textArea.setFont(_font);

		TerminalBuilder terminalBuilder = new TerminalBuilder();

		Node terminal = terminalBuilder.newTerminal().getContent();

		//new com.kodedu.terminalfx.Terminal(
		//	new TerminalConfig(), Paths.get(System.getProperty("user.home")));

		setContent(
			SplitPaneUtils.getSplitPane(
				new FSTree(), terminal));
	}

	public void _setOutputStream(OutputStream outputStream) {

		PrintStream printStream = new PrintStream(outputStream, true);

		_textArea.addEventHandler(KeyEvent.KEY_RELEASED, Event::consume);

		_textArea.addEventHandler(KeyEvent.KEY_TYPED, Event::consume);

		_textArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

			byte[] code = KeyCodeUtils.getCodeByEvent(event);

			if (code != null) {
				try {
					printStream.write(code);
				}
				catch (IOException e) {
					_log.warn(e, e);
				}
			}
			else {
				printStream.print(event.getText());
			}

			event.consume();
		});
	}

	private Terminal _terminal;
	private TextArea _textArea;

	private static int tabCount = 1;
	private static final Font _font = Font.font("Monospaced", 14);

	private static final Logger _log = Logger.getLogger(TerminalTab.class);

}