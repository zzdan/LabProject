package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

import org.apache.catalina.util.StringParser;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.OSFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.util.Shell;

import common.VM;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> json;
	private String newdir;
	private String result;
	private String[] rbdinfo;

	private String poolName;
	private Integer pg_num;
	private Integer pgp_num;
	private List<VM> vms;
	private String pool_name;
	private String rbd_name;
	private Integer rbd_size;
	private List<String[]> poolinfos;
	private List<String[]>voluminfos;
	private List<String[]>vmsinfos;
	private List<String[]>treeinfos;
	private String tree;
	public ManageAction() {
		// TODO Auto-generated constructor stub
		json =new HashMap<String, Object>();
	}

	public String manageosd() throws Exception {

		//System.out.println("test");
		return super.execute();
	}
	public String getpoolinfo() throws Exception {
		String exec = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "ceph osd dump |grep pool");
		String[] strArr = exec.split("\n");
		List<String[]>poolMap=new ArrayList<String[]>();
		if(exec.trim().equals("")==false){
			//System.out.println(strArr.length);
			for(int i=0;i<strArr.length;i++){
				String []onepool=strArr[i].split(" ");
				String[] oneresut={"","","","",""};
				oneresut[0]=onepool[2].substring(1, onepool[2].length()-1);
				oneresut[1]=onepool[5];
				oneresut[2]=onepool[9];
				oneresut[3]=onepool[13];
				oneresut[4]=onepool[15];
				poolMap.add(oneresut);
			}
		}
		setPoolinfos(poolMap);
		return super.execute();
	}

	public String newpool()throws Exception{
		String exec1 = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "ceph osd pool create "+pool_name+" "+pg_num+" "+pgp_num);
		if(exec1==null){
			json.put("result",0);
		}else
		{
			json.put("result",1);
		}
		return super.execute();
	}
	
	public String removepool()throws Exception{
		 String exec1 = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "ceph osd pool delete "+pool_name+" "+pool_name+" --yes-i-really-really-mean-it");
		 if(exec1==null){
				json.put("result",0);
			}else
			{
				json.put("result",1);
			}
			return super.execute();
	}
	
	
	public String managerbd() throws Exception {
		String exec1 = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "rados lspools");
		String[] strArr = exec1.split("\n");
		setRbdinfo(strArr);
		return super.execute();
	}

	public String getState(String serviceId) {

		OSClient os = OSFactory.builder().endpoint("http://116.56.140.61:5000/v2.0").credentials("liyi", "liyi")
				.tenantName("liyi").authenticate();
		return os.compute().servers().get(serviceId).getVmState();
	}

	public String managevolumes() throws Exception {
		OSClient os = OSFactory.builder().endpoint("http://116.56.140.61:5000/v2.0").credentials("zehang", "zehang")
				.tenantName("zehang").authenticate();
		List<? extends Volume> volumes = os.blockStorage().volumes().list();
		List<String[]>volumeList=new ArrayList<String[]>();
		for (Volume volume : volumes) {
			String[] voluminfo={"","","","","","","","","",""};
//			System.out.println(volume);
			voluminfo[0]=volume.getId();
			voluminfo[1]=volume.getName();
			voluminfo[2]=volume.getDescription();
			voluminfo[3]=volume.getStatus().toString();
			voluminfo[4]=String.valueOf(volume.getSize());
			voluminfo[5]=volume.getZone();
			voluminfo[6]=volume.getCreated().toString();
			voluminfo[7]=volume.getVolumeType();
			voluminfo[8]=volume.getImageRef();
			voluminfo[9]=volume.getMetaData().toString();
			volumeList.add(voluminfo);
		}
		setVoluminfos(volumeList);
		return super.execute();
	}

	
	
	
	
	public String managevms() throws Exception{
		OSClient os = OSFactory.builder().endpoint("http://116.56.140.61:5000/v2.0").credentials("zehang", "zehang")
				.tenantName("zehang").authenticate();
		List<? extends Server> servers = os.compute().servers().list();
		List<? extends Image> images = os.compute().images().list();
		List<String[]>vmslist=new ArrayList<String[]>();
		HashMap<String, String>imageMap=new HashMap<String,String>();
		for (Image image : images) {
			imageMap.put(image.getId(), image.getName());

		}
		for (Server server : servers) {
//			System.out.println(server);
			String[]vms={"","","","","","","","","","","","",""};
			vms[0]=server.getId();
			vms[1]=server.getName();
			vms[2]=imageMap.get(server.getImageId());
			vms[3]=server.getFlavorId();
			vms[4]=server.getStatus().toString();
			vms[5]=server.getUserId();
			vms[6]=server.getCreated().toString();
			vms[7]=server.getUpdated().toString();
			vms[8]=server.getHostId();
			vms[9]=server.getAddresses().getAddresses().get("private").get(0).getAddr();
			vms[10]=server.getAddresses().getAddresses().get("private").get(0).getMacAddr();
			vms[11]=server.getPowerState();
			vms[12]=server.getVmState();
			vmslist.add(vms);
		}
		setVmsinfos(vmslist);
		return super.execute();
	}
	

	
	public String manageimage() throws Exception{
	OSClient os = OSFactory.builder().endpoint("http://116.56.140.61:5000/v2.0").credentials("zehang", "zehang")
			.tenantName("zehang").authenticate();
	List<? extends Image> images = os.compute().images().list();
	List<String[]>imagelist=new ArrayList<String[]>();
	for (Image image : images) {
//		System.out.println(image);
		String[]imageinfo={"", "", "", "", "","","", "",""};
		imageinfo[0]=image.getId();
		imageinfo[1]=image.getName();
		imageinfo[2]=String.valueOf(image.getStatus());
		imageinfo[3]=String.valueOf(image.isSnapshot());
		imageinfo[4]=String.valueOf(image.getSize());
		imageinfo[5]=String.valueOf(image.getCreated());
		imagelist.add(imageinfo);
	}
	json.put("list", imagelist);
	return super.execute();
}

	
	
	public String get_rbd_list() throws Exception {
		String exec2 = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "rbd -p " + poolName + " ls");
		String[] strArr = exec2.split("\r\n");
		List<String[]> list = new ArrayList<>();
		if(exec2.trim().equals("")==false){
			for (int i = 0; i < strArr.length; i++) {
				String exec3 = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22,
						"rbd info " + poolName + "/" + strArr[i]);
				Pattern p1 = Pattern.compile("size\\s(.*?)\\sin");
				Matcher m1 = p1.matcher(exec3);
				String size = "";
				while (m1.find()) {
					size = m1.group(1);
				}
				// System.out.println(size);
				Pattern p2 = Pattern.compile("order\\s(\\d+)\\s");
				Matcher m2 = p2.matcher(exec3);
				String bianhao = "";
				while (m2.find()) {
					bianhao = m2.group(1);
				}
				// System.out.println(bianhao);
				Pattern p3 = Pattern.compile("format:\\s(\\d+)\\s");
				Matcher m3 = p3.matcher(exec3);
				String form = "";
				while (m3.find()) {
					form = m3.group(1);
				}
				// System.out.println(form);
				String[] rbd_list = new String[] { strArr[i], size, bianhao, form };
				list.add(rbd_list);
			}
		}
		json.put("list", list);
		return super.execute();
	}
	
	public String newrbd() throws Exception{
		String exec = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "rbd create --size "+rbd_size+" " + pool_name + "/"+rbd_name);
		if(exec==null){
			json.put("result",0);
		}else
		{
			json.put("result",1);
		}
		return super.execute();
	}
	
	public String getcrushmap() throws Exception{
		String exec1 = Shell.exec("116.56.140.61", "ceph", "111hadoop", 22, "ceph osd tree");

	    String[] strArr = exec1.split("\n");
	    List<String []>treelist=new ArrayList<>();
	    for (int i=1;i<strArr.length;i++) {
	    	
	    	String [] data=strArr[i].trim().split(" ");
	    	 String[] oneline={"","","","","","",""};
	    	 int len=0;
	    	for (int j=0;j<data.length;j++) {
	    		if(data[j].trim().equals("")){
	    			
	    		}else {
	    			if(j==6){
	    				data[j+1]=data[j+1]+" "+data[j];
	    			}else {
	    				System.out.print(j+"     "+data[j]+"  ");
		    			oneline[len]=data[j];
		    			len++;
					}
				}
			}
	    	System.out.println("");
	    	treelist.add(oneline);
		}
		setTreeinfos(treelist);

		return super.execute();
	}
	

	public String newosd() throws Exception {
		result = getNewdir();
		//System.out.println(result);
		return super.execute();
	}

	public String getNewdir() {
		return newdir;
	}

	public void setNewdir(String newdir) {
		this.newdir = newdir;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String[] getRbdinfo() {
		return rbdinfo;
	}

	public void setRbdinfo(String[] rbdinfo) {
		this.rbdinfo = rbdinfo;
	}

	public Map<String, Object> getJson() {
		return json;
	}

	public void setJson(Map<String, Object> json) {
		this.json = json;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public List<VM> getVms() {
		return vms;
	}

	public void setVms(List<VM> vms) {
		this.vms = vms;
	}

	public String getPool_name() {
		return pool_name;
	}

	public void setPool_name(String pool_name) {
		this.pool_name = pool_name;
	}

	public String getRbd_name() {
		return rbd_name;
	}

	public void setRbd_name(String rbd_name) {
		this.rbd_name = rbd_name;
	}

	public Integer getRbd_size() {
		return rbd_size;
	}

	public void setRbd_size(Integer rbd_size) {
		this.rbd_size = rbd_size;
	}

	public List<String[]> getPoolinfos() {
		return poolinfos;
	}

	public void setPoolinfos(List<String[]> poolinfos) {
		this.poolinfos = poolinfos;
	}

	public Integer getPg_num() {
		return pg_num;
	}

	public void setPg_num(Integer pg_num) {
		this.pg_num = pg_num;
	}

	public Integer getPgp_num() {
		return pgp_num;
	}

	public void setPgp_num(Integer pgp_num) {
		this.pgp_num = pgp_num;
	}

	public List<String[]> getVoluminfos() {
		return voluminfos;
	}

	public void setVoluminfos(List<String[]> voluminfos) {
		this.voluminfos = voluminfos;
	}

	public List<String[]> getVmsinfos() {
		return vmsinfos;
	}

	public void setVmsinfos(List<String[]> vmsinfos) {
		this.vmsinfos = vmsinfos;
	}

	public List<String[]> getTreeinfos() {
		return treeinfos;
	}

	public void setTreeinfos(List<String[]> treeinfos) {
		this.treeinfos = treeinfos;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}


}
