package com;

import java.util.Date;

public class Cunchu {
public static void main(String args[]){
	    long start;
	    long end;
	    long use_time;
	    Date a = new Date();
		start=a.getTime();
	   for(int i=0;i<100;i++){
		  System.out.println(i);
	   }
	   
	    Date b = new Date();
	 	end=b.getTime();
	 	use_time=end-start;
}
}
