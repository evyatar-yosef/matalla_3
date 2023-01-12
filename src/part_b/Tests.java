package part_b;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.*;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
    public static final Logger logger =  LoggerFactory.getLogger(Tests.class);


    @Test
    /**
     * in this class we create new Task using different ways our code can,
     * sumbiting the tasks, and check if the task realy execute.
     */
    public void createand_sumbit()
    {
        CustomExecutor customExecutor = new CustomExecutor();

        Task task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;

        }, TaskType.COMPUTATIONAL);
        Future<Integer> sum = customExecutor.submit(task);


        Callable<String> callable = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Future<String> reverseTask = customExecutor.submit(callable, TaskType.OTHER);

        Future <Double> priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);

        Future <Double> priceTask1 = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        });


        final Double price,price2;
        final String reversed;
        final Integer totalsum;


        try
        {
            reversed = reverseTask.get();
            price =  priceTask.get();
            price2 =  priceTask1.get();
            totalsum = sum.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(e);
        }

       // assertEquals(reversed.toString(),sb.reverse().toString());
        assertEquals(55, totalsum);
        assertEquals(1000 * Math.pow(1.02, 5),price);
        assertEquals(1000 * Math.pow(1.02, 5),price2);
        assertEquals("ZYXWVUTSRQPONMLKJIHGFEDCBA",reversed);

        customExecutor.gracefullyTerminate();
    }

    /**
     * in this test we check our queue, for that we fill the queue with task and check if the max priority is different from zero,
     * and in the end we check if the max priority is zero (tells that all the tasks executed).
     * @throws InterruptedException
     */
    @Test
    public void queue_check() throws InterruptedException {
       CustomExecutor customExecutor = new CustomExecutor();

        Task task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        Future sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum =(int) sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        customExecutor.getCurrentMax();
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Future sumTask1 = customExecutor.submit(task);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Future priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            sleep(1000);
            return sb.reverse().toString();
        };
        Callable<Integer> sleep2 = ()-> {
            sleep(2000);
            return 1;
        };

        Future reverseTask1 = customExecutor.submit(callable1, TaskType.OTHER);
        Future reverseTask2 = customExecutor.submit(callable2, TaskType.OTHER); // 1
        Future sumTask2 = customExecutor.submit(task,TaskType.IO);
        Future reverseTask3 = customExecutor.submit(callable2, TaskType.IO);  //1
        Future reverseTask4 = customExecutor.submit(callable2, TaskType.OTHER);//1
        Future sumTask3 = customExecutor.submit(task,TaskType.COMPUTATIONAL);
        Future sumTask4 = customExecutor.submit(task,TaskType.IO);
        Future reverseTask6 = customExecutor.submit(callable2, TaskType.OTHER);//1
        Future sumTask5 = customExecutor.submit(task,TaskType.COMPUTATIONAL);
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());

        Future sleep = customExecutor.submit(sleep2,TaskType.OTHER);//2
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());


        Future priceTask4= customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.IO);

        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());

        Future reverseTask9 = customExecutor.submit(callable2, TaskType.IO);//1
        Future reverseTask10 = customExecutor.submit(callable2, TaskType.IO);//1
        Future reverseTask11 = customExecutor.submit(callable2, TaskType.IO);//1


        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());


        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            reversed = (String)reverseTask2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        sleep(2000);
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());

        customExecutor.gracefullyTerminate();
    }
}
