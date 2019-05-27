package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken = null; 
	private HashMap<String,Integer> semestersByYearAndSemester;
	
	public Student(String studentId) {
		this.studentId = studentId;
	}
	public void addCourse(Course newRecord) {
		if(coursesTaken == null) {
		coursesTaken = new ArrayList<Course>();
		coursesTaken.add(newRecord);
		}
		else
		{
			coursesTaken.add(newRecord);
		}
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
			
		}
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseInNthSemester(int semester) {
		int count = 0;		
		HashMap<String,Integer> semesterNum = getSemestersByYearAndSemester();
		String result = null;
		String keyCheck = null;
		for(String key : semesterNum.keySet())
		{
			if(semester == semesterNum.get(key)) {
				result = key;
			}
		}
		for(Course c : coursesTaken) {
			keyCheck = c.getYearTaken() + "-" + c.getSemesterCourseTaken();
			if(keyCheck.equals(result))
			{
				count++;
			}	
		}
		return count;
	}
}
