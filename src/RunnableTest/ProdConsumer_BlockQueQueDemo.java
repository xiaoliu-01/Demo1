package RunnableTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: LLXX
 * @Date: 2020/11/20 14:13
 * @Description:
 */
class mySource
{
    private volatile static Boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> queue = null;
    public mySource(BlockingQueue<String> queue) {
        this.queue = queue;
        System.out.println(queue.getClass().getName());
    }

    // 生产
    public void myProd()throws Exception{
        String data = null;
        Boolean result = null;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            result = queue.offer(data,2L,TimeUnit.SECONDS);
            if(result){
                System.out.println(Thread.currentThread().getName()+"\t 开始生产队列"+result+"成功");
            }else{
                System.out.println("FLAG为false停止生产"+result);
            }
            try { TimeUnit.SECONDS.sleep(1L); } catch (InterruptedException e) { e.printStackTrace();}
        }
        System.out.println("老板叫停，停止生产");
    }

    // 消费
    public void myConsumer()throws  Exception{
        String result = null;
        while (FLAG){
            try { TimeUnit.SECONDS.sleep(5L); } catch (InterruptedException e) { e.printStackTrace();}
            result = queue.poll(2L, TimeUnit.SECONDS);
             if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                 System.out.println("2秒中没有队列生产，停止消费");
                 return;
             }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功！！");
        }
    }

    // 停止
    public void stop()throws  Exception{
        this.FLAG = false;
    }

}


//  Volatile/AtomicInteger/BlockQueQue/线程交互/原子引用
public class ProdConsumer_BlockQueQueDemo {
    public static void main(String[] args) throws Exception {
        mySource mySource = new mySource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            try {
                mySource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            try {
                mySource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BBB").start();
        try { TimeUnit.SECONDS.sleep(5L); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("5秒中后老板叫停！");
        mySource.stop();
    }
}
