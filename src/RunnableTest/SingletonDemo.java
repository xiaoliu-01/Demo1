package RunnableTest;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: LLXX
 * @Date: 2020/11/23 15:36
 * @Description:
 */
public class SingletonDemo {

        private volatile static SingletonDemo uniqueInstance;

        private SingletonDemo() {
        }

        public static SingletonDemo getUniqueInstance() {
            //先判断对象是否已经实例过，没有实例化过才进入加锁代码
            if (uniqueInstance == null) {
                //类对象加锁
                synchronized (SingletonDemo.class) {
                    if (uniqueInstance == null) {
                        uniqueInstance = new SingletonDemo();
                    }
                }
            }
            return uniqueInstance;
        }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                uniqueInstance = new SingletonDemo();
                System.out.println(uniqueInstance);
            },"AAA").start();
            new Thread(()->{
                try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println(getUniqueInstance());
            },"BBB").start();

        }
//        new Thread(() -> System.out.println("利用Runnable创建线程,我叫"+Thread.currentThread().getName()),"线程AAA").start();
//        System.out.println("我是主线程:"+Thread.currentThread().getName());
    }
}
