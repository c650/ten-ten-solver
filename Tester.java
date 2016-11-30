import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {

	public static void main(String[] args) {

		
		/*
		 * Board b = new Board(); System.out.println(b);
		 * 
		 * for (Piece testpiece : Game.pieces) {
		 * 
		 * System.out.println(testpiece);
		 * System.out.println(b.getAvailableSpots(testpiece));
		 * 
		 * }
		 */
		LocalDateTime currentTime = LocalDateTime.now();
		long t=(currentTime.getNano()/1000000)+currentTime.getSecond()*1000;
		ArrayList<Integer> scoresA = new ArrayList<>();
		for (int i=1;i<=1000;i++) {
			Game g=new Game();
			scoresA.add(g.play());
		}
		
		Collections.sort(scoresA);
		currentTime = LocalDateTime.now();
		t=currentTime.getNano()/1000000-t+currentTime.getSecond()*1000;
		System.out.println((double)(t)/1000);
		System.out.println(scoresA);
		
		currentTime=LocalDateTime.now();
		t=(currentTime.getNano()/1000000)+currentTime.getSecond()*1000;
		ArrayList<Integer> scoresB = new ArrayList<>();
		for (int i=1;i<=1000;i++) {
			Game g=new Game();
			scoresB.add(g.playByLinear());
		}
		
		Collections.sort(scoresB);
		currentTime = LocalDateTime.now();
		t=currentTime.getNano()/1000000-t+currentTime.getSecond()*1000;
		System.out.println((double)(t)/1000);
		System.out.println(scoresB);
	}
}
