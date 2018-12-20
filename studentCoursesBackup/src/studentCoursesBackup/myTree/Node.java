package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.Utility;
import studentCoursesBackup.util.MyLogger.DebugLevel;
import studentCoursesBackup.util.Utility.Operation;

public class Node implements SubjectI, ObserverI, Cloneable{
	
	
	private int bnumber;	
	private Set<String> courseNamesList;
	private Node leftChild, rightChild;
	private Map<ObserverI, List<ObserverI>> observersMap = new HashMap<ObserverI, List<ObserverI>>();
	

	/* (non-Javadoc)
	 * @see studentCoursesBackup.myTree.SubjectI#registerObserver(studentCoursesBackup.myTree.ObserverI, studentCoursesBackup.myTree.ObserverI)
	 * return null
	 */
	@Override
	public void registerObserver(ObserverI origNode, ObserverI backUpNode) {
		// TODO Auto-generated method stub
		List<ObserverI> backUpNodeList ;
		if(observersMap.containsKey(origNode)) {
			backUpNodeList = observersMap.get(origNode);
		}else {
			backUpNodeList = new ArrayList<ObserverI>();
		}
		backUpNodeList.add(backUpNode);
		observersMap.put(origNode, backUpNodeList);
		MyLogger.writeMessage("Registered the cloned backup nodes against the original node \n", DebugLevel.DEBUG);
	}

	
	/* (non-Javadoc)
	 * @see studentCoursesBackup.myTree.SubjectI#removeObserver(studentCoursesBackup.myTree.ObserverI, studentCoursesBackup.myTree.ObserverI)
	 * return null
	 */
	@Override
	public void removeObserver(ObserverI origNode, ObserverI backUpNode) {
		// TODO Auto-generated method stub
		if(observersMap.containsKey(origNode)) {
			List<ObserverI> backUpNodeList = observersMap.get(origNode);
			backUpNodeList.remove(backUpNode);
			observersMap.put(origNode, backUpNodeList);			
		}
		MyLogger.writeMessage("Deregistered the cloned backup nodes against the original node. \n", DebugLevel.DEBUG);
	}

	
	/* (non-Javadoc)
	 * @see studentCoursesBackup.myTree.ObserverI#update(java.lang.Object, studentCoursesBackup.util.Utility.Operation, java.lang.String)
	 * return null
	 */
	@Override
	public void update(Object backUpNode, Operation op,  String course) {
		// TODO Auto-generated method stub
		if(op.equals(Utility.Operation.ADD)) {
			
			((Node)backUpNode).getCourseNamesList().add(course);
		}else if(op.equals(Utility.Operation.REMOVE)) {

			((Node)backUpNode).getCourseNamesList().remove(course);			
		}
		MyLogger.writeMessage("Registered observer backup nodes updated with changes. \n", DebugLevel.DEBUG); 
	}

	
	/* (non-Javadoc)
	 * @see studentCoursesBackup.myTree.SubjectI#notifyAll(java.lang.Object, studentCoursesBackup.util.Utility.Operation, java.lang.String)
	 * return null
	 */
	@Override
	public void notifyAll(Object origNode, Operation op, String course) {
		// TODO Auto-generated method stub
		for(ObserverI obsNode: observersMap.get(origNode)) {
			update(obsNode, op, course);
		}		
		MyLogger.writeMessage("All Registered observer backup nodes notified of changes by subject. \n", DebugLevel.DEBUG); 
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone(){
		// Deep copy for the Node object
		Node copyObj = null;
		try {
			copyObj = (Node)super.clone();
		}catch(CloneNotSupportedException e) {
			MyLogger.writeMessage("Node cannot be cloned \n", DebugLevel.ERROR);
			System.err.println("Node cannot be cloned \n");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}
		copyObj.courseNamesList = new HashSet<String>();
		copyObj.courseNamesList.addAll(this.courseNamesList);
		MyLogger.writeMessage("Nodes cloned for the original node:"+ copyObj.getBnumber()+". \n", DebugLevel.INFO);
		MyLogger.writeMessage("Nodes cloned. \n", DebugLevel.DEBUG);  
		return copyObj;
    }
	

	/**
	 * @return
	 */
	public int getBnumber() {
		return bnumber;
	}

	/**
	 * @param bnumber
	 * return null
	 */
	public void setBnumber(int bnumber) {
		this.bnumber = bnumber;
	}

	/**
	 * @return
	 */
	public Set<String> getCourseNamesList() {
		return courseNamesList;
	}

	/**
	 * @param courseNamesList
	 * return null
	 */
	public void setCourseNamesList(Set<String> courseNamesList) {
		this.courseNamesList = courseNamesList;
	}

	/**
	 * @return
	 */
	public Node getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild
	 * return null
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return
	 */
	public Node getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild
	 * return null
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

}
