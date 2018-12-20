package studentCoursesBackup.myTree;

import studentCoursesBackup.util.Utility.Operation;

public interface SubjectI {

	/**
	 * @param origNode
	 * @param backUpNode
	 * return null
	 */
	public void registerObserver(ObserverI origNode, ObserverI backUpNode);
	
	
	/**
	 * @param origNode
	 * @param backUpNode
	 * return null
	 */
	public void removeObserver(ObserverI origNode, ObserverI backUpNode);
	
	
	/**
	 * @param origNode
	 * @param op
	 * @param course
	 * return null
	 */
	public void notifyAll(Object origNode, Operation op, String course);
	
}
