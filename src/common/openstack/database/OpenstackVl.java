package common.openstack.database;

/**
 * OpenstackVm entity. @author MyEclipse Persistence Tools
 */

public class OpenstackVl  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private OpenstackAccount openstackAccount;
     private String name;
     private String type;
     private int size;
     private String status;
     private String shareable;


    // Constructors

    /** default constructor */
    public OpenstackVl() {
    }

    
    /** full constructor */
    public OpenstackVl(OpenstackAccount openstackAccount, String name, String type, int size, String status, String shareable) {
        this.openstackAccount = openstackAccount;
        this.name = name;
        this.type = type;
        this.size = size;
        this.status = status;
        this.shareable = shareable;
    }

    // Property accessors
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public OpenstackAccount getOpenstackAccount() {
		return openstackAccount;
	}


	public void setOpenstackAccount(OpenstackAccount openstackAccount) {
		this.openstackAccount = openstackAccount;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getShareable() {
		return shareable;
	}


	public void setShareable(String shareable) {
		this.shareable = shareable;
	}

    
}