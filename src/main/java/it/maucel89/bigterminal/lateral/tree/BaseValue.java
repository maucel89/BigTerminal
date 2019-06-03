package it.maucel89.bigterminal.lateral.tree;

/**
 * @author Mauro Celani
 */
public abstract class BaseValue {

	@Override
	public String toString() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	private String _text;

}
