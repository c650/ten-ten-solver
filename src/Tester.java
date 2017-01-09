import java.util.*;
import java.lang.Thread;

public class Tester {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Incorrect # of args.");
			return;
		}

		Game g = new Game(new MySolution(), args[0], true);
		g.play();


	/*	Board b = new Board();
		System.out.println(b);

		for (Piece testpiece : Game.pieces) {

			System.out.println(testpiece);
			System.out.println(b.getAvailableSpots(testpiece));

		}
		ArrayList<Integer> scores = new ArrayList<>();
		int i = 100;
		while(i-->0) {
			System.out.println("trial#"+i);
			scores.add(g.play());
			g.reset();
			try{
				Thread.sleep(50);
			} catch (Exception e) {
				System.out.println("lol ok");
			}
		}

		Collections.sort(scores);

		System.out.println("Min: " + scores.get(0));
		System.out.println("Max: " + scores.get(scores.size()-1));
		System.out.println("Average: " + avg(scores));*/
	}

	public static double avg(ArrayList<Integer> arr) {
		double a = 0;
		for (int b : arr)
			a += b;

		return a / arr.size();
	}
}
