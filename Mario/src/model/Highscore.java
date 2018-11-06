package model;

public class Highscore {
	private String name;
	private int score;

	public Highscore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public final String getName() {
		return name;
	}

	public final int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return name + " - " + score + " points";
	}
}
