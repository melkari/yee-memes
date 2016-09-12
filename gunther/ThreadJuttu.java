import java.util.Date;

class ThreadJuttu implements Runnable {
   private Thread t;
   private String threadName;
   boolean suspended = false;
   
   ThreadJuttu(String name){
       threadName = name;
   }
   public void run() {
      try {
         for(int i = 0;i < 50;i++) {
            System.out.println(new Date() + ": GUNTHER TULE TAKAISIN!");
            Thread.sleep(5000);
			synchronized(this) {
				while(suspended) {
				   wait();
				}
			}
         }
		} catch (InterruptedException e) {
			System.out.println(threadName + " interrupted.");
		}
   }
   
   public void start ()
   {
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
   
   void suspend() {
      suspended = true;
   }
   
   synchronized void resume() {
      suspended = false;
      notify();
   }

}