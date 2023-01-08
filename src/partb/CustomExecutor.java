package partb;

import javax.swing.text.html.HTMLDocument;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.*;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class CustomExecutor<T>
{
    //public static PriorityBlockingQueue<Task> p = new PriorityBlockingQueue<>();

    static int min_num_of_threads = getRuntime().availableProcessors()/2;
    int max_num_of_threads = getRuntime().availableProcessors() - 1;
    //ExecutorService executor = Executors.newFixedThreadPool();
    public static PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>( min_num_of_threads, new Task());//(t1 , t2 ) -> ((Task) t1).compareTo((Task)t2));



    ThreadPoolExecutor pool = new ThreadPoolExecutor(min_num_of_threads,max_num_of_threads,300, TimeUnit.MILLISECONDS,pq);
    TaskType max_priority;

    public Future<T> submit (Task t)
    {
        return pool.submit(t);
    }

    public Future<T> addTask (Task t , TaskType taskType)
    {
        return submit(Task.createTask(t,taskType));
    }
    public Future<T> addTask (Task t)
    {
        return submit(Task.createTask(t));
    }

//    public void queqe(Task t)
//    {
//        p.add(t);
//    }
//public int compare(Task<T> o1 , Task<T> o2)
//{
//    if (o1.tasktype.getPriorityValue() > o2.tasktype.getPriorityValue())
//    {
//        return 1;
//    }
//    else if (o1.tasktype.getPriorityValue() == o2.tasktype.getPriorityValue())
//    {
//        return 0;
//    }
//    else
//    {
//        return -1;
//    }

}


