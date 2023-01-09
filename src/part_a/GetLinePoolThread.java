package part_a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class GetLinePoolThread implements Callable<Integer>
{
    String path;

    public GetLinePoolThread (String filename)
    {
        this.path = filename;
    }

    /**
     * this call method count number of lines in text file.
     * @return num of lines.
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        int count = 0; // int to count the number of lines.
        File f1 = new File(path); // create new file according to the file path
        Scanner sc = null;
        try {
            sc = new Scanner(f1); // create scanner to go ovr the text lines.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            sc.nextLine();
            count++;
        }
        //System.out.println("num of lines: "+ count);

        return count;
    }
}
