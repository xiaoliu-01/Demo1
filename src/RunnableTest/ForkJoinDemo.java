package RunnableTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: LLXX
 * @Date: 2020/11/9 14:25
 * @Description:
 */
class MyTask extends RecursiveTask<Integer>
{
    private final static Integer INIT_VALUES = 10;
    private int begin;
    private int end;
    private int result;
    MyTask(){}

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if((end - begin) <= INIT_VALUES){
            for (int i = begin; i <= end; i++) {
                 result += i;
            }
        }else {
            int middle = (begin + end)/2;
            MyTask myTask1 = new MyTask(begin,middle);
            MyTask myTask2 = new MyTask(middle+1,end);
            myTask1.fork();
            myTask2.fork();
            result = myTask1.join() + myTask2.join();
        }
        return result;
    }
}

/**
 * Fork：把一个复杂任务进行分拆，大事化小
 * Join：把分拆任务的结果进行合并
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println(Runtime.getRuntime().availableProcessors());
        MyTask myTask = new MyTask(0, 100000);
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = pool.submit(myTask);
        int integer = submit.get();
        System.out.println(integer);
        pool.shutdown();
    }
}
