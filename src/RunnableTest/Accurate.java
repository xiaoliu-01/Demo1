package RunnableTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: LLXX
 * @Date: 2020/11/7 14:34
 * @Description:
 */
// 资源类
class CircularResource
{
    private int i = 1; //1.A,2.B,3.C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    // 操作
    public void print5(){
        lock.lock();
        try {
            while (i != 1){
                condition1.await();
            }

            for (int j = 1; j <= i*5; j++) {
                System.out.println(Thread.currentThread().getName()+"\t "+j);
            }

            i = 2;
            // 精准唤醒2号线程
            condition2.signal();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (i != 2){
                condition2.await();
            }

            for (int j = 1; j <= 10; j++) {
                System.out.println(Thread.currentThread().getName()+"\t "+j);
            }

            i = 3;
            // 精准唤醒3号线程
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (i != 3){
                condition3.await();
            }

            for (int j = 1; j <= 15; j++) {
                System.out.println(Thread.currentThread().getName()+"\t "+j);
            }

            i = 1;
            // 精准唤醒1号线程
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}



public class Accurate {
    public static void main(String[] args) {
        CircularResource circularResource = new CircularResource();

        new Thread(() ->{
            for (int k = 0; k < 10; k++) {
                try {
                    circularResource.print5();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() ->{
            for (int k = 0; k < 10; k++) {
                try {
                    circularResource.print10();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() ->{
            for (int k = 0; k < 10; k ++) {
                try {
                    circularResource.print15();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
