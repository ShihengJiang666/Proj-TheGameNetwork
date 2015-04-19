package list;

import dict.Entry;

/**
 *  A DListNode is a node in a DList (doubly-linked list).
 */

public class DListNode2 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public Entry item;
  public DListNode2 prev;
  public DListNode2 next;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  DListNode2(Entry i, DListNode2 p, DListNode2 n) {
    item = i;
    prev = p;
    next = n;
  }
}
