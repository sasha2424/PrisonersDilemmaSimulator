import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import processing.core.PApplet;

public class Graph extends PApplet {

	private static final double WIDTH = 450, HEIGHT = 450;
	private static final double GRAPH_X_SPACE = 20, GRAPH_Y_SPACE = 20, TEXT_SHIFT = 15;

	private static double BUTTON_SIZE = 50;
	private static double BUTTON_SHIFT = 10;

	private static int MEMORY_MAX = -1;

	private static int HEAT_MAT_DIV = 50;
	private static int HIST_DIV = 20;

	private static Button b1 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE, HEIGHT / 2 + GRAPH_Y_SPACE, BUTTON_SIZE,
			BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			if (Main.BoundUpper) {
				p.fill(0, 255, 0);
			} else {
				p.fill(100);
			}
			this.standardDraw(p);
			p.fill(0);
			p.text("Bound\nUpper", (float) x + 5f, (float) y + 20f);
		}

		public void function() {
			Main.BoundUpper = !Main.BoundUpper;
		}
	};

	private static Button b2 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE + BUTTON_SIZE + BUTTON_SHIFT,
			HEIGHT / 2 + GRAPH_Y_SPACE, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			if (Main.BoundLower) {
				p.fill(0, 255, 0);
			} else {
				p.fill(100);
			}
			this.standardDraw(p);
			p.fill(0);
			p.text("Bound\nLower", (float) x + 5f, (float) y + 20f);
		}

		public void function() {
			Main.BoundLower = !Main.BoundLower;
		}
	};

	private static Button b3 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE + 2 * BUTTON_SIZE + 2 * BUTTON_SHIFT,
			HEIGHT / 2 + GRAPH_Y_SPACE, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(255, 0, 0);
			this.standardDraw(p);
			p.fill(0);
			p.text("Player\nCount\n" + Main.PLAYER_COUNT, (float) x + 5f, (float) y + 12f);
		}

		public void function() {
			try {
				Main.PLAYER_COUNT = Integer.parseInt(JOptionPane.showInputDialog("New Player Count:"));
			} catch (Exception e) {

			}
		}
	};

	private static Button b4 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE,
			HEIGHT / 2 + GRAPH_Y_SPACE + BUTTON_SIZE + BUTTON_SHIFT, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(0, 0, 255);
			this.standardDraw(p);
			p.fill(0);
			p.text("Mutation\n" + Main.MUTATION_VARIATION, (float) x + 2f, (float) y + 20f);
		}

		public void function() {
			try {
				Main.MUTATION_VARIATION = (float) Double.parseDouble(JOptionPane.showInputDialog("New Mutation Rate:"));
			} catch (Exception e) {

			}
		}
	};
	private static Button b5 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE + BUTTON_SIZE + BUTTON_SHIFT,
			HEIGHT / 2 + GRAPH_Y_SPACE + BUTTON_SIZE + BUTTON_SHIFT, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(255, 0, 0);
			this.standardDraw(p);
			p.fill(0);
			p.text("Migrant\nChance\n" + Main.MIGRATION_CHANCE, (float) x + 3f, (float) y + 12f);
		}

		public void function() {
			try {
				Main.MIGRATION_CHANCE = (float) Double.parseDouble(JOptionPane.showInputDialog("New Migrant Chance:"));
			} catch (Exception e) {

			}
		}
	};

	private static Button b6 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE + 2 * BUTTON_SIZE + 2 * BUTTON_SHIFT,
			HEIGHT / 2 + GRAPH_Y_SPACE + BUTTON_SIZE + BUTTON_SHIFT, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(255, 0, 0);
			this.standardDraw(p);
			p.fill(0);
			p.text("Rounds\n" + Main.NUM_ROUNDS, (float) x + 5f, (float) y + 20f);
		}

		public void function() {
			try {
				Main.NUM_ROUNDS = Integer.parseInt(JOptionPane.showInputDialog("New Player Count:"));
			} catch (Exception e) {

			}
		}
	};
	private static Button b7 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE + 2 * BUTTON_SIZE + 2 * BUTTON_SHIFT,
			HEIGHT / 2 + GRAPH_Y_SPACE + 2 * BUTTON_SIZE + 2 * BUTTON_SHIFT, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(200, 200, 200);
			this.standardDraw(p);
			p.fill(0);
			p.text("HeatMap\nDivs\n" + HEAT_MAT_DIV, (float) x + 2f, (float) y + 12f);
		}

		public void function() {
			try {
				HEAT_MAT_DIV = Integer.parseInt(JOptionPane.showInputDialog("New HeatMap Resolution:"));
			} catch (Exception e) {

			}
		}
	};
	private static Button b8 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE + BUTTON_SIZE + BUTTON_SHIFT,
			HEIGHT / 2 + GRAPH_Y_SPACE + 2 * BUTTON_SIZE + 2 * BUTTON_SHIFT, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(200, 200, 200);
			this.standardDraw(p);
			p.fill(0);
			p.text("Hist\nDivs\n" + HIST_DIV, (float) x + 5f, (float) y + 12f);
		}

		public void function() {
			try {
				HIST_DIV = Integer.parseInt(JOptionPane.showInputDialog("New Histogram Resolution:"));
			} catch (Exception e) {

			}
		}
	};
	private static Button b9 = new Graph.Button(WIDTH * 2.5 + GRAPH_X_SPACE,
			HEIGHT / 2 + GRAPH_Y_SPACE + 2 * BUTTON_SIZE + 2 * BUTTON_SHIFT, BUTTON_SIZE, BUTTON_SIZE) {
		public void draw(PApplet p) {
			p.stroke(0);
			p.fill(200, 200, 200);
			this.standardDraw(p);
			p.fill(0);
			p.text("Record\nAmount\n" + MEMORY_MAX, (float) x + 5f, (float) y + 12f);
		}

		public void function() {
			try {
				MEMORY_MAX = Integer.parseInt(JOptionPane.showInputDialog("New Memory Max:"));
			} catch (Exception e) {

			}
		}
	};

	private static DataSet s;
	private static DataSet c;
	private static DataSet d;

	static DecimalFormat df;
	static DecimalFormat dfShort;

	private static ArrayList<Player> playersToDraw;

	private static ArrayList<GameRecord> Records;

	public Graph() {

	}

	public void settings() {
		size(3 * (int) WIDTH, 2 * (int) HEIGHT);
		s = new DataSet();
		c = new DataSet();
		d = new DataSet();
		playersToDraw = new ArrayList<Player>();
		Records = new ArrayList<GameRecord>();
		df = new DecimalFormat("#.##");
		dfShort = new DecimalFormat(".##");
	}

	public void setup() {

	}

	public void keyPressed() {
		if (key == 't') {
			Records.add(new GameRecord(Main.As, Main.Bs));
		}
		if (key == 'p') {
			Main.PAUSED = !Main.PAUSED;
		}
		if (key == 'r') {
			Main.reset();
			s = new DataSet();
			c = new DataSet();
			d = new DataSet();
			playersToDraw.clear();
			Records.clear();
		}
	}

	public void mouseClicked() {
		b1.doClick(this);
		b2.doClick(this);
		b3.doClick(this);
		b4.doClick(this);
		b5.doClick(this);
		b6.doClick(this);
		b7.doClick(this);
		b8.doClick(this);
		b9.doClick(this);
	}

	public void draw() {
		background(255);
		double sizeX = WIDTH - GRAPH_X_SPACE * 2;
		double sizeY = (HEIGHT - 4 * GRAPH_Y_SPACE) / 3;
		double y2 = 2 * GRAPH_Y_SPACE + sizeY;
		double y3 = 3 * GRAPH_Y_SPACE + 2 * sizeY;
		// double y4 = 4 * GRAPH_Y_SPACE + 3 * sizeY;

		try {
			drawSGraph(this, GRAPH_X_SPACE, GRAPH_Y_SPACE, sizeX, sizeY);
			drawCGraph(this, GRAPH_X_SPACE, y2, sizeX, sizeY);
			drawDGraph(this, GRAPH_X_SPACE, y3, sizeX, sizeY);
			// drawKGraph(this, GRAPH_X_SPACE, y4, sizeX, sizeY);

			drawSHistogram(this, WIDTH + GRAPH_X_SPACE, GRAPH_Y_SPACE, sizeX, sizeY, HIST_DIV);
			drawCHistogram(this, WIDTH + GRAPH_X_SPACE, y2, sizeX, sizeY, HIST_DIV);
			drawDHistogram(this, WIDTH + GRAPH_X_SPACE, y3, sizeX, sizeY, HIST_DIV);
			// drawKHistogram(this, WIDTH + GRAPH_X_SPACE, y4, sizeX, sizeY,
			// 20);

			displayRecords(this, WIDTH * 2 + GRAPH_X_SPACE, GRAPH_Y_SPACE, 4, 20, 10);
			piChart(this, WIDTH * 2 + GRAPH_X_SPACE, HEIGHT / 2 + GRAPH_Y_SPACE,
					Math.min(HEIGHT / 2 - 2 * GRAPH_Y_SPACE, WIDTH / 2 - 2 * GRAPH_X_SPACE),
					Math.min(HEIGHT / 2 - 2 * GRAPH_Y_SPACE, WIDTH / 2 - 2 * GRAPH_X_SPACE));
			buttons(this);
			description(this);

			heatMap(this, GRAPH_X_SPACE, HEIGHT + GRAPH_Y_SPACE, WIDTH - GRAPH_X_SPACE * 2, HEIGHT - GRAPH_Y_SPACE * 2,
					HEAT_MAT_DIV);
		} catch (Exception e) {

		}

	}

	public static void description(PApplet p) {
		p.text("P to Pause   R to Restart   T to Show Round", 10, 10);
	}

	public static void buttons(PApplet p) {
		b1.draw(p);
		b2.draw(p);
		b3.draw(p);
		b4.draw(p);
		b5.draw(p);
		b6.draw(p);
		b7.draw(p);
		b8.draw(p);
		b9.draw(p);

	}

	public static void heatMap(PApplet p, double x, double y, double sx, double sy, int div) {
		int[][] heatmap = new int[div][div];
		for (int i = 0; i < playersToDraw.size(); i++) {
			double C = playersToDraw.get(i).getChanceAfterCooperation();
			double D = playersToDraw.get(i).getChanceAfterDenial();
			int a = (int) (C * div) >= div ? div - 1 : (int) (C * div);
			int b = (int) (D * div) >= div ? div - 1 : (int) (D * div);
			heatmap[a][b]++;
		}
		int max = 0;
		for (int[] a : heatmap) {
			for (int b : a) {
				max = b > max ? b : max;
			}
		}

		double xsize = sx / (double) div;
		double ysize = sy / (double) div;

		p.noStroke();
		for (double i = 0; i < div; i++) {
			for (double j = 0; j < div; j++) {
				float r = 255;
				float g = 255 * (max - heatmap[(int) i][(int) j]) / max;
				float b = 255 * (max - heatmap[(int) i][(int) j]) / max;
				p.fill(r, g, b);
				p.rect((float) (x + i * xsize), (float) (y + j * ysize), (float) xsize, (float) ysize);
			}
		}
		p.stroke(0);
		p.noFill();
		p.rect((float) x, (float) y, (float) sx, (float) sy);
		p.fill(0);
		float text_shift = 8;
		p.text("0", (float) (x - text_shift), (float) (y - text_shift));
		p.text("1", (float) (x - text_shift + sx), (float) (y - text_shift));
		p.text("1", (float) (x - text_shift), (float) (y - text_shift + sy));
		p.text("Cooperation Chance", (float) (x + sx / 3 - 20), (float) (y - text_shift));
		p.text("D\ne\nn\ni\na\nl\n \nC\nh\na\nn\nc\ne", (float) (x - text_shift - 3), (float) (y + sy / 3 - 20));
	}

	public static void piChart(PApplet p, double x, double y, double sx, double sy) {
		float j = Main.CooperationCount;
		float k = Main.DenailCount;
		Float o = (float) (Math.PI * 2 * (j / (j + k)));
		p.fill(0, 0, 255);
		p.arc((float) (x + sx / 2), (float) (y + sy / 2), (float) sx, (float) sy, 0, (float) (Math.PI * 2));
		p.fill(255, 0, 0);
		p.arc((float) (x + sx / 2), (float) (y + sy / 2), (float) sx, (float) sy, 0, o);
		p.fill(255, 0, 0);
		p.text("Cooperation", (float) (x - 20), (float) (y + sy));
		p.fill(0, 0, 255);
		p.text("Denail", (float) (x + sx - 25), (float) (y + sy));
	}

	public static void displayRecords(PApplet p, double x, double y, int limit, double borderShift, double shift) {
		for (int i = 0; i < Records.size(); i++) {
			if (i > limit) {
				return;
			}
			int j = Records.size() - i - 1;
			p.text(i + ": ", (float) x, (float) (y + 4 * shift * i));
			Records.get(j).draw(p, x + borderShift, y + 4 * shift * i, shift);
		}
	}

	public static void drawSHistogram(PApplet p, double x, double y, double sx, double sy, int bins) {
		int[] hist = new int[bins];
		for (int i = 0; i < playersToDraw.size(); i++) {
			Player player = playersToDraw.get(i);
			if (player == null) {
				return;
			}
			int k = (int) (((double) bins) * player.getFirstChance());
			k = k > bins - 1 ? bins - 1 : k;
			hist[k] = hist[k] + 1;
		}
		int max = 0;
		for (int i = 0; i < bins; i++) {
			max = hist[i] > max ? hist[i] : max;
		}

		double scaley = sy / ((double) max);
		double scalex = sx / ((double) bins);

		p.stroke(0);
		for (int i = 0; i < hist.length; i++) {
			float P = (float) (i) / ((float) bins);
			float Q = 1 - P;
			p.fill(255 * P * P, 4 * 255 * P * Q, 255 * Q * Q);
			p.rect((float) (x + i * scalex), (float) (y + sy), (float) (scalex), (float) (-scaley * hist[i]));
		}
		scalex = sx / ((double) (bins > 20 ? 20 : bins));
		p.fill(0);
		for (int i = 0; i < (bins > 20 ? 20 : bins); i++) {
			p.text("" + dfShort.format((double) i / (double) (bins > 20 ? 20 : bins)), (float) (x + i * scalex - 3),
					(float) (y + sy + TEXT_SHIFT));
		}
	}

	public static void drawCHistogram(PApplet p, double x, double y, double sx, double sy, int bins) {
		int[] hist = new int[bins];
		for (int i = 0; i < playersToDraw.size(); i++) {
			Player player = playersToDraw.get(i);
			if (player == null) {
				return;
			}
			int k = (int) (((double) bins) * player.getChanceAfterCooperation());
			k = k > bins - 1 ? bins - 1 : k;
			hist[k] = hist[k] + 1;
		}
		int max = 0;
		for (int i = 0; i < bins; i++) {
			max = hist[i] > max ? hist[i] : max;
		}

		double scaley = sy / ((double) max);
		double scalex = sx / ((double) bins);

		p.stroke(0);
		for (int i = 0; i < hist.length; i++) {
			float P = (float) (i) / ((float) bins);
			float Q = 1 - P;
			p.fill(255 * P * P, 4 * 255 * P * Q, 255 * Q * Q);
			p.rect((float) (x + i * scalex), (float) (y + sy), (float) (scalex), (float) (-scaley * hist[i]));
		}
		scalex = sx / ((double) (bins > 20 ? 20 : bins));
		p.fill(0);
		for (int i = 0; i < (bins > 20 ? 20 : bins); i++) {
			p.text("" + dfShort.format((double) i / (double) (bins > 20 ? 20 : bins)), (float) (x + i * scalex - 3),
					(float) (y + sy + TEXT_SHIFT));
		}
	}

	public static void drawDHistogram(PApplet p, double x, double y, double sx, double sy, int bins) {
		// bins++;
		int[] hist = new int[bins];
		for (int i = 0; i < playersToDraw.size(); i++) {
			Player player = playersToDraw.get(i);
			if (player == null) {
				return;
			}
			int k = (int) (((double) bins) * player.getChanceAfterDenial());
			k = k > bins - 1 ? bins - 1 : k;
			hist[k] = hist[k] + 1;
		}
		int max = 0;
		for (int i = 0; i < bins; i++) {
			max = hist[i] > max ? hist[i] : max;
		}

		double scaley = sy / ((double) max);
		double scalex = sx / ((double) bins);

		p.stroke(0);
		for (int i = 0; i < hist.length; i++) {
			float P = (float) (i) / ((float) bins);
			float Q = 1 - P;
			p.fill(255 * P * P, 4 * 255 * P * Q, 255 * Q * Q);
			p.rect((float) (x + i * scalex), (float) (y + sy), (float) (scalex), (float) (-scaley * hist[i]));
		}
		scalex = sx / ((double) (bins > 20 ? 20 : bins));
		p.fill(0);
		for (int i = 0; i < (bins > 20 ? 20 : bins); i++) {
			p.text("" + dfShort.format((double) i / (double) (bins > 20 ? 20 : bins)), (float) (x + i * scalex - 3),
					(float) (y + sy + TEXT_SHIFT));
		}
	}

	public static void drawSGraph(PApplet p, double x, double y, double sx, double sy) {
		p.noFill();
		p.stroke(0);
		p.rect((float) x, (float) y, (float) sx, (float) sy);
		s.drawMean(p, x, y, sx, sy);
		s.drawMin(p, x, y, sx, sy);
		s.drawMax(p, x, y, sx, sy);
		p.fill(0);

		double min = s.getMin().get(s.getMin().size() - 1);
		double max = s.getMax().get(s.getMax().size() - 1);
		double mean = s.getMean().get(s.getMean().size() - 1);
		double median = s.getMean().get(s.getMean().size() / 2);

		String display = "Cooperation on Start   ";
		display = display + "Min: " + df.format(min) + "   ";
		display = display + "Mean: " + df.format(mean) + "   ";
		display = display + "Median: " + df.format(median) + "   ";
		display = display + "Max: " + df.format(max);
		p.text(display, (float) x, (float) (y + sy + TEXT_SHIFT));

	}

	public static void drawCGraph(PApplet p, double x, double y, double sx, double sy) {
		p.noFill();
		p.stroke(0);
		p.rect((float) x, (float) y, (float) sx, (float) sy);
		c.drawMean(p, x, y, sx, sy);
		c.drawMin(p, x, y, sx, sy);
		c.drawMax(p, x, y, sx, sy);
		p.fill(0);

		double min = c.getMin().get(c.getMin().size() - 1);
		double max = c.getMax().get(c.getMax().size() - 1);
		double mean = c.getMean().get(c.getMean().size() - 1);
		double median = c.getMean().get(c.getMean().size() / 2);

		String display = "Cooperation after Cooperation   ";
		display = display + "Min: " + df.format(min) + "   ";
		display = display + "Mean: " + df.format(mean) + "   ";
		display = display + "Median: " + df.format(median) + "   ";
		display = display + "Max: " + df.format(max);
		p.text(display, (float) x, (float) (y + sy + TEXT_SHIFT));
	}

	public static void drawDGraph(PApplet p, double x, double y, double sx, double sy) {
		p.noFill();
		p.stroke(0);
		p.rect((float) x, (float) y, (float) sx, (float) sy);
		d.drawMean(p, x, y, sx, sy);
		d.drawMin(p, x, y, sx, sy);
		d.drawMax(p, x, y, sx, sy);
		p.fill(0);

		double min = d.getMin().get(d.getMin().size() - 1);
		double max = d.getMax().get(d.getMax().size() - 1);
		double mean = d.getMean().get(d.getMean().size() - 1);
		double median = d.getMean().get(d.getMean().size() / 2);

		String display = "Cooperation after Denial   ";
		display = display + "Min: " + df.format(min) + "   ";
		display = display + "Mean: " + df.format(mean) + "   ";
		display = display + "Median: " + df.format(median) + "   ";
		display = display + "Max: " + df.format(max);
		p.text(display, (float) x, (float) (y + sy + TEXT_SHIFT));
	}

	public static void updateValues(ArrayList<Player> players) {
		try {
			playersToDraw.clear();
			for (Player p : players) {
				playersToDraw.add(p);
			}
		} catch (Exception e) {

		}

		double meanS = 0;
		double meanC = 0;
		double meanD = 0;
		double meanK = 0;

		double maxS = players.get(0).getFirstChance();
		double maxC = players.get(0).getChanceAfterCooperation();
		double maxD = players.get(0).getChanceAfterDenial();

		double minS = players.get(0).getFirstChance();
		double minC = players.get(0).getChanceAfterCooperation();
		double minD = players.get(0).getChanceAfterDenial();

		for (int i = 0; i < players.size(); i++) {

			Player test = players.get(i);
			meanS = meanS + test.getFirstChance();
			meanC = meanC + test.getChanceAfterCooperation();
			meanD = meanD + test.getChanceAfterDenial();
			if (test.getFirstChance() >= maxS) {
				maxS = test.getFirstChance();
			} else if (test.getFirstChance() <= minS) {
				minS = test.getFirstChance();
			}

			if (test.getChanceAfterCooperation() >= maxC) {
				maxC = test.getChanceAfterCooperation();
			} else if (test.getChanceAfterCooperation() <= minC) {
				minC = test.getChanceAfterCooperation();
			}

			if (test.getChanceAfterDenial() >= maxD) {
				maxD = test.getChanceAfterDenial();
			} else if (test.getChanceAfterDenial() <= minD) {
				minD = test.getChanceAfterDenial();
			}

		}

		meanS = meanS / players.size();
		meanC = meanC / players.size();
		meanD = meanD / players.size();
		meanK = meanK / players.size();

		s.update(minS, maxS, meanS);
		c.update(minC, maxC, meanC);
		d.update(minD, maxD, meanD);
	}

	public static class DataSet {

		private ArrayList<Double> mean = new ArrayList<Double>();
		private ArrayList<Double> max = new ArrayList<Double>();
		private ArrayList<Double> min = new ArrayList<Double>();

		private DataSet() {

		}

		public void drawMean(PApplet p, double x, double y, double sx, double sy) {
			if (mean.size() < 2) {
				return;
			}
			double step = 1;
			if (mean.size() > sy) {
				step = mean.size() / sy;
			}
			for (double i = 0; i < mean.size() - step; i += step) {
				p.stroke(0, 255, 0);
				p.line((float) (x + i * (sx / (mean.size() - 1))), (float) (y + sy - sy * mean.get((int) i)),
						(float) (x + (i + step) * (sx / (mean.size() - 1))),
						(float) (y + sy - sy * mean.get((int) (i + step))));
			}
		}

		public void drawMin(PApplet p, double x, double y, double sx, double sy) {
			if (min.size() < 2) {
				return;
			}
			double step = 1;
			if (min.size() > sy) {
				step = min.size() / sy;
			}
			for (double i = 0; i < min.size() - step; i += step) {
				p.stroke(0, 0, 255);
				p.line((float) (x + i * (sx / (min.size() - 1))), (float) (y + sy - sy * min.get((int) i)),
						(float) (x + (i + step) * (sx / (min.size() - 1))),
						(float) (y + sy - sy * min.get((int) (i + step))));
			}
		}

		public void drawMax(PApplet p, double x, double y, double sx, double sy) {
			if (max.size() < 2) {
				return;
			}
			double step = 1;
			if (max.size() > sy) {
				step = max.size() / sy;
			}
			for (int i = 0; i < max.size() - step; i += step) {
				p.stroke(255, 0, 0);
				p.line((float) (x + i * (sx / (max.size() - 1))), (float) (y + sy - sy * max.get((int) i)),
						(float) (x + (i + step) * (sx / (max.size() - 1))),
						(float) (y + sy - sy * max.get((int) (i + step))));
			}
		}

		private ArrayList<Double> getMin() {
			return min;
		}

		private ArrayList<Double> getMax() {
			return max;
		}

		private ArrayList<Double> getMean() {
			return mean;
		}

		private void update(double min, double max, double mean) {
			this.min.add(min);
			this.max.add(max);
			this.mean.add(mean);
			if (MEMORY_MAX > 0) {
				while (this.min.size() > MEMORY_MAX) {
					this.min.remove(0);
				}
				while (this.max.size() > MEMORY_MAX) {
					this.max.remove(0);
				}
				while (this.mean.size() > MEMORY_MAX) {
					this.mean.remove(0);
				}
			}
		}

	}

	public class GameRecord {
		String A;
		String B;

		public GameRecord(String a, String b) {
			A = a;
			B = b;
		}

		public void draw(PApplet p, double x, double y, double shift) {
			p.text(A, (float) x, (float) y);
			p.text(B, (float) x, (float) (y + shift));
		}
	}

	public static abstract class Button {
		double x, y, sx, sy;

		boolean pressed = false;

		public Button(double x, double y, double sx, double sy) {
			this.x = x;
			this.y = y;
			this.sx = sx;
			this.sy = sy;
		}

		public void standardDraw(PApplet p) {
			p.rect((float) x, (float) y, (float) sx, (float) sy);
		}

		public void draw(PApplet p) {

		}

		public void doClick(PApplet p) {
			if (x < p.mouseX && p.mouseX < x + sx) {
				if (y < p.mouseY && p.mouseY < y + sy) {
					function();
				}
			}
		}

		public abstract void function();
	}

}