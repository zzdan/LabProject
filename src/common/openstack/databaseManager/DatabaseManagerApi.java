package common.openstack.databaseManager;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import common.openstack.database.OpenstackAccount;
import common.openstack.database.OpenstackVl;
import common.openstack.database.OpenstackVm;
import common.openstack.database.ImageChain;

/**
 * @author moview
 *
 */

public class DatabaseManagerApi {
	/** 添加用户 **/
	public static void insertAccount(String name, String password) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			OpenstackAccount data = new OpenstackAccount();
			data.setUsername(name);
			data.setPassword(password);
			session.save(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/** 验证账户是否存在 **/
	public static boolean hasAccount(String name, String password) {
		try {
			Session session = HibernateUtil.currentSession();
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", name));
			if (c.list().isEmpty())
				return false;
			else
				return true;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/** 验证用户 **/
	public static String checkAccount(String name, String password) {
		try {
			Session session = HibernateUtil.currentSession();
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", name));
			c.add(Restrictions.eq("password", password));
			if (c.list().isEmpty())
				return "false";
			else
				return "true";
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/** 验证用户并返回token **/
	public static String checkAccountAndAuth(String name, String password) {
		Session session = HibernateUtil.currentSession();
		String token;
		Transaction tx = session.beginTransaction();
		try {
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", name));
			c.add(Restrictions.eq("password", password));
			List list = c.list();
			if (list.isEmpty())
				return "false";
			else {
				OpenstackAccount data = (OpenstackAccount) list.get(0);
				token = (int) Math.random() * 1000000 + "";
				data.setAuth(token);
				session.save(data);
			}
			tx.commit();
			return token;

		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 获取用户的虚拟机列表
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public static Iterator getVM(String username) {
		try {
			Session session = HibernateUtil.currentSession();
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", username));
			List list = c.list();
			if (list.isEmpty())
				return null;
			else {
				OpenstackAccount data = (OpenstackAccount) list.get(0);
				return data.getOpenstackVms().iterator();
			}

		} catch (HibernateException e) {
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}

	}

	/**
	 * 添加虚拟机
	 * 
	 * @param username
	 *            用户
	 * @param name
	 *            虚拟机名
	 * @param ip  
	 *            虚拟机ip 
	 * @param cpu
	 *            CPU
	 * @param memory
	 *            内存
	 * @param system
	 *            系统
	 * @param openstackVm
	 *            对应openstack的虚拟机id
	 */
	public static void addVm(String username, String name, String ip,String cpu, String memory, String system,
			String openstackVm) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", username));
			OpenstackAccount user = (OpenstackAccount) c.list().get(0);
			OpenstackVm data = new OpenstackVm();
			data.setName(name);
			data.setIp(ip);
			data.setSystem(system);
			data.setCpu(cpu);
			data.setMemory(memory);
			data.setOpenstackVm(openstackVm);
			user.getOpenstackVms().add(data);
			data.setOpenstackAccount(user);
			session.save(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}

	}

	/**
	 * 删除虚拟机
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public static void rmVM(Integer VMid) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {

			OpenstackVm data = new OpenstackVm();
			data.setId(VMid);
			session.delete(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 获取用户的镜像列表
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public static Iterator getImage(String username) {
		try {
			Session session = HibernateUtil.currentSession();
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", username));
			List list = c.list();
			if (list.isEmpty())
				return null;
			else {
				OpenstackAccount data = (OpenstackAccount) list.get(0);
				return data.getImagechains().iterator();
			}

		} catch (HibernateException e) {
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 删除镜像
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public static void rmImage(Integer IMid) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {

			ImageChain data = new ImageChain();
			data.setId(IMid);
			session.delete(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 添加镜像
	 * 
	 * @param softimage
	 * @param layers
	 * @param softlist
	 * @param imageid
	 */
	public static void addIm(String username, String softimage, int layers, String softlist, String imageid) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", username));
			OpenstackAccount user = (OpenstackAccount) c.list().get(0);
			ImageChain data = new ImageChain();
			data.setImageid(imageid);
			data.setSoftimage(softimage);
			data.setLayers(layers);
			data.setSoftlist(softlist);
			user.getImagechains().add(data);
			data.setOpenstackAccount(user);
			session.save(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 添加云硬盘
	 * 
	 * @param name
	 * @param type
	 * @param size
	 * @param status
	 * @param shareable
	 */
	public static void addVl(String username, String name, String type, int size, String status, String shareable) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", username));
			OpenstackAccount user = (OpenstackAccount) c.list().get(0);
			OpenstackVl data = new OpenstackVl();
			data.setName(name);
			data.setType(type);
			data.setSize(size);
			data.setStatus(status);
			data.setShareable(shareable);
			user.getOpenstackVls().add(data);
			data.setOpenstackAccount(user);
			session.save(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 删除云硬盘
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public static void rmVolume(Integer vlid) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		try {

			OpenstackVl data = new OpenstackVl();
			data.setId(vlid);
			session.delete(data);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * 获取用户的云硬盘列表
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public static Iterator getVolume(String username) {
		try {
			Session session = HibernateUtil.currentSession();
			Criteria c = session.createCriteria(OpenstackAccount.class);
			c.add(Restrictions.eq("username", username));
			List list = c.list();
			if (list.isEmpty())
				return null;
			else {
				OpenstackAccount data = (OpenstackAccount) list.get(0);
				return data.getOpenstackVls().iterator();
			}

		} catch (HibernateException e) {
			throw e;
		} finally {
			HibernateUtil.closeSession();
		}
	}

}