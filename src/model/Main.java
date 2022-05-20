package model;

import java.util.TreeSet;

import vue.MainWindows;

public class Main {
	
	public static void main(String[] args) {
		TreeSet<RockConcert> rockConcert = new TreeSet<RockConcert>();
		TreeSet<Opera> operaConcert = new TreeSet<Opera>();
		TreeSet<Theatre> theatreRepresentations = new TreeSet<Theatre>();
		MainWindows myWindow = new MainWindows(rockConcert, operaConcert, theatreRepresentations);
		myWindow.setVisible(true);
	}

}
