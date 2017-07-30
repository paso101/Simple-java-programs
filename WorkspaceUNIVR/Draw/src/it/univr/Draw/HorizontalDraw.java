package it.univr.Draw;

public final class HorizontalDraw extends Draw {

	private final Draw[] children;

	public HorizontalDraw(Draw... children) throws IllegalArgumentException {
		if (children.length == 0)
			throw new IllegalArgumentException("HorizontalDraw: one Draw is needed at least");

		this.children = children;
	}

	@Override
	public int getWidth() {
		// la larghezza è la somma delle larghezze
		int width = 0;
		for (Draw child: children)
			width += child.getWidth();

		return width;
	}

	@Override
	public int getHeight() {
		// l'altezza è la massima fra le altezze
		int height = 0;
		for (Draw child: children)
			height = Math.max(height, child.getHeight());

		return height;
	}

	@Override
	protected String getRow(int num) throws IllegalArgumentException {
		int height = getHeight();
		if (num < 0 || num >= height)
			throw new IllegalArgumentException("HorizontalDraw: row " + num + " does not exist");

		// concateniamo le righe di ciascun figlio
		String result = "";
		for (Draw child: children)
			if (num < child.getHeight())
				result += child.getRow(num);
			else
				// se un figlio è troppo piccolo, aggiungiamo spazi
				result += spaces(child.getWidth());

		return result;
	}

	private String spaces(int width) {
		String result = "";
		while (width-- > 0)
			result += " ";

		return result;
	}

}