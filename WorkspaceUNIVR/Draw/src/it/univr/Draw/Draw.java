package it.univr.Draw;

public abstract class Draw {

	public abstract int getWidth();

	public abstract int getHeight();

	protected abstract String getRow(int num) throws IllegalArgumentException;

	@Override
	public final String toString() {
		String result = "";

		
		int height = getHeight();
		for (int row = 0; row < height; row++)
			result += getRow(row) + "\n"; 
			
		return result;
	}
}