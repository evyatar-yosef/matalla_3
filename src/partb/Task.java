package partb;

import java.util.concurrent.Callable;

public class Task <T>  implements Callable<T>

{
     private TaskType tasktype;
     //private int TypePriority;
    MyFunctionalInterface lambda;

    public Task(MyFunctionalInterface lam,TaskType tasktype)
    {
        this.tasktype = tasktype;
        this.lambda = lam;
        System.out.println(lambda.generic());
    }
    public Task(MyFunctionalInterface lam)
    {
        this.tasktype = tasktype.COMPUTATIONAL ;
        this.lambda = lam;
    }
    public static Task factory (MyFunctionalInterface lam,TaskType tasktype)
    {
        return new Task(lam,tasktype);
    }
    public static Task factory (MyFunctionalInterface lam)
    {
        return new Task(lam);
    }

    @Override
    public T call() throws Exception {
        return null;
    }
}
