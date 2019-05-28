package it.maucel89.bigterminal.terminal;

import com.pty4j.PtyProcess;
import org.apache.log4j.Logger;

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
		Map<String, String> env = new HashMap<>(System.getenv());
		env.put("TERM", "xterm");

		_title = System.getProperty("user.home");

		_pty = PtyProcess.exec(
			cmd, env, _title);

		_out = _pty.getOutputStream();
		_in = _pty.getInputStream();
	}

	@Override
	public InputStream getInputStream() {
		return _in;
	}

	@Override
	public OutputStream getOutputStream() {
		return _out;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public void close() {

		if (_pty != null) {
			_pty.destroy();
		}

		if (_in != null) {
			try {
				_in.close();
			}
			catch (IOException e) {
				_log.error(e, e);
			}
		}

		if (_out != null) {
			try {
				_out.close();
			}
			catch (IOException e) {
				_log.error(e, e);
			}
		}
	}

	private PtyProcess _pty;
	private InputStream _in;
	private OutputStream _out;
	private String _title;

	private static final Logger _log = Logger.getLogger(LocalTerminal.class);

}
