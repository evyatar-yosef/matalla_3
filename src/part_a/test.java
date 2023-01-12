package part_a;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

/**
 * in this test we check if all the count methods return the same value
 * and also if we realy create 50 files.
 */
public class test
{
    @Test

        public void TestNumLines() throws IOException, InterruptedException {
        String test [] = Ex2_1.createTextFiles(50,100,200);
        int getNumOfLines = Ex2_1.getNumOfLines(test);
        assertEquals(4737,getNumOfLines,"getNumOfLines failed");

        int GetNumOfLinesThreads = Ex2_1.getNumOfLinesThreads(test);
        assertEquals(4737,getNumOfLines,"GetNumOfLinesThreads failed");

       int GetNumOfLinesPoolThreads = Ex2_1.getNumOfLinesThreadPool(test);
        assertEquals(4737,getNumOfLines,"GetNumOfLinesPoolThreads failed");

        assertEquals(50,test.length, GetNumOfLinesPoolThreads);
        }

}
