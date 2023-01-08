package partb;

import java.util.concurrent.Callable;

import static partb.TaskType.*;

public class Main {
public static void main(String[] args) throws Exception {

    Callable<Double> callable1 = ()-> {
        return 1000 * Math.pow(1.02, 5);
    };
    Callable<String> callable2 = ()-> {
        StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return sb.reverse().toString();
    };

    Callable<Double> callable3 = ()-> {
        return 1000 * Math.pow(1.02, 5);
    };
    Callable<String> callable4 = ()-> {
        StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return sb.reverse().toString();
};
    Task t1 = Task.createTask(callable4,OTHER);
    Task t2 = Task.createTask(callable3,OTHER);
    Task t3 = Task.createTask(callable2,IO);
    Task t4 =Task.createTask(callable1,COMPUTATIONAL);

    CustomExecutor exe = new CustomExecutor<>();

//
//    exe.queqe(t1);
//    exe.queqe(t2);
//    exe.queqe(t3);
//    exe.queqe(t4);
//    int size = exe.p.size();
//    for (int i = 0; i <size; i++) {
//        t1 = exe.p.poll();
//        System.out.println("priority : "+ t1.tasktype.getPriorityValue() +"type : " + t1.tasktype);


    }




}
