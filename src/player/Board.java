package player;
import list.*;


// the Board class is to record all the moves in a chess board
public class Board {
	public static final int ROW = 8;
	public static final int COL = 8;
	public int[][] location;
	public static DList network;

	// constructor of an empty board
	public Board(){
		location = new int[ROW][COL];
		for (int i = 0; i < ROW; i ++){
			for (int j = 0; j < COL; j++){
				location [i][j] = 2;  //"2" is the # for blank;
			}
		}
		location[0][0] = 3; // "3" is the # for forbidden corners;
		location[0][7] = 3;
		location[7][0] = 3;
		location[7][7] = 3;
	}
	
	//play() updates the Board field by Moves
	//different cases for add and step moves
	public void play(int color, Move m) {
		if (m.moveKind == 1){
			location[m.y1][m.x1] = color;
		}else if (m.moveKind == 2){
			location[m.y1][m.x1] = color;
			location[m.y2][m.x2] = 2;
		}
	}

	//finding the chips (of the same color) that form connections with a chip,
  	//connection() returns a of list existing connections, including the move itself 
  	public DList connection(Move m) {
  		DList connection = new DList();
  		int i;
  		//upper left
  		for (i=1;m.x1-i>=0&&m.y1-i>=0;i++){
  			if(location[m.y1-i][m.x1-i]!=2){
	  			if (location[m.y1][m.x1]==location[m.y1-i][m.x1-i]){
	  				connection.insertBack(new Move(m.x1-i, m.y1-i));
	  				break;
	  			}
	  			else break;
	  		}
  		}
  		//lower right
  		for (i=1;m.x1+i<=7&&m.y1+i<=7;i++){
  			if(location[m.y1+i][m.x1+i]!=2){
	  			if (location[m.y1][m.x1]==location[m.y1+i][m.x1+i]){
	  				connection.insertBack(new Move(m.x1+i, m.y1+i));
	  				break;
	  			}
	  			else break;
	  		}
  		}
  		//lower left
  		for (i=1;m.x1-i>=0&&m.y1+i<=7;i++){
  			if(location[m.y1+i][m.x1-i]!=2){
	  			if (location[m.y1][m.x1]==location[m.y1+i][m.x1-i]){
	  				connection.insertBack(new Move(m.x1-i, m.y1+i));
	  				break;
	  			}
	  			else break;
  			}
  		}
  		//upper right
  		for (i=1;m.x1+i<=7&&m.y1-i>=0;i++){
  			if(location[m.y1-i][m.x1+i]!=2){
	  			if (location[m.y1][m.x1]==location[m.y1-i][m.x1+i]){
	  				connection.insertBack(new Move(m.x1+i, m.y1-i));
	  				break;
	  			}
  				else break;	
  			}
  		}
  		//right
  		for (i=1;m.x1+i<=7;i++){
  			if(location[m.y1][m.x1+i]!=2&&m.y1!=0&&m.y1!=7){
	  			if (location[m.y1][m.x1]==location[m.y1][m.x1+i]){
	  				connection.insertBack(new Move(m.x1+i, m.y1));
	  				break;
	  			}
	  			else break;	
	  		}
  		}
  		//up
  		for (i=1;m.y1-i>=0;i++){
  			if(location[m.y1-i][m.x1]!=2&&m.x1!=0&&m.x1!=7){
	  			if (location[m.y1][m.x1]==location[m.y1-i][m.x1]){
	  				connection.insertBack(new Move(m.x1, m.y1-i));
	  				break;
	  			}
	  			else break;
	  		}	
  		}
  		//left
  		for (i=1;m.x1-i>=0;i++){
  			if(location[m.y1][m.x1-i]!=2&&m.y1!=0&&m.y1!=7){
	  			if (location[m.y1][m.x1]==location[m.y1][m.x1-i]){
	  				connection.insertBack(new Move(m.x1-i, m.y1));
	  				break;
	  			}
	  			else break;	
	  		}
  		}
  		//down
  		for (i=1;m.y1+i<=7;i++){
  			if(location[m.y1+i][m.x1]!=2&&m.x1!=0&&m.x1!=7){
	  			if (location[m.y1][m.x1]==location[m.y1+i][m.x1]){
	  				connection.insertBack(new Move(m.x1, m.y1+i));
	  				break;
	  			}
	  			else break;	
	  		}
  		}
		return connection;		  		
  	}

    
  	// the number of all connections that either side has
  	public int numOfConn(int side){
  		DList temp = new DList();
  		int n = 0;
  		for (int i = 0; i < ROW; i++){
  			for (int j = 0; j < COL; j++){
  				if (location[i][j] == side){
  					temp.insertBack(new Move(i,j));
  				}
  			}
  		}
  		DListNode cur = temp.getHead().next();
  		while (cur.item != null){
  			n += connection(cur.item).length();
  			cur = cur.next();
  		}
  		return n;
  	}

  	//check if a network passes through the same chip twice
  	public boolean repeatMoves(DList moves, DListNode chip){
  		DListNode cur = moves.getHead().next();
  		while(cur.item!=null){
  			if (cur.item.x1==chip.item.x1&&cur.item.y1==chip.item.y1){
  				return true;
  			}
  			cur = cur.next();
  		}
  		return false;
  	}

  	//check if a network passes through a chip without changing a direction
    public boolean badLine(DList moves, DListNode chip){
    	DList moves1 = moves.clone();
    	moves1.insertBack(chip.item);
    	if (moves1.invalidLine()){
    		return true;
    	}
    	return false;
    }


    //moves: keep track of the valid moves in a network
    public boolean Network(DListNode c, int color, DList moves){
    	if (color==0){
    		if(c.item==null){
    			return false;
    		}else if (c.item.y1==7){
    			if(repeatMoves(moves, c)||badLine(moves, c)){
    				return this.Network(c.next(),color, moves);
    			}
    			if(moves.length()<5) {
    				return this.Network(c.next(),color, moves);
    			}
    			moves.insertBack(c.item);
    			network = moves;
    			return true;
    		}else{
    			if (c.next().item!=null){
    				if(c.item.y1==0||repeatMoves(moves, c)||badLine(moves, c)){
    					return this.Network(c.next(),color, moves);
    				}else{
    					DList newMoves = moves.clone();
     	         moves.insertBack(c.item);
     	         return this.Network(c.next(),color, newMoves) 
     	        		 || this.Network(this.connection(c.item).getHead().next(),color, moves);
    				}
    			}else{
    	        if (c.item.y1==0||repeatMoves(moves, c)||badLine(moves, c)){
    	        	return false;
    	        }else{
    	        	moves.insertBack(c.item);
    	        	return this.Network(this.connection(c.item).getHead().next(),color, moves);
    	        }
    			}           
    		}
    	}else if (color==1){
    		if(c.item==null){
    			return false;
    		}else if (c.item.x1==7){
    			if(repeatMoves(moves, c)||badLine(moves, c)){
    				return this.Network(c.next(),color, moves);
    			}
    			if(moves.length()<5) {
    				return this.Network(c.next(),color, moves);
    			}
    			moves.insertBack(c.item);
    			network = moves;
    			return true;
    		}else{
    			if (c.next().item!=null){
    				if(c.item.x1==0||repeatMoves(moves, c)||badLine(moves, c)){
    					return this.Network(c.next(),color, moves);
    				}else{
    					DList newMoves = moves.clone();
    					moves.insertBack(c.item);
    					return this.Network(c.next(),color, newMoves) 
    							|| this.Network(this.connection(c.item).getHead().next(),color, moves);
    				}
    			}else{
    				if (c.item.x1==0||repeatMoves(moves, c)||badLine(moves, c)){
    					return false;
    				}else{
    					moves.insertBack(c.item);
    					return this.Network(this.connection(c.item).getHead().next(),color, moves);
    				}
    			}           
    		}
    	}else{
    		return false;
    	}
  	}
  	
  	//determining whether a game board contains anm.y1 networks 
  	//for a given player (very difficult; put your smartest teammate on this)
  	public boolean isNetwork(int color) {	
  		int i;
  		DListNode c;
  		Move m;
  		boolean	t;
  		DList moves;
  		if (color==1){
  			t = false;
  			for(i=0;i<8;i++){
  				if (location[i][0]==color){
  					m = new Move(0,i);
  					c = this.connection(m).getHead().next();
  					moves = new DList();
  					moves.insertBack(m);
  					t = t||Network(c,color, moves);
  				}
  			}
  			return t;
  		}else if (color==0){
  			t = false;
  			for(i=0;i<8;i++){
  				if (location[0][i]==color){
  					m = new Move(i,0);
  					c = this.connection(m).getHead().next();
  					moves = new DList();
  					moves.insertBack(m);
  					t = t||Network(c,color, moves);
  				}
  			}
  			return t;
  		}else{
  			return false;
  		}
  	}
  	
  	//check if the main area (not including the goal areas) is empty 
  	public boolean centerIsEmpty(int side){
  		for (int i = 1; i< COL-1; i++){
  			for (int j = 1; j < ROW-1; j++){
  				if (location[i][j] == side){
  					return false;
  				}
  			}
  		}
  		return true;
  	}
  
  	//check if the first step locates in goal areas
  	public boolean bestFirst(int side){
  		int count = 0;
  		if (side == 0){
  			count = 0;
  			for (int i = 0 ; i< COL; i++){
  				if (location[0][i] == 0){
  					count ++;
  				}else if (location[7][i] == 0){
  					count ++;
  				}
  			}
  		}else if(side == 1){
  			count = 0;
  			for (int j = 0; j < ROW; j++){
  				if(location[j][0] == 1){
  					count ++;
  				}else if (location[j][7] == 1){
  					count ++;
  				}
  			}
  		}
  		if (count == 1){
  			return true;
  		}else{
  			return false;
  		}
  	}

  	// print out the board
	public void print(){
		for (int i = 0; i < ROW; i++){
			System.out.print("| ");
			for (int j = 0; j < COL; j++){
				if (this.location[i][j]==2){
					System.out.print(" " + " | ");
				}else if (this.location[i][j]==3){
					System.out.print("X" + " | ");
				}else{
					System.out.print(this.location[i][j] + " | ");
				}
			}
			System.out.println();
		}
		System.out.println();		
	}

}
