package it.maucel89.bigterminal;

import com.pty4j.PtyProcess;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mauro Celani
 */
public class LocalTerminal implements Terminal {

	public LocalTerminal() throws IOException {

		// The command to run in a PTY...
		String[] cmd = { "/bin/sh", "-l" };

		// The initial environment to pass to the PTY child process...
		Map<String, String> env = new HashMap<>();
		env.put("TERM", "xterm");

		_pty = PtyProcess.exec(
			cmd, env, System.getProperty("user.home"));

		OutputStream os = _pty.getOutputStream();
		InputStream is = _pty.getInputStream();

		// ... work with the streams ...

	}

	/**
	 * Free up resources.
	 */
	public void close() {

		if (_pty != null) {
			_pty.destroy();
		}
	}

	private PtyProcess _pty;

}
