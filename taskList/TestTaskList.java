/*-------------------------------------------------------------
 * TestTaskList.java
 * Author: Verena Schickmair
 * Date: 06.05.2020
 * Task: Task 6
 * 
 * This class is for testing the methods of the TaskList class.
 * The user can add Tasks to the new List, can delete Tasks,
 * can search for Tasks, can filter the List and can sort
 * the List Nodes by date.
 * -----------------------------------------------------------*/

import kwm.taskList.Task;
import kwm.taskList.TaskList;

public class TestTaskList {

	public static void main(String[] args) {

		Task t1 = new Task("t1", "Task 1", "Helmut", true, 2017, 3, 1);
		Task t2 = new Task("t2", "Task 2", "Helmut", false, 2018, 9, 11);
		Task t3 = new Task("t3", "Task 3", "August", false, 2016, 3, 10);
		Task t4 = new Task("t4", "Task 4", "August", true, 2018, 4, 1);

		TaskList list = new TaskList();

		//ADDTASKS
		list.addTask(t1);
		list.addTask(t2);
		list.addTask(t3);
		list.addTask(t4);	
		//list.printTaskList();

		//SEARCHTASK
		//Task tsearch = list.searchTask("t1");
		//tsearch.printTask();

		//REMOVETASK
		//list.removeTask("t3");
		//list.printTaskList();

		//GETOPENTASKS
		//TaskList openTasks = list.getOpenTasks();
		//openTasks.printTaskList();

		//GETTASKSBYPROCESSOR
		//TaskList byprocessor = list.getTasksByProcessor("Helmut");
		//byprocessor.printTaskList();

		//GETNUMTASKS
		//int num = list.getNumTasks();
		//System.out.print(num);

		//SORTBYDATE
		list.sortByDate();
		list.printTaskList();
	}
}
