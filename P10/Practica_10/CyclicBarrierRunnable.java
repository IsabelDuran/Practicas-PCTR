import java.util.concurrent.*;

public class CyclicBarrierRunnable implements Runnable{

    CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;

    public CyclicBarrierRunnable(
            CyclicBarrier barrier1,
            CyclicBarrier barrier2) {

        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +
                                " waiting at barrier 1");
            this.barrier1.await();

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +
                                " waiting at barrier 2");
            this.barrier2.await();

            System.out.println(Thread.currentThread().getName() +
                                " done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

  public static void main(String[] args){
    CyclicBarrier barrier1 = new CyclicBarrier(2);
    CyclicBarrier barrier2 = new CyclicBarrier(2);
    new Thread(new CyclicBarrierRunnable(barrier1, barrier2)).start();
    new Thread(new CyclicBarrierRunnable(barrier1, barrier2)).start();
  }
}
