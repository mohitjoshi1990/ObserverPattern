package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {
	
    public static enum Operation { ADD, REMOVE};
    
    public static enum TreeType { MAIN, BACKUP1, BACKUP2};
    
    public static List<String> possibleCourseList = 
    		new  ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"));
	
}
