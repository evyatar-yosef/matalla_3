package part_b;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * this class wrapping a Callable object with FutureTask and adding ability to sort it based on the priority of the task.
 * the class extends FutureTask and implements Comparable<AdapterToTask>,Runnable.
 * use adapter design pattern.
 */
public class AdapterToTask extends FutureTask implements Comparable<AdapterToTask>,Runnable
{
     public Callable call;

    /**
     *  constructor that takes a single Callable object, which is passed to the super constructor of FutureTask
     *  and stored in the call attribute for later access.
     * @param call -  a callable object
     */
    public AdapterToTask(Callable call)
    {
        super(call); // pass the callable object to the super constructor.
        this.call = call; // assigns the Callable parameter passed to the constructor to the call attribute.
    }

    /**
     * compare AdapterToTask objects based on the priority of their Task objects.
     * @param t the object to be compared.
     * @return which object is a higher priority.
     */
    @Override
    public int compareTo(AdapterToTask t)
    {
        if (((Task)(call)).tasktype.getPriorityValue() > ((Task)t.getCall()).tasktype.getPriorityValue())
        {
            return 1;
        }
        else if (((Task)(call)).tasktype.getPriorityValue() == ((Task)t.getCall()).tasktype.getPriorityValue())
        {
            return 0;
        }

        else return -1;
    }

    /**
     *  simple getter to access the callable object stored inside the instance.
     * @return return the callable object.
     */
    public Callable getCall()
    {
        return call;
    }

}
