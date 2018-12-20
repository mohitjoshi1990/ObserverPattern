package studentCoursesBackup.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import studentCoursesBackup.myTree.MyTreeImpl;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.MyLogger.DebugLevel;
import studentCoursesBackup.util.Utility.TreeType;

public class TreeBuilder {


	private MyTreeImpl originalTree, backUpTree1, backUpTree2;
	
	public TreeBuilder() {
		// TODO Auto-generated constructor stub
		originalTree = new MyTreeImpl();
		backUpTree1 = new MyTreeImpl();
		backUpTree2 = new MyTreeImpl();
		MyLogger.writeMessage("Initialized the constructor of TreeBuilder class \n", DebugLevel.CONSTRUCTOR);
	}

	
	/**
	 * @param inputFile
	 * @param deleteFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 * return null
	 */
	public void buildTrees(String inputFile, String deleteFile) throws IOException,FileNotFoundException,Exception{
		String line = null;				
		FileProcessor procObj = new FileProcessor(inputFile);
        while((line = (String)procObj.readLine(inputFile)) != null) {
        	String arr[] = line.split(":");
    		int bNumber = 0;
        	try {
        		bNumber = Integer.parseInt(arr[0]);
        	}catch(NumberFormatException e) {
    			MyLogger.writeMessage("Bnumber passed cannot be parsed in input file\n", DebugLevel.ERROR);
    			System.err.println("Bnumber passed cannot be parsed in input file\n");
    			System.err.println("Exiting the program. \n");
    			System.exit(0);
    		}
        	String courseName = arr[1];
        	try{
        		if(!Utility.possibleCourseList.contains(courseName)) {
        			throw new Exception();
        		}
        	}catch(Exception e) {
    			MyLogger.writeMessage("Incorrect course name passed in input file, acccepted(A-K) \n", DebugLevel.ERROR);
    			System.err.println("Incorrect course name passed in input file, acccepted(A-K) \n");
    			System.err.println("Exiting the program. \n");
    			System.exit(0);
    		}
        	Node nodeObj = originalTree.searchNode(bNumber);
        	if(nodeObj == null) {
	        	nodeObj = new Node();
	        	nodeObj.setBnumber(bNumber);
	        	nodeObj.setCourseNamesList(new HashSet<>());
	        	nodeObj.getCourseNamesList().add(courseName);
	        	Node backUpNode1 = (Node) nodeObj.clone();
	        	Node backUpNode2 = (Node) nodeObj.clone();
	        	nodeObj.registerObserver(nodeObj, backUpNode1);
	        	originalTree.insertNode(nodeObj);
	        	nodeObj.registerObserver(nodeObj, backUpNode2);
	        	backUpTree1.insertNode(backUpNode1);
	        	backUpTree2.insertNode(backUpNode2);
        	}else {
            	originalTree.updateNode(nodeObj, courseName, Utility.Operation.ADD);
            	nodeObj.notifyAll(nodeObj, Utility.Operation.ADD, courseName);
        	}		
        }
		MyLogger.writeMessage("input file read and courses updated accordingly. \n", DebugLevel.INFO);
		MyLogger.writeMessage("input file read and courses updated accordingly. \n", DebugLevel.DEBUG);
        FileProcessor delFileObj = new FileProcessor(deleteFile);
        while((line = (String)delFileObj.readLine(deleteFile)) != null) {
        	String arr[] = line.split(":");
        	int bNumber = 0;
        	try {
        		bNumber = Integer.parseInt(arr[0]);
        	}catch(NumberFormatException e) {
    			MyLogger.writeMessage("Bnumber passed cannot be parsed in delete file\n", DebugLevel.ERROR);
    			System.err.println("Bnumber passed cannot be parsed in delete file \n");
    			System.err.println("Exiting the program. \n");
    			System.exit(0);
    		}
        	String courseName = arr[1];
        	try{
        		if(!Utility.possibleCourseList.contains(courseName)) {
        			throw new Exception();
        		}
        	}catch(Exception e) {
    			MyLogger.writeMessage("Incorrect course name passed in delete file, acccepted(A-K) \n", DebugLevel.ERROR);
    			System.err.println("Incorrect course name passed in delete file, acccepted(A-K) \n");
    			System.err.println("Exiting the program. \n");
    			System.exit(0);
    		}
        	Node nodeObj = originalTree.searchNode(bNumber);
        	if(nodeObj == null) {
        		// Node not present for deleting the passed input
        	}else {
            	originalTree.updateNode(nodeObj, courseName, Utility.Operation.REMOVE);
            	nodeObj.notifyAll(nodeObj,  Utility.Operation.REMOVE, courseName);
        	}		
        }
		MyLogger.writeMessage("delete file read and courses updated accordingly. \n", DebugLevel.INFO);
		MyLogger.writeMessage("delete file read and courses updated accordingly. \n", DebugLevel.DEBUG);
	}
	
	
	/**
	 * @param result
	 * @param type
	 * @throws Exception
	 * return null
	 */
	public void printNodes(Results result, TreeType type) throws Exception{
			if(type.equals(Utility.TreeType.MAIN)) {
				MyLogger.writeMessage("\n\n--------------------------------------------------", DebugLevel.INFO);
				MyLogger.writeMessage("\n Printing the original tree. \n", DebugLevel.INFO);
				MyLogger.writeMessage("\n\n--------------------------------------------------", DebugLevel.DEBUG);
				MyLogger.writeMessage("\nPrinting the original tree. \n", DebugLevel.DEBUG);
				originalTree.printNodes(result);
			}else if(type.equals(Utility.TreeType.BACKUP1)) {
				MyLogger.writeMessage("\n\n--------------------------------------------------", DebugLevel.INFO);
				MyLogger.writeMessage("\nPrinting the backup 1 tree. \n", DebugLevel.INFO);
				MyLogger.writeMessage("\n\n--------------------------------------------------", DebugLevel.DEBUG);
				MyLogger.writeMessage("\nPrinting the backup 1 tree. \n", DebugLevel.DEBUG);
				backUpTree1.printNodes(result);
			}else if(type.equals(Utility.TreeType.BACKUP2)) {
				MyLogger.writeMessage("\n\n--------------------------------------------------", DebugLevel.INFO);
				MyLogger.writeMessage("\nPrinting the backup 2 tree. \n", DebugLevel.INFO);
				MyLogger.writeMessage("\n\n--------------------------------------------------", DebugLevel.DEBUG);
				MyLogger.writeMessage("\nPrinting the backup 2 tree. \n", DebugLevel.DEBUG);
				backUpTree2.printNodes(result);
			}        
	}
	
}
