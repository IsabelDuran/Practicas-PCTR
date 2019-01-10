package dekker;

/* Copyright (C) 2006 M. Ben-Ari. See copyright.txt */
/* Programmed by Panu Pitkämäki */

/* algDekker's algorithm */
/**
 * @author isa
 *
 */
class algDekker {
    /* Number of processes currently in critical section */
    static volatile int inCS = 0;
    /* Process p wants to enter critical section */
    static volatile boolean wantp = false;
    /* Process q wants to enter critical section */    
    static volatile boolean wantq = false;
    /* Process r wants to enter critical section */    
    static volatile boolean wantr = false;
    /* Which processes turn is it */
    static volatile int turn = 1;

    /**
     * @author isa
     *
     */
    class P extends Thread {
        /* (non-Javadoc)
         * @see java.lang.Thread#run()
         */
        public void run() {
            while (true) {
                /* Non-critical section */
                wantp = true;
                while (wantq || wantr) {
                    if (turn != 1) {
                        wantp = false;
                        while (turn != 1)
                            Thread.yield();
                        wantp = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                turn = 2;
                wantp = false;
            }
        }
    }
    
    /**
     * @author isa
     *
     */
    class Q extends Thread {
        /* (non-Javadoc)
         * @see java.lang.Thread#run()
         */
        public void run() {
            while (true) {
                /* Non-critical section */
                wantq = true;
                while (wantp || wantr) {
                    if (turn != 2) {
                        wantq = false;
                        while (turn != 2)
                            Thread.yield();
                        wantq = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                turn = 3;
                wantq = false;
            }
        }
    }
    
    /**
     * @author isa
     *
     */
    class R extends Thread {
        /* (non-Javadoc)
         * @see java.lang.Thread#run()
         */
        public void run() {
            while (true) {
                /* Non-critical section */
                wantr = true;
                while (wantq || wantp) {
                    if (turn != 3) {
                        wantr = false;
                        while (turn != 3)
                            Thread.yield();
                        wantr = true;
                    }
                }
                inCS++;
                Thread.yield();
                /* Critical section */
                System.out.println("Number of processes in critical section: "
                        + inCS);
                inCS--;
                turn = 1;
                wantr = false;
            }
        }
    }

    /**
     * 
     */
    algDekker() {
        Thread p = new P();
        Thread q = new Q();
        Thread r = new R();
        p.start();
        q.start();
        r.start();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new algDekker();
    }
}