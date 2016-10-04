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
		private int gsTotal = 0;
		private int gaTotal = 0;
		private int gdTotal = 0;
		private int[] goalsScored = new int[Team.values().length];

		public TableEntry() {
			for (int i=0;i<Team.values().length;i++) {
				goalsScored[i] = -1;
			}
		}
		
		public void addGame(Team opponent, int gs, int ga) {
			if (gs>ga) {
				points += 3;
			} else {
				if (gs==ga) {
					points += 1;
				} else {
					points += 0;
				}
			}
			goalsScored[opponent.ordinal()] = gs;
			gsTotal += gs;
			gaTotal += ga;
			gdTotal += Math.min(Math.max(-3, gs-ga),3);
		}
				
		public int getPoints() {
			return points;
		}

		public int getGsTotal() {
			return gsTotal;
		}

		public int getGaTotal() {
			return gaTotal;
		}

		public int getGdTotal() {
			return gdTotal;
		}

		public int getGoalsScored(Team opponent) {
			return goalsScored[opponent.ordinal()];
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(points);
			sb.append(' ');
			sb.append(gaTotal);
			sb.append(' ');
			sb.append(gdTotal);
			sb.append(' ');
			sb.append(gsTotal);
			sb.append(' ');
			for(int gs : goalsScored) {
				sb.append(gs);
				sb.append(' ');
			}
			return sb.toString();
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
				TableEntry home = table.get(game.getHome());
				TableEntry away = table.get(game.getAway());
				home.addGame(game.getAway(), result.getHomeScore(), result.getAwayScore());
				away.addGame(game.getHome(), result.getAwayScore(), result.getHomeScore());
			}
		}
	}
	
	public List<Team> sortedTable() {
	    List<Entry<Team, TableEntry>> list = new LinkedList<>(table.entrySet());
	    Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			@Override
	        public int compare(Object o1, Object o2) {
				int returnValue = 0;
				Map.Entry<Team, TableEntry> entry1 = (Map.Entry<Team, TableEntry>) (o1);
				Map.Entry<Team, TableEntry> entry2 = (Map.Entry<Team, TableEntry>) (o2);
				returnValue = Integer.valueOf(entry2.getValue().getPoints()).compareTo(Integer.valueOf(entry1.getValue().getPoints()));

				//(1) Head-to-head conference result between tied teams
				if (returnValue==0) {
					returnValue = entry2.getValue().getGoalsScored(entry1.getKey()) - entry1.getValue().getGoalsScored(entry2.getKey());
				}
				
				//(2) Fewest goals allowed (all conference games)
				if (returnValue==0) {
					returnValue = entry1.getValue().getGaTotal() - entry2.getValue().getGaTotal();
				}
				//(3) Goal differential (all conference games; 3-goal maximum per game)
				if (returnValue==0) {
					returnValue = entry2.getValue().getGdTotal() - entry1.getValue().getGdTotal();
				}

				//(4) Compare record vs. No. 1 team in the conference and continue down through the
				//conference standings until one team has an advantage
				//a. When arriving at another group of tied teams while comparing records, use
				//each team’s record against the collective tied teams as a group (prior to their
				//own tie-breaking procedures) rather than the performance against the
				//individual tied teams.

				//(5) Most total goals (all conference games)
				if (returnValue==0) {
					returnValue = entry2.getValue().getGsTotal() - entry1.getValue().getGsTotal();
				}

				//(6) Coin flip			
				
				return returnValue;
			
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
			System.out.print(table.get(team));
			System.out.println();
		}
	}
}
