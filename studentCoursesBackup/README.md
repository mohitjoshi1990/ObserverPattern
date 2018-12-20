Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=<Absolute path of input file> -Darg1=<Absolute path of delete file> -Darg2=<Absolute path of output1 file> -Darg3=<Absolute path of output2 file> -Darg4=<Absolute path of output3 file> -Darg5=<Debug value from 1-5>

-----------------------------------------------------------------------
-----------------------------------------------------------------------
choice of Tree chosen for this assignment in terms of time complexity for insert, search, and delete:

I choose BST(Binary Search Tree) for the insert, search and delete operations which take
time complexity of order O(log n) where n is the number of the nodes in the tree at a given point in time.
The insert and search operations are of O(log n) complexity as we keep on reducing our scope to half of previous
with each new level. The delete operation has been modified here as the node still exist even if all the courses
assigned to the student are removed. The delete operation is effectively a search operation to identify the node
taking O(log n) time, and O(1) time to remove the course name from node; hence effectively the time complexity
for deletion is still O(log n)
-----------------------------------------------------------------------
-----------------------------------------------------------------------
Cases for which command line validation has been provided 
1. number of passed command line arguments.
2. input.txt not found
3. delete.txt not found
3. Debug value not between 1 and 5; which are not defined in enums
4. Passing String which is not a number to the debug value
5. Passing incorrect input like bnumber as a string, or an incorrect course(other than A-K)

-----------------------------------------------------------------------
Observer pattern implementation::

I have implemented the observer pattern by creating a hashmap (Map<ObserverI, List<ObserverI>> observersMap).
This hashmap stores the original node as the key , and a list of backup nodes as the value.
The key serves as subject, while the value serves as the list of observer backup nodes which would be notified once some
changes are made to the subject original node.

-----------------------------------------------------------------------
Classes added::
1. studentCoursesBackup.myTree.MyTreeImpl: Class added for the BST structure and the operations that can be performed over the tree.
2. studentCoursesBackup.util.Utility: Utility class to use enums and lists for using in other class of project.
