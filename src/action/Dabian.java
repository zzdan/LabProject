package action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.glassfish.jersey.internal.util.Base64;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.actions.LiveMigrateOptions;
import org.openstack4j.openstack.OSFactory;

public class Dabian {
	private static StringBuffer logininfo;
	public static void main(String[] args){
		long start;
		long end;
		long use_time=0;
		OSClient os = OSFactory.builder()
				.endpoint("http://116.56.140.61:5000/v2.0")
				.credentials("liyi","liyi")
				.tenantName("liyi")
				.authenticate();
		/*logininfo=new StringBuffer();
			logininfo.append("#!/bin/sh\n");
			logininfo.append("passwd ubuntu<<EOF\n");
			logininfo.append("ubuntu\n");
			logininfo.append("ubuntu\n");
			logininfo.append("EOF\n");
			logininfo.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
			logininfo.append("service ssh restart\n");
		List<String> net =new ArrayList<String>();
		net.add("0e029cc2-e03a-4816-af3f-0dc051859737");
		String userdata=Base64.encodeAsString(logininfo.toString().getBytes());*/
/*	    ServerCreate sc = Builders.server().name("da").userData(userdata).flavor("2").networks(net).image("c7fe57be-b31d-46ff-ad45-40678920e7b6").build();
	    Date a = new Date();
		start=a.getTime();
	    Server server=os.compute().servers().boot(sc);
	    Date b = new Date();
	 	end=b.getTime();
	 	use_time=end-start;
	 	System.out.println(use_time);*/
		
	
		/*LiveMigrateOptions targethost=LiveMigrateOptions.create().host("compute1");
		os.compute().servers().liveMigrate(os.compute().servers().get("0").getId(),targethost);*/
		
		String hostname=os.compute().servers().list().get(0).getName();
		String hostid=os.compute().servers().list().get(0).getId();
		Date a = new Date();
		start=a.getTime();
		LiveMigrateOptions targethost=LiveMigrateOptions.create().host("compute1");
		os.compute().servers().liveMigrate(hostid,targethost);
	    Date b = new Date();
		end=b.getTime();
		use_time=end-start;
		System.out.println("time:"+use_time);
		System.out.println("end");
		//System.out.println(targethost);
		//System.out.println(hostname+":"+hostid);
		
	}
}
	

