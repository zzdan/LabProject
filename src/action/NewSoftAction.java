package action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.glassfish.jersey.internal.util.Base64;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;

import com.opensymphony.xwork2.ActionSupport;

import common.openstack.databaseManager.DatabaseManagerApi;

public class NewSoftAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String image;
	private String uu;
	private String pp;
	private String tt;
	private String imageid;
	private String flavor;
	private String flavorid;
	private int disk;
	private String hostname;
	private String neicun;
	private String neihe;
	private String systemname;

	private Integer[] software;
	private StringBuffer softInstall;

	@JSON(serialize = false)
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@JSON(serialize = false)
	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	@JSON(serialize = false)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@JSON(serialize = false)
	public Integer[] getSoftware() {
		return software;
	}

	public void setSoftware(Integer[] software) {
		this.software = software;
	}

public String execute() throws Exception {
	System.out.println(hostname);
	System.out.println(flavor);
	System.out.println(image);
	try{
		System.out.println(flavor);
		if(flavor.equals("低配")){
			flavorid="2";
		    neicun="2048MB";
	        neihe="1";
	        disk=20;
		}else if(flavor.equals("中配")){
			flavorid="3";
			neicun="4096MB";
	        neihe="2";
	        disk=40;
		}else if(flavor.equals("高配")){
			flavorid="4";
			neicun="8192MB";
	        neihe="4";
	        disk=80;
		}
		System.out.println(neicun);
		System.out.println(neihe);
		System.out.println(flavorid);
		System.out.println(disk);
		
		if(image.equals("0")){
			imageid="c7fe57be-b31d-46ff-ad45-40678920e7b6";
		    systemname="ubuntu14";
		}else if(image.equals("1") ){
			imageid="2d8b2564-61c7-4e87-b0db-28298d29bb3f";
		    systemname="centos7";
		}else if(image.equals("2")){
			imageid="678c0758-d7be-47a0-8a6e-7aa0d5be3612";
		    systemname="win7";}
		
	    }catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(systemname);
		System.out.println(imageid);
	  /*OSClient os = OSFactory.builder()
				.endpoint("http://116.56.140.61:5000/v2.0")
				.credentials("zehang","zehang")
				.tenantName("zehang")
				.authenticate();*/
	  /*List<? extends Volume> volumes = os.blockStorage().volumes().list();
	  int v_num=volumes.size();
	  int number=v_num+1;
	  System.out.println(number);
	  Volume v = os.blockStorage().volumes()
		             .create(Builders.volume()
		                .name("v"+number)
		                .size(disk)
		                .volumeType("rbd")
		                .description("Bootable install volume")
		                .imageRef(imageid)
		                .bootable(true)
		                .build()
		             );
	  System.out.println(v.getName());*/
		/*BlockDeviceMappingBuilder blockDeviceMappingBuilder = Builders.blockDeviceMapping() 
                .uuid("7d51e377-fea5-4408-ac18-9cfdca678d4a")
                .deviceName("/dev/vda")
                .bootIndex(0);	
		StringBuffer accountset;
		accountset=new StringBuffer();
		accountset.append("#!/bin/sh\n");
		accountset.append("passwd ubuntu<<EOF\n");
		accountset.append("ubuntu\n");
		accountset.append("ubuntu\n");
		accountset.append("EOF\n");
		accountset.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
		accountset.append("service ssh restart\n");
		String userdata=Base64.encodeAsString(accountset.toString().getBytes());
		//System.out.println(userdata);
		List<? extends Server> servers = os.compute().servers().list();
		System.out.println(servers.size());
		List<String> net =new ArrayList<String>();
		net.add("0e029cc2-e03a-4816-af3f-0dc051859737");
		ServerCreate sc = (ServerCreate) Builders.server().name(hostname).flavor(flavorid).networks(net).userData(userdata).blockDevice(blockDeviceMappingBuilder.build()).build();
		Server server = os.compute().servers().boot(sc);
		List<? extends Server> servers1 = os.compute().servers().list();
		System.out.println(servers1.size());*/
	  /*  ServerCreate sc = Builders.server()
              .name("hostname")
              .flavor("flavorId")
              .image("imageId")
              .addPersonality("/etc/motd", "Welcome to the new VM! Restricted access only")
              .build();*/

/*//Boot the Server
Server server = os.compute().servers().boot(sc);
		DatabaseManagerApi.addVm("liyi","vm2","2","4096MB","ubuntu14","0601e77d-5bdf-4da6-9663-09d74f80850a");
		//DatabaseManagerApi.addVm("liyi","vm1","2","4096MB","ubuntu14","0601e77d-5bdf-4da6-9663-09d74f80850a");
		System.out.println(os.compute().servers().list().get(0).getAddresses().getAddresses().get("private").get(0).getAddr());
		System.out.println(os.compute().servers().list().get(0).getId());
	} catch (Exception e){
		e.printStackTrace();
	}
	//softInstall=new StringBuffer();
*/

	  return SUCCESS;
	  }

}
