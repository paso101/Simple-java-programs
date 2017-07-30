package it.univr.Draw;
public final class L extends Letter {

	@Override
	protected String getRow(int num) throws IllegalArgumentException {
		switch (num) {
		case 0:
			return "       ";
		case 7:
			return " ***** ";
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			return " *     ";
		default:
			throw new IllegalArgumentException("row " + num + " does not exist in L");
		}
	}

}