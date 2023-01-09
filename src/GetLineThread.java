import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class GetLineThread extends Thread
{
    String path;
    int numOfLines = 0;
    public GetLineThread (String filename)
    {
        this.path = filename;
    }

    public int getlines()
    {
        return numOfLines;
    }

    /**
     * this run method count number of lines in text file.
     */
    public void run()
    {
        int count = 0; //int to count the number of lines.
        File f1 = new File(path); // create new file according to the file path
        Scanner sc = null;
        try
        {
            sc = new Scanner(f1);  // create scanner to go ovr the text lines.
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        while(sc.hasNextLine())
        {
            sc.nextLine();
            count ++;
        }
        numOfLines = count;
    }

}