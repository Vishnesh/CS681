package edu.umb.cs681.hw21;

import java.util.ArrayList;
import java.util.List;

public class IplPlayerStat {

	private String name;
	private int total_runs, total_centuries, batting_avg, batting_strike_rate;

	public IplPlayerStat(String name, int total_runs, int total_centuries, int batting_avg, int batting_strike_rate) {
		this.name = name;
		this.total_runs = total_runs;
		this.total_centuries = total_centuries;
		this.batting_avg = batting_avg;
		this.batting_strike_rate = batting_strike_rate;
	}

	public String getName() {
		return name;
	}

	public int gettotal_runs() {
		return total_runs;
	}

	public int gettotal_centuries() {
		return total_centuries;
	}

	public int getbatting_strike_rate() {
		return batting_strike_rate;
	}

	public int getbatting_avg() {
		return batting_avg;
	}

	@Override
	public String toString() {

		return this.name + " " + this.total_runs + " " + this.total_centuries + " " + this.batting_avg + " "
				+ this.batting_strike_rate;
	}

	public static void main(String[] args) {
		List<IplPlayerStat> list = new ArrayList<IplPlayerStat>();
		list.add(new IplPlayerStat("KL Rahul", 670, 1, 56, 140));
		list.add(new IplPlayerStat("S Gill", 440, 0, 34, 112));
		list.add(new IplPlayerStat("V Kohli", 431, 0, 48, 125));
		list.add(new IplPlayerStat("S Dhawan", 471, 2, 47, 133));

		Integer minRuns = list.stream().parallel().map((IplPlayerStat player) -> player.gettotal_runs()).reduce(0,
				(result, runs) -> {
					if (result == 0)
						return runs;
					else if (runs < result)
						return runs;
					else
						return result;
				}, (finalResult, interMediateResult) -> {
					return (finalResult < interMediateResult) ? finalResult : interMediateResult;
				});
		System.out.println("Least Score: " + minRuns);

		int totalnumberofplayers = list.stream().parallel().map((IplPlayerStat player) -> player.getName()).reduce(0,
				(result, name) -> ++result, (finalResult, intermediateResult) -> finalResult + intermediateResult);
		System.out.println("Player count: " + totalnumberofplayers);

		Integer maxCenturies = list.stream().map((IplPlayerStat player) -> player.gettotal_centuries()).parallel()
				.reduce(0, (result, century) -> {
					if (result == 0)
						return century;
					else if (century >= result)
						return century;
					else
						return result;
				}, (finalResult, interMediateResult) -> {
					return (finalResult > interMediateResult) ? finalResult : interMediateResult;
				});
		System.out.println("Most Number of Centuries: " + maxCenturies);

		Integer bestStrikeRate = list.stream().map((IplPlayerStat player) -> player.getbatting_strike_rate()).parallel()
				.reduce(0, (result, strike) -> {
					if (result == 0)
						return strike;
					else if (strike >= result)
						return strike;
					else
						return result;
				}, (finalResult, interMediateResult) -> {
					return (finalResult > interMediateResult) ? finalResult : interMediateResult;
				});
		System.out.println("Best Strike Rate: " + bestStrikeRate);

	}
}