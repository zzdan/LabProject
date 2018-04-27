package com;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.util.Shell;
public class PgStatus {
	public static void main(String[] args){
		String exec5 = Shell.exec("10.0.0.7", "ceph", "ceph", 22, "ceph mon dump");
		String[] content = exec5.split("\n"); 
	    Pattern p5 = Pattern.compile("\\sepoch\\s(\\d+)\\b");
	    System.out.println(content[0]);
		Matcher m5= p5.matcher(content[0]);
	    int mon_map=0;
		while (m5.find())
		{
			System.out.println(m5.group(1));
		
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

//		System.out.println(mon_map);
		//System.out.println(epo);
		//System.out.println(quo);
		String exec7 = Shell.exec("10.0.0.7", "ceph", "ceph", 22, "ceph mds stat");
		Pattern p8= Pattern.compile("e(\\d+):(.*?)\\s(.*?)\\s\\{(\\d+)=(.*?)=(.*?)\\}");	
		Matcher m8= p8.matcher(exec7);
		while (m8.find()){
			System.out.println(m8.group(1));
			System.out.println(m8.group(2));
			System.out.println(m8.group(3));
			System.out.println(m8.group(4));
			System.out.println(m8.group(5));
			System.out.println(m8.group(6));
		}
		
	} 
}
