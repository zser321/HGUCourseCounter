package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken; 
	private HashMap<String,Integer> semestersByYearAndSemester;
	
	public Student(String studentId) {
		this.studentId = studentId;
	}
	public void addCourse(Course newRecord) {
		coursesTaken = new ArrayList<Course>();
		coursesTaken.add(newRecord);
	}
	
	public String getStudentId() {
		return studentId;
	}
	public HashMap<String,Integer> getSemestersByYearAndSemester(){
		int i;
		int num = 0;
		semestersByYearAndSemester = new HashMap<String,Integer>();
		for(Course c : coursesTaken) {
			String keyCheck;
			keyCheck = c.getYearTaken() + "-" + c.getSemesterCourseTaken();
			
			if(!semestersByYearAndSemester.containsKey(keyCheck))
			{
				num++;
				semestersByYearAndSemester.put(keyCheck, num);
			}
			System.out.println(keyCheck);
			System.out.println(num);
		}
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseInNthSemester(int semester) {
		int count = 0;		
		HashMap<String,Integer> semesterNum = getSemestersByYearAndSemester();
		
		for(String key : semesterNum.keySet()) {
			if(semesterNum.get(key) == semester )
			{
				count++;
			}
		}
		return count;
	}
}