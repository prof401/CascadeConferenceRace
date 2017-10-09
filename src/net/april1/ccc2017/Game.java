package net.april1.ccc2017;

public class Game {
	private Team home;
	private Team away;

	/**
	 * @param homeTeam
	 * @param awayTeam
	 */
	public Game(String homeTeam, String awayTeam) {
		home = Team.valueOf(homeTeam);
		away = Team.valueOf(awayTeam);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((away == null) ? 0 : away.hashCode());
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (away != other.away)
			return false;
		if (home != other.home)
			return false;
		return true;
	}

	public Team getHome() {
		return home;
	}

	public Team getAway() {
		return away;
	}
}
