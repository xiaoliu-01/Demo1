package RunnableTest;

/**
 * @Auther: LLXX
 * @Date: 2020/11/6 19:46
 * @Description:
 */
class AirConditioner{
    int a = 0;
    public synchronized void increment() throws InterruptedException {
        while(a != 0){
            this.wait();
        }
        a++;
        System.out.println("线程名："+Thread.currentThread().getName()+"\t a当前值"+a);
        // 通知
        this.notifyAll();

    }

    public synchronized void decrement() throws InterruptedException {
        while(a == 0){
            this.wait();
        }
        a--;
        System.out.println("线程名："+Thread.currentThread().getName()+"\t a当前值"+a);
        // 通知
        this.notifyAll();
    }

}

/**
 * 判断/干活/通知
 * 线程虚假唤醒：多线程交互中，必须要防止虚假线程唤醒，可用 while () 判断避免，切记不能用if()判断
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(100);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(100);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}
