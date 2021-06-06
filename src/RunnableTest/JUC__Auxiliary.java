package RunnableTest;

/**
 * @Auther: LLXX
 * @Date: 2020/11/8 10:15
 * @Description:
 */

import utils.SeasonUtil;

import java.util.concurrent.CountDownLatch;

/**
 *  1、 CountDownLatch (减少计数): 在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 *   1.1 原理：   * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 *               * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 *               * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 *
 *  2、 CyclicBarrier (循环栅栏) : 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
*                                  在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 *                                  因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier
 *   2.2 原理： * CyclicBarrier
 *              * 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，
 *              * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
 *              * 直到最后一个线程到达屏障时，屏障才会开门，所有
 *              * 被屏障拦截的线程才会继续干活。
 *              * 线程进入屏障通过CyclicBarrier的await()方法。
 *
 *  3、Semaphore (信号灯): 一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，
*                          然后再获取该许可。每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实
 *                          际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 *    3.3 原理： 在信号量上我们定义两种操作：
 *              * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 *              * 要么一直等下去，直到有线程释放信号量，或超时。
 *              * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 *              * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 *
 */
public class JUC__Auxiliary {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println(Runtime.getRuntime().freeMemory()/(1024*1024)+"MB");
//        System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024)+"MB");
//        System.out.println(Runtime.getRuntime().totalMemory()/(1024*1024)+"MB");
        CountDownLatch latch = new CountDownLatch(4);
        for (int i = 1; i <=4 ; i++) {
            new Thread(() ->{
//                System.out.println(Thread.currentThread().getName()+"\t 离开了教室");
                System.out.println(Thread.currentThread().getName()+"季节已过");
                latch.countDown();
            }, SeasonUtil.getSeasonBySeasonCode(i).getSeasonMessage()).start();
        }
        latch.await();
//        System.out.println("班长锁门");
        System.out.println("一年四季已过完");

        /*CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{System.out.println("7颗龙珠已收集，可召唤神龙");});
        for (int i = 1; i <=7 ; i++) {
            new Thread(() ->{
                try {
                    System.out.println(Thread.currentThread().getName()+"号龙珠已收集");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }*/

        /*Semaphore semaphore = new Semaphore(3,false);
        for (int i = 1; i <=5 ; i++) {
            new Thread(() -> {
                try {
                    // 信号量减1
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "已抢占到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 信号量释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }*/

    }
}
