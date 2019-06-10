package it.maucel89.bigterminal.terminal;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import com.kodedu.terminalfx.config.TerminalConfig;
import it.maucel89.bigterminal.lateral.filesystem.FSTree;
import it.maucel89.bigterminal.util.SplitPaneUtils;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;

/**
 * @author Mauro Celani
 */
public class TerminalTabBuilder {

	public static Tab createLocalTerminalTab() {

		TerminalTab terminalTab = _getTerminalBuilder().newTerminal();

		terminalTab.setText(tabCount++ + " - " + "PIPPO");

		terminalTab.setContent(
			SplitPaneUtils.getSplitPane(
				new FSTree(), terminalTab.getContent()));

		return terminalTab;
	}

	private static TerminalBuilder _getTerminalBuilder() {
		if (_terminalBuilder == null) {

			TerminalConfig darkConfig = new TerminalConfig();
			darkConfig.setBackgroundColor(Color.rgb(16, 16, 16));
			darkConfig.setForegroundColor(Color.rgb(240, 240, 240));
			darkConfig.setCursorColor(Color.rgb(255, 0, 0, 0.5));

			_terminalBuilder = new TerminalBuilder(darkConfig);
		}
		return _terminalBuilder;
	}

	private static int tabCount = 1;

	public static TerminalBuilder _terminalBuilder;

}
