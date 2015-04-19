/* MachinePlayer.java */

package player;
import list.*;
/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {
	
	public DList mMoves;
	public DList hMoves;
	public int count;  		//either side has 10 chips
	public int color;
	public int searchDepth;
	public Board machineBoard;		//the board of this player

	// Creates a machine player with the given color.  Color is either 0 (black)
	// or 1 (white).  (White has the first move.)
	public MachinePlayer(int color) {
		count = 10;
		this.color = color;
		mMoves = new DList();
		hMoves = new DList();
		machineBoard = new Board();
		searchDepth = 2;
	}

	// Creates a machine player with the given color and search depth.  Color is
	// either 0 (black) or 1 (white).  (White has the first move.)
	public MachinePlayer(int color, int searchDepth) {
		count = 10;
		this.color = color;
		mMoves = new DList();
		hMoves = new DList();
		machineBoard = new Board();
		this.searchDepth = searchDepth;
	}

	// updates the machineBoard by machine's moves
	public void mPlay(Move m) {
		machineBoard.play(this.color, m);
		mMoves.insertBack(m);
		count -= 1;
		if (count <= 0){
			searchDepth = 1;
		}
	}

	// updates the machineBoard by opponent's moves
	public void hPlay(Move m) {
		machineBoard.play(1-this.color, m);
		hMoves.insertBack(m);
	}
	//creates a new gameboard for a potential move
	public Board newBoard(int color, Move m) {
		Board newBoard = new Board();
		int i, j;
		for (i=0;i<8;i++){
			for (j=0;j<8;j++){
				newBoard.location[i][j]=machineBoard.location[i][j];
			}
		}
		newBoard.play(color, m);
		return newBoard;
	}
	
	// create a clone of contemporary board and take a move
	public Board copyBoard(int color, Move m, Board b){
		Board copyBoard = new Board();
		for (int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				copyBoard.location[i][j]=b.location[i][j];
			}
		}
		copyBoard.play(color, m);
		return copyBoard;
	}

	// Returns a new move by "this" player.  Internally records the move (updates
	// the internal game board) as a move by "this" player.
	public Move chooseMove() {
		Move newMove;
		newMove = this.minimaxTree(this.machineBoard,this.color,0,-200,200,0).move;
		this.mPlay(newMove);
		return newMove;
	} 

	// If the Move m is legal, records the move as a move by the opponent
	// (updates the internal game board) and returns true.  If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player.  This method allows your opponents to inform you of their moves.
	public boolean opponentMove(Move m) {
		if (isValid(m, 1-this.color, machineBoard)){
			this.hPlay(m);
			return true;
		}else{
			return false;
		}
	}

	// If the Move m is legal, records the move as a move by "this" player
	// (updates the internal game board) and returns true.  If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player.  This method is used to help set up "Network problems" for your
	// player to solve.
	public boolean forceMove(Move m) {
		if (isValid(m,color,machineBoard)){
			this.mPlay(m);
			return true;
		}
		return false;
	}
  
	//to decide whether a move will form into cluster
	public boolean inCluster(Move m, int color){
		Board newBoard = this.newBoard(color, m);
		int counter = 0;
		int a=0, b=0, c=0, d=0;
		int i, j;
		for (i=0;i<8;i++){
			for (j=0;j<8;j++){
				if(newBoard.location[i][j]==color){
					counter = 0;
					if ((i>=1 && i<=6)&&(j>=1 && j<=6)){
						a=i - 1;
						b=i + 1;
						c=j - 1;
						d=j + 1;
					}
					else if ((i==0)&&(j>=1 && j<=6)){
						a=i;
						b=i + 1;
						c=j - 1;
						d=j + 1;
					}
					else if ((i==7)&&(j>=1 && j<=6)){
						a=i - 1;
						b=i;
						c=j - 1;
						d=j + 1;
					}
					else if ((i>=1 && i<=6)&&(j==0)){
						a=i - 1;
						b=i + 1;
						c=j;
						d=j + 1;
					}
					else if ((i>=1 && i<=6)&&(j==7)){
						a=i - 1;
						b=i + 1;
						c=j - 1;
						d=j;
					}
					for (int row = a; row <= b; row++){
						for (int col =c; col <= d; col++){
							if (newBoard.location[row][col] == color){
								counter++;
							}
						}
					}
					if (counter > 2){	//has to be 1 at least
						return true;
					}
				}        
			}
		}
		return false;
	}
  
	//isValid() determines whether "this" move is a valid move. 
	public boolean isValid(Move m, int color, Board b) {
		if (b.location[m.y1][m.x1] == 3){
			return false;
		}
		else if (b.location[m.y1][m.x1]!=2){ // occupied
			return false;
		}
		else if (inCluster(m, color)){
			return false;
		}
		else{
			if (color==0){
				if (m.x1 == 0 || m.x1 == 7){
          return false;
        }
        else if (m.moveKind==2){
        	if(m.y1==m.y2&&m.x1==m.x2){
        		return false;
        	} 
        	else return true;
        }
        else return true;
			}
			else{
				if (m.y1 == 0 || m.y1 == 7){
					return false;
				}
				else if (m.moveKind==2){
					if(m.y1==m.y2&&m.x1==m.x2){
						return false;
					} 
					else return true;
				}
				else return true;
			}
		}
	}

	//validMoves() returns a list of all possible moves for the current player. 
	//Use the result from isValid()
	public DList validMoves(Board g, int color) {
		int i, j, a, b;
		Move m;
		DList validMoves = new DList();
		for (i=0;i<8;i++){
			for (j=0;j<8;j++){
				if (count <= 0) {
					for (a=0;a<8;a++){
						for (b=0;b<8;b++){
							if (g.location[a][b]==color){
								m = new Move(j,i,b,a);
								if (isValid(m, color, g)){
									validMoves.insertBack(m);
								}
							}
						}
					}
					m = null;           
				}
				else{
					m = new Move(j, i);
					if (isValid(m,color,g)){
						validMoves.insertBack(m);
					}
				}
			}
		}
		return validMoves;
	}



	//computing an evaluation function for a board (possibly difficult), and
	//evaScore() assigns a score to each board that estimates how well 
	//your MachinePlayer is doing. In mahineplayer.
	public int evaScore(Board b, int side, int depth, int i) {
		int score = 0;
		final int CONST = 200;
		if (b.isNetwork(this.color)){ //machine wins
			score = CONST - 5*(depth-1);
		}
		else if (b.isNetwork(1-this.color)){// opponent wins
			score = -CONST + 5*(depth-1);
		}
		score += 10*(b.numOfConn(this.color)-b.numOfConn(1-this.color));
		if (b.centerIsEmpty(this.color)){
			if (b.bestFirst(this.color)){
				score += 50;
			}
		} 
		else if (b.centerIsEmpty(1-this.color)){
			if (b.bestFirst(1-this.color)){
				score -= 50;
			}
		}
		return score;
	}

	
	// minimax tree to optimize scores
	public Best minimaxTree(Board b, int side, int depth, int alpha, int beta, int i){
		DListNode cur;
		Best myBest = new Best(null, 0);
		Best reply;

    if (evaScore(b,side,depth,i)>=170||evaScore(b,side,depth,i)<=-170){
      myBest.score = evaScore(b,side,depth,i);
      return myBest;
    }
    if (depth==this.searchDepth){
    	myBest.score = evaScore(b,side,depth,i);
    	return myBest;
    }
    if (side == this.color){
    	myBest.score = alpha;
    }else{
    	myBest.score = beta;
    }
    cur = validMoves(b, side).getHead().next();
    myBest.move = cur.item;
    i = 0;

    while (cur.item!=null){
    	Board newBoard = copyBoard(side, cur.item, b);
    	i ++;
    	reply = minimaxTree(newBoard, 1-side, depth+1, alpha, beta, i);
    	if(side==this.color&&reply.score>myBest.score){
    		myBest.move = cur.item;
    		myBest.score = reply.score;
    		alpha = reply.score;
    	}else if (side==1-this.color&&reply.score<myBest.score){
    		myBest.move = cur.item;
    		myBest.score = reply.score;
    		beta = reply.score;
    	}
    	if (alpha>=beta){
    		return myBest;
    	}
    	cur=cur.next();
    }
    return myBest;
	}

}
