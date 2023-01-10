package part_b;

import java.util.concurrent.Callable;

/**
 * class that represent a task to execute, implements Callable and Comparable interfaces
 * @param <T>
 */
public class Task <T>  implements Callable<T>, Comparable<Task>
{
    protected  TaskType tasktype; // object thar represent the priority of the task(by numerical value).

    protected Callable<T> call; // object that return value after execute task.

    /**\
     * constructor for create Task object recieve Callable,TaskType
     * inner constructor(not visible to the user).
      * @param c - callable object
     * @param tasktype - type of the task
     */
    private Task(Callable<T> c,TaskType tasktype)
    {
        this.tasktype = tasktype;
        this.call = c;

    }

    /**
     * constructor for create Task object recieve Callable and default TaskType.
     * inner constructor(not visible to the user).
     * @param c - callable object
     */
    private Task(Callable<T> c)
    {
        this.tasktype = tasktype.OTHER; // default TaskType.
        this.call = c;
    }

    /**
     * method that create instances of the class (use by the user).
     * @param c - callable object
     * @param tasktype - type of the task
     * @return - Task object.
     */
    public static Task createTask (Callable c,TaskType tasktype)
    {
        return new Task(c,tasktype);
    }

    /**
     * method that create instances of the class (use by the user).
     * @param c - callable object.
     * @return - Task object.
     */
    public static Task createTask (Callable c)
    {
        return new Task(c);
    }

    /**
     * method for determine which task is a higher priority.
     * @param other the object to be compared.
     * @return - which task is a higher priority.
     */
    public int compareTo(Task other)
    {
        if(this.tasktype.getPriorityValue()>other.tasktype.getPriorityValue()) return 1;
        else if (this.tasktype.getPriorityValue()<other.tasktype.getPriorityValue())
            return -1;
         return 0;
    }

    /**
     *
     * @return Callable object.
     * @throws Exception
     */
    @Override
    public T call() throws Exception
    {
      return call.call();
    }


    }


