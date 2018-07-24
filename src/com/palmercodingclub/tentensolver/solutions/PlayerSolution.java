package com.palmercodingclub.tentensolver.solutions;

import java.util.ArrayList;
import java.util.Scanner;
import com.palmercodingclub.tentensolver.Solution;
import com.palmercodingclub.tentensolver.Piece;
import com.cassdelacruzmunoz.library.ConsoleIO;
import com.palmercodingclub.tentensolver.Coordinate;

public class PlayerSolution extends Solution {

	Scanner in=new Scanner(System.in);
	
	public PlayerSolution() {
		super("The Player, unassisted");
	}

	public int doMove(ArrayList<Piece> choices) {
		String[] pieceArray = new String[choices.size()];
		for(int i=0;i < choices.size();i++) {
			pieceArray[i]=choices.get(i).toString();
		}
		Piece p=choices.get(ConsoleIO.generateMenu("Which piece would you like to play?", pieceArray));
		int x=ConsoleIO.getNumFromUser("Which column do you want to play it in?");
		int y=ConsoleIO.getNumFromUser("Which row do you want to play it in?");
		for (Coordinate c : b.getAvailableSpots(p)) {
			if (c.col==x && c.row==y) {
				b.placePiece( choices.remove(choices.indexOf(p)) , c );
			}
		}
		return p.getScore();
	}
}
