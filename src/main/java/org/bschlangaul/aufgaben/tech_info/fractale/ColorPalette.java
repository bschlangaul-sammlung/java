package org.bschlangaul.aufgaben.tech_info.fractale;

import java.awt.Color;

/**
 * Represents an array of colors.
 */
public class ColorPalette {

	/**
	 * Creates a color array with a gradient
	 *
	 * @param n
	 * 			number of generated colors
	 * @param colors
	 *          the colors at the border of the gradient
	 * @return the color array
	 */
	public static Color[] create(int n, Color[] colors) {
		Color[] palette = new Color[n];
		int stepWidth = (n - 1) / (colors.length - 1);
		int start = 0;
		for (int i = 0; i < colors.length - 1; i++) {
			if (i == colors.length - 2) {
				stepWidth = palette.length - start;
			}
			Color startColor = colors[i];
			Color endColor = colors[i + 1];
			palette[start] = startColor;
			double r = startColor.getRed();
			double g = startColor.getGreen();
			double b = startColor.getBlue();
			double rSchritt = ((double) (endColor.getRed() - startColor
					.getRed()))
					/ stepWidth;
			double gSchritt = ((double) (endColor.getGreen() - startColor
					.getGreen()))
					/ stepWidth;
			double bSchritt = ((double) (endColor.getBlue() - startColor
					.getBlue()))
					/ stepWidth;
			for (int j = 1; j < stepWidth; j++) {
				r += rSchritt;
				g += gSchritt;
				b += bSchritt;
				palette[start + j] = new Color((int) r, (int) g, (int) b);
			}
			start += stepWidth;
		}
		palette[palette.length - 1] = colors[colors.length - 1];
		return palette;
	}

	/**
	 *
	 * Creates a color gradient with 200 colors from white over <code>col</code> to black
	 * @param col the color to use for the gradient
	 *
	 * @return a color gradient
	 */
	public static Color[] createSimpleGradient(final Color col) {
		return create(200,
				new Color[] { Color.WHITE, col, Color.BLACK });
	}

	/**
	 *
	 * Creates a standard color gradient with 500 colors.
	 *
	 * @return a standard color gradient
	 */
	public static Color[] createStandardGradient() {
		return create(500,
				new Color[] {
					Color.WHITE,
					new Color(128, 192, 255),
					new Color(0, 128, 255),
					new Color(32, 107, 245),
					new Color(64, 86, 235),
					new Color(96, 64, 224),
					new Color(103, 60, 222),
					new Color(110, 56, 220),
					new Color(117, 52, 218),
					new Color(124, 48, 216),
					new Color(131, 44, 214),
					new Color(138, 40, 212),
					new Color(145, 35, 210),
					new Color(152, 30, 208),
					new Color(159, 25, 206),
					new Color(166, 20, 204),
					new Color(173, 15, 202),
					new Color(180, 10, 200),
					new Color(187, 5, 198),
					new Color(196, 0, 196),
					new Color(128, 0, 128),
					new Color(64, 0, 64),
					new Color(32, 0, 32),
					Color.BLACK });
	}

}
