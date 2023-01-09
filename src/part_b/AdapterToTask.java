package part_b;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class AdapterToTask extends FutureTask implements Comparable<AdapterToTask> , Runnable
{
    public Callable call;

    public AdapterToTask(Callable call)
    {
        super(call);
        this.call = call;
    }

    @Override
    public int compareTo(AdapterToTask o)
    {
        if (((Task)(call)).tasktype.getPriorityValue() > ((Task)o.getCall()).tasktype.getPriorityValue())
        {
            return 1;
        }
        else if (((Task)(call)).tasktype.getPriorityValue() == ((Task)o.getCall()).tasktype.getPriorityValue())
        {
            return 0;
        }

        return -1;
    }

    public Callable getCall()
    {
        return call;
    }

}
