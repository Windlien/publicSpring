package cn.itcast.service.itcastserviceprovider;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest extends Thread{
    static void test() {
        //yield 返回到就绪状态.会调用起其它线程。
        //sleep 休息一会
        //join 加入。在主线程里，t1.join(t2.join(t3.join())),那么顺序是t3\t2\t1执行完了，才会继续执行主线程.
        //让线程正常结束就是关闭。不建议stop。 interrupt 然后catch exception 去处理，但极少用来控制业务逻辑
    }

    /*
    *  synchronized (Object)
    * 不能用String常量及基础数据类型 Integer Long
    * “obejct”
    * */
    //synchronized 加锁，锁是o。线程去访问的时候，先拿到锁才能执行。锁的是对象的前2位。
    //synchronized 保证了原子性、可见性、可重入锁。
    // 锁定方法 非锁定方法同时执行
    /*锁升级
    * sync(Object)
    * markword 记录这个线程ID （偏向锁）
    * 如果线程争用：升级为 自旋锁 （占cpu） 执行时间短（加锁代码），线程数少，用自旋
    * 10次以后：升级为重量级锁 -os （进入排队） 时间长，线程数多，用系统锁 sync
    * */
    private Object o = new Object();
    private int count =10;
    public void m(){
        synchronized(o){ //synchronized(this)   也行 ，或者在方法加synchronized
            count--;
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadTest().start();
        new Thread(new ThreadTest()).start();
        new Thread(()->{
            System.out.println("hello lambda");
        });
        List<Object> list = new ArrayList<>();
        while(true){
            list.add(list);
            Thread.sleep(5000);
        }
    }
}
