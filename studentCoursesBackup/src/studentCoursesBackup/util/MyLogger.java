package studentCoursesBackup.util;

public class MyLogger {
	

    public static enum DebugLevel { CONSTRUCTOR, FILE_PROCESSOR, NONE, INFO, DEBUG, ERROR
                                   };

    private static DebugLevel debugLevel;


    /**
     * @param levelIn
     * @return void
     */
    public static void setDebugValue (int levelIn) {
		switch (levelIn) {
			case 5: debugLevel = DebugLevel.ERROR; break;
			case 4: debugLevel = DebugLevel.DEBUG; break;
			case 3: debugLevel = DebugLevel.INFO; break;
			case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
			case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
			default: debugLevel = DebugLevel.NONE; break;
		}
    }

    
    /**
     * @param levelIn
     * @return void
     */
    public static void setDebugValue (DebugLevel levelIn) {
    	debugLevel = levelIn;
    }

    
    /**
     * @param message
     * @param levelIn
     * @return void
     */
    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
		if (levelIn == debugLevel)
		    System.out.println(message);
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * @return String
     */
    public String toString() {
    	return "The debug level has been set to the following " + debugLevel;
    }
}

