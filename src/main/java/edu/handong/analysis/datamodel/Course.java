package edu.handong.analysis.datamodel;

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

	private int semesterCourseTaken;

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
	
}
