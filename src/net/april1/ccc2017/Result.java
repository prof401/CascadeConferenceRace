package net.april1.ccc2017;

public class Result {
	private int homeScore;
	private int awayScore;

	public Result(String homeScore, String awayScore) {
		try {
			this.homeScore = Integer.parseInt(homeScore);
		} catch (NumberFormatException nfe) {
			this.homeScore = -1;
		}
		try {
			this.awayScore = Integer.parseInt(awayScore);
		} catch (NumberFormatException nfe) {
			this.awayScore = -1;
		}
	}

	public int getHomeScore() {
		return homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

}
