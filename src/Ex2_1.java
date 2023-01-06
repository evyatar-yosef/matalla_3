import java.io.*;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;

public class Ex2_1
{
    public static String[] createTextFiles(int n, int seed, int bound) throws IOException
    {
        Random rand = new Random(seed);
        String file_name[] = new String[n];
        for (int i = 0; i < n; i++)
        {
            File file1 = new File("C:\\Users\\97252\\Desktop\\classes b\\oop\\matala_3\\" + "file_"+(i+1)+".txt");
            try
            {
                file1.createNewFile();
                //System.out.println(file1.getAbsolutePath());
                file_name[i] = file1.getAbsolutePath();

                FileWriter fw = new FileWriter( file_name[i]);
                PrintWriter pw = new PrintWriter(fw);
                int x = rand.nextInt(bound);
                String s = "hello world";
                for (int j = 0; j <x ; j++)
                {
                    pw.println(s);
                }
                //System.out.println("file num "+i+"created");
                pw.println(s);
                pw.close();
                fw.close();

            }
            catch (IOException e)
            {
                System.err.println("asasass");
            }
        }

       //GetLineThread g1 = new GetLineThread();
        //int c = getNumOfLines(file_name);


        return file_name;

    }
/////////////////////////////////////////////////////////////////////////////////////////////////
    public static int getNumOfLines(String[] fileNames)
    {
        long start_time = System.currentTimeMillis();
        int length = fileNames.length;
        int count , sum = 0;
        try{
        for (int i = 0; i <length; i++)
           {
               count = 0;
               File f1 = new File(fileNames[i]);
               Scanner sc = new Scanner(f1);
               while(sc.hasNextLine())
               {
                   sc.nextLine();
                   count ++;
               }
               //System.out.println("num of lines: "+ count);
               sum = sum + count;
               //System.out.println(sum);

           }

        }
        catch (Exception e)
        {
           // throw new RuntimeException(e);
            System.err.println("asasass");
        }
        System.out.println("total sum:"+sum);

        long finish_time = System.currentTimeMillis();
        long total_time = finish_time -start_time;
     //   System.out.println("total time for method 2: " + total_time + "ms");
        return sum;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        long start_time = System.currentTimeMillis();
        GetLineThread[] threadArray = new GetLineThread[fileNames.length];
        int sum = 0;
        for (int i = 0 ; i < fileNames.length ; i++)
        {
            GetLineThread t1 = new GetLineThread(fileNames[i]);
            threadArray [i] = t1;
            try
            {
               t1.start();
            }
            catch (Exception e)
            {
                System.err.println("eroor in start");
                throw new RuntimeException(e);
            }

           // System.out.println("sum: "+ sum);
        }

        //  System.out.println(sum);

        for (int i = 0 ; i < threadArray.length ; i++)
        {
             try
             {
                 threadArray[i].join();
                 sum = sum + threadArray[i].getlines();
             }
             catch (Exception e)
             {
                 System.err.println("eroor in join");
             }
        }
        System.out.println(sum);
        long finish_time = System.currentTimeMillis();
        long total_time = finish_time - start_time;
        System.out.println("total time for method 3: " + total_time + "ms");
        return sum;

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public static int getNumOfLinesThreadPool(String[] fileNames)  {
        long start_time = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);//create thread pool
        List<Future<Integer>> Threadlist = new ArrayList<Future<Integer>>();
        int sum = 0;
        for (int i = 0; i < fileNames.length ; i++)
        {
            try
            {
                Callable<Integer> t1  = new GetLinePoolThread(fileNames[i]);
                Future<Integer> future  =  executor.submit(t1);
                Threadlist.add((Future<Integer>) future);
            }
            catch (Exception e)
            {
                System.err.println("error in join");
            }

        }

      //  List<Future<Integer>> future = executor.invokeAll(Threadlist);

        for(Future<Integer> fut : Threadlist)
        {
            try
            {
                int count = fut.get();
               // System.out.println("num of lines: "+ count);

                sum = sum + count;
            }
            catch (ExecutionException | InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
        System.out.println("sum: "+ sum);
        executor.shutdown();
        long finish_time = System.currentTimeMillis();
        long total_time = (finish_time -start_time) ;
        System.out.println("total time for method 4: " + total_time + "ms");

        return sum;
    }
    }


