package team.web.tool;

import org.hibernate.Session;

import team.web.Entity.EmailEntity;
import team.web.Service.EmailService;

public class ToolTimer extends Thread{
	public volatile boolean stop=false;
	public volatile boolean canDelete=true;
	
	public ToolTimer() {
		
	}
	public void run() {
		int count=0;
		while(!stop&&count<120) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			System.out.println(count);
		}
		if(canDelete) {
			//**
			System.out.println(Global.code);
			Global.code=null;
		}	  
	}
}
