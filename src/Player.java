import java.util.Random;

public class Player implements Comparable<Player> {

	private double s, c, d;

	private int score;

	private Player(double s, double c, double d) {
		super();
		this.s = s;
		this.c = c;
		this.d = d;
		score = 0;
	}

	public static Player getNewPlayer() {
		Player newPlayer = new Player(Math.random(), Math.random(), Math.random());
		return newPlayer;
	}

	public boolean getMove(boolean opponentmove) {
		if (opponentmove) {
			return Math.random() < c;
		} else {
			return Math.random() < d;
		}
	}

	public boolean getFirstMove() {
		return Math.random() < s;
	}

	public void incrementScore(int a) {
		score = score + a;
	}

	public int getScore() {
		return score;
	}

	public Player getChild(double k) {
		Random rand = new Random();
		double S = s + rand.nextGaussian() * k;
		double C = c + rand.nextGaussian() * k;
		double D = d + rand.nextGaussian() * k;
		S = S > 1 ? 1 : S;
		S = S < 0 ? 0 : S;
		C = C > 1 ? 1 : C;
		C = C < 0 ? 0 : C;
		D = D > 1 ? 1 : D;
		D = D < 0 ? 0 : D;
		return new Player(S, C, D);
	}

	public void resetScore() {
		score = 0;
	}

	@Override

	public int compareTo(Player o) {
		int scorea = getScore();
		int scoreb = o.getScore();
		if (scorea > scoreb) {
			return 1;
		} else if (scorea < scoreb) {
			return -1;
		} else {
			return 0;
		}

	}

	public double getFirstChance() {
		return s;
	}

	public double getChanceAfterCooperation() {
		return c;
	}

	public double getChanceAfterDenial() {
		return d;
	}

}