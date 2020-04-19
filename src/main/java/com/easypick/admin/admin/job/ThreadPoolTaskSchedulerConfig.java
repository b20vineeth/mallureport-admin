package com.easypick.admin.admin.job;
 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler; 

@Configuration 
public class ThreadPoolTaskSchedulerConfig     {
	 @Bean
	    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
	        ThreadPoolTaskScheduler tpts = new ThreadPoolTaskScheduler();
	        tpts.setPoolSize(3);
	        tpts.setRemoveOnCancelPolicy(true); 
	        tpts.setRejectedExecutionHandler(
	                (r, e) ->System.out.println("Execution of task {} was rejected for unknown reasons."+r.toString())
	        );
	        return tpts;
	    }

	    
}
