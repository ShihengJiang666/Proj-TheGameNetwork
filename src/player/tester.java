package player;

import list.*;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Board myboard = new Board();
		//myboard.print();
		
		DList list = new DList();
		list.insertBack(new Move (5,0));
		//list.insertBack(new Move (3,6));
		list.insertBack(new Move (5,1));
//		list.insertBack(new Move (4,5));
//		list.insertBack(new Move (4,3));
		list.insertBack(new Move (5,3));
//		list.insertBack(new Move (7,4));
		list.print();
		System.out.println();
		DListNode cur = list.getHead().next();
		/*while (cur.next().item != null){
		System.out.println(list.inDiag(cur, cur.next()));
		System.out.println(list.inOrth(cur, cur.next()));
		System.out.println(list.direction(cur, cur.next()));
		cur = cur.next();
		}
		*/
		System.out.println(list.invalidLine());
	//	DListNode sevenfour = list.getHead().prev();
//		DListNode fivesix = sevenfour.prev();
	//	DListNode fourfive = fivesix.prev();
//		System.out.println(list.direction(fourfive, fivesix));
	//	System.out.println(list.direction(fivesix, sevenfour));
		
		Board myboard = new Board();
		myboard.location[2][4] = 0;
		myboard.location[3][6] = 0;
		myboard.location[1][2] = 0;
		myboard.location[1][5] = 0;
		myboard.location[4][1] = 0;
		myboard.location[6][3] = 0;
		
	//	myboard.print();
	
//		System.out.println(myboard.numOfConn(0));
	//	System.out.println(myboard.numOfConn(1));
	
		MachinePlayer mp = new MachinePlayer(0);
		MachinePlayer mp2 = new MachinePlayer(1);
		Board newboard = new Board();
//		newboard.play(0, new Move(4,0));
		//		1,0; 1,2; 3,4;6,4; 5,5; 5,7
		newboard.play(0, new Move(1,0));
		newboard.play(0, new Move(1,2));
		newboard.play(0, new Move(3,4));
		newboard.play(0, new Move(6,4));
		newboard.play(0, new Move(5,5));
		newboard.play(0, new Move(5,7));
//		newboard.play(1, mp2.random());

		newboard.print();
		System.out.println(newboard.centerIsEmpty(0));
		System.out.println(newboard.bestFirst(0));
		System.out.println(newboard.numOfConn(0));
		System.out.println(newboard.numOfConn(1));
		System.out.println("---------------------------------------");
//		System.out.println("score of 0: "+mp.evaScore(newboard, 0, 2));
		System.out.println("---------------------------------------");
//		System.out.println("score of 1: "+mp.evaScore(newboard, 1, 2));
		
	}

}
