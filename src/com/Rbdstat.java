package com;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.util.Shell;

public class Rbdstat {
	private static final long serialVersionUID = 3817877247941416228L;
	private InputStream inputStream;	
	public InputStream getInputStream() {
		return inputStream;
	}
	public static void main(String[] args) throws IOException{
		String hostname="10.0.0.20";
		String dir="/var/local/osd5";
		InputStream inputStream;
		String exec=Shell.exec(hostname, "ceph", "ceph", 22, 
				"sudo mkdir"+dir);
		System.out.println(exec);
		StringBuffer buffer = new StringBuffer(); 
		String tmp="0";
		Shell.exec("10.0.0.7", "ceph", "ceph", 22, 
				"cd ~/cluster");	
		Process pro = Runtime.getRuntime().exec(new String[]{"cd ~/cluster",
				"ceph-deploy --overwrite-conf osd prepare "+hostname+":"+dir,
				"ceph-deploy osd activate "+hostname+":"+dir
				,"ceph osd tree"}); 
		try
		{
			pro.waitFor();
		}
		catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}  
		InputStream in = pro.getInputStream();  
        BufferedReader read = new BufferedReader(new InputStreamReader(in));  
        while((tmp=read.readLine())!=null)
        {
        	System.out.println(tmp);
        	buffer.append(tmp);
        }		
		inputStream = new ByteArrayInputStream("success".toString().getBytes("UTF-8")); 
		
	}
    

}


