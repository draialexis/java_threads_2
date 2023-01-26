import java.util.Random;
import java.util.concurrent.Callable;

public class Singe implements Callable<Integer> {
    Random random = new Random();

    @Override
    public Integer call() throws Exception {
        return random.nextInt(10);
    }
}
