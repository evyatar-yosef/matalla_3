package part_a;

import java.io.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class Ex2_1
{
    /**
     * The function creates text files on disk and returns an array
     * of the file names, containing at least 10 characters.
     * @param n - number of text files.
     * @param seed - a positive integer that initializes a random-number generator.
     * @param bound - use Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value.
     * @return array of the files names.
     * @throws IOException
     */
    public static String[] createTextFiles(int n, int seed, int bound) throws IOException
    {
        Random rand = new Random(seed); //create random int
        String file_name[] = new String[n]; // define array size n for the files names.
        for (int i = 0; i < n; i++)
        {
            File file1 = new File("C:\\Users\\97252\\Desktop\\classes b\\oop\\" +
                    "matala_3\\" + "file_"+(i+1)+".txt");// create paths for the n new files.
            try
            {
                file1.createNewFile(); //create new file
                file_name[i] = file1.getAbsolutePath();// insert the new file in the array.

                // write x lines in the file.
                FileWriter fw = new FileWriter( file_name[i]);
                PrintWriter pw = new PrintWriter(fw);
                int x = rand.nextInt(bound);
                String s = "hello world";
                for (int j = 0; j < x ; j++)
                {
                    pw.println(s);
                }

                pw.println(s);
                pw.close();
                fw.close();

            }
            catch (IOException e)
            {
                System.err.println("error");
            }
       }

        return file_name; // return the array

    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * this method returns the total sum of the files lines.
     * @param fileNames - array of the files names.
     * @return the num of lines in the files created in the createTextFiles method.
     */
    public static int getNumOfLines(String[] fileNames)
    {
        long start_time = System.currentTimeMillis(); // get start time of the processs
        int length = fileNames.length;
        int count , sum = 0;
        try{
        for (int i = 0; i <length; i++) // loop over the array and count the number of lines in every file.
           {
               count = 0;
               File f1 = new File(fileNames[i]); // define file according to the path(name).
               Scanner sc = new Scanner(f1);     // using scanner to go over the lines.
               while(sc.hasNextLine())
               {
                   sc.nextLine();
                   count ++;
               }
               sum = sum + count;                // sum all the files num of lines.
           }
        }
        catch (Exception e)
        {
            System.err.println("error");
        }
        System.out.println("sum 2 :"+sum);

        long finish_time = System.currentTimeMillis(); // get finish time of the proccess.
        long total_time = finish_time -start_time;     // calculate the total time.
        System.out.println("total time for method 2: " + total_time + "ms");
        return sum;

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * this method returns the total sum of the files lines using Threads.
     * @param fileNames - array of the files names.
     * @return - the num of lines in the files created in the createTextFiles method.
     * @throws InterruptedException
     */
    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {

        long start_time = System.currentTimeMillis();   // get start time of the proccess.
        GetLineThread[] threadArray = new GetLineThread[fileNames.length]; // create thread array in size of num of files
        int sum = 0;
        for (int i = 0 ; i < fileNames.length ; i++)// looping over the files
        {
            GetLineThread t1 = new GetLineThread(fileNames[i]); //create a new part_a.GetLineThread and pass it the file name.
            threadArray [i] = t1; // insert the part_a.GetLineThread object to the Threas array.
            try
            {
             t1.start(); // execute the current Thread.

            }
            catch (Exception e)
            {
                System.err.println("eroor in start");
                throw new RuntimeException(e);
            }
        }

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
        System.out.println("sum 3 = "+sum);
        long finish_time = System.currentTimeMillis(); // get finish time of the proccess.
        long total_time = finish_time - start_time;    // calculate the total time.
        System.out.println("total time for method 3: " + total_time + "ms");
        return sum;

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * this method returns the total sum of the files lines using Threadpool.
     * @param fileNames - array of the files names.
     * @return  - the num of lines in the files created in the createTextFiles method.     *
     */
    public static int getNumOfLinesThreadPool(String[] fileNames)  {

        long start_time = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length); //create thread pool
        List<Future<Integer>> Threadlist = new ArrayList<Future<Integer>>(); // create a future list.
        int sum = 0;
        for (int i = 0; i < fileNames.length ; i++)
        {
            try
            {
                Callable<Integer> t1  = new GetLinePoolThread(fileNames[i]); // create callable object using part_a.GetLinePoolThread
                Future<Integer> future  =  executor.submit(t1); // sumbiting the task into future list
                Threadlist.add((Future<Integer>) future); // add the result to the threadlist
            }
            catch (Exception e)
            {
                System.err.println("error in join");
            }

        }


        for(Future<Integer> fut : Threadlist) // loop over Threadlist
        {
            try
            {
                int count = fut.get(); // get the results of the task from the threadlist.

                sum = sum + count; // sum the num of lines from the textfile
            }
            catch (ExecutionException | InterruptedException e)
            {
                throw new RuntimeException(e);
            }

        }
        System.out.println("sum 4: "+ sum);
        executor.shutdown();
        long finish_time = System.currentTimeMillis(); // get finish time of the proccess.
        long total_time = (finish_time -start_time) ;  //  calculate the total time.
        System.out.println("total time for method 4: " + total_time + "ms");
        return sum;
    }}

