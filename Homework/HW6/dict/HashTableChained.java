/* HashTableChained.java */
package dict;

import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  public int numBucket; // num of buckets
  public int numEntry; // num of entries
  private SList[] hashTable;
  private int collisions;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/ // load factor = numEntry / numBucket

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    // sizeEstimate <= numBucket <= 2 * sizeEstimate (Because we set load factor between 0.5 and 1)
    // check the number begin with sizeEstimate (and add one continually until it is prime number)
    while (! isPrime(sizeEstimate)) {
      sizeEstimate++;
    }
    numBucket = sizeEstimate;
    numEntry = 0;
    collisions = 0;
    hashTable = new SList[numBucket]; 
    // https://stackoverflow.com/questions/5364278/creating-an-array-of-objects-in-java
    for (int i = 0; i < numBucket; i++) {
      hashTable[i] = new SList(); 
    }
    
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(100);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution. 
    return java.lang.Math.abs(code) % numBucket; // N is the number of buckets
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    int size = 0;
    for (List bucket : hashTable) {
      size = size + bucket.length();
    }
    return size;
  }


  private static boolean isPrime(int num) {
    if (num <= 1) 
      return false;
    for (int i = 2; i <= java.lang.Math.sqrt(num); i++) {
      if (num % i == 0)
        return false;
    }
    return true;
  }

  public double calExpCollision() { // n:key, N: buckets
    int n = numEntry;
    int N = numBucket;
    return n - N + N * java.lang.Math.pow((1 - 1.0 / N), n); 
  }

  public int actualCollision() {
    return collisions;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    for (int i = 0; i < numBucket; i++) {
      if (hashTable[i] != null)
        return false;
    }
    return true;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry entry = new Entry();
    entry.key = key;
    entry.value = value;
    if (hashTable[compFunction(key.hashCode())].length() > 0)
      collisions++;
    hashTable[compFunction(key.hashCode())].insertBack(entry); // Object.hashCode()
    numEntry++;
    return entry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    ListNode listNode = hashTable[compFunction(key.hashCode())].front();
    while (listNode.isValidNode()) { 
      try {
        Entry entry = (Entry)listNode.item();
        if (entry.key().equals(key)) 
          return entry;
        listNode = listNode.next(); // have to catch the exception from method next()
      } catch(InvalidNodeException e) { // remember to catch the exception
        System.err.println(e);
      }
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    ListNode listNode = hashTable[compFunction(key.hashCode())].front();
    while (listNode.isValidNode()) {
      try {
        Entry entry = (Entry)listNode.item();
        if (entry.key().equals(key)) {
          listNode.remove();
          numEntry--;
          return entry;
        }
        listNode = listNode.next(); // have to catch the exception from method next()
      } catch(InvalidNodeException e) { // remember to catch the exception
        System.err.println(e);
      }
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    numEntry = 0;
    for (int i = 0; i < numBucket; i++) {
      hashTable[i] = new SList(); 
    }
  }
}
