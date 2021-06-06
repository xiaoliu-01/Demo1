package RunnableTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: LLXX
 * @Date: 2020/11/9 15:08
 * @Description:
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步回调
        CompletableFuture<Void> completableFuture1 =CompletableFuture.runAsync(() ->{
            System.out.println(Thread.currentThread().getName()+"completableFuture1");
        });
        completableFuture1.get();

        // 异步回调
        CompletableFuture<Integer> completableFuture2 =CompletableFuture.supplyAsync(() ->{
            System.out.println(Thread.currentThread().getName()+"completableFuture2");
//            int i = 2/0 ;
            return 1024;
        });

        completableFuture2.whenComplete((u,t) ->{
            System.out.println(t);
            System.out.println(u);
        }).exceptionally((t) ->{
            System.out.println(t);
            return  4444 ;
        });


    }
}
