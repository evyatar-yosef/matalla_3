package partb;

import javax.swing.text.html.HTMLDocument;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.*;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class CustomExecutor<T> extends ThreadPoolExecutor
{
    //public static PriorityBlockingQueue<Task> p = new PriorityBlockingQueue<>();

     int min_num_of_threads = getRuntime().availableProcessors()/2;
     int max_num_of_threads = getRuntime().availableProcessors()/2;
     public  PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>( min_num_of_threads,(t1 , t2 ) -> ((Task) t1).compareTo((Task)t2));

     int [] priority_arr = new int[10];


   // ThreadPoolExecutor pool = new ThreadPoolExecutor(min_num_of_threads,max_num_of_threads,300, TimeUnit.MILLISECONDS,pq);
  //  int max_priority;

    public CustomExecutor()
    {
        super(getRuntime().availableProcessors()/2,getRuntime().availableProcessors()/2,300,
                TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>(  getRuntime().availableProcessors()/2,(t1 , t2 ) -> ((Task) t1).compareTo((Task)t2)));

    }
    public Future<T> submit (Task t)
    {
        priority_arr[t.tasktype.getPriorityValue()]++;
        return super.submit(t);
    }

    public  Future<T> submit (Callable t , TaskType taskType)
    {
        Callable task = Task.createTask(t,taskType);
        return submit(task);
    }
    public Future<T> submit (Callable t)
    {
        Callable task = Task.createTask(t);
        return submit(task);
    }

    public int getCurrentMax()
    {
        for (int i = priority_arr.length; i <= 0  ; i++)
        {
           if(priority_arr[i] != 0 ) return priority_arr[i];

        } return 0;
    }

    public void gracefullyTerminate()
    {
        super.shutdown();
    }

//    @Override
//    protected void afterExecute(Runnable r, Throwable t)
//    {
//        int priority = ((Task)r).tasktype.getPriorityValue();
//        priority_arr[priority]--;
//    }

    @Override
    protected void beforeExecute(Thread t, Runnable r)
    {
        int priority = ((Task)r).tasktype.getPriorityValue();
        priority_arr[priority]--;
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable)
    {
            TaskType type = TaskType.OTHER;
            return new FutureTask<T>(Task.createTask(callable,type));
    }

}

