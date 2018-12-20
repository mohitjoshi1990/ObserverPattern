package studentCoursesBackup.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import studentCoursesBackup.util.FileDisplayInterface;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.Utility;

public class Driver {

	
	/**
	 * @param args
	 * @return void
	 */
	public static void main(String args[]) {
		try {
			if(args.length!=6 || args[0]==null || args[0].equalsIgnoreCase("${arg0}") || 
					args[1]==null || args[1].equalsIgnoreCase("${arg1}") || 
					args[2]==null || args[2].equalsIgnoreCase("${arg2}")|| 
					args[3]==null || args[3].equalsIgnoreCase("${arg3}")|| 
					args[4]==null || args[4].equalsIgnoreCase("${arg4}")|| 
					args[5]==null || args[5].equalsIgnoreCase("${arg5}")) {
				MyLogger.writeMessage("Incorrect arguments passed, Expected <input.txt> <delete.txt> <output1.txt>  <output2.txt> <output3.txt> <Debug Value>. \n Exiting the program. \n", DebugLevel.ERROR);
				System.err.println("Incorrect arguments passed, Expected <input.txt> <delete.txt> <output1.txt>  <output2.txt> <output3.txt> <Debug Value>. \n Exiting the program. \n");
				System.exit(0);
			}
			int debugValue = Integer.parseInt(args[5]);
			if(!(debugValue > 0 && debugValue < 6)) {
				MyLogger.writeMessage("Debug value is outside of the expected range (1-5) per code \n", DebugLevel.ERROR);
				System.err.println("Debug value is outside of the expected range (1-5) per code.");
				System.err.println("Exiting the program. \n");
				System.exit(0);				
			}
			MyLogger.setDebugValue(debugValue);
			Results resultsObj = new Results(args[2]);
			Results resultsObjBack1 = new Results(args[3]);
			Results resultsObjBack2 = new Results(args[4]);
			TreeBuilder treeBuildObj = new TreeBuilder();
			treeBuildObj.buildTrees(args[0], args[1]);
			// Iterating main tree
			resultsObj.writeToFile("Printing the main tree \n");
			FileDisplayInterface fdInterface = resultsObj;
			//StdoutDisplayInterface stdDispInterface = resultsObj;
			treeBuildObj.printNodes(resultsObj, Utility.TreeType.MAIN);
			for(String result : resultsObj.getResultsList()) {
				fdInterface.writeToFile(result);
			}
			resultsObj.closeBufferWriter();
			// Iterating backup1 tree
			resultsObjBack1.writeToFile("Printing the backup 1 tree \n");
			FileDisplayInterface fdInterfaceBack1 = resultsObjBack1;
			//StdoutDisplayInterface stdDispInterfaceBack1 = resultsObjBack1;
			treeBuildObj.printNodes(resultsObjBack1, Utility.TreeType.BACKUP1);
			for(String result : resultsObjBack1.getResultsList()) {
				fdInterfaceBack1.writeToFile(result);
			}
			resultsObjBack1.closeBufferWriter();
			// Iterating backup2 tree
			resultsObjBack2.writeToFile("Printing the backup 2 tree \n");
			FileDisplayInterface fdInterfaceBack2 = resultsObjBack2;
			//StdoutDisplayInterface stdDispInterfaceBack2 = resultsObjBack2;
			treeBuildObj.printNodes(resultsObjBack2, Utility.TreeType.BACKUP2);
			for(String result : resultsObjBack2.getResultsList()) {
				fdInterfaceBack2.writeToFile(result);
			}
			resultsObjBack2.closeBufferWriter();
			
		}catch(NumberFormatException e) {
			MyLogger.writeMessage("Exception occured as debug argument is not a number \n", DebugLevel.ERROR);
			System.err.println("Exception occured as debug argument is not a number. \n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}catch (FileNotFoundException e) {
			MyLogger.writeMessage(e.getMessage()+"\n", DebugLevel.ERROR);
			System.err.println(e.getMessage()+"\n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}catch (IOException e) {
			MyLogger.writeMessage(e.getMessage()+"\n", DebugLevel.ERROR);
			System.err.println(e.getMessage()+"\n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}
		catch (Exception e) {
			MyLogger.writeMessage("Exception occured : "+ e.getMessage()+"\n", DebugLevel.ERROR);
			System.err.println("Exception occured : "+ e.getMessage()+"\n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}finally {
			//nothing implemented
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString(){
		return "";
	}
}
