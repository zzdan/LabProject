package action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Shell;

import common.CM;

public class MonitorAction extends ActionSupport
{   
	private static final long serialVersionUID = 1L;
	private Map<String, Object> json;
	private String health;
	private Integer mon_num;
	private Integer osd_sum;
	private Integer osd_up;
	private Integer osd_in;
	private Integer mds_sum;
	private Integer mds_up;
	private Integer mds_in;
	private String tips;
	private Integer monmap;
	private Integer epoch;
	private Integer quorum;
	
	private String jsonmon;
    private String jsonosd;
    private String jsonmds;
	private Map<String, String[]> infoMap;
	private List<String[]>vmsinfos;
	
	public MonitorAction()
	{
		json = new HashMap<String, Object>();
	}

	public String showhealth() throws Exception
	{
	    String exec1 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph health");
		//String exec1 = Shell.exec("10.0.0.49", "ceph", "111hadoop", 22, "ceph health");
		// System.out.println(exec1);
	    Pattern p1 = Pattern.compile("HEALTH_(.*?)\\b");
		Matcher m1 = p1.matcher(exec1);
	    String stat="";
	    String info="";
		while (m1.find())
		{
			stat = m1.group(1);
		}
	    System.out.println(stat);
	    if(!stat.equals("OK")){
	    	int start=exec1.indexOf(" ");
		    System.out.println(exec1.indexOf(" "));
		    info=exec1.substring(start+1, exec1.length());
		    System.out.println(info);
	    }
	    String exec2 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph mon stat");
	    //String exec2 = Shell.exec("10.0.0.49", "ceph", "111hadoop", 22, "ceph mon stat");
	    Pattern p2= Pattern.compile(":\\s(\\d+)\\smons");
		Matcher m2 = p2.matcher(exec2);
		String montotal="";
		int monnum=0;
		while (m2.find())
		{
			montotal = m2.group(1);
		}
		System.out.println(montotal);
		monnum = Integer.parseInt(montotal);
		System.out.println(monnum);
		String exec3 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph osd stat");
		//String exec3 = Shell.exec("10.0.0.49", "ceph", "111hadoop", 22, "ceph osd stat");
		Pattern p3= Pattern.compile(":\\s(\\d+)\\sosds:\\s(\\d+)\\sup,\\s(\\d+)\\sin\\b");
		Matcher m3 = p3.matcher(exec3);
		int number_osd = 0;
		int up_osd = 0;
		int in_osd = 0;
		while (m3.find())
		{
			String osdsum = m3.group(1);
			number_osd = Integer.parseInt(osdsum);
			String osdup = m3.group(2);
			up_osd = Integer.parseInt(osdup);				
			String osdin = m3.group(3);
			in_osd = Integer.parseInt(osdin);				
			}
		System.out.println(number_osd);
		System.out.println(up_osd);
		System.out.println(in_osd);
		String exec4 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph mds stat");
		//String exec4 = Shell.exec("10.0.0.49", "ceph", "111hadoop", 22, "ceph mds stat");
		Pattern p4= Pattern.compile(":\\s(\\d+)/(\\d+)/(\\d+)}");
		Matcher m4 = p4.matcher(exec4);
		int number_mds = 0;
		int up_mds = 0;
		int in_mds=0;
		while (m4.find())
		{
			String mdssum = m4.group(1);
			number_mds = Integer.parseInt(mdssum);
			String mdsup = m4.group(2);
			up_mds= Integer.parseInt(mdsup);
			String mdsin = m4.group(2);
			in_mds= Integer.parseInt(mdsin);
		}
		System.out.println(number_mds);
		System.out.println(up_mds);
		setMds_sum(new Integer(number_mds));
		setMds_up(new Integer(up_mds));
		setMds_in(new Integer(in_mds));
		setOsd_sum(new Integer(number_osd));
		setOsd_up(new Integer(up_osd));
		setOsd_in(new Integer(in_osd));
		setMon_num(new Integer(monnum));
		setHealth(stat);
		setTips(info);
		ActionContext context = ActionContext.getContext();
		context.getSession().put("MON_SUM", monnum);
		return super.execute();
	}

	public String showmon() throws Exception
	{
		int num_mon=(int)ActionContext.getContext().getSession().get("MON_SUM");
		setMon_num(new Integer(num_mon));
		System.out.println(num_mon);
		String exec5 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph status -f json-pretty");
		System.out.println(exec5);
        setJsonmon(exec5);
		return super.execute();
	}

	public String showosd() throws Exception
	{
		String exec6 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph status -f json-pretty");
		setJsonosd(exec6);
		Map<String, String[]> infoMapTemp = CM.getOSDInfo("116.56.140.62", "ceph", "111hadoop");
		setInfoMap(infoMapTemp);
		return super.execute();
	}
	public String showmds() throws Exception
	{

		String exec7 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph status -f json-pretty");
		setJsonmds(exec7);
		return super.execute();
	}
	
	//by zedanzheng
	public String showvms() throws Exception{
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
	
	
	
	
	
	
	public String get_mds_list() throws Exception
	{
		String exec8 = Shell.exec("116.56.140.62", "ceph", "111hadoop", 22, "ceph mds stat");
		
		String[] strArr = exec8.trim().split("\\s+");
		for (int i = 0; i < strArr.length; i++)
		{
			System.out.println(i + "," + strArr[i]);
		}

		String[] desArr = strArr[3].substring(1, strArr[3].length()-1).split("=");
		for (int i = 0; i < desArr.length; i++)
		{
			System.out.println(i + "," + desArr[i]);
		}

		json.put("list", desArr);
		return super.execute();
	}
	public String showpool() throws Exception
	{
		return super.execute();
	}
	public String showpg() throws Exception
	{
		return super.execute();
	}
	public String get_all_status() throws Exception
	{
		return SUCCESS;
	}

	public String getHealth()
	{
		return health;
	}

	public void setHealth(String health)
	{
		this.health = health;
	}

	public Integer getMon_num()
	{
		return mon_num;
	}

	public void setMon_num(Integer mon_num)
	{
		this.mon_num = mon_num;
	}

	public Integer getOsd_sum()
	{
		return osd_sum;
	}

	public void setOsd_sum(Integer osd_sum)
	{
		this.osd_sum = osd_sum;
	}

	public Integer getOsd_up()
	{
		return osd_up;
	}

	public void setOsd_up(Integer osd_up)
	{
		this.osd_up = osd_up;
	}

	public Integer getOsd_in()
	{
		return osd_in;
	}

	public void setOsd_in(Integer osd_in)
	{
		this.osd_in = osd_in;
	}

	public Integer getMds_sum()
	{
		return mds_sum;
	}

	public void setMds_sum(Integer mds_sum)
	{
		this.mds_sum = mds_sum;
	}

	public Integer getMds_up()
	{
		return mds_up;
	}
    
	public String getTips()
	{
		return tips;
	}

	public void setTips(String tips)
	{
		this.tips = tips;
	}

	public void setMds_up(Integer mds_up)
	{
		this.mds_up = mds_up;
	}

	public Map<String, Object> getJson()
	{
		return json;
	}

	public void setJson(Map<String, Object> json)
	{
		this.json = json;
	}

	public Integer getMonmap()
	{
		return monmap;
	}

	public void setMonmap(Integer monmap)
	{
		this.monmap = monmap;
	}

	public Integer getEpoch()
	{
		return epoch;
	}

	public void setEpoch(Integer epoch)
	{
		this.epoch = epoch;
	}

	public Integer getQuorum()
	{
		return quorum;
	}

	public void setQuorum(Integer quorum)
	{
		this.quorum = quorum;
	}

	public Integer getMds_in()
	{
		return mds_in;
	}

	public void setMds_in(Integer mds_in)
	{
		this.mds_in = mds_in;
	}

	public String getJsonmon()
	{
		return jsonmon;
	}

	public void setJsonmon(String jsonmon)
	{
		this.jsonmon = jsonmon;
	}

	public Map<String, String[]> getInfoMap()
	{
		return infoMap;
	}

	public void setInfoMap(Map<String, String[]> infoMap)
	{
		this.infoMap = infoMap;
	}

	public String getJsonosd()
	{
		return jsonosd;
	}

	public void setJsonosd(String jsonosd)
	{
		this.jsonosd = jsonosd;
	}

	public String getJsonmds()
	{
		return jsonmds;
	}

	public void setJsonmds(String jsonmds)
	{
		this.jsonmds = jsonmds;
	}
	
	//by zedanzheng
	public void setVmsinfos(List<String[]> vmsinfos) {
		this.vmsinfos = vmsinfos;
	}
	
	public List<String[]> getVmsinfos() {
		return vmsinfos;
	}
}
