package part_a;

public class Main
{
    public static void main(String[] args) throws Exception {
        Ex2_1 ex1 = new Ex2_1();
           String test [] = new String[5];
           test = Ex2_1.createTextFiles(15,500,100);
        Ex2_1.getNumOfLines(test);

       // part_a.Ex2_1.getNumOfLinesThreadPool(test);
        Ex2_1.getNumOfLinesThreads(test);

       // long start_time = System.currentTimeMillis();
        // for run method 2.
         // part_a.Ex2_1.getNumOfLines(test);

       //     part_a.Ex2_1.getNumOfLinesThreads(test);
//        long finish_time = System.currentTimeMillis();
//        long total_time = finish_time -start_time;
//        System.out.println("total time for method 2: " + total_time + "ms");
         // for run method 3.
         // ex1.getNumOfLinesThreads(test);

         // for run method 4.
         // ex1.getNumOfLinesThreadPool(test);

    }
}