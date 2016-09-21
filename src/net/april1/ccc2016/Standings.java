package net.april1.ccc2016;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Standings {
	
	private class TableEntry {
		private int points = 0;

		public int getPoints() {
			return points;
		}

		public void addPoints(int points) {
			this.points += points;
		}
	}

	private Map<Team,TableEntry> table;

	public Standings(Map<Game, Result> season) {
		init();
		calculatePoints(season);
	}

	private void init() {
		table = new java.util.HashMap<Team,TableEntry>();
		for (Team team : Team.values()) {
			table.put(team, new TableEntry());
		}
	}

	private void calculatePoints(Map<Game, Result> season) {
		for (Game game : season.keySet()) {
			Result result = season.get(game);
			if (result != null) {
				if (result.getHomeScore() > result.getAwayScore()) {
					// home wins
					table.get(game.getHome()).addPoints(3);
					table.get(game.getAway()).addPoints(0);
				} else {
					if (result.getHomeScore() == result.getAwayScore()) {
						// tie
						table.get(game.getHome()).addPoints(1);
						table.get(game.getAway()).addPoints(1);
					} else {
						// away wins
						table.get(game.getHome()).addPoints(0);
						table.get(game.getAway()).addPoints(3);
					}

				}
			}
		}
	}
	
	private List<Team> sortedTable() {
	    List<Entry<Team, TableEntry>> list = new LinkedList<>(table.entrySet());
	    Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			@Override
	        public int compare(Object o1, Object o2) {
				Map.Entry<Team, TableEntry> entry1 = (Map.Entry<Team, TableEntry>) (o1);
				Map.Entry<Team, TableEntry> entry2 = (Map.Entry<Team, TableEntry>) (o2);
				
				return Integer.valueOf(entry2.getValue().getPoints()).compareTo(Integer.valueOf(entry1.getValue().getPoints()));
			
	        }
	    });

	    List<Team> result = new LinkedList<Team>();
	    for (Iterator<Entry<Team, TableEntry>> it = list.iterator(); it.hasNext();) {
	        Map.Entry<Team, TableEntry> entry = (Map.Entry<Team, TableEntry>) it.next();
	        result.add(entry.getKey());
	    }

	    return result;
	}

	public void print() {
		for (Team team : sortedTable()) {
			System.out.print(team.toString());
			System.out.print(' ');
			System.out.print(table.get(team).getPoints());
			System.out.println();
		}
	}
}
