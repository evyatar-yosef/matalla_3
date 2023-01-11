package part_a;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class test
{
    @Test

        public void TestNumLines() throws IOException
        {
        String test [] = Ex2_1.createTextFiles(50,100,200);
        int getNumOfLines = Ex2_1.getNumOfLines(test);
        assertEquals(50,getNumOfLines,"getNumOfLines failed");
        int GetNumOfLinesThreads = Ex2_1.getNumOfLines(test);
        assertEquals(50,GetNumOfLinesThreads,"GetNumOfLinesThreads failed");
        int GetNumOfLinesPoolThreads = Ex2_1.getNumOfLines(test);
        assertEquals(50,GetNumOfLinesThreads,"GetNumOfLinesPoolThreads failed");
        assertEquals(getNumOfLines,GetNumOfLinesThreads,GetNumOfLinesPoolThreads);
        }

        }
