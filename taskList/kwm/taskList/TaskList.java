/*-------------------------------------------------------------
 * TaskList.java
 * Author: Verena Schickmair
 * Date: 06.05.2020
 * Task: Task 6
 * 
 * This class is for the object TaskList. It sets the Listhead,
 * the number of Tasks and true or false, wheter the list
 * is sorted or not. With various methods, the list and its
 * Tasks can be modified. 
 * 
 * Methods:
 *	searchTask(String id)
	Searches the list for a id entered by the user.
	If there is no Node found, null will be returned.
	Return: Task

	addTask(Task t)
	If the id of the new Node is not existing yet, a new node will be
	added to the list.
	Return: boolean

	removeTask(String id)
	Removes tasks of the list (those with the id entered by the user)
	Return: Task

	getOpenTasks()
	Creates a new list and copy those nodes, which are open, from
	the old list in the new list
	Return: TaskList

	getTasksByProcessor(String processor)
	Creates a new list and copy those nodes (with the processor
	name entered by the user) from the old list in the new list
	Return: TaskList

	printTaskList()
	Prints every node available in the list
	Return: void

	getNumTasks()
	Gives back the number of nodes in the list
	Return: int

	sortByDate()
	Creates a new list and gives every node in the old list
	and the new list to the method insertSorted. So, the old
	list will be replaced by a new, sorted list
	Return: void

	insertSorted(TaskList newList, Task node)
	Creates new nodes from the old nodes and sort them 
	by date (ascending) in the new list
	Return: TaskList
 * -----------------------------------------------------------*/

package kwm.taskList;

public class TaskList {
	public Task firstNode;
	public int numTasks;
	public boolean sorted;

	//searchTask(String id)
	//Searches the list for a id entered by the user.
	//If there is no Node found, null will be returned.
	//Return: Task
	public Task searchTask(String id) {
		Task node = this.firstNode;
		while(node != null) {
			if(node.id.equals(id))
				return node;			
			node = node.next;
		}
		return null;
	}

	//addTask(Task t)
	//If the id of the new Node is not existing yet, a new node will be
	//added to the list.
	//Return: boolean
	public boolean addTask(Task node) {
		if(this.searchTask(node.id) == null) {
			node.next = this.firstNode;
			this.firstNode = node;
			this.numTasks++;
			this.sorted = false;
			return true;
		}
		else return false;
	}

	//removeTask(String id)
	//Removes tasks of the list (those with the id entered by the user)
	//Return: Task
	public Task removeTask(String id) {
		Task prev = null;
		Task node = this.firstNode;

		while(node != null && !id.equals(node.id)) {
			prev = node;
			node = node.next;
		}

		if(node != null) {
			if(prev == null) {
				this.firstNode = node.next;
			}
			else {
				prev.next = node.next;
			}
			this.numTasks--;
			return node;
		}
		else {
			return null;
		}
	}

	//getOpenTasks()
	//Creates a new list and copy those nodes, which are open, from
	//the old list in the new list
	//Return: TaskList
	public TaskList getOpenTasks() {
		Task node = this.firstNode;
		TaskList newList = new TaskList();

		while(node != null) {
			if(node.status == true) {
				Task nodeCopy = new Task(node);
				newList.addTask(nodeCopy);
			}
			node = node.next;
		}
		return newList;	
	}

	//getTasksByProcessor(String processor)
	//Creates a new list and copy those nodes (with the processor
	//name entered by the user) from the old list in the new list
	//Return: TaskList
	public TaskList getTasksByProcessor(String processor) {
		Task node = this.firstNode;
		TaskList newList = new TaskList();

		while(node != null) {
			if(node.processor.equals(processor)) {
				Task newTask = new Task(node);
				newList.addTask(newTask);
			}
			node = node.next;
		}
		return newList;
	}

	//printTaskList()
	//Prints every node available in the list
	//Return: void
	public void printTaskList() {
		Task node = this.firstNode;	
		while(node != null) {
			node.printTask();
			node = node.next;
		}
	}

	//getNumTasks()
	//Gives back the number of nodes in the list
	//Return: int
	public int getNumTasks() {
		return this.numTasks;
	}

	//sortByDate()
	//Creates a new list and gives every node in the old list
	//and the new list to the method insertSorted. So, the old
	//list will be replaced by a new, sorted list
	//Return: void
	public void sortByDate() {
		TaskList newList = new TaskList();
		Task node = this.firstNode;

		while(node != null) {
			newList = TaskList.insertSorted(newList, node);
			node = node.next;
		}
		this.firstNode = newList.firstNode;
		this.sorted = true;
	}

	//insertSorted(TaskList newList, Task node)
	//Creates new nodes from the old nodes and sort them 
	//by date (ascending) in the new list
	//Return: TaskList
	public static TaskList insertSorted(TaskList newList, Task node) {
		Task prev = null;
		Task nodeSorted = newList.firstNode;
		Task n = new Task(node);	

		while(nodeSorted != null && 
				nodeSorted.dueDate.compareTo(n.dueDate)>0) {
			prev = nodeSorted;
			nodeSorted = nodeSorted.next;
		}		
		if(prev == null) {
			newList.firstNode = n;
		}
		else
			prev.next = n;	

		n.next = nodeSorted;
		return newList;
	}
}