/* Dictionary.java */

package dict;

import list.*;

/**
 *  An interface for (unordered) dictionary ADTs.
 *
 *  DO NOT CHANGE THIS FILE.
 **/

public class Dictionary {
	int size;
	DList2 data;
	

	public Dictionary(){
		data = new DList2();
		size = data.length();
	}

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size(){
	  return data.length();
  } 

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty(){
	  return this.size()==0;
  }

  /** 
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public void insert(DList key, int value){
	  if (key.isEmpty() == false){
		  Entry newEntry = new Entry();
		  newEntry.key = key;
		  newEntry.value = value;
		  data.insertBack(newEntry);
	  }
  };

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  //public Entry find(Object key);

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

 // public Entry remove(Object key);

  /**
   *  Remove all entries from the dictionary.
   */

 // public void makeEmpty();

}
