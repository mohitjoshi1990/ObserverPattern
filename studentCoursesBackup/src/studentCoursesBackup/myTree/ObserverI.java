package studentCoursesBackup.myTree;

import studentCoursesBackup.util.Utility.Operation;

public interface ObserverI {

	/**
	 * @param backUpNode
	 * @param op
	 * @param course
	 * return null
	 */
	void update(Object backUpNode, Operation op, String course);

}
