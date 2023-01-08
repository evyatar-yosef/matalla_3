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

    public void run()
    {
        int count = 0;
        File f1 = new File(path);
        Scanner sc = null;
        try
        {
            sc = new Scanner(f1);
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
        //System.out.println("num of lines: "+ count);
        //return count;

    }


//    public int start() {
//        int x =call();
//        return x;
//    }
}

