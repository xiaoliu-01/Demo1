package RunnableTest;

import java.util.concurrent.*;

/**
 * @Auther: LLXX
 * @Date: 2020/11/8 21:25
 * @Description:
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,2L,
                TimeUnit.SECONDS,new  LinkedBlockingQueue(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <=10 ; i++) {
            poolExecutor.execute(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 受理");
            });
        }
        poolExecutor.shutdown();
    }
}
