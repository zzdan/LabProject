package common.openstack.database;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


public class OpenstackAccount  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String username;
     private String password;
     private String auth;
     private Time time;
     private Set openstackVms = new HashSet(0);
     private Set imagechains = new HashSet(0);
     private Set openstackVls= new HashSet(0);

     


    // Constructors

    /** default constructor */
    public OpenstackAccount() {
    }

	/** minimal constructor */
    public OpenstackAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /** full constructor */
    public OpenstackAccount(String username, String password, String auth, Time time, Set imagechains, Set openstackVms,Set openstackVls) {
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.time = time;
        this.imagechains=imagechains;
        this.openstackVms = openstackVms;
        this.openstackVls=openstackVls;
        
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth() {
        return this.auth;
    }
    
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Time getTime() {
        return this.time;
    }
    
    public void setTime(Time time) {
        this.time = time;
    }

    public Set getOpenstackVms() {
        return this.openstackVms;
    }
    
    public void setOpenstackVms(Set openstackVms) {
        this.openstackVms = openstackVms;
    }

	public Set getImagechains() {
		return imagechains;
	}

	public void setImagechains(Set imagechains) {
		this.imagechains = imagechains;
	}

	public Set getOpenstackVls() {
		return openstackVls;
	}

	public void setOpenstackVls(Set openstackVls) {
		this.openstackVls = openstackVls;
	}
    
}