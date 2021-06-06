package RunnableTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: LLXX
 * @Date: 2020/11/6 16:05
 * @Description:
 */
class SellTicket{
    int a = 30 ;
    final Lock lock = new ReentrantLock();
   public void purchase(){
       lock.lock();
       try {
            if(a > 0){
                System.out.println("当前线程名："+Thread.currentThread().getName()+"\t 卖出编号为"+(a--)+"的票 \t 剩余票数"+a);
            }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }
   }

}
@FunctionalInterface
interface A{
    int add(int a,int b);
    default int mv(int a,int b){
        System.out.println("乘");
        return a*b;
    }

    static int div(int a,int b){
        System.out.println("除法");
        return a/b;
    }
}

public class Ticket {


    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        /**
         * 线程创建口诀： 线程，操作，资源类
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    sellTicket.purchase();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    sellTicket.purchase();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 1; i < 40; i++) {
                    sellTicket.purchase();
                }
            }
        },"C").start();
    //lambda表达式写法： 拷贝小括号（），写死右箭头->，落地大括号{...}
//        new Thread(() -> {
//            for (int i = 1; i <= 40; i++) sellTicket.purchase();
//        }, "A").start();
//        new Thread(() -> {
//            for (int i = 1; i <= 40; i++) sellTicket.purchase();
//        }, "B").start();
//        new Thread(() -> {
//            for (int i = 1; i <= 40; i++) sellTicket.purchase();
//        }, "C").start();


//        A a1 = (a,b) -> {
//            System.out.println("加法");
//            return a+b;
//        };
//
//        System.out.println(a1.add(1,2));
//        System.out.println(a1.mv(4,2));
//        System.out.println(A.div(4,2));

    }
}