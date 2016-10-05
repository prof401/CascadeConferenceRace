package net.april1.ccc2016;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Season {
	static final private int RESULTS = 3;
	private Map<Game, Result> season;

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
		String[][] stringResults={
				{"NW","WPAC","3","0"},{"ESC","EOU","0","2"},{"CORB","UGF","2","1"},{"NCU","CC","0","1"}, //Sept 16
				{"NW","EOU","0","1"},{"ESC","WPAC","0","0"},{"CORB","CC","1","2"},{"NCU","UGF","3","0"},{"RMC","CI","2","1"}, //Sept 17
				{"CC","EOU","0","0"}, //Sept 22
				{"SOU","CORB","5","0"},{"CC","WPAC","2","0"},{"UGF","EOU","0","1"},{"OIT","NCU","1","1"}, //Sept 23
				{"SOU","NCU","2","0"},{"UGF","WPAC","1","3"},{"OIT","CORB","1","2"}, {"NW","ESC","2","0"},//Sept 24
				{"RMC","UGF","0","1"},{"EOU","OIT","4","0"},{"WPAC","SOU","0","2"},{"CI","CC","0","1"}, //Sept 30
				{"CORB","NCU","4","0"},{"EOU","SOU","0","1"},{"WPAC","OIT","3","1"}, //Oct 1
				{"CI","UGF","1","2"},{"RMC","CC","0","4"} //Oct 2
		};

		for (String[] stringResult : stringResults) {
			Game game = new Game(stringResult[0], stringResult[1]);
			Result result = new Result(stringResult[2], stringResult[3]);
			season.put(game, result);
		}
	}

	public void loseRest(String team) {
		for(Entry<Game, Result> gameEntry : season.entrySet()) {
			String homeTeam = gameEntry.getKey().getHome().toString();
			String awayTeam = gameEntry.getKey().getAway().toString();
			if(awayTeam.equals(team) || homeTeam.equals(team)) {
			//team is either Away or Home team
				if (gameEntry.getValue()==null) {
					//game not played yet
					Result newResult = null;
					if ((awayTeam.equals(team)))
						newResult = new Result("3","0");
					else 
						newResult = new Result("0","3");
					season.put(gameEntry.getKey(), newResult);			
				}		
			}
		}
	}
	
	public void future() {
		Map<Game, Result> futureSeason = new java.util.HashMap<Game, Result>(season);
		List<Game> noResultGames = new java.util.ArrayList<Game>();
		for (Game game : futureSeason.keySet()) {
			if (futureSeason.get(game) == null) {
				noResultGames.add(game);
			}
		}
		int fgLast = noResultGames.size() - 1;
		System.out.println(fgLast);
		int[] fg = new int[fgLast + 1];
		int z = 0;
		while (fg[0] != RESULTS) {
			if (++z%100000000==0) System.out.println(z);
			fg[fgLast]++;
			int count = fgLast;
			while (fg[count] > (RESULTS - 1) && count > 0) {
				fg[count] = 0;
				fg[--count] += 1;
			}
		}
	};

	public void printStandings() {
		Standings table = new Standings(season);
		for (Team t : table.sortedTable()) {
			System.out.println(t.name());
		}
		table.print();
	}

	static final public void main(String[] args) {
		Season current = new Season();
		current.loadSchedule();
		current.loadResults();
		current.loseRest("CC");
		current.printStandings();
		current.future();
	}
}
