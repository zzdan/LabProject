package com;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.util.Shell;  
  
public class HealthStatus {  
    public static void main(String args[]) {   
            //cluster status
    		String exec=Shell.exec("10.0.0.7", "ceph", "ceph", 22, 
    				"ceph status");
            //System.out.println(exec);     
    		StringBuffer buffer1 = new StringBuffer(); 		
    		Pattern p1 = Pattern.compile("HEALTH_(.*?)\\b");
    		Matcher m1 = p1.matcher(exec);
    		while (m1.find()) {
    			buffer1.append(m1.group(1));
    		} 
    		String str1 = buffer1.toString();
    		System.out.println(str1);
    		
    		//mon status
    		String[] content=exec.split("\n");
     		String mondata=content[2];
    		 int p = mondata.indexOf(": ");
    			p+=2;
    			String result="";
    			while(true)
    			{
    				char c = mondata.charAt(p);
    				if(c!=' ')
    				{
    					result += c;
    				}
    				else
    				{
    					break;
    				}
    				p++;
    			}
    	        int num = Integer.parseInt(result);
    	        System.out.println(num);
                
    	        //osd status
    	        Pattern p2 = Pattern.compile(":\\s(\\d+)\\sosds:\\s(\\d+)\\sup,\\s(\\d+)\\sin\\b");
        		Matcher m2 = p2.matcher(exec);
        		while (m2.find()) {
        			String osdsum=m2.group(1);
        			int number_osd=Integer.parseInt(osdsum);
        			System.out.println(number_osd);
        			String osdup=m2.group(2);
        			int number_up=Integer.parseInt(osdup);
        			System.out.println(number_up);
        			String osdin=m2.group(3);
        			int number_in=Integer.parseInt(osdin);
        			System.out.println(number_in);
        		} 
        		
        		//mds status
       		    String mdsdata="mdsmap e95: 1/1/1 up {0=node2=up:active}, 1 up:standby";
                // String mdsdata=content[4];      		
        		int mds_exist=mdsdata.indexOf("mdsmap");
        		int number_mds=0;
        		if(mds_exist==-1){
        			System.out.println(number_mds);
        			
        		}
        		else{
        			 Pattern p3 = Pattern.compile(":\\s(\\d+)/(\\d+)/");
             		 Matcher m3 = p3.matcher(mdsdata);
                     while(m3.find())
             		{
             			String mdssum=m3.group(1);
             			number_mds=Integer.parseInt(mdssum);
             			System.out.println(number_mds);      			
             			String mds_up=m3.group(2);
             			int number_up=Integer.parseInt(mds_up);
             			System.out.println(number_up);
             		}
        			
        		} 
        		String exec1 = Shell.exec("10.0.0.49", "ceph", "111hadoop", 22, "ceph status");
        		// System.out.println(exec1);
        		Pattern p4 = Pattern.compile("HEALTH_(.*?)\\b");
        		Matcher m4 = p4.matcher(exec1);
        		String stat="";
        		int mon_exist=content[2].indexOf("monmap");
        		String[] info=new String[10];
        		while (m4.find())
        		{
        			stat=m4.group(1);
        		}
        		
        		if(mon_exist!=-1){
        			System.out.println(stat);
        		}     			
        		else{
        			int i=2;
        			int j=0;
        			while(mon_exist==-1){
        				info[j]=content[i];
        				i++;
        				j++;        				
        			}
        			for(int m=0;m<=j;m++){
        				System.out.println(info[i]);
        			}
        		}
        		String exec5 = Shell.exec("10.0.0.7", "ceph", "ceph", 22, "ceph mon dump");
        		//String exec1 = Shell.exec("10.0.0.49", "ceph", "111hadoop", 22, "ceph health");
        		// System.out.println(exec1);
        	    Pattern p5 = Pattern.compile("dumped monmap epoch\\s(\\d+)\\b");
        		Matcher m5= p5.matcher(exec5);
        	    int mon_map=0;
        		while (m5.find())
        		{
        			mon_map = Integer.parseInt(m5.group(1));
        		}
        		Pattern p6 = Pattern.compile("epoch\\s(\\d+)\\b");
        		Matcher m6= p6.matcher(exec5);
        	    int epo=0;
        		while (m6.find())
        		{
        			epo = Integer.parseInt(m6.group(1));
        		}
        		Pattern p7 = Pattern.compile("(\\d+):(.*?):");
        		Matcher m7= p7.matcher(exec5);
        	    int quo=0;
        		while (m7.find())
        		{
        			quo = Integer.parseInt(m7.group(1));
        		}

        		System.out.println(mon_map);
        		System.out.println(epo);
        		System.out.println(quo);
        		
        		

        		
    		
      }
}  

