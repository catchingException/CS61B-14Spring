/* LockDList.java */
package list;
public class LockDList extends DList {

	public LockDList() {
	    super();
  	}

	public void lockNode(DListNode node) {
		if (node instanceof LockDListNode) {
			LockDListNode lockNode = (LockDListNode)node;
			lockNode.isLocked = true;
		}
			
	}

	@Override
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    	return new LockDListNode(item, prev, next);
 	}

	@Override
	public void remove(DListNode node) {
		if (node instanceof LockDListNode) {
			LockDListNode lockNode = (LockDListNode)node;
			if (! lockNode.isLocked)
    			super.remove(node);
		}
   		
	}

	// add for testing
	public LockDListNode front() {
		return (LockDListNode)(super.front());
	} 

	// add for testing
	public LockDListNode back() {
		return (LockDListNode)(super.back());
	}

}