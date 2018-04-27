package com;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.util.*;

public class OsdManager extends ActionSupport{
	
	private static final long serialVersionUID = 3817877247941416228L;

	private InputStream inputStream;
	private int HOST_NUM = 3;
	private String[] HOST = {"10.0.0.7","10.0.0.11","10.0.0.20"};
	private String[] HOST_NAME = {"monitor","node1","node2"};
 	
	public InputStream getInputStream() {
		return inputStream;
	}

	public String addOSD() throws Exception
	{
		
		
		int num = getOsdNum();
		String exec=Shell.exec(HOST[num%HOST_NUM], "ceph", "ceph", 22, 
				"sudo mkdir /var/local/osd"+num);
		System.out.println(exec);
		
		StringBuffer buffer = new StringBuffer(); 
		String tmp="0";
		
	
		Process pro = Runtime.getRuntime().exec(new String[]{"cd ~/cluster",
				"ceph-deploy --overwrite-conf osd prepare "+HOST_NAME[num%HOST_NUM]+":"+"/var/local/osd"+num,
				"ceph-deploy osd activate "+HOST_NAME[num%HOST_NUM]+":"+"/var/local/osd"+num
				,"ceph osd tree"});
		pro.waitFor();  
		InputStream in = pro.getInputStream();  
        BufferedReader read = new BufferedReader(new InputStreamReader(in));  
        while((tmp=read.readLine())!=null)
        {
        	System.out.println(tmp);
        	buffer.append(tmp);
        }
		
		inputStream = new ByteArrayInputStream("success".toString().getBytes("UTF-8")); 
		return SUCCESS;
	}
	
	int getOsdNum() throws Exception
	{
		System.out.println("getNum");
		StringBuffer buffer = new StringBuffer(); 
		String tmp="0";
		InputStream in = null;  
		Process pro = Runtime.getRuntime().exec(new String[]{"ceph osd stat"});
		pro.waitFor();  
        in = pro.getInputStream();  
        BufferedReader read = new BufferedReader(new InputStreamReader(in));  
        while((tmp=read.readLine())!=null)
        {
        	System.out.println(tmp);
        	buffer.append(tmp);
        }
        
        String str = buffer.toString();
        int p = str.indexOf(": ");
		p+=2;
		String result="";
		while(true)
		{
			char c = str.charAt(p);
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
		return num;
	}
	
}
