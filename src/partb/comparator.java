package partb;

import java.util.Comparator;

public class comparator implements Comparator<Task>
{
    @Override
    public int compare(Task o1, Task o2) {
        return 0;
    }
//
//    @Override
//    public int compare( Task other) {
//        if(this.tasktype.getPriorityValue()>other.tasktype.getPriorityValue()) return 1;
//        else if (this.tasktype.getPriorityValue()<other.tasktype.getPriorityValue())
//            return -1;
//        return 0;
//    }
}
