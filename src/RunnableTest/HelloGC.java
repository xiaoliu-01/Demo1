package RunnableTest;

/**
 * @Auther: LLXX
 * @Date: 2020/11/21 20:33
 * @Description:
 */

/**
 * java -XX:+PrintCommandLineFlags -version 命令查看GC收集器
 */
public class HelloGC {
    public static void main(String[] args) {
      /*  while (true)
        {
            System.out.println(""+new Random().nextInt(999999));
        }*/

      new Thread(()->{
          System.out.println(Thread.currentThread().getName()+"开始执行");
          Thread.yield();
          System.out.println(Thread.currentThread().getName()+"正在执行");
      },"AAA").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"开始执行");
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"正在执行");
        },"BBB").start();

        System.out.println("主线程正在执行");
    }
}
