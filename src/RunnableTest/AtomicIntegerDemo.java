package RunnableTest;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Auther: LLXX
 * @Date: 2020/11/19 10:42
 * @Description:
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        /*AtomicInteger atomicInteger = new AtomicInteger(100);
        new Thread(()->{
            System.out.println("当前线程为："+Thread.currentThread().getName());
            System.out.println("初始值为："+atomicInteger.get());
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
            System.out.println("交换后值为："+atomicInteger.get());
        },"AA").start();

        new Thread(()->{
            System.out.println("当前线程为："+Thread.currentThread().getName()+"\t"+atomicInteger.compareAndSet(100, 2019));
            System.out.println("修改值后为："+atomicInteger.get());
        },"BB").start();*/

        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 1);

        new Thread(()->{
            System.out.println("当前线程为："+Thread.currentThread().getName());
            System.out.println("初始值为："+reference.getReference()+" 当前版本号为:"+reference.getStamp());
            reference.compareAndSet(100,101,1,2);
            reference.compareAndSet(101,100,2,3);
            System.out.println("交换后值为："+reference.getReference()+"\t 当前版本号为："+reference.getStamp());
        },"AA").start();

        new Thread(()->{
            System.out.println("当前线程为："+Thread.currentThread().getName()+"\t 修改是否成功:"+reference.compareAndSet(100, 2019,1,2));
            System.out.println("当前最新版本号为："+reference.getStamp()+"\t 修改值后为："+reference.getReference());
        },"BB").start();

    }
}
