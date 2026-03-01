package DSA;

import java.util.concurrent.*;

public class ThreadPractise {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Callable<Integer> task = () -> {
            try {
                Thread.sleep(3);
                return 2;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Future<Integer> future1 = service.submit(task);
        Future<Integer> future2 = service.submit(task);
        Future<Integer> future3 = service.submit(task);
        System.out.println("future1" + future1.get() + " future 2" + future2.get() + "future3" + future3.get());

        CompletableFuture.supplyAsync(() -> 9000 + Math.random() * 1000)
                .thenApply(data-> data*2).thenAccept(data-> System.out.println("Final Result: "+ data));
    }


}
