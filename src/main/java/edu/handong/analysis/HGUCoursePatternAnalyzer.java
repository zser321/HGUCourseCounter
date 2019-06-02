package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	ArrayList<Course> courseStore = new ArrayList<Course>();
	
	boolean help;
	String input;
	String output;
	int analysis;
	String coursecode;
	int startyear;
	int endyear;
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 * @throws IOException 
	 */
	public void run(String[] args) throws IOException {
		

		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0); 
		}
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			if(analysis == 1) {
				try (
			            Reader reader = Files.newBufferedReader(Paths.get(input));
			            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
			                    .withFirstRecordAsHeader()
			                    .withIgnoreHeaderCase()
			                    .withTrim());
			        ) {
			            
			            	students = loadStudentCourseRecords(csvParser);
			            
				 }
				// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
				Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
				
				// Generate result lines to be saved.
				ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
				
				// Write a file (named like the value of resultPath) with linesTobeSaved.
				Utils.writeAFile(linesToBeSaved, output);
			}
			else if (analysis == 2) {
				try (
			            Reader reader = Files.newBufferedReader(Paths.get(input));
			            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
			                    .withFirstRecordAsHeader()
			                    .withIgnoreHeaderCase()
			                    .withTrim());
			        ) {
						ArrayList<String> linesToBeSaved = new ArrayList<String>();
						ArrayList<String> result = new ArrayList<String>();
						int totalStudents = 0;
						int studentsTaken = 0;
						String checkYearAndSem;
						double rate = 0;
						result.add("Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate");
						for (CSVRecord csvRecord : csvParser) {			
							//System.out.println(csvRecord.get(4));
						if(startyear <= Integer.parseInt(csvRecord.get(7)) && 
								Integer.parseInt(csvRecord.get(7)) <= endyear)
							{
							linesToBeSaved.add(csvRecord.get(7) + "," + csvRecord.get(8) + "," + csvRecord.get(4)
							+ "," + csvRecord.get(5));
								if(csvRecord.get(4).equals(coursecode))
								{	
									System.out.println(csvRecord.get(7) + "," + csvRecord.get(8) + "," + csvRecord.get(4)
									+ "," + csvRecord.get(5));
									linesToBeSaved.add(csvRecord.get(7) + "," + csvRecord.get(8) + "," + csvRecord.get(4)
									+ "," + csvRecord.get(5));
								}
							}
						}
						/*for(String s : linesToBeSaved)
						{
							int year;
							int sem;
							String[] data = s.split(",");
							
							for( int i = startyear ; i <= endyear ; i++ )
							{
								for(int j = 1 ; )
							}
							System.out.println(s);
						}*/
						Utils.writeAFile(linesToBeSaved, output);
						//Utils.writeAFile(result, output);
					}
				}
					/*{
				}
			            
			            	students = loadStudentCourseRecords(csvParser);
			            
				 }
				// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
				Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
				
				for(String key : sortedStudents.keySet()) {
					for(Course course : sortedStudents.get(key).getCoursesTaken()) {
						
					}
				}
				
			}*/
			
		}
		//String dataPath = args[0]; // csv file to be analyzed
		//String resultPath = args[1]; // the file path where the results are saved.
		
		//ArrayList<String> lines = Utils.getLines(input, true);
		//students = loadStudentCourseRecords(lines);
		/* try (
	            Reader reader = Files.newBufferedReader(Paths.get(args[0]));
	            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	                    .withFirstRecordAsHeader()
	                    .withIgnoreHeaderCase()
	                    .withTrim());
	        ) {
	            
	            	students = loadStudentCourseRecords(csvParser);
	            
		 }
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, args[1]);
		*/
		
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = Integer.parseInt(cmd.getOptionValue("a"));
			coursecode = cmd.getOptionValue("c");
			startyear = Integer.parseInt(cmd.getOptionValue("s"));
			endyear = Integer.parseInt(cmd.getOptionValue("e"));
			help = cmd.hasOption("h");
			

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());

		options.addOption(Option.builder("o").longOpt("output")
				.desc("set an output file path")
				.hasArg()    
				.argName("output path")
				.required() 
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()     
				.argName("Start year for analysis")
				.required() 
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -s 2005")
				.hasArg()     
				.argName("end year for analysis")
				.required() 
				.build());
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("Count courses per semester")
				.hasArg()   
				.argName("Analysis option")
				.required()
				.build());
		/*options.addOption(Option.builder("a").longOpt("analysis")
				.desc("Count per course name and year")
				.hasArg()    
				.argName("Analysis option")
				.required() 
				.build());
				*/
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()    
				.argName("course code")
				//.required() 
				.build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}


	
	/**
	 * This method create HashMap<String,Student> from the data csv file.
	 *  Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
		// TODO: Implement this method
		HashMap<String,Student> courseRecord = new HashMap<String,Student>();
		
		ArrayList<Course> courseStore = new ArrayList<Course>();
		
		for(String line : lines) {
			Course c = new Course(line);
			courseStore.add(c);
		}
				
		for(Course c : courseStore)
		{
			String id = c.getStudentId();
			if(courseRecord.containsKey(id))
			{
				courseRecord.get(id).addCourse(c);
			}
			else {
				Student s = new Student(id);
				courseRecord.put(id, s);
				courseRecord.get(id).addCourse(c);
			}		
		}
		
		return courseRecord; // do not forget to return a proper variable.
	}
	
	private HashMap<String,Student> loadStudentCourseRecords(CSVParser csvParser) {
		
		// TODO: Implement this method
		HashMap<String,Student> courseRecord = new HashMap<String,Student>();
		
		//ArrayList<Course> courseStore = new ArrayList<Course>();
		
		for (CSVRecord csvRecord : csvParser) {
			Course c = new Course(csvRecord);
			courseStore.add(c);
		}
		
				
		for(Course course : courseStore)
		{
			String id = course.getStudentId();
			if(courseRecord.containsKey(id))
			{
				if(startyear <= course.getYearTaken() && course.getYearTaken() <= endyear)
				courseRecord.get(id).addCourse(course);
			}
			else {
				Student s = new Student(id);
				courseRecord.put(id, s);
				if(startyear <= course.getYearTaken() && course.getYearTaken() <= endyear)
				courseRecord.get(id).addCourse(course);
			}		
		}
		
		return courseRecord; // do not forget to return a proper variable.
	}
	

	/**
	 * This method generate the number of courses taken by a student in each semester. 
	 * The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total.
	 *  In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		ArrayList<String> result = new ArrayList<String>();
		result.add("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
		
		for(String key : sortedStudents.keySet()) {
		for(int i = 1 ; i <= sortedStudents.get(key).getSemestersByYearAndSemester().size() ; i++ )
		result.add(key + "," + sortedStudents.get(key).getSemestersByYearAndSemester().size()
				+ "," + i + "," + sortedStudents.get(key).getNumCourseInNthSemester(i));
		}
		
		return result; // do not forget to return a proper variable.
	}
}
