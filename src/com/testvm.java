package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.glassfish.jersey.internal.util.Base64;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.openstack.OSFactory;

import common.openstack.database.ImageChain;
import common.openstack.database.OpenstackVm;
import common.openstack.databaseManager.DatabaseManagerApi;

public class testvm {
public static void main(String[] args){
	/*ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  
	  for (int i = 0; i < 10; i++) {  
	   final int index = i;  
	   fixedThreadPool.execute(new Runnable() {  
	    public void run() {  
	     try {  
	      System.out.println(index);  
	      Thread.sleep(2000);  
	     } catch (InterruptedException e) {  
	      e.printStackTrace();  
	     }  
	    }  
	   });  
	  }  */

//	long start;
//	long end;
//	long use_time=0;
//	
//	Date a = new Date();
//	start=a.getTime();
    OSClient os = OSFactory.builder()
			.endpoint("http://116.56.140.61:5000/v2.0")
			.credentials("liyi","liyi")
			.tenantName("liyi")
			.authenticate();
//	String hostname="oee";
//	StringBuffer accountset;
//	String flavorid="2";
//	String imageid="c7fe57be-b31d-46ff-ad45-40678920e7b6";
//	List<String> net =new ArrayList<String>();
//	net.add("0e029cc2-e03a-4816-af3f-0dc051859737");
//	String ip="";
//	accountset=new StringBuffer();
//	accountset.append("#!/bin/sh\n");
//	accountset.append("passwd ubuntu<<EOF\n");
//	accountset.append("ubuntu\n");
//	accountset.append("ubuntu\n");
//	accountset.append("EOF\n");
//	accountset.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
//	accountset.append("service ssh restart\n");
//	String userdata=Base64.encodeAsString(accountset.toString().getBytes());
//	System.out.println(os.compute().servers().list().get(0).getAddresses().getAddresses().get("private").get(0).getAddr());
//	Date b=new Date();
//	end=b.getTime();
//	use_time=end-start;
//	System.out.println(use_time);
	/*try{
	ServerCreate sc = Builders.server().name(hostname).userData(userdata).networks(net).flavor(flavorid).image(imageid).build();
	Server server=os.compute().servers().boot(sc);
	for(int i=0;i<100;i++){
		i++;
	}
	System.out.println(server.getAddresses().getAddresses().get("private").get(0).getAddr());
    System.out.println(server.getId());
	//System.out.println(os.compute().servers().list().get(0).getAddresses().getAddresses().get("private").get(0).getAddr());
    //System.out.println(ip);
	}catch (NullPointerException e) {
		e.printStackTrace();
	}*/
	
}
}
