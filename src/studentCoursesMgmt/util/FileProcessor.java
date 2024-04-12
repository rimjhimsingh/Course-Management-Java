package studentCoursesMgmt.util;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProcessor {
    // this function reads the entire content of the file that is passed into a
    // single string
    public String readFile(String filename) {
        StringBuilder content = new StringBuilder();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                content.append(line).append("\n");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
//CourseAllocator c = new CourseAllocator();
    // calling in courseAllocator and sending data into it

    public void createOutputFile(String name) {
        // creates empty input file using the names entered from CMD
        try {
            
            File file = new File(name);
            boolean created = file.createNewFile();
            {
                if (created) {
                    System.out.println("Created" + name);
                } else if (file.exists()) {
                    file.delete();
                    created = file.createNewFile();
                } else
                    System.out.println("not created because exists already" + name);
            }
        } catch (IOException e) {
            System.err.println("IO Exception " + e.getMessage());
            Results res = new Results();
            String msg = " " + e.getMessage();
            res.writeToFile("errorLog.txt", msg);
            System.err.println("IO Exception  " + e.getMessage());
            e.printStackTrace();
        } 
        

    }

    public void closeFile(String filename)
    {
        File file = new File(filename);
        

    }

}