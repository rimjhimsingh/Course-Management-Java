# Course Management System

## Project Overview

The Course Management System is a Java-based application designed to automate the allocation of courses to students based on their preferences and course capacities. The system reads course information and student preferences from files, processes the data to assign courses according to a first-come, first-served policy, and outputs the results along with satisfaction ratings and potential conflicts.

The system is meticulously architected using the principles of object-oriented design (OOD), which allows for modular, scalable, and maintainable code. The application leverages several OOD principles such as encapsulation, inheritance, and polymorphism to build a robust system.

## Key Features

- **Course Allocation**: Dynamically assigns courses to students based on preferences, course capacity, and scheduling constraints.
- **Conflict Management**: Identifies and reports on any registration conflicts or capacity issues.
- **Satisfaction Rating**: Calculates and reports a satisfaction score for each student based on the courses assigned versus their preferences.
- **File Handling**: Reads input from and writes output to files, handling data persistence.

## Technologies and Concepts Used

- **Java**: The entire application is built using Java, leveraging its object-oriented capabilities for structuring the project.
- **Exception Handling**: Extensively uses exception handling to manage I/O operations and data processing errors.
- **Data Structures**:
  - **ArrayLists**: Used for storing and managing lists of courses, students, and their preferences dynamically.
  - **HashSet**: Utilized for tracking unique time slots for course scheduling to avoid conflicts.
  - **Scanner**: Employed for parsing input files.

## Detailed Functionality

### Modules and Classes

- **`CourseAllocator`**:
  - Handles the core functionality of course assignments based on student preferences and available course capacities.
  - **Nested Classes**:
    - `Courseinfo`: Stores information about each course, including name, capacity, and time slot.
    - `Student`: Maintains student ID and their course preferences.
    - `Outputstudent`: Formats and prepares the final output detailing assigned courses and satisfaction ratings.

- **`FileProcessor`**:
  - Manages file operations such as reading input data from files and creating output files to store results.
  - Handles file creation and content writing, ensuring that output files are ready for storing the results of course allocations.

- **`Results`**:
  - Implements `FileDisplayInterface` and `StdoutDisplayInterface` to support writing outputs to files and standard output.
  - Manages both appending to existing files and overwriting files when needed.

### Workflow

1. **Input Reading**:
   - Course details and student preferences are read from specified files.
   - Data is parsed and loaded into respective `ArrayLists` of `Courseinfo` and `Student` objects.

2. **Course Assignment**:
   - Courses are assigned to students based on a first-come, first-served basis, respecting course capacities and scheduling constraints.
   - Conflicts due to capacity limits or scheduling are managed and logged.

3. **Output Generation**:
   - Assigned courses and satisfaction ratings for each student are calculated and formatted.
   - Results are written to output files, including details of any registration conflicts or errors encountered during processing.

4. **Logging and Error Handling**:
   - All operations are wrapped in appropriate exception handling blocks to capture and log errors to designated error log files.

## Usage

The system is designed to be run from the command line, where the user specifies the paths to the input files (course information and student preferences) and the output files for results and logs as command-line arguments.

## Conclusion

The Course Management System is a robust Java application designed to simplify the process of course registration, managing complexities of student preferences and course capacities effectively. It stands as a testament to the power of combining efficient data structures with thoughtful software design to create practical and reliable software solutions.


## Instruction to clean:

#### Command: ant -buildfile studentCoursesMgmt/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

#### Command: ant -buildfile studentCoursesMgmt/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Command: ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>
