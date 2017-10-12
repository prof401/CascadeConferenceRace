package net.april1.ccc2017;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Season {
	private Map<Game, Result> season;
	private static final int WRITE_COUNT = 19683 * 3 * 3 * 3 * 3 * 3 * 3 * 3 * 3;

	public void loadSchedule() {
		String[][] stringSchedule = {
				{ "SOU", "CI" }, { "OIT", "RMC" }, { "NCU", "ESC" }, { "CORB", "NW" },
				{ "SOU", "RMC" }, { "OIT", "CI" }, { "CORB", "ESC" }, { "NCU", "NW" }, 
				{ "EOU", "WPAC" },
				{ "NW", "WPAC" }, { "ESC", "EOU" }, { "CORB", "UP" }, { "NCU", "CC" }, 
				{ "NW", "EOU" }, { "ESC", "WPAC" }, { "CORB", "CC" }, { "NCU", "UP" },
				{ "RMC", "CI" }, 
				{ "SOU", "CORB" },  { "OIT", "NCU" },{ "CC", "WPAC" }, { "UP", "EOU" }, 
				{ "SOU", "NCU" }, { "OIT", "CORB" },  { "CC", "EOU" }, { "UP", "WPAC" },
				{ "NW", "ESC" }, 
				{ "RMC", "UP" },{ "CI", "CC" },  { "EOU", "OIT" }, { "WPAC", "SOU" }, 
				{ "RMC", "CC" }, { "CI", "UP" }, { "EOU", "SOU" }, { "WPAC", "OIT" }, 
				{ "CORB", "NCU" }, 
				{ "OIT", "NW" }, { "SOU", "ESC" }, { "CI", "CORB" }, { "RMC", "NCU" }, 
				{ "OIT", "ESC" },  { "SOU", "NW" },{ "RMC", "CORB" }, { "CI", "NCU" },
				{ "UP", "CC" },
				{ "WPAC", "CI" }, { "EOU", "RMC" }, { "NW", "UP" }, { "ESC", "CC" }, 
				{ "EOU", "CI" },{ "WPAC", "RMC" },  { "NW", "CC" }, { "ESC", "UP" }, 
				{ "SOU", "OIT" }, 
				{ "CORB", "EOU" }, { "NCU", "WPAC" }, { "UP", "OIT" }, { "CC", "SOU" }, { "CI", "ESC" }, { "RMC", "ESC" },
				{ "CORB", "WPAC" }, { "NCU", "EOU" },	{ "UP", "SOU" }, { "CC", "OIT" }, { "CI", "NW" },{ "RMC", "NW" }
		};

		season = new java.util.HashMap<Game, Result>();
		for (String[] stringGame : stringSchedule) {
			season.put(new Game(stringGame[0], stringGame[1]), null);
		}
	}

	public void loadResults() {
		String[][] stringResults = {
				{ "SOU", "CI", "1", "0"}, { "OIT", "RMC", "1", "0"}, { "NCU", "ESC", "2", "0"}, { "CORB", "NW", "1", "0"},
				{ "SOU", "RMC", "3", "4"}, { "OIT", "CI", "0", "3"}, { "CORB", "ESC", "4", "2"}, { "NCU", "NW", "0", "1"}, 
				{ "NW", "WPAC", "0", "1"}, { "ESC", "EOU", "0", "1"}, { "CORB", "UP", "2", "0"}, { "NCU", "CC", "1", "2"}, 
				{ "NW", "EOU", "1", "2"}, { "ESC", "WPAC", "1", "1"}, { "CORB", "CC", "2", "2"}, { "NCU", "UP", "2", "3"},
				{ "RMC", "CI", "1", "0"}, 
				{ "SOU", "CORB", "1", "0"},  { "OIT", "NCU", "4", "0"},{ "CC", "WPAC", "3", "3"}, { "UP", "EOU", "2", "3"}, 
				{ "SOU", "NCU", "1", "0"}, { "OIT", "CORB", "2", "0"},  { "CC", "EOU", "0", "2"}, { "UP", "WPAC", "4", "3"},
				{ "NW", "ESC", "3", "0"}, 
				{ "RMC", "UP", "2", "1"},{ "CI", "CC", "1", "0"},  { "EOU", "OIT", "2", "0"}, { "WPAC", "SOU", "1", "3"}, 
				{ "RMC", "CC", "1", "0"}, { "CI", "UP", "0", "1"}, { "EOU", "SOU", "4", "0"}, { "WPAC", "OIT", "0", "3"}, 
				{ "CORB", "NCU", "2", "1"}
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
		int[] placeCount = new int[Team.values().length]; //number of teams
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

//			if (lowest < carrollPlace) {
//				lowest = carrollPlace;
//				lowestOrder = order.toString();
//			}
			if (++count % 39916800 == 0) { //print every so often 
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
			opponent[standings.get(12-carrollPlace).ordinal()]++;

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
		current.winRest(Team.CC);
		current.winRest(Team.CI);
		current.winRest(Team.RMC);
		current.winRest(Team.EOU);
		current.winRest(Team.CORB);
		current.winRest(Team.NCU);
		current.winRest(Team.UP);
		current.winRest(Team.NW);
		current.winRest(Team.ESC);
		current.winRest(Team.SOU);
		current.winRest(Team.OIT);
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
