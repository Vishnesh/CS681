package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.util.Comparator;

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
		int max_runs, min_runs, y, count;
		List<IplPlayerStat> list = new ArrayList<IplPlayerStat>();
		list.add(new IplPlayerStat("KL Rahul", 670, 1, 56, 140));
		list.add(new IplPlayerStat("S Gill", 440, 0, 34, 112));
		list.add(new IplPlayerStat("V Kohli", 431, 0, 48, 125));
		list.add(new IplPlayerStat("S Dhawan", 471, 2, 47, 133));

		max_runs = list.stream().map((IplPlayerStat batsman) -> batsman.gettotal_runs()).reduce(0,
				(result, price) -> result > price ? result : price);
		System.out.println("Maximum number of runs scored : " + max_runs);

		min_runs = list.stream().map((IplPlayerStat batsman) -> batsman.getbatting_avg()).reduce(1000000000,
				(result, price) -> price > result ? result : price);
		System.out.println("Minimum number of runs per match : " + min_runs);

		y = 0;
		count = list.stream().map(x -> y + 1).reduce(0, (a, b) -> a + b);
		System.out.println("Total number of batsman : " + count);
		System.out.println("");

		System.out.println("Number of runs scored in sorted order : ");
		List<IplPlayerStat> sortedByRuns = list.stream().sorted(Comparator.comparingInt(IplPlayerStat::gettotal_runs))
				.collect(Collectors.toList());
		sortedByRuns.forEach(System.out::println);
		System.out.println("");

		System.out.println("Strike rates in sorted order : ");
		List<IplPlayerStat> sortedByStrikeRate = list.stream()
				.sorted(Comparator.comparingInt(IplPlayerStat::getbatting_strike_rate)).collect(Collectors.toList());
		sortedByStrikeRate.forEach(System.out::println);
	}
}