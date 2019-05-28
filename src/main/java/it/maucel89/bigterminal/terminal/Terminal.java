package it.maucel89.bigterminal.terminal;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Mauro Celani
 */
public interface Terminal {

	public InputStream getInputStream();

	public OutputStream getOutputStream();

	public void close();

	public String getTitle();

}
