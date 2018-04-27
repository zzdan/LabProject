package action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.openstack.OSFactory;
import org.glassfish.jersey.internal.util.Base64;
import com.opensymphony.xwork2.ActionSupport;

public class NewvmAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String flavor_vm;
	private String hostname_vm;
	private String image_vm;
	private String flavorid;
	
	@JSON(serialize = false)
	public String getHostname_vm() {
		return hostname_vm;
	}

	public void setHostname_vm(String hostname_vm) {
		this.hostname_vm = hostname_vm;
	}

	@JSON(serialize = false)
	public String getFlavor_vm() {
		return flavor_vm;
	}

	public void setFlavor_vm(String flavor_vm) {
		this.flavor_vm = flavor_vm;
	}

	@JSON(serialize = false)
	public String getImage_vm() {
		return image_vm;
	}

	public void setImage_vm(String image_vm) {
		this.image_vm = image_vm;
	}
	public String execute() throws Exception {
		 List<String> softtarget = new ArrayList<String>();
		 if (flavor_vm.equals("低配")) {
				flavorid = "2";
			} else if (flavor_vm.equals("中配")) {
				flavorid = "3";
			} else if (flavor_vm.equals("高配")) {
				flavorid = "4";
			}
			
			
		 OSClient os = OSFactory.builder().endpoint("http://116.56.140.61:5000/v2.0").credentials("zehang", "zehang")
					.tenantName("zehang").authenticate();
			StringBuffer accountset = null;
			List<String> net =new ArrayList<String>();
			net.add("0e029cc2-e03a-4816-af3f-0dc051859737");
			String ip="";
			if(image_vm.equals("c7fe57be-b31d-46ff-ad45-40678920e7b6")){
				accountset=new StringBuffer();
				accountset.append("#!/bin/sh\n");
				accountset.append("passwd ubuntu<<EOF\n");
				accountset.append("ubuntu\n");
				accountset.append("ubuntu\n");
				accountset.append("EOF\n");
				accountset.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
				accountset.append("service ssh restart\n");
			}
			if(image_vm.equals("2d8b2564-61c7-4e87-b0db-28298d29bb3f")){
				accountset=new StringBuffer();
				accountset.append("#!/bin/sh\n");
				accountset.append("passwd ubuntu<<EOF\n");
				accountset.append("ubuntu\n");
				accountset.append("ubuntu\n");
				accountset.append("EOF\n");
				accountset.append("sed -i 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config\n");
				accountset.append("service ssh restart\n");
			}
     		String userdata=Base64.encodeAsString(accountset.toString().getBytes());
			try{
				ServerCreate sc =null;
				if(image_vm.equals("1ea0e8c0-75e2-4aaa-a7ca-6abb8ec38300")){
					sc= Builders.server().name(hostname_vm).networks(net).flavor(flavorid).image(image_vm).build();
				}else {
				   sc = Builders.server().name(hostname_vm).userData(userdata).networks(net).flavor(flavorid).image(image_vm).build();

				}
			Server server=os.compute().servers().boot(sc);
			for(int i=0;i<1000;i++){
				i++;
			}
		    System.out.println(server.getId());
			}catch (NullPointerException e) {
				e.printStackTrace();
				return ERROR;
			}
			return SUCCESS;
	
	}
	
	
	
}
