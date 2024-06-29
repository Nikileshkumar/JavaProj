package org.example;

import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class VirtualThreadExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //completableFutureExample();
        //virtualExample();
        //virtualWithCompletable();
        CompletableFuture.supplyAsync(() ->{
            Thread.currentThread().setDaemon(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("child Thread");
            return null;
        });
        System.out.println("main Thread");
    }

    private static void virtualWithCompletable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread());
            return "future1";
        }, executorService);
        //future1.complete("hello");
        System.out.println(future1.get());
    }

    private static void virtualExample() throws InterruptedException {
        IntStream.range(1, 1000).forEach(x ->
                Thread.ofVirtual().name(STR."vt \{x}")
                        .start(() -> System.out.println(STR."\{Thread.currentThread().getName()}")));
        System.out.println(Thread.currentThread().getName());
    }

    private static void completableFutureExample() throws InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "future2";
        });

        future1.thenCombine(future2, (x, y) -> x + y).thenAccept(System.out::println).join();
    }
}
