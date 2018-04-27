package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * SSH������
 */
public class Shell {
  
  /**
   * Զ�� ִ��������ؽ�����ù��� ��ͬ���ģ�ִ����Ż᷵�أ�
   * @param host	������
   * @param user	�û���
   * @param psw	              ����
   * @param port	�˿�
   * @param command	����
   * @return
   */
  public static String exec(String host,String user,String psw,int port,String command){
    String result="";
    Session session =null;
    ChannelExec openChannel =null;
    try {
      JSch jsch=new JSch();
      session = jsch.getSession(user, host, port);
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.setPassword(psw);
      session.connect();
      openChannel = (ChannelExec) session.openChannel("exec");
      openChannel.setCommand(command);
      int exitStatus = openChannel.getExitStatus();
//      System.out.println(exitStatus);
      openChannel.connect();  
            InputStream in = openChannel.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
            String buf = null;
            while ((buf = reader.readLine()) != null) {
            	result+= new String(buf.getBytes("gbk"),"UTF-8")+"\r\n";  
            }  
    } catch (JSchException | IOException e) {
      //result+=e.getMessage();
    	System.out.println(e.getMessage());
    	result=null;
    }finally{
      if(openChannel!=null&&!openChannel.isClosed()){
        openChannel.disconnect();
      }
      if(session!=null&&session.isConnected()){
        session.disconnect();
      }
    }
    return result;
  }}