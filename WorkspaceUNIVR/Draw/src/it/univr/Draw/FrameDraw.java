package it.univr.Draw;


public final class FrameDraw extends Draw {

	private final Draw child;

	public FrameDraw(Draw child) {
		this.child = child;
	}

	@Override
	public int getWidth() {
		return 2 + child.getWidth();
	}

	@Override
	public int getHeight() {
		return 2 + child.getHeight();
	}

	@Override
	protected String getRow(int num) throws IllegalArgumentException {
		int height = getHeight();
		if (num < 0 || num >= height)
			throw new IllegalArgumentException("FrameDraw: row " + num + " does not exist");
		else if (num == 0 || num == height - 1)
			return strudels();
		else
			return "@" + child.getRow(num - 1) + "@";
	}

	private String strudels() {
		int width = getWidth();
		String result = "";
		while (width-- > 0)
			result += "@";

		return result;
	}
}	