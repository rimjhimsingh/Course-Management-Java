package studentCoursesMgmt.util;

import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CourseAllocator {
    // this is the class where the allocation of courses would take place
    // we will use FCFS to assign courses to students
    // satisfaction scores will also be calculated hereudent preferences
    

    /**
    * The `Courseinfo` class represents information about a course.
    */
    public class Courseinfo {
        private String coursename;
        private int capacity;
        private int time;

    /**
     * Constructs a new `Courseinfo` object with the given parameters.
     *
     * @param coursename The name of the course.
     * @param capacity   The maximum capacity of students in the course.
     * @param time       The time slot at which the course is offered.
     */

        public Courseinfo(String coursename, int capacity, int time) {
            this.coursename = coursename;
            this.capacity = capacity;
            this.time = time;
        }
        /**
         * Gets the name of the course.
         *
         * @return The name of the course.
         */
        public String getCoursename() {
            return coursename;
        }
        /**
         * Gets the maximum capacity of students in the course.
         *
         * @return The maximum capacity of the course.
         */

        public int getCapacity() {
            return capacity;
        }
        /**
         * Gets the time at which the course is offered.
         *
         * @return The time at which the course is offered.
         */
        public int getTime() {
            return time;
        }
        /**
         * Sets the maximum capacity of the course, used to decrease the capacity after a student is assigned
         *
         * @param newCapacity The new maximum capacity of the course.
         */
        public void setCapacity(int newCapacity) {
            this.capacity = newCapacity;
        }
        /**
         * Overrides the `toString` method from the Object class to provide a custom
         * string representation of the `Courseinfo` object.
         *
         * @return A string representation of the `Courseinfo` object.
         */
        @Override
        public String toString() {
            return "Course[courseName=" + coursename + ", capacity=" + capacity + ", time=" + time + "]";
        }
    }

    public class Student {
        private int id; // int id of a student, always unique
        private List<String> pref; // list of strings of course preference for a student

        public Student(int id, List<String> pref) {
            this.id = id;
            this.pref = pref;
        }

        public int getId() {
            return id;
        }

        public List<String> getPreferences() {
            return pref;
        }

        @Override
        public String toString() {
            return "Student ID=" + id + ", pref=" + pref;

        }
    }
    
    public class Outputstudent {
        private int outputId;
        private String course1;
        private String course2;
        private String course3;
        private float satisfactionRate;

        public Outputstudent(int id, List<String> pref, float satisfactionRate) {
            this.outputId = id;

            this.course1 = pref.get(0);
            this.course2 = pref.get(1);
            this.course3 = pref.get(2);

            this.satisfactionRate = satisfactionRate;
        }

        @Override // printing in the desired output format
        public String toString() {
            return outputId + ":" + course1 + "," + course2 + "," + course3 + "::SatisfactionRating="
                    + satisfactionRate + "\n";
        }
    }

    public List<Courseinfo> readCourseinfo(String filecontent) throws IOException {
        // here we define a constructor and use it as a data strcuture to store the
        // values of coursename, capactity, time
        
        FileProcessor f = new FileProcessor();
        String content = f.readFile(filecontent);
        if(content.isEmpty())
        {
           // System.out.println("Course Information file is empty, exiting program"); //fatal error
            String msg = "Course Information file is empty, exiting program";
            Results res = new Results();
            res.overwriteToFile("errorLog.txt",msg);
        }
        
        List<Courseinfo> L1 = new ArrayList<>();
        Scanner sc1 = new Scanner(content).useDelimiter("\n");
        for (int i = 0; i < 9; i++) {
            String[] T = sc1.nextLine().split(":");
            String coursename = T[0];
            int capacity = Integer.parseInt(T[1]);
            int time = Integer.parseInt(T[2]);
            Courseinfo course = new Courseinfo(coursename, capacity, time);
            L1.add(course);
        }
        System.out.println(L1);
        sc1.close();
        return L1;
    }/*catch(Exception e)
    {
        System.err.println("String cannot be converted into Integer");
        e.printStackTrace();
        return null;
    }*/
    

    public List<Student> readCoursePrefs(String filecontent) {
        try{
        FileProcessor f = new FileProcessor();
        String content = f.readFile(filecontent);
        if(content.isEmpty())
        {
            //System.out.println("Course Prefs file is empty"); 
            String msg = "Course Prefs file is empty no students are available";
            Results res = new Results();
            res.writeToFile("errorLog.txt",msg);
        }
        Student st = null;
        List<Student> studentList = new ArrayList<>();
        Scanner sc2 = new Scanner(content).useDelimiter("\n");
        while (sc2.hasNext()) {
            // read the content into a data structure but not all at once
            String T = sc2.nextLine(); // here we read the first line of the input file
            T = T.replace(";", ""); // now we remove the last character
            String[] parts = T.split("\\s+"); // we split the input string by whitespace
            String id = parts[0];
            int stid = Integer.parseInt(id);
            List<String> pref = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                // List<String> pref = new ArrayList<>();
                pref.add(parts[i]);
                pref.toString();
                st = new Student(stid, pref);
            }
            studentList.add(st);
        }
        sc2.close();
        return studentList;
    }catch(Exception e)
    {
        System.err.println("String cannot be converted into Integer");
        e.printStackTrace();
        return null;
    }
    }

    public float calculateSatisfactionRating(List<String> preferences, List<String> assignedcourses) {
        float satisfactionRate = 0;
        for (int i1 = 0; i1 < preferences.size(); i1++) {
            String preference = preferences.get(i1);
            if (assignedcourses.contains(preference)) {
                // System.out.println("Match found at index " + i1);
                satisfactionRate += (9 - i1); // course at index 0 has score 9
            }
        }
        float rating = satisfactionRate / 3.0f;
        return Float.parseFloat(String.format("%.2f", rating)); // formating it to 2 decimal places
    }

    public void assignCourse(List<Courseinfo> courses, List<Student> student) throws IOException {
        float totalAvRating = 0;
        List<Student> S = new ArrayList<>(); // intialising a arraylist S of objects of the type student
        S = student; 
        List<Courseinfo> coursedata = new ArrayList<>(); // intialising a arraylist coursedata of objects of the type Courseinfo
        coursedata = courses;
        List<Outputstudent> Studentsassignedcourses = new ArrayList<>();
    
        Results res1 = new Results();
        Results res2 = new Results();
        for (int i = 0; i < S.size(); i++) {
            Student stu = S.get(i); // get the information for the ith student
            int studentId = stu.getId(); // get the id of the ith student
            List<String> preferences = stu.getPreferences(); // get the list of course preferences of the ith student
            int coursecounter = 0;

            Set<Integer> set = new HashSet<Integer>();
            List<String> assignedlist = new ArrayList<String>();
            for (int j = 0; j < preferences.size() && coursecounter < 3; j++) // students only assigned 3 courses at max
            {
                String preference = preferences.get(j); // getting the pref for a student
                for (int k = 0; k < coursedata.size(); k++) {
                    Courseinfo course = coursedata.get(k); // intialising an object course of the type course info
                    String coursename = course.getCoursename();
                    int capacity = course.getCapacity();
                    int time = course.getTime();
                    String assigned = preference;
                    // only condition when courses will be assigned
                    if (preference.equals(coursename) && capacity > 0 && !set.contains(time)) {
                        course.setCapacity(capacity - 1); // Update the course capacity
                       // System.out.println("Student ID " + studentId + " assigned to course " + assigned);
                        coursecounter++;
                        assignedlist.add(assigned);
                        set.add(time);

                    } else if (preference.equals(coursename) && capacity == 0) {

                       // System.out.println("Cannot assign course " + preference + " to student " + studentId + " no capacity");
                        String msg1 = "Cannot assign course " + preference + " to student " + studentId + " no capacity";
                        res1.writeToFile("errorLog.txt", msg1);

                    } else if (preference.equals(coursename) && set.contains(time)) {

                        //System.out.println( "Cannot assign course" + preference + "to student" + studentId + "time conflict");
                        String msg = "Cannot assign course " + preference + " to student " + studentId + " time conflict";
                        res2.writeToFile("regConflicts.txt", msg);

                    }
                }
            }
            // Add students with less than 3 courses to assignedlist
            if (coursecounter < 3) {
                for (int k = coursecounter; k < 3; k++) {
                    assignedlist.add(""); // Add empty strings for unassigned courses
                }
            }
            System.out.println(assignedlist);
            float rate = calculateSatisfactionRating(preferences, assignedlist); //calculate the average satisfaction rating for each student
            Outputstudent finalstudent = new Outputstudent(studentId, assignedlist, rate);
            Studentsassignedcourses.add(finalstudent);
            Results res = new Results();
            res.overwriteToFile("regResults.txt", Studentsassignedcourses );
            if (coursecounter == 0) {
                //System.out.println("No course assigned to student" + studentId);
                String msg = "No course assigned to student" + studentId;
                res2.writeToFile("regConflicts.txt", msg);
            }
            totalAvRating = totalAvRating + rate;
        }
        //finally print the average satisfaction rating for all students
        float p = totalAvRating/S.size();
        float printAvRate = Float.parseFloat(String.format("%.2f", p));
        Results res = new Results();
        res.writeToFile("regResults.txt", " AverageSatisfactionRating = " + printAvRate);
    }
}
