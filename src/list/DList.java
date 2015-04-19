/* DList.java */

package list;
import player.*;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Move item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    //  Your solution here.
    head = newNode(null, head, head);
    head.next=head;
    head.prev=head;
    size=0;
  }

  public DListNode getHead() {
    return this.head;
  }
  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Move item) {
    // Your solution here.
    DListNode new_Node=this.newNode(item, head, head.next);
    head.next=new_Node;
    new_Node.next.prev=new_Node;
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Move item) {
    // Your solution here.
    DListNode new_Node=this.newNode(item, head.prev, head);
    head.prev=new_Node;
    new_Node.prev.next=new_Node;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    // Your solution here.
    if (size==0) {
      return null;
    }
    else {
      return head.next;
    }
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    // Your solution here.
    if (size==0) {
      return null;
    }
    else {
      return head.prev;
    }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    // Your solution here.
    if ((node==null)||(node.next==head)) {
      return null;
    }
    else return head.next;
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    // Your solution here.
    if ((node==null)||(node.prev==head)) {
      return null;
    }
    else return head.prev;
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Move item, DListNode node) {
    // Your solution here.
    if (node!=null) {
      DListNode new_Node=this.newNode(item, node, node.next);
      node.next=new_Node;
      new_Node.next.prev=new_Node;
      size++;
    }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Move item, DListNode node) {
    // Your solution here.
    if (node!=null) {
      DListNode new_Node=this.newNode(item, node.prev, node);
      node.prev.next=new_Node;
      new_Node.next.prev=new_Node;
      size++;
    }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
    if (node!=null) {
      node.prev.next=node.next;
      node.next.prev=node.prev;
      size--;
    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

  public boolean inCol(DListNode a, DListNode b){
      if (a.item.x1 == b.item.x1){
        return true;
      }else{
        return false;
      }
    }
  public boolean inRow(DListNode a, DListNode b){
      if (a.item.y1 == b.item.y1){
        return true;
      }else{
        return false;
      }
    }
    
  public boolean inDiag(DListNode a, DListNode b){
    if(a.item.x1 != b.item.x1){
      int dif = b.item.x1 - a.item.x1;
      if (b.item.y1 == a.item.y1 + dif||b.item.y1 == a.item.y1 - dif){
        return true;
      }else{
        return false;
      }
    }
    return false;
  }
    
  public int direction(DListNode a, DListNode b){
    if (inDiag(a,b)){
      if (a.item.x1 < b.item.x1){
        if (a.item.y1 < b.item.y1){
          return 1;
        }else{
          return 2;
        }
      }else{
        if (a.item.y1 < b.item.y1){
          return 3;
        }else {
          return 4;
        }
      }
    }else{
      return 0;
    }
  }
  
  public boolean invalidLine(){
  DListNode cur = this.getHead().next().next();
    while(cur.next().item!=null){
      if (inRow(cur.prev(), cur)){
        if (inRow(cur, cur.next())){
          return true;
        }else{
          cur = cur.next();
        }
      }else if (inCol(cur.prev(), cur)){
          if (inCol(cur, cur.next())){
              return true;
            }else{
              cur = cur.next();
            }
      }else if (inDiag(cur.prev(), cur)){
        if (inDiag(cur, cur.next())){
          if (direction(cur.prev(), cur)==direction(cur,cur.next())){
            return true;
          }else{
            cur = cur.next(); 
          }
        }else{
          cur = cur.next();
        }
      }else{
        cur = cur.next();
      }
    }
    return false;
  }

  public void print(){
    DListNode cur = head.next;
    System.out.print("[");
    while (cur.item != null){
      System.out.print("("+cur.item.x1+", " + cur.item.y1 + ")");
      cur = cur.next;
    }
    System.out.print("]");
    System.out.println();
  }

  public DList clone(){
    DList newList = new DList();
    DListNode cur = this.front();
    while (cur.item!=null){
      newList.insertBack(cur.item);
      cur = cur.next;
    }
    return newList;
  }
  public static void main(String[] args) {
    DList list = new DList();
    list.insertBack(new Move(5,0));
    list.insertBack(new Move(5,1));
    list.insertBack(new Move(5,3));
    System.out.println(list.invalidLine());
  }
}
