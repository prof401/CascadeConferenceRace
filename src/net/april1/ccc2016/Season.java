package net.april1.ccc2016;

import java.util.Map;

public class Season {
	private Map<Game, Result> season;
	public void loadSchedule() {
		String[][] stringSchedule={
				{"NW","WPAC"},{"ESC","EOU"},{"CORB","UGF"},{"NCU","CC"}, //Sept 16
				{"NW","EOU"},{"ESC","WPAC"},{"CORB","CC"},{"NCU","UGF"},{"RMC","CI"}, //Sept 17
				{"CC","EOU"}, //Sept 22
				{"SOU","CORB"},{"CC","WPAC"},{"UGF","EOU"},{"OIT","NCU"}, //Sept 23
				{"SOU","NCU"},{"UGF","WPAC"},{"OIT","CORB"}, {"NW","ESC"},//Sept 24
				{"RMC","UGF"},{"EOU","OIT"},{"WPAC","SOU"},{"CI","CC"}, //Sept 30
				{"CORB","NCU"},{"EOU","SOU"},{"WPAC","OIT"}, //Oct 1
				{"CI","UGF"},{"RMC","CC"}, //Oct 2
				{"OIT","NW"},{"SOU","ESC"},{"CI","CORB"},{"RMC","NCU"}, //Oct 7
				{"OIT","ESC"},{"UGF","CC"},{"SOU","NW"}, //Oct 8
				{"RMC","CORB"},{"CI","NCU"}, //Oct 9
				{"SOU","OIT"}, //Oct 13
				{"WPAC","CI"},{"EOU","RMC"},{"NW","UGF"},{"ESC","CC"}, //Oct 14
				{"EOU","CI"},{"NW","CC"},{"ESC","UGF"},{"WPAC","RMC"}, //Oct 15
				{"CC","OIT"}, //Oct 20
				{"CORB","EOU"},{"NCU","WPAC"},{"UGF","OIT"},{"CC","SOU"},{"CI","ESC"},{"RMC","NW"}, //Oct 21
				{"CORB","WPAC"},{"UGF","SOU"},{"NCU","EOU"}, //Oct 22
				{"CI","NW"},{"RMC","ESC"}, //Oct 23
				{"SOU","CI"},{"OIT","RMC"},{"NCU","ESC"},{"CORB","NW"}, //Oct 28
				{"EOU","WPAC"},{"SOU","RMC"},{"OIT","CI"},{"CORB","ESC"},{"NCU","NW"} //Oct 29
		};

		season = new java.util.HashMap<Game, Result>();
		for(String[] stringGame : stringSchedule) {
			season.put(new Game(stringGame[0], stringGame[1]), null);
		}
	}

	public void loadResults() {
		String[][] stringResults={
				{"NW","WPAC","3","0"},{"ESC","EOU","0","2"},{"CORB","UGF","2","1"},{"NCU","CC","0","1"}, //Sept 16
				{"NW","EOU","0","1"},{"ESC","WPAC","0","0"},{"CORB","CC","1","2"},{"NCU","UGF","3","0"},{"RMC","CI","2","1"} //Sept 17
		};
		
		for(String[] stringResult : stringResults) {
			Game game = new Game(stringResult[0], stringResult[1]);
			Result result = new Result(stringResult[2], stringResult[3]);
			season.put(game,result);
		}
	}
	
	public void printStandings() {
		Standings table = new Standings(season);
		table.print();
	}
	
	static final public void main(String[] args) {
		Season current = new Season();
		current.loadSchedule();
		current.loadResults();
		current.printStandings();
	}
}
