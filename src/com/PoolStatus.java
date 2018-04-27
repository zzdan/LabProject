package com;
import com.util.Shell;
public class PoolStatus {
	 public static void main(String args[]) {   
         //pool status
 		String exec=Shell.exec("10.0.0.7", "ceph", "ceph", 22, 
 				"rados lspools");
 		System.out.println(exec); 
 		String[] content=exec.split("\n");
 		int poolnum=content.length;
 		System.out.println(poolnum);
	 }
}
