package studentCoursesMgmt.util;
//this is an interface that describes a method that will write to output file

import java.io.IOException;

public interface FileDisplayInterface 
{
    public void writeToFile(String filename, Object object) throws IOException;
    //this method is defined in Results.java
}
