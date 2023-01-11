package part_a;

public class Main
{
    public static void main(String[] args) throws Exception {
        //Ex2_1 ex1 = new Ex2_1();
        String test [] = new String[1000];
        test = Ex2_1.createTextFiles(1000,200,100000);
        Ex2_1.getNumOfLines(test);
        test = Ex2_1.createTextFiles(1000,200,100000);
        Ex2_1.getNumOfLinesThreads(test);
        test = Ex2_1.createTextFiles(1000,200,100000);
        Ex2_1.getNumOfLinesThreadPool(test);

    }
}