package RunnableTest;

/**
 * @Auther: LLXX
 * @Date: 2020/11/8 15:45
 * @Description:
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  阻塞队列:
       1.  抛出异常
             当阻塞队列满时，再往队列里add插入元素会抛IllegalStateException:Queue full
             当阻塞队列空时，再往队列里remove移除元素会抛NoSuchElementException
       2.  特殊值
             插入方法，成功ture失败false
             移除方法，成功返回出队列的元素，队列里没有就返回null
       3.  一直阻塞
             当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据or响应中断退出
             当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用
       4.  超时退出
            当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        // 抛出异常
        /*queue.add("A");
        queue.add("B");
        queue.add("C");
//        queue.add("A");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
//        System.out.println(queue.remove());*/

        // 特殊值
        /*System.out.println(queue.offer("A"));
        System.out.println(queue.offer("B"));
        System.out.println(queue.offer("C"));
        System.out.println(queue.offer("D"));
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());*/

        // 阻塞等待
        /*queue.put("A");
        queue.put("B");
        queue.put("C");
//        queue.put("D");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
//        System.out.println(queue.take());*/

        // 超时退出
        queue.offer("A",2L, TimeUnit.SECONDS);
        queue.offer("A",2L, TimeUnit.SECONDS);
        queue.offer("A",2L, TimeUnit.SECONDS);
//        System.out.println(queue.offer("A", 2L, TimeUnit.SECONDS));

        queue.poll(2L,TimeUnit.SECONDS);
        queue.poll(2L,TimeUnit.SECONDS);
        queue.poll(2L,TimeUnit.SECONDS);
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));


    }
}
