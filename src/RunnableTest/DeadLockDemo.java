package RunnableTest;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: LLXX
 * @Date: 2020/11/20 18:58
 * @Description:
 */
class DeadDemo implements Runnable {
    String lockA;
    String lockB;

    public DeadDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t 我自己持有锁" + lockA + "尝试取获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t 我自己持有锁" + lockB + "尝试取获取" + lockA);
            }
        }

    }
}

// 死锁Demo jps -l :查看进程, jstack 进程ID :jstack能得到运行java程序的java stack和native stack的信息
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadDemo(lockA, lockB), "Thread——AAA").start();
        new Thread(new DeadDemo(lockB, lockA), "Thread——BBB").start();


    }
}
