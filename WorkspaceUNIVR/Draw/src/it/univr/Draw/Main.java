package it.univr.Draw;
public class Main {
	public static void main(String[] args) {
		Draw h = new H(), e = new E(), l = new L(), o = new O(), star = new Star();
		Draw hello = new HorizontalDraw(star, h, e, l, l, o, star);
		Draw frame = new FrameDraw(hello);
		System.out.println(new HorizontalDraw(hello, frame));
	}
}