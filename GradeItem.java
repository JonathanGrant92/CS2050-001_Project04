/**
 * GradeItem class declares basic information representing a single graded item
 * 
 * 
 * @author Brendan Bassett
 * @version 08/27/2019
 */

package BrendanBassettJonathanGrant_04;

public class GradeItem {

//	Declare static variables and methods
	
	static private final String[] TYPE_LIST = 
		{"HW", "Quiz", "Class Work", "Test", "Final"};

//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** matchTypeOptions	Verify whether the type provided matches one 
	 * 						of the options
	 * 
	 * @param	type	The string to be verified against the option list
	 * @return	boolean	Whether the string matches one of the represented types
	*/
	
	static boolean matchTypeOptions(String type) {
		for (String listType : TYPE_LIST) {			// Search through list
			if (listType.equals(type)) {
				return true; // Type found, end method
			} // End if
		} // End for
		return false;	// No match found
	} // End method

//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
//	Declare instance variables
	
	private String	studentId;		// Student ID
	private int		gradeItemId;	// Grade Item ID
	private String	courseId;		// Course ID
	private String	type;			// Type
	private String	date;			// Date
	private int		maximumScore;	// Maximum Score
	private int		actualScore;	// Actual Score

//	***************************************************************************

	/** GradeItem	Constructor for GradeItem class which verifies that 
	 * 				parameters received have proper formatting, verifies that
	 * 				the student ID matches one on the master student list, and
	 * 				verifies that the type indicated matches on of the options.
	 * 
	 * @param	studentId		The unique id of the student
	 * @param	gradeItemId		The unique id of the GradeItem
	 * @param	courseId		The unique id of the course
	 * @param	type			The type of grade item
	 * @param	date			The date the item was created
	 * @param	maximumScore	The maximum score for the item
	 * @param	actualScore		The actual score for the item
	 * 
	 * @throws	IllegalArgumentException if a string parameter is a blank 
	 * 			string or null. If the maximum score is < 0. If the actual 
	 * 			score is < 0 or > the maximum score. If the gradeItemId cannot 
	 * 			be converted to an int, or if it is < 0. Or if the type does 
	 * 			not match one of the options defined. Or if the student ID does
	 * 			not correspond to an student on file.
	*/
	/* Delete this comment!
	 * Swapped gradeItemId and studentId so object is correctly constructed.
	 */
	public GradeItem(	String 	gradeItemId, 
						String	studentId, 
						String	courseId, 
						String 	type,
						String	date, 						 
						int 	maximumScore,
						int		actualScore) throws IllegalArgumentException {
		
//		Verify that String parameters given are not blank or null
		
		if(studentId.equals("") || studentId == null) {
			throw new IllegalArgumentException("The Student ID is blank");
		} // End if
		
		if(gradeItemId.equals("") || gradeItemId == null) {
			throw new IllegalArgumentException("The Grade Item ID is blank");
		} // End if
		
		if(courseId.equals("") || courseId == null) {
			throw new IllegalArgumentException("The Course ID is blank");
		} // End if
		
		if(type.equals("") || type == null) {
			throw new IllegalArgumentException("The Grade Item Type is blank");
		} // End if
		
		if(date.equals("") || date == null) {
			throw new IllegalArgumentException("The Date is blank");
		} // End if

//		Verify that gradeItemId and scores are all within their range
		
		if(Integer.parseInt(gradeItemId) <= 0) {
			throw new IllegalArgumentException(
					"The Grade Item ID must be greater than zero.");
		} // End if
		
		if(actualScore < 0 || actualScore > maximumScore){
			throw new IllegalArgumentException(
					"The Actual Score must range from zero to maximum score.");
		} // End if
		
		if(maximumScore <= 0) {
			throw new IllegalArgumentException(
					"The Maximum Score must be greater than zero.");
		} // End if

//		Verify that the type given matches one of the options from the list
		
		if(!matchTypeOptions(type)) {
			throw new IllegalArgumentException(
				"The Grade item type must match one of the following"
				+ "(case-sensitive): \n "
				+ "\"HW\", \"Quiz\", \"Class Work\", \"Test\", \"Final\"");
		} // End if
		
//		Attach parameters to instance fields
		this.studentId = studentId;
		this.gradeItemId = Integer.parseInt(gradeItemId);
		this.courseId = courseId;
		this.type = type;
		this.date = date;
		this.maximumScore = maximumScore;
		this.actualScore = actualScore;
	} // End constructor

//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**	equals		Override the default equals method to provide more
	 * 				useful information
	 * 
	 * @param	gradeItem	The instance of GradeItem class to be compared
	 * @return	boolean		Whether both instances contain equivalent data
	 */
	
	@Override
	public boolean equals(Object object) {
		
		GradeItem gradeItem;	// Grade entry to compare to this one.
		
		try {
			gradeItem = (GradeItem)object;
			
//			Compare fields of two instances for equality.

			if(gradeItem != null && this.gradeItemId == gradeItem.getGradeItemId()
					&&	this.studentId.equals(gradeItem.getStudentId())
					&& 	this.courseId.equals(gradeItem.getCourseId()) 
					&& 	this.type.equals(gradeItem.getType())
					&& 	this.date.equals(gradeItem.getDate())
					&& 	this.maximumScore == gradeItem.getMaximumScore()
					&& this.actualScore == gradeItem.getActualScore()) {
			
				return true;	// Everything matches.
			} // End if
			
		} // End try
		catch(ClassCastException e) {
			System.out.println("The object being compared for equals() method "
					+ "is not a GradeItem");
		} // End catch
		
//		Compare fields from this instance with that of the instance provided.
		
		return false;		// Something didn't match.
	} // End method

//	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**	toString 	Override the default toString method to provide more
	 * 				useful information
	 * 
	 * @return	String	A multiple-line String representation of this instance
	 */
	
	public String toString() {
			return "GradeItem:" 		+ 
					"\n actualScore: " 	+ actualScore + 
					"\n courseId: " 	+ courseId +
					"\n date: " 		+ date + 
					"\n gradeItemId: " 	+ gradeItemId + 
					"\n maximumScore: " + maximumScore +
					"\n studentId: " 	+ studentId + 
					"\n type: " 		+ type;
	} // End method

//	GETTERS
//	***************************************************************************

	public int getActualScore() { return actualScore; }
	public String getCourseId() { return courseId; }
	public String getDate() { return date; }
	public int getGradeItemId() { return gradeItemId; }
	public int getMaximumScore() { return maximumScore; }
	public String getStudentId() { return studentId; }
	public String getType() { return type; }

} // End class
