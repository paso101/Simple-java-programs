package it.univr.Draw;

public final class Star extends Draw {

	@Override
	public int getWidth() {
		return 5;
	}

	@Override
	public int getHeight() {
		return 5;
	}

	@Override
	protected String getRow(int num) throws IllegalArgumentException {
		switch (num) {
		case 1:
		case 3:
			return " X X ";
		case 0:
		case 4:
			return "X   X";
		case 2:
			return "  X  ";
		default:
			throw new IllegalArgumentException("row " + num + " does not exist in Star");
		}
	}

}