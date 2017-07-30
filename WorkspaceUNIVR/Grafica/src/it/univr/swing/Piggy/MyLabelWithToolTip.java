package it.univr.swing.Piggy;

import java.awt.Color;

import javax.swing.JLabel;

public class MyLabelWithToolTip extends JLabel {

	public MyLabelWithToolTip(String text, String toolTip) {
		super(text);

		setOpaque(true);
		setBackground(new Color(248, 213, 131));
		setToolTipText(toolTip);
		setHorizontalAlignment(JLabel.CENTER);
	}
}
