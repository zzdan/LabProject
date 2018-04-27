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

public class NewvolumeAction extends ActionSupport {
	private String volname;
	private String voltype;
	private String imagename;
	private int volsize;
	private int share;
	private String imageid;

	public String execute() throws Exception {
		// System.out.println(imagename);

		System.out.println(volname + voltype + volsize);
		OSClient os = OSFactory.builder().endpoint("http://116.56.140.61:5000/v2.0").credentials("zehang", "zehang")
				.tenantName("zehang").authenticate();

		if (voltype.equals("数据盘")) {
			System.out.println(share);
			Volume v = os.blockStorage().volumes().create(Builders.volume().name(volname)
					.description("Simple volume to store backups on").volumeType("rbd").size(volsize).build());

		} else {
			System.out.println(imagename);
			if (imagename.equals("0")) {
				imageid = "c7fe57be-b31d-46ff-ad45-40678920e7b6";

			} else if (imagename.equals("1")) {
				imageid = "2d8b2564-61c7-4e87-b0db-28298d29bb3f";

			} else if (imagename.equals("2")) {
				imageid = "678c0758-d7be-47a0-8a6e-7aa0d5be3612";
			}
			// System.out.println(imageid);
			Volume v = os.blockStorage().volumes()
					.create(Builders.volume().name(volname).description("Bootable install volume").imageRef(imageid)
							.volumeType("rbd").size(volsize).bootable(true).build());
			String stat=v.getStatus().toString();
		}
		return SUCCESS;
	}

	@JSON(serialize = false)
	public String getVolname() {
		return volname;
	}

	public void setVolname(String volname) {
		this.volname = volname;
	}

	@JSON(serialize = false)
	public String getVoltype() {
		return voltype;
	}

	public void setVoltype(String voltype) {
		this.voltype = voltype;
	}

	@JSON(serialize = false)
	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	@JSON(serialize = false)
	public int getVolsize() {
		return volsize;
	}

	public void setVolsize(int volsize) {
		this.volsize = volsize;
	}

	@JSON(serialize = false)
	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

}
