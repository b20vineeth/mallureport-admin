package com.easypick.admin.admin.job;

public class ThreadPoolTaskSchedulerExamples  implements Runnable {
	String message=null;
	Boolean isActive=true;
	Integer value=0;
	public ThreadPoolTaskSchedulerExamples(String string) {
	 this.message=string;
	}
	  
	public void run() {
		if(isActive)
		{
		 System.out.println(this.message+"-"+value +" ,Active Thread : "+Thread.activeCount());
		 value++;
		 if(value>5)
		 {
			 Thread.currentThread().interrupt();
		 
		 }
		}
	}

	 
}  

	 