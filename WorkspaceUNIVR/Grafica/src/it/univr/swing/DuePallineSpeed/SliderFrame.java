package it.univr.swing.DuePallineSpeed;

import javax.swing.JFrame;
import javax.swing.JSlider;

public class SliderFrame extends JFrame {

	private final JSlider slider;

	public SliderFrame(int min, int max, int init) {
		super("Control");

		slider = new JSlider(min, max, init);
		slider.setToolTipText("Controlla la velocità delle palline");
		getContentPane().add(slider);
		setBounds(550, 30, 100, 50);
		pack();
		setVisible(true);
	}

	public int getValue() {
		return slider.getValue();
	}
}
