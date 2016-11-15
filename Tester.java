public class Tester {
	public static void main(String[] args) {

		//Game g = new Game();
		Board b = new Board();
		System.out.println(b);
	
		for (boolean[][] testpiece : Game.pieces)	
			System.out.println(b.getAvailableSpots(testpiece));
	}
}
