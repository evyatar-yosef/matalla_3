package part_b;

import java.util.concurrent.*;

import static java.lang.Runtime.getRuntime;

/**
 *  type of ThreadPool that runs Task operations asynchronously according to priority.
 *  holds a priority queue for performing tasks.
 * @param <T>
 */
public class CustomExecutor<T> extends ThreadPoolExecutor
{
    static int min_num_of_threads = getRuntime().availableProcessors()/2; //The minimum number of threads in the collection of threads
    static int max_num_of_threads = getRuntime().availableProcessors()/2; //The maximum number of threads in the collection of threads
    int [] priority_arr = new int[10]; // array for keep what is the max priority in the queue.

    /**
     * constructor for create CustomExecuter object.
     * use super to access the constructor of the class ThreadPoolExecutor from which we inherited
     * pass min/max threads in the queue, How long a thread can be without a task assigned to it,
     * the priority queue and comperator for compare 2 AdapterToTask objects.
     */
    public CustomExecutor()
    {
        super(min_num_of_threads,max_num_of_threads,300,
                TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>(getRuntime().availableProcessors()/2,
                        (t1 , t2 ) -> ((AdapterToTask) t1).compareTo((AdapterToTask) t2))); //This comparator function is defined as a lambda.
    }

    /**
     * submit the task to the threadpool and execute it and then return a future with the returned value from the task
     * also add 1 to the current right place in our priority_arr.
     * @param t - the task to execute.
     * @return - Future - holds the returned value.
     */
    public Future<T> submit (Task t)
    {
        priority_arr[t.tasktype.getPriorityValue()]++;
        return super.submit(t);
    }

    /**
     * A method whose purpose is to submit to the queue an operation that can be performed asynchronously with the addition of TaskType
     * @param t - the task to execute.
     * @param taskType - the priority of the task.
     * @return - Future - holds the returned value.
     */
    public  Future<T> submit (Callable t , TaskType taskType)
    {

        Callable task = Task.createTask(t,taskType);
        return submit((Task)task);
    }

    /**
     * A method whose purpose is to submit to the priority queue an operation that can be performed asynchronously without a TaskType as a parameter
     * @param t  the task to submit
     * @return - Future - holds the returned value.
     */
    public Future<T> submit (Callable t)
    {
        Callable task = Task.createTask(t); // use createTask to create a callable task.
        return submit((Task)task); // Task submit the task type to the threadpool and execute it and then return a future.
    }

    /**
     * A method that will loop over priority_arr and return the highest priority task in the queue in o(1).
     * @return - the nax priority.
     */
    public int getCurrentMax()
    {
        for (int i = 0; i <10 ; i++)
        {
           if(priority_arr [i] != 0 )
           {
               return i; // return max current priority.
           }

        } return 0;
    }

    /**
     * A method that terminates an instance of the CustomExecutor type.
     * before that the method make sure that: no more task enter to the queue, all the task in the queue execute
     * finish of all tasks currently in execution in the CustomExecutor's thread pool
     */
    public void gracefullyTerminate()
    {
        super.shutdown();
    }

    /**
     * this method invoke before the task execute and responsible to decrease by 1
     * the priority from the appropriate place in the array priority_arr.
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        AdapterToTask adapt = AdapterToTask.class.cast(r); // cast runable type to AdapterToTask type.
        Callable call = adapt.getCall(); // return callable object from the AdapterToTask.
        Task task = (Task) call; // cast callable to task

        priority_arr[task.tasktype.getPriorityValue()]--; // decrease the priority by 1 from the
    }

    /**
     * The method takes a Callable as a parameter, and it returns a new RunnableFuture that wraps the Callable.
     * @param callable the callable task being wrapped
     * @return
     * @param <T>
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable)
    {
            return new AdapterToTask(callable);
    }

}


