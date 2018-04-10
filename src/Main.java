import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import processing.core.PApplet;

public class Main {

	public static int PLAYER_COUNT = 300;
	public static float MUTATION_VARIATION = .01f;
	public static float MIGRATION_CHANCE = .01f;
	public static int NUM_ROUNDS = 10;

	public static final int ROUND_RECORD_LENGTH = 28;

	public static boolean PAUSED = true;

	public static ArrayList<Player> playerList;

	public static String As;
	public static String Bs;

	public static int CooperationCount = 0;;
	public static int DenailCount = 0;

	public static int CCount = 0;
	public static int DCount = 0;

	public static boolean BoundLower = true;
	public static boolean BoundUpper = true;

	public static void main(String[] args) {
		playerList = new ArrayList<Player>();
		for (int i = 0; i < PLAYER_COUNT; i++) {
			Player newPlayer = Player.getNewPlayer();
			playerList.add(newPlayer);
		}

		PApplet.main("Graph", args);
		Graph.updateValues(playerList);

		// while (!JOptionPane.showInputDialog("exit").equals("exit")) {

		while (true) {
			while (PAUSED) {

			}
			try {
				doRound();
				Graph.updateValues(playerList);
			} catch (Exception e) {

			}

		}

	}

	public static void reset() {
		playerList = new ArrayList<Player>();
		for (int i = 0; i < PLAYER_COUNT; i++) {
			Player newPlayer = Player.getNewPlayer();
			playerList.add(newPlayer);
		}
	}

	public static void printResults() {
		Collections.sort(playerList);
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println(playerList.get(i).getScore());
		}
	}

	public static void doRound() {
		ArrayList<Player> childrenList = new ArrayList<Player>();

		for (int i = 0; i < playerList.size() - 1; i++) {
			for (int j = i + 1; j < playerList.size(); j++) {
				playXRounds(NUM_ROUNDS, playerList.get(i), playerList.get(j));
			}
		}

		CooperationCount = CCount;
		DenailCount = DCount;
		CCount = 0;
		DCount = 0;

		for (int i = playerList.size() - 1; i >= 0; i--) {
			if (Math.random() < (1.0 - (double) (i) / playerList.size())) {
				if (Math.random() < (1.0 - (double) (i) / playerList.size())) {
					playerList.remove(i);
				}
			}
		}

		for (int i = 0; i < playerList.size(); i++) {
			childrenList.add(playerList.get(i).getChild(MUTATION_VARIATION));
		}

		if (BoundUpper) {
			while (childrenList.size() + playerList.size() > PLAYER_COUNT && childrenList.size() > 0) {
				childrenList.remove((int) (Math.random() * childrenList.size()));
			}
		}

		if (BoundLower && playerList.size() > 0) {
			while (childrenList.size() + playerList.size() < PLAYER_COUNT) {
				childrenList
						.add(playerList.get((int) (Math.random() * playerList.size())).getChild(MUTATION_VARIATION));
			}
		}

		if (Math.random() < MIGRATION_CHANCE) {
			Player p = Player.getNewPlayer();
			for (int i = 0; i < Math.random() * (double) PLAYER_COUNT * .05; i++) {
				playerList.add(p.getChild(MUTATION_VARIATION));
			}
		}

		playerList.addAll(childrenList);
		childrenList.removeAll(childrenList);
	}

	public static void playXRounds(int x, Player a, Player b) {

		int round = 1;
		String as = "";
		String bs = "";
		boolean A = a.getFirstMove();
		boolean B = b.getFirstMove();

		int t = (A ? CCount++ : DCount++);
		t = (B ? CCount++ : DCount++);

		as = as + (A ? "C " : "D ");
		bs = bs + (B ? "C " : "D ");

		a.incrementScore(getPayoffA(A, B));
		b.incrementScore(getPayoffB(A, B));
		do {
			A = a.getMove(B);
			B = b.getMove(A);

			t = (A ? CCount++ : DCount++);
			t = (B ? CCount++ : DCount++);

			as = as + (A ? "C " : "D ");
			bs = bs + (B ? "C " : "D ");
			a.incrementScore(getPayoffA(A, B));
			b.incrementScore(getPayoffB(A, B));
			round++;
		} while (round <= x);

		As = as.length() >= ROUND_RECORD_LENGTH * 2 ? as.substring(0, ROUND_RECORD_LENGTH * 2) : as;
		Bs = bs.length() >= ROUND_RECORD_LENGTH * 2 ? as.substring(0, ROUND_RECORD_LENGTH * 2) : bs;

	}

	public static int getPayoffA(boolean A, boolean B) {
		if (A & B) {
			return 2;
		} else if (A & !B) {
			return 0;
		} else if (!A & B) {
			return 3;
		} else {
			return 1;
		}

	}

	public static int getPayoffB(boolean A, boolean B) {
		if (A & B) {
			return 2;
		} else if (A & !B) {
			return 3;
		} else if (!A & B) {
			return 0;
		} else {
			return 1;
		}
	}
}