package BrendanBassettJonathanGrant_04;

/**
 * The linked-list is an implementation of MyCollectionInterface. It is a linked
 * list collection.
 * 
 * @author JonathanGrant & BrendanBassett
 * @version 10/15/2019
 */

public class LList<T> implements MyCollectionInterfaceProject04<T> {

//	Declare Instance Variables	
	
	private int numberOfNodes;			// The number of nodes in a list.
	private Node<T> firstNode;			// The first node of the list.
	
//	***************************************************************************

   /**
    * Creates an instance of linked list with only one null node.
    */
	public LList() {
		
		numberOfNodes = 0;
		firstNode = null;
		
	} // End List constructor.
    
//***************************************************************************

	/**
     * Adds a new node to the front of the list and create a link.
     * 
     * @param	newEntry 	The object to be added as a node of the list.
     * @return 				True if the addition is successful, or false if not.
     */
	public boolean add(T newEntry) {
		
//		Local Variable Declarations
		
		boolean isAdded = false;					// True if node added.
		Node<T> newNode = new Node<T>(newEntry);	// A node of new entry.
		Node<T> nextNode;							// The next node of list.
		
//		Add the node to the back of the list.
		
		if (isEmpty()) {
			
			firstNode = newNode;
			isAdded = true;
			numberOfNodes++;
			
		} // End if.
		
		else {
			
			nextNode = firstNode;
			firstNode = newNode;
			firstNode.setNextNode(nextNode);
			isAdded = true;
			numberOfNodes++;
			
		} // End else.
		
		return isAdded;
		
	} // End add method with one argument.
	    
//	***************************************************************************

	/**
     * Adds a new node to the given position of the list and resets the
     * neighboring links.
     * 
     * @param 	newEntry 	The object to be added as a node of the list.
     * @param 	position 	The location where the new entry will be inserted.
     * @return 				True if the addition is successful, or false if not.
     */
    public boolean add(T newEntry, int position) {
    	
//		Local Variable Declarations
    	
		boolean isAdded = false;
		Node<T> newNode = new Node<T>(newEntry);
		Node<T> nodeBefore = getNodeAt(position - 1);
		Node<T> nodeAfter = nodeBefore.getNextNode();
		
//		Add the node to the list if the position is not out of bounds.
		
		if ( position > 0 || position < getCurrentSize() ) {
			
			if (firstNode == null) {
			
				System.out.println("Position is invalid because list is empty,"
						+ " so the new node is the first node.");
				firstNode = newNode;
				isAdded = true;
				numberOfNodes++;
			
			} // End if.
			
			else {
		
				System.out.println("Given position " + position + " is out of bounds.");
			
			} // End else.
			
		} // End if.
		
		else {
			
			nodeBefore = getNodeAt( position - 1 );
			nodeAfter = nodeBefore.getNextNode();
			nodeBefore.setNextNode(newNode);
			
	//		Set the next node of node added if the next node has data.
			
			if ( nodeAfter != null ) {
				
				newNode.setNextNode(nodeAfter);
				
			} // End if.
			
			isAdded = true;
			numberOfNodes++;
			
		} // End else.
		
		return isAdded;
		
	} // End add method with two arguments.
	    
//	***************************************************************************
	    
    /**
     * Removes one occurrence of a given node from the list and resets the
     * neighboring links.
     *
     * @param 	anEntry 	The equivalent node to be removed from the list.
     * @return 				True if the removal was successful, or false if not.
     */
    public boolean remove(T anEntry) {
    	
//		Local Variable Declarations
    	
    	Node<T> obsoleteNode = new Node<T>(anEntry); // Node to remove from list.
    	Node<T> comparedNode = firstNode;			 // Node to compare agaisnt entry.
    	Node<T> nodeBefore;							 // The node before compared node.
    	Node<T> nodeAfter;							 // The node after compared node.
    	
//		Search the list for the first occurence of node equivalent to entry.
    	
    	for (int i = 1; i < numberOfNodes; i++) {
    		
    //		Remove the node from list if found.
    		
    		if (obsoleteNode.equals(comparedNode)) {
    			
    			nodeBefore = getNodeAt(i);
    			nodeAfter = comparedNode.getNextNode();
    			nodeBefore.setNextNode(nodeAfter);
    			comparedNode = null;
    			numberOfNodes--;
    			
    			return true;
    			
    		} // End if.
    		
    		comparedNode = comparedNode.getNextNode();
    		
    	} // End for.
    	
		return false;
		
	} // End remove method.
	    
//	***************************************************************************
	    
    /**
     * Removes all nodes from the list.
     */
    public void clear() {
    	
//		Remove all nodes from the list.
    	
    	while (numberOfNodes > 0) {
    		
    		getNodeAt(numberOfNodes--).setData(null);
    		
    	} // End while.
    	
	} // End clear method.
    
//	***************************************************************************
    
    /**
     * 
     * @param 	position	The location of the node in the list.
     * @return				The node at the given position in the list.
     */
    private Node<T> getNodeAt(int position) {
    	
//		Local Variable Declarations
    	
	    Node<T> currentNode = firstNode;	// The node of position to be found.
	    
//		Search the list for the node at the given position.
	    
	    for (int i = 1; i < position; i++) {
	    	
	    	currentNode = currentNode.getNextNode();
	    	
	    } // End for.
	    
		return currentNode;
		
	} // End getNodeAt method.
    
//	***************************************************************************
    
    /**
     * Gets the current number of nodes in the list.
     *
     * @return 		The number of nodes currently in the list.
     */
    public int getCurrentSize() {
    	
		return numberOfNodes;
		
	} // End getCurrentSize method.
    
//	***************************************************************************
    
    /**
     * Check to see if the list is empty.
     *
     * @return 		True if the list is empty, or false if not.
     */
    public boolean isEmpty() {
    	
//		Local Variable Declarations
    	
    	boolean isEmpty = false;		// True if the list has no nodes.
    	
//		Find if there are no nodes in the list.
    	
    	if (numberOfNodes == 0) {
    		
    		isEmpty = true;
    		
    	} // End if.
    	
		return isEmpty;
		
	} // End isEmpty method.
    
//	***************************************************************************
    
    /**
     * Counts the number of times a given node appears in the list.
     *
     * @param 	anEntry		The entry to be compared against nodes of the list.
     * @return 				The number of equivalent nodes in the list.
     */
    public int getFrequencyOf(T anEntry) {
    	
//		Local Variable Declarations
    	
    	int numberOfEntries = 0;					// The number of an entry in the list.
    	Node<T> currentNode = new Node<T>(anEntry); // The node to find in the list.
    	
//		Search the list for all instances of a specific node and keep count.
    	
    	for (int i = 1; i <= numberOfNodes; i++) {
    		if (currentNode.equals(getNodeAt(i))) {
    			
    			numberOfEntries++;
    			
    		} // End if.
    	} // End for.
    	
		return numberOfEntries;
		
	} // End getFrequencyOf method.
    
//	***************************************************************************
    
    /**
     * Tests whether this list contains a node equivalent to the given entry.
     *
     * @param 	anEntry		The entry to compare against nodes of the list.
     * @return 				True only if the list contains an equivalent node.
     */
    public boolean contains(T anEntry) {
    	
    	
//		Local Variable Declarations
    	boolean entryFound = false;			// True if the entry is in the list.
    	Node<T> currentNode = firstNode;	// The list node compared against the entry.
    		
//		Search the list for all equivalent nodes of the given entry.
    	
		while (currentNode != null) {
				
			if (anEntry.equals(currentNode.getData())) {
				
				entryFound = true;
				break;
				
			} // End if.
			
			currentNode = currentNode.getNextNode();
				
		} // End while.
    	
		return entryFound;
		
	} // End contains method.
    
//	***************************************************************************
    
    /**
     * Retrieves all nodes in the list for an array of objects.
     *
     * @return	A newly allocated array of all the nodes in the list. 
     * 			Note: If the list is empty, the array is empty.
     */
    public Object[] toArray() {
    	
//		Local Variable Declaration
    	
    	Node<T> currentNode = null;						  // Node to add to array.
    	Object[] returnArray = new Object[numberOfNodes]; // The array of nodes.
		
//		Search the list of entries and add each entry to the new array.
		
		for (int i = 1; i <= numberOfNodes; i++) {
			
			currentNode = getNodeAt(i);
			
			returnArray[numberOfNodes - i] = currentNode.getData();
			
		} // End for
		
		return returnArray;
		
	} // End toArray method.

} // End List class.
