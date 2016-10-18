package net.april1.ccc2016;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Season {
	private Map<Game, Result> season;
	private static final int WRITE_COUNT = 19683 * 3 * 3 * 3 * 3 * 3 * 3 * 3 * 3;

	public void loadSchedule() {
		String[][] stringSchedule = { { "NW", "WPAC" }, { "ESC", "EOU" }, { "CORB", "UGF" }, { "NCU", "CC" }, // 09-16
				{ "NW", "EOU" }, { "ESC", "WPAC" }, { "CORB", "CC" }, { "NCU", "UGF" }, { "RMC", "CI" }, // 09-17
				{ "CC", "EOU" }, // 09-22
				{ "SOU", "CORB" }, { "CC", "WPAC" }, { "UGF", "EOU" }, { "OIT", "NCU" }, // 09-23
				{ "SOU", "NCU" }, { "UGF", "WPAC" }, { "OIT", "CORB" }, { "NW", "ESC" }, // 09-24
				{ "RMC", "UGF" }, { "EOU", "OIT" }, { "WPAC", "SOU" }, { "CI", "CC" }, // 09-30
				{ "CORB", "NCU" }, { "EOU", "SOU" }, { "WPAC", "OIT" }, // 10-1
				{ "CI", "UGF" }, { "RMC", "CC" }, // 10-2
				{ "OIT", "NW" }, { "SOU", "ESC" }, { "CI", "CORB" }, { "RMC", "NCU" }, // 10-7
				{ "OIT", "ESC" }, { "UGF", "CC" }, { "SOU", "NW" }, // 10-8
				{ "RMC", "CORB" }, { "CI", "NCU" }, // 10-9
				{ "SOU", "OIT" }, // 10-13
				{ "WPAC", "CI" }, { "EOU", "RMC" }, { "NW", "UGF" }, { "ESC", "CC" }, // 10-14
				{ "EOU", "CI" }, { "NW", "CC" }, { "ESC", "UGF" }, { "WPAC", "RMC" }, // 10-15
				{ "CC", "OIT" }, // 10-20
				{ "CORB", "EOU" }, { "NCU", "WPAC" }, { "UGF", "OIT" }, { "CC", "SOU" }, { "CI", "ESC" },
				{ "RMC", "NW" }, // 10-21
				{ "CORB", "WPAC" }, { "UGF", "SOU" }, { "NCU", "EOU" }, // 10-22
				{ "CI", "NW" }, { "RMC", "ESC" }, // 10-23
				{ "SOU", "CI" }, { "OIT", "RMC" }, { "NCU", "ESC" }, { "CORB", "NW" }, // 10-28
				{ "EOU", "WPAC" }, { "SOU", "RMC" }, { "OIT", "CI" }, { "CORB", "ESC" }, { "NCU", "NW" } // 10-29
		};

		season = new java.util.HashMap<Game, Result>();
		for (String[] stringGame : stringSchedule) {
			season.put(new Game(stringGame[0], stringGame[1]), null);
		}
	}

	public void loadResults() {
		String[][] stringResults = { { "NW", "WPAC", "3", "0" }, { "ESC", "EOU", "0", "2" },
				{ "CORB", "UGF", "2", "1" }, { "NCU", "CC", "0", "1" }, // Sept
																		// 16
				{ "NW", "EOU", "0", "1" }, { "ESC", "WPAC", "0", "0" }, { "CORB", "CC", "1", "2" },
				{ "NCU", "UGF", "3", "0" }, { "RMC", "CI", "2", "1" }, // Sept
																		// 17
				{ "CC", "EOU", "0", "0" }, // Sept 22
				{ "SOU", "CORB", "5", "0" }, { "CC", "WPAC", "2", "0" }, { "UGF", "EOU", "0", "1" },
				{ "OIT", "NCU", "1", "1" }, // Sept 23
				{ "SOU", "NCU", "2", "0" }, { "UGF", "WPAC", "1", "3" }, { "OIT", "CORB", "1", "2" },
				{ "NW", "ESC", "2", "0" }, // Sept 24
				{ "RMC", "UGF", "0", "1" }, { "EOU", "OIT", "4", "0" }, { "WPAC", "SOU", "0", "2" },
				{ "CI", "CC", "0", "1" }, // Sept 30
				{ "CORB", "NCU", "4", "0" }, { "EOU", "SOU", "0", "1" }, { "WPAC", "OIT", "3", "1" }, // Oct
																										// 1
				{ "CI", "UGF", "1", "2" }, { "RMC", "CC", "0", "4" }, { "OIT", "NW", "1", "3" },
				{ "CI", "CORB", "1", "2" }, { "RMC", "NCU", "3", "2" }, // 10-7
				{ "OIT", "ESC", "1", "0" }, { "UGF", "CC", "1", "2" }, { "SOU", "NW", "1", "0" }, // 10-8
				{ "RMC", "CORB", "0", "3" }, { "CI", "NCU", "3", "0" }, // 10-9
				{ "SOU", "OIT", "1", "0" }, // 10-13
				{ "WPAC", "CI", "2", "3" }, { "EOU", "RMC", "0", "1" }, { "NW", "UGF", "1", "0" },
				{ "ESC", "CC", "1", "1" }, // 10-14
				{ "EOU", "CI", "2", "2" }, { "NW", "CC", "0", "0" }, { "ESC", "UGF", "1", "3" },
				{ "WPAC", "RMC", "1", "3" } // 10-15
		};

		for (String[] stringResult : stringResults) {
			Game game = new Game(stringResult[0], stringResult[1]);
			Result result = new Result(stringResult[2], stringResult[3]);
			recordResult(game, result);
		}
	}

	private void recordResult(Game game, Result result) {
		season.put(game, result);
	}

	public void loseRest(String team) {
		for (Entry<Game, Result> gameEntry : season.entrySet()) {
			String homeTeam = gameEntry.getKey().getHome().toString();
			String awayTeam = gameEntry.getKey().getAway().toString();
			if (awayTeam.equals(team) || homeTeam.equals(team)) {
				// team is either Away or Home team
				if (gameEntry.getValue() == null) {
					// game not played yet
					Result newResult = null;
					if ((awayTeam.equals(team)))
						newResult = new Result("3", "0");
					else
						newResult = new Result("0", "3");
					season.put(gameEntry.getKey(), newResult);
				}
			}
		}
	}

	public void winRest(Team team) {
		for (Entry<Game, Result> gameEntry : season.entrySet()) {
			Team homeTeam = gameEntry.getKey().getHome();
			Team awayTeam = gameEntry.getKey().getAway();
			if (awayTeam.equals(team) || homeTeam.equals(team)) {
				// team is either Away or Home team
				if (gameEntry.getValue() == null) {
					// game not played yet
					Result newResult = null;
					if ((awayTeam.equals(team)))
						newResult = new Result("0", "1");
					else
						newResult = new Result("1", "0");
					season.put(gameEntry.getKey(), newResult);
				}
			}
		}
	}

	static public void winPermenutations() {
		int count = 0;
		int[] placeCount = new int[Team.values().length];
		int lowest = 0;
		long start = System.currentTimeMillis();
		String lowestOrder = "";
		TeamPermutations pg = new TeamPermutations();
		Season current = new Season();
		StringBuilder order = new StringBuilder();
		while (pg.hasMore()) {
			current.loadSchedule();
			current.loadResults();
			order.setLength(0);
			int[] temp = pg.getNext();
			for (int i = 0; i < temp.length; i++) {
				current.winRest(Team.values()[temp[i]]);
				order.append(' ');
				order.append(Team.values()[temp[i]].toString());
			}
			int carrollPlace = current.place(Team.CC);
			placeCount[carrollPlace]++;

			if (lowest < carrollPlace) {
				lowest = carrollPlace;
				lowestOrder = order.toString();
			}
			if (++count % 39916800 == 0) {
				for (int cnt : placeCount) {
					System.out.printf("%5.1f ", (100.0 * cnt) / count);
				}
				System.out.printf("%9d %9d %9d ", placeCount[0],
						placeCount[8] + placeCount[9] + placeCount[10] + placeCount[11], count);
				System.out.println((System.currentTimeMillis() - start) / 1000);
			}

		}
		for (int cnt : placeCount) {
			System.out.print(cnt);
			System.out.print(' ');
		}
		System.out.println(count);
		for (int cnt : placeCount) {
			System.out.printf("%5.1f ", (100.0 * cnt) / count);
		}
		System.out.println((System.currentTimeMillis() - start) / 1000);
		System.out.println("Lowest place " + (lowest + 1));
		System.out.println(lowestOrder);
	}

	static public void gameIteration() {
		Season current = new Season();
		current.loadSchedule();
		current.loadResults();
		Game[] unplayedGames = unplayedGameArray(current);

		int count = 0;
		int[] placeCount = new int[Team.values().length];
		long start = System.currentTimeMillis();
		GameIterations pg = new GameIterations(unplayedGames.length, 3);
		Result[] results = new Result[3];
		results[0] = new Result("1", "0");
		results[1] = new Result("0", "0");
		results[2] = new Result("0", "1");
		
		int[] opponent = new int[Team.values().length];
		while (pg.hasMore()) {
			current.loadSchedule();
			current.loadResults();
			int[] temp = pg.getNext();
			for (int i = 0; i < temp.length; i++) {
				current.recordResult(unplayedGames[i], results[temp[i]]);
			}
			int carrollPlace = current.place(Team.CC);
			placeCount[carrollPlace]++;
			List<Team> standings = (new Standings(current.season)).sortedTable();
			opponent[standings.get(7-carrollPlace).ordinal()]++;

			if (++count % WRITE_COUNT == 0) {
				for (int cnt : opponent) {
					System.out.printf("%5.1f ", (100.0 * cnt) / count);
				}
				System.out.println();
				for (int cnt : placeCount) {
					System.out.printf("%5.1f ", (100.0 * cnt) / count);
				}
				System.out.printf("%9d %9d %9d ", placeCount[0],
						placeCount[8] + placeCount[9] + placeCount[10] + placeCount[11], count);
				System.out.println((System.currentTimeMillis() - start) / 1000);
			}

		}
		for (int cnt : placeCount) {
			System.out.print(cnt);
			System.out.print(' ');
		}
		System.out.println(count);
		for (int cnt : placeCount) {
			System.out.printf("%5.1f ", (100.0 * cnt) / count);
		}
		System.out.println((System.currentTimeMillis() - start) / 1000);
	}

	private static Game[] unplayedGameArray(Season current) {
		List<Game> unplayedGameList = new java.util.ArrayList<Game>();
		for (Entry<Game, Result> gameEntry : current.season.entrySet()) {
			if (gameEntry.getValue() == null) {
				unplayedGameList.add(gameEntry.getKey());
			}
		}
		Game[] unplayedGames = unplayedGameList.toArray(new Game[unplayedGameList.size()]);
		return unplayedGames;
	}

	public int place(Team team) {
		Standings table = new Standings(season);
		int place = 0;
		for (Team t : table.sortedTable()) {
			if (t.equals(team))
				break;
			else
				place++;
		}
		return place;
	}

	public void printStandings() {
		Standings table = new Standings(season);
		for (Team t : table.sortedTable()) {
			System.out.println(t.name());
		}
		table.print();
	}

	public static void test() {
		long start = System.currentTimeMillis();
		Season current = new Season();
		current.loadSchedule();
		current.loadResults();
		current.winRest(Team.NCU);
		current.winRest(Team.OIT);
		current.winRest(Team.ESC);
		current.winRest(Team.RMC);
		current.winRest(Team.CORB);
		current.winRest(Team.EOU);
		current.winRest(Team.NW);
		current.winRest(Team.SOU);
		current.winRest(Team.CI);
		current.winRest(Team.UGF);
		current.winRest(Team.WPAC);
		current.printStandings();
		System.out.println(current.place(Team.CC));
		System.out.println(System.currentTimeMillis() - start);
	}

	static final public void main(String[] args) {
		Season.test();
//		Season.winPermenutations();
		Season.gameIteration();
	}
}
