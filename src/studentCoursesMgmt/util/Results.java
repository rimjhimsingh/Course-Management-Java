package studentCoursesMgmt.util;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface 
{
    public void writeToFile(String filename, Object content)
    {
       try{ 
        //this method will receieve the filename and content and it will write the content to the files
        FileWriter writer = new FileWriter(filename,true);
        writer.write(content + ("\n")); //the idea is to send content line by line and this method will print it line by line
        writer.close();
       }catch (IOException e) {
        
    }
    }
    public void overwriteToFile(String filename, Object content) throws IOException
    {
     try{ 
        FileWriter writer = new FileWriter(filename);
        writer.write(content + ("\n"));
        writer.close();
     }catch (IOException e) {
        Results res = new Results();
		String msg = "IO Exception " + e.getMessage();
		res.writeToFile("errorLog.txt", msg);
        System.err.println("IO Exception  " + e.getMessage());
        e.printStackTrace();
    }
    }
    public void writetoSTDO(Object object)
    {
        System.out.print(object);
        
    }  
}