package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;



public class FileOperation {
	
	/**
	  * 创建文件
	  * @param fileName
	  * @return
	  */
	 public static boolean createFile(File fileName)throws Exception{
	  boolean flag=false;
	  try{
	   if(!fileName.exists()){
	    fileName.createNewFile();
	    flag=true;
	   }
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return true;
	 } 
	 
	 /**
	  * 读TXT文件内容
	  * @param fileName
	  * @return
	  */
	 public static double[][] readTxtFile(File fileName,char axis)throws Exception{
	  String result="";
	  //计算txt文件数据的行数
	  int count=0;
	  double [][]data = null;
	  FileReader fileReader=null;
	  BufferedReader bufferedReader=null;
	  try{
	   fileReader=new FileReader(fileName);
	   bufferedReader=new BufferedReader(fileReader);
	   try{
	    String read="";
	   
	    while((read=bufferedReader.readLine())!=null){
	     result=result+read+"\r\n";
	     count=count+1;
	    }
	    if(axis=='x')
	    {
	    data=new double[count][4];
	    String []read2=result.split("\r\n");
		   for(int i=0;i<count;i++){
			        //将每一行的数据存入二维数组中        
			        String []tempdata=read2[i].split(",");
			        for(int j=0;j<4;j++)
			        {
			        	data[i][j]=Double.parseDouble(tempdata[j]);
			        }
			       
		    }    
	    }
	    else
	    {
	    data=new double[count][1];	
	    String []read2=result.split("\r\n");
		   for(int i=0;i<count;i++){
			        //将每一行的数据存入二维数组中        
			        String []tempdata=read2[i].split(",");
			        for(int j=0;j<1;j++)
			        {
			        	data[i][j]=Double.parseDouble(tempdata[j]);
			        }
			       
		    }    
	    }
	   
	   }catch(Exception e){
	    e.printStackTrace();
	   }
	  }catch(Exception e){
	   e.printStackTrace();
	  }finally{
	   if(bufferedReader!=null){
	    bufferedReader.close();
	   }
	   if(fileReader!=null){
	    fileReader.close();
	   }
	  }
	 // System.out.println("读取出来的文件内容是："+"\r\n"+result);
	//  return result;
	  return data;
	 }
	 
	 
	 public static boolean writeTxtFile(String content,File  fileName)throws Exception{
	  RandomAccessFile mm=null;
	  boolean flag=false;
	  FileOutputStream o=null;
	  try {
	   o = new FileOutputStream(fileName);
	      o.write(content.getBytes("GBK"));
	      o.close();
	//   mm=new RandomAccessFile(fileName,"rw");
	//   mm.writeBytes(content);
	   flag=true;
	  } catch (Exception e) {
	   // TODO: handle exception
	   e.printStackTrace();
	  }finally{
	   if(mm!=null){
	    mm.close();
	   }
	  }
	  return flag;
	 }



	public static void contentToTxt(String filePath, String content) {
	        String str = new String(); //原有txt内容
	        String s1 = new String();//内容更新
	        try {
	            File f = new File(filePath);
	            if (f.exists()) {
	           //     System.out.print("文件存在");
	            } else {
	            //    System.out.print("文件不存在");
	                f.createNewFile();// 不存在则创建
	            }
	            BufferedReader input = new BufferedReader(new FileReader(f));

	            while ((str = input.readLine()) != null) {
	                s1 += str + "\n";
	            }
	         //   System.out.println(s1);
	            input.close();
	            s1 += content;

	            BufferedWriter output = new BufferedWriter(new FileWriter(f));
	            output.write(s1);
	            output.close();
	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	    }

	   

}
