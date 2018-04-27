package common;

public class VM {

	 private String state;
	 private String flavor;
	 private String ram;
	 private String vcpu;
	 private String  serversId;
	 private String instanceName;
	 private String imageName;
	 private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	    public String getState() {
	        return state;
	    }
	    public void setState(String state) {
	        this.state =state;
	    }
	    public String getFlavor() {
	        return flavor;
	    }
	    public void setFlavor(String flavor) {
	        this.flavor =flavor;
	    }
	   
	    public String getServersId() {
	        return serversId;
	    }
	    public void setServersId(String serversId) {
	        this.serversId = serversId;
	    }
	    public String getInstanceName() {
	        return instanceName;
	    }
	    public void setInstanceName(String instanceName) {
	        this.instanceName =instanceName;
	    }
		public String getRam() {
			return ram;
		}
		public void setRam(String ram) {
			this.ram = ram;
		}
		public String getVcpu() {
			return vcpu;
		}
		public void setVcpu(String vcpu) {
			this.vcpu = vcpu;
		}
	    
}


