/* DListNode.java */

package list;
import player.*;

/**
 *  A DListNode is a node in a DList (doubly-linked list).
 */

public class DListNode {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public Move item;
  protected DListNode prev;
  protected DListNode next;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  public DListNode(Move i, DListNode p, DListNode n) {
    item = i;
    prev = p;
    next = n;
  }
  public DListNode next() {
    return next;
  }
  public DListNode prev() {
    return prev;
  }
}