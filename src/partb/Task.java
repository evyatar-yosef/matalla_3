package partb;

import java.util.Comparator;
import java.util.concurrent.Callable;

public class Task <T> implements Callable<T>, Comparator<Task> {
     protected  TaskType tasktype;
     //private int TypePriority;
     //MyFunctionalInterface lambda;
     private Callable<T> call;

    private Task(Callable<T> c,TaskType tasktype)
    {
        this.tasktype = tasktype;
        this.call = c;

    }
    private Task(Callable<T> c)
    {
        this.tasktype = tasktype.OTHER ;
        this.call = c;
    }
    protected Task()
    {

    }
    public static Task createTask (Callable c,TaskType tasktype)
    {
        return new Task(c,tasktype);
    }
    public static Task createTask (Callable c)
    {
        return new Task(c);
    }


//    public int compareTo(Task other)
//    {
//        if(this.tasktype.getPriorityValue()>other.tasktype.getPriorityValue()) return 1;
//            else if (this.tasktype.getPriorityValue()<other.tasktype.getPriorityValue())
//            return -1;
//         return 0;
//    }
    @Override
    public T call() throws Exception
    {
        call.call();
        return null;
    }


    @Override
    public int compare(Task t1,Task  t2) {
        if(t1.tasktype.getPriorityValue()>t2.tasktype.getPriorityValue()) return 1;
           else if (t1.tasktype.getPriorityValue()<t2.tasktype.getPriorityValue())
            return -1;
         return 0;
    }
}
