package it.maucel89.bigterminal.util;

import javafx.scene.input.KeyEvent;

/**
 * @author Mauro Celani
 */
public class KeyCodeUtils {

	public static byte[] getCodeByEvent(KeyEvent event) {

		switch (event.getCode()) {

			case BACK_SPACE:
				return new byte[] { (byte) 0x08 };

			case TAB:
				return new byte[] { (byte) 0x09 };

			case ESCAPE:
				return new byte[] { (byte) 0x1b };

			case SPACE:
				return new byte[] { (byte) 0x20 };

			case UP:
				return new byte[] {
					(byte) 0x1b,
					(byte) 0x4f,
					(byte) 0x41
				};

			case DOWN:
				return new byte[] {
					(byte) 0x1b,
					(byte) 0x4f,
					(byte) 0x42
				};

			case RIGHT:
				return new byte[] {
					(byte) 0x1b,
					(byte) 0x4f,
					(byte) 0x43
				};

			case LEFT:
				return new byte[] {
					(byte) 0x1b,
					(byte) 0x4f,
					(byte) 0x44
				};

		}

		return null;
	}

}
