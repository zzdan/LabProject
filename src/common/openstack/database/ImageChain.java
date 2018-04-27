package common.openstack.database;

public class ImageChain  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
    private OpenstackAccount openstackAccount;
     private String softimage;
     private Integer layers;
     private String softlist;
     private String imageid;
     // Constructors

     /** default constructor */
     public ImageChain() {
     }
     /** full constructor */
     public ImageChain(OpenstackAccount openstackAccount,String softimage, Integer layers, String softlist, String imageid) {
    	 this.openstackAccount = openstackAccount;
    	 this.softimage = softimage;
         this.layers = layers;
         this.softlist = softlist;
         this.imageid = imageid;
     }
   // Property accessors
	public String getSoftimage() {
		return softimage;
	}
	public void setSoftimage(String softimage) {
		this.softimage = softimage;
	}
	public Integer getLayers() {
		return layers;
	}
	public void setLayers(Integer layers) {
		this.layers = layers;
	}
	
	public String getSoftlist() {
		return softlist;
	}
	public void setSoftlist(String softlist) {
		this.softlist = softlist;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
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
     
}
