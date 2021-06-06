package RunnableTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: LLXX
 * @Date: 2020/11/6 20:55
 * @Description:
 */
class ABC{
   int aa = 0;
   private Lock lock = new ReentrantLock();
   private Condition condition = lock.newCondition();
   public void increment(){
        lock.lock();
        try {
          while (aa != 0){ // 这里用if()可能导致线程虚假唤醒
               // 新版睡眠
              condition.await();
          }
          aa ++;
            System.out.println(Thread.currentThread().getName()+"\t "+aa);
            // 新版通知
            condition.signalAll();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
   }

    public void decrement(){
        lock.lock();
        try {
            while (aa == 0){
                // 新版睡眠
                condition.await();
            }
            aa --;
            System.out.println(Thread.currentThread().getName()+"\t "+aa);
            // 新版通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class NEWThreadWaitNotifyDemo {
    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(() ->{
            for (int i = 0; i < 2; i++) {
                abc.increment();
            }
        },"A").start();

//        new Thread(() ->{
//            for (int i = 0; i < 10; i++) {
//                abc.decrement();
//            }
//        },"B").start();

        new Thread(() ->{
            for (int i = 0; i < 1; i++) {
                abc.increment();
            }
        },"C").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                abc.decrement();
            }
        },"D").start();
    }
}
