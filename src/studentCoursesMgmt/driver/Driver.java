package studentCoursesMgmt.driver;
import java.io.IOException;
import java.util.List;
import studentCoursesMgmt.util.Results;
import studentCoursesMgmt.util.CourseAllocator;
import studentCoursesMgmt.util.CourseAllocator.Student;
import studentCoursesMgmt.util.CourseAllocator.Courseinfo;
import studentCoursesMgmt.util.FileProcessor;

/**
 * @author Rimjhim Singh
 *         B01040210
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		try {
			//checking boundary condition
			if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
					|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

				System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
				System.exit(0);
			}
			
			// storing the file names of the input files in String variable.
			String pref = args[0];
			String info = args[1];
			String file1 = args[2];
			String file2 = args[3];
			String file3 = args[4];
			FileProcessor output = new FileProcessor();
			output.createOutputFile(file1);
			output.createOutputFile(file2);
			output.createOutputFile(file3);
			//calling the method that allotes courses
			CourseAllocator assign = new CourseAllocator();
			List<Courseinfo> C = assign.readCourseinfo(info);
			List<Student> S = assign.readCoursePrefs(pref);
			assign.assignCourse(C, S);
			//check if courseprefs and course info is empty
		} catch (IOException e) {
			Results res = new Results();
			String msg = "Error creating text files: " + e.getMessage();
			res.writeToFile("errorLog.txt", msg);
			System.err.println("Error creating text files: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
