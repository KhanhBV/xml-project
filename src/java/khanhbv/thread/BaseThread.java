/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.thread;

/**
 *
 * @author buivankhanh
 */
public class BaseThread extends Thread {

    public final static Object LOCK = new Object();

    private static BaseThread instance;
    private static boolean suspend = false;

    public BaseThread() {
    }

    public static BaseThread getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new BaseThread();
            }
        }
        return instance;
    }

    public static boolean isSuspend() {
        return suspend;
    }

    public static void setSuspend(boolean suspend) {
        BaseThread.suspend = suspend;
    }
    
    public void suspendThread() {
        setSuspend(true);
        System.out.println("suspended");
    }
    
    public synchronized void resumeThread() {
        setSuspend(false);
        notifyAll();
        System.out.println("resume");
    }
    

}
