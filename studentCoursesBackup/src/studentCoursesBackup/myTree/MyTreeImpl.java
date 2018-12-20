package studentCoursesBackup.myTree;

import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.Utility;
import studentCoursesBackup.util.MyLogger.DebugLevel;
import studentCoursesBackup.util.Utility.Operation;

public class MyTreeImpl {
	
	private Node rootNode;
	
	
	/**
	 * @param parentNode
	 * @param newNode
	 * @return
	 */
	public Node insertRecursive(Node parentNode, Node newNode) {
		
		if(parentNode == null) {
			parentNode = newNode;
			return parentNode;
		}		
		if(newNode.getBnumber() > parentNode.getBnumber()) {
			parentNode.setRightChild(insertRecursive(parentNode.getRightChild(), newNode));
		}else if(newNode.getBnumber() < parentNode.getBnumber()) {
			parentNode.setLeftChild(insertRecursive(parentNode.getLeftChild(), newNode));			
		}
		return parentNode;
	}
	
	
	/**
	 * @param newNode
	 * @return
	 */
	public Node insertNode(Node newNode) {
		// TODO Auto-generated method stub
		rootNode = insertRecursive(rootNode, newNode);
		return rootNode;
	}
		
	
	/**
	 * @param currentNode
	 * @param subject
	 * @param op
	 */
	public void updateNode(Node currentNode, String subject, Operation op) {
		// TODO Auto-generated method stub
		if(op.equals( Utility.Operation.ADD)) {
			currentNode.getCourseNamesList().add(subject);
		}else if(op.equals( Utility.Operation.REMOVE)){
			currentNode.getCourseNamesList().remove(subject);			
		}
	}

	
	/**
	 * @param parentNode
	 * @param bnum
	 * @return
	 */
	public Node searchNodeRec(Node parentNode, int bnum) {
		// TODO Auto-generated method stub
		if(parentNode == null) {
			return null;
		}
		if(parentNode.getBnumber() == bnum) {
			return parentNode;
		}else if(bnum > parentNode.getBnumber()) {
			parentNode = searchNodeRec(parentNode.getRightChild(), bnum);
		}else if(bnum < parentNode.getBnumber()) {
			parentNode = searchNodeRec(parentNode.getLeftChild(), bnum);			
		}
		return parentNode;
	}

	
	/**
	 * @param bnum
	 * @return
	 */
	public Node searchNode(int bnum) {			
		return searchNodeRec(rootNode, bnum);
	}


    /**
     * @param root
     * @param result
     */
    void inorderRec(Node root, Results result) {
        if (root != null) {
            inorderRec(root.getLeftChild(), result);
            String subjectList = root.getBnumber() +" :: ";
            for(String course: root.getCourseNamesList()) {
            	subjectList += course+",";
            }
            //result.writeToStdout(subjectList );
    		MyLogger.writeMessage(subjectList, DebugLevel.INFO);
    		MyLogger.writeMessage(subjectList, DebugLevel.DEBUG);
            result.writeToFile(subjectList );
            
            inorderRec(root.getRightChild(), result);
        }
    }
    
	
	/**
	 * @param result
	 */
	public void printNodes(Results result) {

		inorderRec(rootNode, result);
	}
}
