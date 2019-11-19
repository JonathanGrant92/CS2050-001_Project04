
package BrendanBassettJonathanGrant_04;

/**
 * Student class declares basic information representing a single student.
 * Provides basic functionality overriding toString() and equals() functions
 * 
 * @author Brendan Bassett
 * @version 08/27/2019
 */



public class Student {
	
//	Declare instance variables

	private String id;			// ID
	private String firstName;	// First Name
	private String lastName;	// Last Name
	private String email;		// Email

//	***************************************************************************

	/** Student		Constructor for Student class which verifies that parameters
	 * 				received have proper formatting and adds= the new id to the 
	 * 				master list
	 * 
	 * @param	id			The unique id for the student
	 * @param	firstName	The student's first name
	 * @param	lastName	The student's last name
	 * @param	email		The student's email
	 * 
	 * @throws	IllegalArgumentException if a parameter is a blank string or 
	 * 			null. Or if the email does not contain '@' character. Or if the
	 * 			student already exists.
	 */
	
	public Student(String id, String firstName, String lastName, String email) throws IllegalArgumentException {
		
//		Verify that parameters given are not blank or null
//			and include required characters
		
		if(isInvalid(id)) {
			throw new IllegalArgumentException("The id cannot be blank.");
		}  // End if
		
		if(isInvalid(firstName)) {
			throw new IllegalArgumentException("The first name cannot be blank.");
		}  // End if
		
		if(isInvalid(lastName)) {
			throw new IllegalArgumentException("The last name cannot be blank.");
		} // End if
		
		if(isInvalid(email) || !email.contains("@")) {
			throw new IllegalArgumentException(
					"The email cannot be blank and must contain the \"@\" character.");
		} // End if

//		Attach parameters to instance fields
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	} // End constructor
	
//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/** isInvalid	Check whether a given string is "" or null
	 * 
	 * @param	string	The string to be checked
	 * 
	 * @return	Whether the string is "" or null
	 */
	
	/* Delete this comment!
	 * Swapped boolean values in return statements.
	 */
	private boolean isInvalid(String string) {
		if(string.equals("") || string == null) {
			return true; // String is blank
		}
		return false; // String is not blank
	} // End method

//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**	equals		Override the default equals method to provide more
	 * 				useful information; 
	 * 
	 * @param	student		The instance of Student class to be compared
	 * @return				Whether both instances contain equivalent data
	 */
	
	@Override
	public boolean equals(Object object) {
		
		Student student;	// Student to compare to this one.
		
		try {
			student = (Student) object;
			
//			Compare fields of two instances for equality.
			
			if (student != null && this.id.equals(student.getId())
				&& 	this.firstName.equals(student.getFirstName()) 
				&&	this.lastName.equals(student.getLastName())
				&& 	this.email.equals(student.getEmail())) {
				
				return true;	// Everything matches.
			} // End if
		} // End try
		catch(ClassCastException e) {
			System.out.println("The object being compared for equals() method "
					+ "is not a Student");
		} // End catch

		return false;		// Something didn't match.
	} // End method

//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**	toString 	Override the default toString method to provide more
	 * 				useful information.
	 * 
	 * @return		A multiple-line String representation of this instance
	 */
	
	public String toString() {
			return "Student:" 			+ 
					"\n id: " 			+ id + 
					"\n firstName: " 	+ firstName + 
					"\n lastName: " 	+ lastName + 
					"\n email: " 		+ email;
	} // End method

//	GETTERS
//	***************************************************************************

	public String getId() { return id; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }	
	public String getEmail() { return email; }
	
} // End class
