import java.util.ArrayList;
import java.util.Random;
public class AiPlayer implements Cloneable{
	Random rand = new Random();
	int move=0;
	int initdepth,initalpha,initbeta;

	AiPlayer() {
		initdepth = 0;
		initalpha=Integer.MIN_VALUE;
		initbeta=Integer.MAX_VALUE;
	}
	AiPlayer(int depth) {
		initdepth = depth;
		initalpha=Integer.MIN_VALUE;
		initbeta=Integer.MAX_VALUE;
	}
	public int randomMove(Connect4 game) {
		ArrayList moves = game.getAvailableMoves();
		int temp = rand.nextInt(moves.size());
		return (int) moves.get(temp) - 1;
	}
}