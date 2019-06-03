package it.maucel89.bigterminal.terminal;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import it.maucel89.bigterminal.lateral.filesystem.FSTree;
import it.maucel89.bigterminal.util.SplitPaneUtils;
import javafx.scene.control.Tab;

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
			_terminalBuilder = new TerminalBuilder();
		}
		return _terminalBuilder;
	}

	private static int tabCount = 1;

	public static TerminalBuilder _terminalBuilder;

}
