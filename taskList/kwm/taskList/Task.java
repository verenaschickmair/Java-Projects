/*-------------------------------------------------------------
 * Task.java
 * Author: Verena Schickmair
 * Date: 06.05.2020
 * Task: Task 6
 * 
 * This class sets constructors for the object Task. One constructor is
 * for the user to enter the values. The other one is to
 * copy a Task in a new Object Task. Also, the values of the
 * tasks will be printed in the console with the method
 * printTask().
 * 
 * Methods:
 *	printTask(): Prints the values of a Task, Return: void
 * -----------------------------------------------------------*/

package kwm.taskList;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Task {
	public String id;
	public String description;
	public String processor;
	public boolean status;
	public GregorianCalendar dueDate;
	public String formatedDueDate;
	public Task next;

	//Constructor
	public Task(String id, String description, String processor, 
			boolean status, int year, int month, int day) {
		this.id = id;
		this.description = description;
		this.processor = processor;
		this.status = status;
		this.dueDate = new GregorianCalendar(year,month-1,day);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.formatedDueDate = sdf.format(dueDate.getTime());
		this.next = null;
	}

	//To copy a Task object: Constructor
	public Task(Task task) {
		this.id = task.id;
		this.description = task.description;
		this.processor = task.processor;
		this.status = task.status;

		this.dueDate = new GregorianCalendar(task.dueDate.get(GregorianCalendar.YEAR), 
				task.dueDate.get(GregorianCalendar.MONTH), 
				task.dueDate.get(GregorianCalendar.DAY_OF_MONTH));

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		this.formatedDueDate = sdf.format(dueDate.getTime());
		this.next = task.next;
	}

	//printTask()
	//Prints the values of a Task
	//Return: void
	public void printTask() {
		System.out.println("ID: " + this.id + " | Description: " + this.description + " | Processor: " + this.processor);
		System.out.println("Status: " + this.status + " | Fälligkeitsdatum: " + this.formatedDueDate);
		System.out.println("----------");
	}
}