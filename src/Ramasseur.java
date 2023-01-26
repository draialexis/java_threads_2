import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Ramasseur {
    private static final int BUFFER_SIZE = 10;
    private static final int NB_THREADS = 12;

    private static Integer numberOfBananasCollected = 0;
    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(NB_THREADS);

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> casier =
                new ArrayBlockingQueue<Integer>(BUFFER_SIZE);

        FutureTask<Integer> f1 = new FutureTask<>(new Singe());
        FutureTask<Integer> f2 = new FutureTask<>(new Singe());
        FutureTask<Integer> f3 = new FutureTask<>(new Singe());
        FutureTask<Integer> f4 = new FutureTask<>(new Singe());
        FutureTask<Integer> f5 = new FutureTask<>(new Singe());
        FutureTask<Integer> f6 = new FutureTask<>(new Singe());
        FutureTask<Integer> f7 = new FutureTask<>(new Singe());
        FutureTask<Integer> f8 = new FutureTask<>(new Singe());
        FutureTask<Integer> f9 = new FutureTask<>(new Singe());
        FutureTask<Integer> f10 = new FutureTask<>(new Singe());

        List<FutureTask<Integer>> futureTasks =
                new ArrayList<>(Arrays.asList(
                        f1, f2, f3, f4, f5, f6, f7, f8, f9, f10)
                );

        futureTasks.forEach(executorService::execute);

        // ARGHH!
        while (true) {
            if (futureTasks.stream().allMatch(FutureTask::isDone)) {
                System.out.println("bananas : " + numberOfBananasCollected);
                executorService.shutdown();
                return;
            }

            futureTasks.forEach(f -> {
                // this was giving unexpected results ...
                // if (!(f.isDone())) {
                try {
                    numberOfBananasCollected +=
                            f.get(1000L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException |
                        ExecutionException |
                        TimeoutException e) {
                    e.printStackTrace();
                }
                // }
            });
        }
    }
}
