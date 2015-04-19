package player;

// the class is to store pairs of best move in the form of (move, score)
public class Best {
	
	protected Move move; 
	protected int score;
	
	public Best(Move move, int score){
		this.move = move;
		this.score = score;
	}
}


