package edu.handong.analysis.datamodel;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Course {
	private String studentId;
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
	public int getYearTaken() {
		return yearTaken;
	}

	public void setYearTaken(int yearTaken) {
		this.yearTaken = yearTaken;
	}

	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}

	public void setSemesterCourseTaken(int semesterCourseTaken) {
		this.semesterCourseTaken = semesterCourseTaken;
	}


	public Course(String line) {
		
		String[] data = line.split(",");

		
		
		setStudentId(data[0].trim());
		yearMonthGraduated = data[1].trim();
		firstMajor = data[2].trim();
		secondMajor = data[3].trim();
		courseCode = data[4].trim();
		courseName = data[5].trim();
		courseCredit = data[6].trim();
		setYearTaken(Integer.parseInt(data[7].trim()));
		setSemesterCourseTaken(Integer.parseInt(data[8].trim()));
		
	}
	public Course(CSVRecord line) {
		
		setStudentId(line.get(0));
		yearMonthGraduated = line.get(1);
		firstMajor = line.get(2);
		secondMajor = line.get(3);
		courseCode = line.get(4);
		courseName = line.get(5);
		courseCredit = line.get(6);
		setYearTaken(Integer.parseInt(line.get(7)));
		setSemesterCourseTaken(Integer.parseInt(line.get(8)));
		
	}
	
}
