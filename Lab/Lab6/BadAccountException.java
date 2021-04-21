/* BadAccountException.java */

/**
 *  Implements an exception that should be thrown for nonexistent accounts.
 **/
public class BadAccountException extends Exception {
	// This class does not have any methods except the default constrctor. There's no need!
	// The only purpose of class BadAccountException is to be e distinguishable from other types of exception.
  public int accountNumber;  // The invalid account number.

  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadAccountException(int badAcctNumber) {
    super("Invalid account number: " + badAcctNumber);

    accountNumber = badAcctNumber;
  }
}
