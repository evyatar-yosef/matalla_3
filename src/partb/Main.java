package partb;

public class Main {
public static void main(String[] args) throws Exception {

    var task = Task.factory(()->{
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        return sum;
    }, TaskType.COMPUTATIONAL);

    }
}
