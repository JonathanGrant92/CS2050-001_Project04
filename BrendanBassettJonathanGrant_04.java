
package BrendanBassettJonathanGrant_04;

/*
 * ComputerScience_02_01
 * Write the student and gradeItem classes to track students and their grades 
 * for all enrolled courses at MSU Denver. 
 * JonathanGrant & BrendanBassett
 * UniversityStudentTracking Program_04
 * Windows 10 Eclipse IDE JRE 1.8
 * 
 * Interlude: a temporary amusement or source of entertainment that contrasts with 
 * 				what goes before or after.
 * 
 * "Computer Science is no more about computers than astronomy is about
 *  telescopes."
 * Edsger W. Dijkstra, born March 11, 1930. died August 6, 2002.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * BrendanBassettJonathanGrant_04 executes the University Students and Grades 
 * Program, using methods that create instance of classes Student, GradeItem 
 * and List.
 * 
 * @author JonathanGrant & BrendanBassett
 * @version 10/02/2019
 */

public class BrendanBassettJonathanGrant_04 {
	
// 	Declare variables.
	
	static private String fileInputName;	// The input file name as a string.
	static private String fileOutputName;	// The output file name as a string.
	
//	Declare constants.
	
//	The input and output file name formats as strings.
	
	static private final String INPUT_FILE = "Project_04_InputXX"
											 + ".txt";
	static private final String OUTPUT_FILE = "Project_04_OutputXX"
											  + ".txt";
	
//	Declare fields.
	
//	The lists of Students and GradeItems to be manipulated using a file.
	
	static private LList<Student> listOfStudents;
	static private LList<GradeItem> listOfGradeItems;

//	***************************************************************************

	/**
	 * Main execution of program with tests.
	 * 
	 * @param								args
	 * @throws	FileNotFoundException		If file does not exist.
	 */

	static public void main(String args[]) {
//		Declare Variables
		int numberOfInputFiles;				// The number of files to parse.
		String fileNumberXX = "01";			// The default file number.
		
//		Instantiate lists.
		
		listOfStudents = new LList<Student>();
		listOfGradeItems = new LList<GradeItem>();
		
//		Create and print a program header.
		
		System.out.println("The University Students And Grades Program"
							+ "\n*********************************************"
							+ "***********************************\n");
		
//		Prompt for number of files to parse.
		
		System.out.println(
				"How many Project_04_InputXX text files need parsing: ");
		
		Scanner scannedObject = new Scanner(System.in);
		
		numberOfInputFiles = scannedObject.nextInt();
		
		scannedObject.close();
		
//		Process the input file and generate a report afterwards.
		
		for (int i = 1; i <= numberOfInputFiles; i++) {
			
	//		Parse the file for processing.
			fileNumberXX = "0" + Integer.toString(i);
			fileInputName = INPUT_FILE.replace("XX", fileNumberXX);
			fileOutputName = OUTPUT_FILE.replace("XX", fileNumberXX);
			
	//		Process the file and generate corresponding report.
			processInput();
			
	// 		Only generate the report if the list of students has students.
			if (!(listOfStudents.isEmpty())) {
				generateReport();
			} // End if. 
			else {
				System.out.println("\nThere are no students in the list or the "
						+ "list is not implemented yet.\n");
			} // End else.
			
	//		Reset the lists to an empty state.
			listOfStudents.clear();
			listOfGradeItems.clear();
		} // End for.
		
	} // End main() method.

//	***************************************************************************

	/**
	 * Reads a file and creates new Student or GradeItems according to the
	 * data contained in each line of text.
	 * 
	 * @param fileName A string representing the file name
	 * @throws FileNotFoundException If the file does not exist
	 */

	static public void processInput() {
		
//		Declare Variables.
		
		File textFile;		// The file as a File object.
		Scanner scanner;	// The file as a Scanner.
		
// 		Load a text file for program to read.

		textFile = new File(fileInputName);

//		Print an error message if file does not exist or is not a valid.
		
		if (!textFile.exists() || !textFile.isFile()) {
			System.out.println("\nNo file found named: " + fileInputName);
		} // End if

// 		Return the file's "in" stream as a scanner variable. Then 
//		read each line separately.

		try {
			scanner = new Scanner(textFile);
			System.out.println("\nReading data from file: " + fileInputName);
			
			while (scanner.hasNextLine()) {
				
//				Get a single line as an array of strings divided by "," 
//				characters.
				
				String[] fileLine = scanner.nextLine().split(",");
				
//				Skip the line if it is blank or contains commenting characters.
				
				if (fileLine[0].contentEquals("//")
						|| fileLine[0].contentEquals("")
						|| fileLine[0] == null) {
					continue;
				} // End if
				
//				Parse the line if it indicates it is a student to be added.
				
				if (fileLine[0].contentEquals("STUDENT")) {
					processStudentData(fileLine);
				} // End if

//				Parse the line if it indicates it is a grade item to be 
//				added.
				
				if (fileLine[0].contentEquals("GRADE ITEM")) {
					processGradeItemData(fileLine);
				} // End if
				
			} // End while

//			File parsing finished. Close the scanner.
			
			scanner.close();

		} // End try
		
		catch (FileNotFoundException e) {
			System.err.print(e.getMessage());
		} // End catch
		
	} // End parseFile() method.

//	***************************************************************************

	/**
	 * Creates a new Student from a single array of data from a single line 
	 * in a text file. Only a maximum of two students can be parsed.
	 * 
	 * @param	data		A string array with data of the Student to be parsed.
	 * @throws	Exception	If the data is invalid and the Student could not be
	 *                   	created. Or if more than two students are created.
	 */

	static public void processStudentData(String[] data) {

//		Declare Variables.

		String id = data[2];		// Id for new Student.
		String firstName = data[3]; // First Name for new Student.
		String lastName = data[4];	// Last Name for new Student.
		String email = data[5];		// Email for new Student.
		
		boolean isValid = true;	

//		Create new Student with parsed data.
		
		try {
			Student newStudent = new Student(id, firstName, lastName, email);
			
//			Determine whether to add or remove the student.
			
			if (data[1].contentEquals("ADD")) {
				
//				If the list already contains the student, print an error message. 
//				Otherwise add the new student to the list.
				
				if (listOfStudents.contains(newStudent)) {
					System.out.println("Student with Student Id "
							+ id + " is already in the list.");
				
				} // End if
				else {
					isValid = listOfStudents.add(newStudent);
					
//					Make sure the student was properly added.
					
					if (!isValid) {
						System.out.println("Student with Student Id " + id
								+ " is not in the list.");
					} // End if
					else {
						System.out.println("Student with Student Id "
								+ id + " was added to the list.");
					} // End else
					
				} // End else
				
			} // End if
			else if (data[1].contentEquals("DEL")) {
				
//				If the file indicates a delete function, try to remove the 
//				student from the list.
				
				isValid = listOfStudents.remove(newStudent);
				
				if (!isValid) {
					System.out.println("Student with Student Id " + id
							+ " is not in the list.");
				} // End if
				else {
					System.out.println("Student with Student Id "
							+ id + " was removed from the list.");
				} // End else
				
			} // End else if
			else {
				System.out.println("The file student does not indicate to "
						+ "either add or delete the Student.");
			} // End else

		} // End try
		catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());
			System.out.println("Student was not added to the Students list.");
		} // End catch
		
	} // End processStudentData method.

//	***************************************************************************

	/**
	 * Creates a new GradeItem from a single array of data from a single line 
	 * in a text file. Only a maximum of two GradeItems can be parsed.
	 * 
	 * @param	data		A string array with data of the GradeItem to be parsed.
	 * @throws	Exception	If the data is invalid and the GradeItem could not be
	 *                  	created. Or if more than two GradeItems are created.
	 */

	static public void processGradeItemData(String[] data) {

//		Declare Variables

		String gradeItemId = data[2]; // Grade Item Id for new GradeItem.
		String studentId = data[3]; // Student Id for new GradeItem.
		String courseId = data[4]; // Course Id for new GradeItem.
		String type = data[5]; // Type for new GradeItem.
		String date = data[6]; // Date for new GradeItem.
		int maximumScore = Integer.parseInt(data[7]); // Maximum Score for GI.
		int actualScore = Integer.parseInt(data[8]); // Actual Score for GI.

//		Create new GradeItem with parsed data.

		try {
			GradeItem newGradeItem = new GradeItem(gradeItemId, studentId, 
					courseId, type, date, maximumScore, actualScore);
	
			boolean isValid = true;	
	
//			Determine whether to add or remove the grade item.
			
			if (data[1].contentEquals("ADD")) {
				
//				If the list already contains the grade item, print an error 
//				message. Otherwise add the new grade item to the list.
				
				if (listOfGradeItems.contains(newGradeItem)) {
					System.out.println("The Grade Item is already in the list."
							+ "\n Student Id " + studentId 
							+ "\n Grade Item Id " + gradeItemId);
				} // End if
				else {
					isValid = listOfGradeItems.add(newGradeItem);
					
//					Make sure the Grade Item was properly added.
					
					if(!isValid) {
						System.out.println("Grade Item with Grade Item Id "
								+ gradeItemId + " and Student Id " + studentId 
								+ " is not in the list.");
					} // End if
					else {
						System.out.println("Grade Item with Grade Item Id "
								+ gradeItemId + " and Student Id " + studentId 
								+  " was added to the list.");
					} // End else
					
				} // End else
				
			} // End if
			else if (data[1].contentEquals("DEL")) {
				
//				If the file indicates a delete function, try to remove the grade 
//				item from the list.
				
				isValid = listOfGradeItems.remove(newGradeItem);
				
				if (!isValid) {
					System.out.println("Grade Item with Grade Item Id "
							+ gradeItemId + " and Student Id " + studentId 
							+ " is not in the list.");
				} // End if
				else {
					System.out.println("Grade Item with Grade Item Id "
							+ gradeItemId + " and Student Id " + studentId 
							+  " was removed from the list.");
				} // End else
				
			} // End else if
			else {
				System.out.println("The file Grade Item does not indicate to "
						+ "either add or delete the Grade Item.");
			} // End else

		} // End try
		catch (IllegalArgumentException e) {
			System.err.print("Error: "+ e.getMessage());
			System.out.println("Grade Item was not added to the Grade Items list.");
		} // End catch
		
	} // End processGradeItemData method.

//	***************************************************************************

	/**
	 * Find the grade items of each student on record by id. Generate report of 
	 * students and their grade items. Print the report and save it to a file.
	 * 
	 * @throws	IOException If the file is not found, successfully created, 
	 * 						or written to and closed.
	 */
	
	static public void generateReport() {
		
//		Declare Variables
		
		int sumOfMaxScore;				// The total max score of a student.
		int sumOfActualScore;			// The student's total score earned.
		double meanGrade;				// The student's overall average grade.
		
		Student student;				// The student being worked on.
		GradeItem gradeItem;			// The grade item being worked on.
		
		String reportBuffer;			// The report to print and write to file.
		Object[] studentArray;			// The array of Student list.
		Object[] gradeItemArray;		// The array of Grade Items list.
		FileWriter fileWriter = null;	// The record of students' grades.
		
		
		
//		Inform client that program is generating a report.
		System.out.print("Generating report to file " + fileOutputName + "... ");
		
//		Create the report header and table headers.
		
		reportBuffer =  "Record of Students' Grades Report\n";
		
//		Cast the lists of objects to arrays for package array methods.
		
		studentArray = listOfStudents.toArray();
		gradeItemArray = listOfGradeItems.toArray();
		
		for (Object studentObject: studentArray) {
			student = (Student)studentObject;
			
			sumOfMaxScore = 0;		// Reset max score total for next student.
			sumOfActualScore = 0;	// Reset score total for next student.
			meanGrade = 0;			// Reset course grade for next student.
			
//			Write the next student's data to the report .
			
			reportBuffer += "\n" 
							+ student.getId() 
							+ "\t"
							+ student.getFirstName() 
							+ "\t"
							+ student.getLastName() 
							+ "\t"
							+ student.getEmail()
							+ "\n\tGradeItems";
			
			for (Object gradeItemObject: gradeItemArray) {
				gradeItem = (GradeItem)gradeItemObject;
				
//				Record if the grade item student id matches student's id.
				
				if (student.getId().equals(gradeItem.getStudentId())) {
					
// 					Sum the scores earned and potential points for grade items.
					
					sumOfActualScore += gradeItem.getActualScore();
					sumOfMaxScore += gradeItem.getMaximumScore();
					
//					Write the student's current grade item to report.
					
					reportBuffer += "\n\t"
							+ gradeItem.getGradeItemId()
							+ "\t" 
							+ gradeItem.getCourseId()
							+ "\t" 
							+ gradeItem.getType()
							+ "\t\t" 
							+ gradeItem.getDate()
							+ "\t" 
							+ gradeItem.getMaximumScore() 
							+ "\t\t" 
							+ gradeItem.getActualScore();
				} // End if
			} // End for
			
//	 		If there are grade items for the student, the sumOfMaxScore will be 
//			more than 0.
			
			if (sumOfMaxScore > 0) {
					
//		 			Calculate the mean grade, round to two decimal places
//					and take the percentage.
				
					meanGrade = sumOfActualScore / ((double) sumOfMaxScore);
					meanGrade = Math.round(meanGrade * 10000) / 100.00;
					
//					Write the student's total scores and grade to report.
					
					reportBuffer += "\n"
									+ "======================================="
									+ "=================="
									+ "\nTotal"
									+ "\t\t\t\t\t\t\t\t"
									+ sumOfMaxScore
									+ "\t\t" 
									+ sumOfActualScore 
									+ "\t\t" 
									+ meanGrade 
									+ "%\n";
					
//			The sumOfMaxScore is 0, so there are no grade items for the student.
			
			} // End if
			else {
					reportBuffer += "\n\tThere are no grade items for this "
							+ "student.\n"
							+ "======================================="
							+ "==================\n";
			} // End else
		}
	
		
//		Attempt to find pre-existing file or create new one of specified name,
//		and write the report.
		
		try {
			fileWriter = new FileWriter(fileOutputName);
			fileWriter.write(reportBuffer);
			fileWriter.close();
		} // End try.
		
		catch (IOException e) {
			System.err.println(e.getMessage());
		}  // End catch.
				
//		Print the report of students' grades.
		
		System.out.print("done.\n");	
		
	} // End generateReport method.

} // End BrendanBassettJonathanGrant_02 class.