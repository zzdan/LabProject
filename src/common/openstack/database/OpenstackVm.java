package common.openstack.database;

/**
 * OpenstackVm entity. @author MyEclipse Persistence Tools
 */

public class OpenstackVm  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private OpenstackAccount openstackAccount;
     private String name;
     private String ip;
     private String cpu;
     private String memory;
     private String system;
     private String openstackVm;


    // Constructors

    /** default constructor */
    public OpenstackVm() {
    }

    
    /** full constructor */
    public OpenstackVm(OpenstackAccount openstackAccount, String name, String ip,String cpu, String memory, String system, String openstackVm) {
        this.openstackAccount = openstackAccount;
        this.name = name;
        this.ip=ip;
        this.cpu = cpu;
        this.memory = memory;
        this.system = system;
        this.openstackVm = openstackVm;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public OpenstackAccount getOpenstackAccount() {
        return this.openstackAccount;
    }
    
    public void setOpenstackAccount(OpenstackAccount openstackAccount) {
        this.openstackAccount = openstackAccount;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	public String getMemory() {
		return memory;
	}


	public void setMemory(String memory) {
		this.memory = memory;
	}


	public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }

    public String getOpenstackVm() {
        return this.openstackVm;
    }
    
    public void setOpenstackVm(String openstackVm) {
        this.openstackVm = openstackVm;
    }
}