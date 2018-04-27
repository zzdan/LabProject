package com;


import net.sf.json.JSONArray;

import com.SuperInsInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Supervisor {
    public static final String dataSource = "172.16.1.51";

    public static List<SuperInfo> requestInfo(List<String> ipList) throws IOException {
        //TODO:获取列表的监控数据
        List<SuperInfo> ls = new ArrayList<SuperInfo>();
        List<DataPoint> cpu = null;
        List<DataPoint> mem = null;
        List<DataPoint> disk_total = null;
        List<DataPoint> disk_avail = null;
        List<DataPoint> innet = null;
        List<DataPoint> outnet = null;
        String cpu_target = null;
        String mem_target = null;
        String disk_total_target = null;
        String disk_avail_target = null;
        String net_target = null;
        double cpu_rate = 0;
        double memory_rate = 0;
        double disk_rate = 0;
        double innet_num = 0;
        double outnet_num = 0;
        for(int i = 0 ; i < ipList.size() ; i++){
            String ip = ipList.get(i);
            cpu_target = "http://"+dataSource+
                    "/ganglia/graph.php?c=my%20cluster&h=" + ip +
                    "&r=hour&z=small&jr=&js=&st=1453345004&v=96.5&" +
                    "m=cpu_idle&vl=%25&ti=CPU%20Idle&json=1";
            mem_target = "http://"+ dataSource +
                    "/ganglia/graph.php?h=" + ip+
                    "&m=load_one&r=hour&s=by%20name&hc=4&mc=2&st=1453345004&" +
                    "g=mem_report&z=large&c=my%20cluster&json=1";
            disk_total_target = "http://" + dataSource +
                    "/ganglia/graph.php?c=my%20cluster&h=" + ip +
                    "&r=hour&z=small&jr=&js=&st=1453345004&v=52.810&" +
                    "m=disk_total&vl=GB&ti=Total%20Disk%20Space&json=1";
            disk_avail_target = "http://" + dataSource+
                    "/ganglia/graph.php?c=my%20cluster&h=" + ip +
                    "&r=hour&z=small&jr=&js=&st=1453345004&v=10.2&" +
                    "m=disk_free&vl=GB&ti=Disk+Space+Available&json=1";
                    //"m=part_max_used&vl=%25&ti=Maximum%20Disk%20Space%20Used&json=1";
            net_target = "http://" + dataSource +
                    "/ganglia/graph.php?h=" + ip +
                    "&m=load_one&r=hour&s=by%20name&hc=4&mc=2&st=1453345004&" +
                    "g=network_report&z=large&c=my%20cluster&json=1";
            cpu = new ArrayList<DataPoint>();
            mem = new ArrayList<DataPoint>();
            disk_total = new ArrayList<DataPoint>();
            disk_avail= new ArrayList<DataPoint>();
            innet = new ArrayList<DataPoint>();
            outnet = new ArrayList<DataPoint>();
            DataPoint mem_total = new DataPoint(1,1);
            System.out.println("Begin CPU query");
            mem_total = new DataPoint(1,1);
            System.out.println("Begin CPU query");
            cpu = getPointList(cpu_target,new DataPoint());
            System.out.println("Begin MEM query");
            mem = getPointList(mem_target,mem_total);
            System.out.println("Begin DISK_TOTAL query");
            disk_total = getPointList(disk_total_target,new DataPoint());
            System.out.println("Begin DISK_USED query");
            disk_avail = getPointList(disk_avail_target,new DataPoint());
            System.out.println("Begin NET query");
            //net = getPointList(net_target,new DataPoint());
            getNetList(net_target,innet,outnet);
            //TODO:cpu使用率
            cpu_rate = changeDouble(100-cpu.get(cpu.size()-1).getData());
            //TODO:内存使用率
            memory_rate = changeDouble((mem.get(mem.size()-1).getData()/mem_total.getData())*100);
            //TODO:存储使用率
            disk_rate = 1.000-changeDouble((disk_avail.get(disk_avail.size()-1).getData()/disk_total.get(disk_total.size()-1).getData())*100);
            //TODO:网络进出
            innet_num = innet.get(innet.size()-1).getData();
            outnet_num = outnet.get(outnet.size()-1).getData();
            System.out.println("cpu:"+cpu_rate+"-memory:"+memory_rate+"-disk:"+disk_rate+"-innet:"+innet_num+"-outnet:"+outnet_num);
            ls.add(new SuperInfo(cpu_rate,memory_rate,disk_rate,innet_num,outnet_num));

        }
        return ls;
    }

    public static double changeDouble(double d){
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        return Double.parseDouble(df.format(d));
    }

    /**
     * 获取实例的平均监控信息
     * @param li
     * @return
     * @throws IOException
     */
    public static List<SuperInsInfo> getInsAVE(List<String> ipList) throws IOException {
        //TODO:获取列表的监控数据
        List<SuperInsInfo> ls = new ArrayList<SuperInsInfo>();
        DataPoint cpu_core = null;
        List<DataPoint> cpu = null;
        List<DataPoint> mem = null;
        List<DataPoint> disk_total= null;
        List<DataPoint> disk_avail = null;
        List<DataPoint> innet = null;
        List<DataPoint> outnet = null;
        List<DataPoint> usemem=null;
        List<DataPoint> sharemem=null;
        List<DataPoint> cachemem=null;
        List<DataPoint> buffermem=null;
        List<DataPoint> swapmem=null;
       
        
        String cpu_core_target;
        String cpu_target = null;
        String mem_target = null;
        String disk_total_target = null;
        String disk_avail_target = null;
        String net_target = null;
        double cpu_total = 0;
        double cpu_use = 0;
        double memory_total = 0;
        double memory_use1 = 0;
        double memory_use = 0;       
        double disk_to = 0;
        double disk_use = 0;
        double innet_num = 0;
        double outnet_num = 0;
        double use_num = 0;
        double share_num = 0;
        double cache_num = 0;
        double buffer_num = 0;
        double swap_num = 0;

        
        for(int i = 0 ; i < ipList.size() ; i++) {
            String ip = ipList.get(i);
            cpu_core_target = "http://" + dataSource +
                    "/ganglia/graph.php?h=" + ip +
                    "&m=load_one&r=hour&s=by%20name&hc=4&mc=2&st=1456834974&" +
                    "g=load_report&z=large&c=my%20cluster&json=1";
            cpu_target = "http://" + dataSource +
                    "/ganglia/graph.php?c=my%20cluster&h=" + ip +
                    "&r=hour&z=small&jr=&js=&st=1453345004&v=96.5&" +
                    "m=cpu_idle&vl=%25&ti=CPU%20Idle&json=1";
            mem_target = "http://" + dataSource +
                    "/ganglia/graph.php?h=" + ip +
                    "&m=load_one&r=hour&s=by%20name&hc=4&mc=2&st=1453345004&" +
                    "g=mem_report&z=large&c=my%20cluster&json=1";
            disk_total_target = "http://" + dataSource +
                    "/ganglia/graph.php?c=my%20cluster&h=" + ip +
                    "&r=hour&z=small&jr=&js=&st=1453345004&v=52.810&" +
                    "m=disk_total&vl=GB&ti=Total%20Disk%20Space&json=1";
            disk_avail_target = "http://" + dataSource +
                    "/ganglia/graph.php?c=my%20cluster&h=" + ip +
                    "&r=hour&z=small&jr=&js=&st=1453345004&v=10.2&" +
                    "m=disk_free&vl=GB&ti=Disk+Space+Available&json=1";
                    //"m=part_max_used&vl=%25&ti=Maximum%20Disk%20Space%20Used&json=1";
            net_target = "http://" + dataSource +
                    "/ganglia/graph.php?h=" + ip +
                    "&m=load_one&r=hour&s=by%20name&hc=4&mc=2&st=1453345004&" +
                    "g=network_report&z=large&c=my%20cluster&json=1";
            cpu_core = new DataPoint(2,1);
            cpu = new ArrayList<DataPoint>();
            mem = new ArrayList<DataPoint>();
            disk_total = new ArrayList<DataPoint>();
            disk_avail = new ArrayList<DataPoint>();
            innet = new ArrayList<DataPoint>();
            outnet = new ArrayList<DataPoint>();
            usemem = new ArrayList<DataPoint>();
            sharemem = new ArrayList<DataPoint>();
            cachemem = new ArrayList<DataPoint>();
            buffermem = new ArrayList<DataPoint>();
            swapmem = new ArrayList<DataPoint>();
            DataPoint mem_total = new DataPoint(1, 1);
            //System.out.println("Begin CPU query");
            getPointList(cpu_core_target, cpu_core);
            cpu = getPointList(cpu_target, new DataPoint());
           // System.out.println("Begin MEM query");
            mem = getPointList(mem_target, mem_total);
           // System.out.println("Begin DISK_TOTAL query");
            disk_total = getPointList(disk_total_target, new DataPoint());
           // System.out.println("Begin DISK_USED query");
            disk_avail= getPointList(disk_avail_target, new DataPoint());
           // System.out.println("Begin NET query");
            //net = getPointList(net_target,new DataPoint());
            getMemList(mem_target,usemem,sharemem,cachemem,buffermem,swapmem);
            getNetList(net_target, innet, outnet);
            //System.out.println(cpu.get(cpu.size() - 1).getData());
            cpu_total = cpu_core.getData();
            double temp = (100 - cpu.get(cpu.size() - 1).getData())/100;
            cpu_use =  temp*cpu_total;
           // System.out.println(temp);
            //System.out.println("cpu_use:"+cpu_use);
            memory_total = mem_total.getData()/1024/1024/1024;
            memory_use1 =(mem.size()>0)?mem.get(mem.size() - 1).getData():0;
            memory_use= memory_use1/1024/1024/1024;
            //System.out.println("memuse:"+memory_use);
            disk_to = (disk_total.size()>0)?disk_total.get(disk_total.size() - 1).getData():0;
            disk_use =disk_to-((disk_avail.size()>0)?disk_avail.get(disk_avail.size() - 1).getData():0);
            innet_num =(innet.size()>0)?innet.get(innet.size() - 1).getData():0;
            outnet_num =(innet.size()>0)?outnet.get(innet.size() - 1).getData():0;
            use_num =(usemem.size()>0)?usemem.get(usemem.size() - 1).getData():0;
            share_num =(sharemem.size()>0)?sharemem.get(usemem.size() - 1).getData():0;
            cache_num =(cachemem.size()>0)?cachemem.get(usemem.size() - 1).getData():0;
            buffer_num =(buffermem.size()>0)?buffermem.get(usemem.size() - 1).getData():0;
            swap_num =(swapmem.size()>0)?swapmem.get(usemem.size() - 1).getData():0;
            
            //ls.add(new SuperInsInfo(cpu_total, cpu_use, memory_total, memory_use, disk_to, disk_use, innet_num, outnet_num,use_num,share_num,cach_emem,buffe_rmem,swap_mem));
            ls.add(new SuperInsInfo(cpu_total, cpu_use, memory_total, memory_use, disk_to, disk_use, innet_num, outnet_num,use_num,share_num,cache_num,buffer_num,swap_num));
        }
        return ls;
    }

    /**获取网络使用情况
     * @param target
     * @param innet
     * @param outnet
     * @throws IOException
     */
    public static void getNetList(String target,List<DataPoint> innet,List<DataPoint> outnet) throws IOException {
        String result = "";
        BufferedReader in = null;
        URL url= new URL(target);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("accept","*/*");
        connection.setRequestProperty("connection","Keep-Alive");
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.connect();

        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while((line=in.readLine())!=null){
            result += line;
        }

        JSONArray array =  JSONArray.fromObject(result);
        JSONArray inDataList = array.getJSONObject(0).getJSONArray("datapoints");
        JSONArray outDataList = array.getJSONObject(1).getJSONArray("datapoints");
        //System.out.println(inDataList.toString());
        //System.out.println(outDataList.toString());
        int num = (inDataList.size()>240)?240:inDataList.size();
        int num1 = (outDataList.size()>240)?240:outDataList.size();
        for(int i= 0;i<num;i++){
            JSONArray data = inDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = inDataList.getJSONArray(i-1).get(0).toString();
            	break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            innet.add(point);
        }

        for(int i= 0;i<num1;i++){
            JSONArray data = outDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = outDataList.getJSONArray(i-1).get(0).toString();
                break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            outnet.add(point);
        }

    }
    /**获取内存使用情况
     * @param target
     * @param usemem
     * @param sharemem
     * @param cachemem
     * @param buffermem
     * @param swapmem
     * @throws IOException
     */
    public static void getMemList(String target,List<DataPoint> usemem,List<DataPoint> cachemem,List<DataPoint> sharemem,List<DataPoint> buffermem,List<DataPoint> swapmem) throws IOException {
        String result = "";
        BufferedReader in = null;
        URL url= new URL(target);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("accept","*/*");
        connection.setRequestProperty("connection","Keep-Alive");
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.connect();

        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while((line=in.readLine())!=null){
            result += line;
        }

        JSONArray array =  JSONArray.fromObject(result);
        JSONArray useDataList = array.getJSONObject(0).getJSONArray("datapoints");
        JSONArray shareDataList = array.getJSONObject(1).getJSONArray("datapoints");
        JSONArray cacheDataList = array.getJSONObject(2).getJSONArray("datapoints");
        JSONArray bufferDataList = array.getJSONObject(3).getJSONArray("datapoints");
        JSONArray swapDataList = array.getJSONObject(4).getJSONArray("datapoints");
        //System.out.println(inDataList.toString());
        //System.out.println(outDataList.toString());
        int num0 = (useDataList.size()>240)?240:useDataList.size();
        int num1 = (shareDataList.size()>240)?240:shareDataList.size();
        int num2 = (cacheDataList.size()>240)?240:cacheDataList.size();
        int num3 = (bufferDataList.size()>240)?240:bufferDataList.size();
        int num4 = (swapDataList.size()>240)?240:swapDataList.size();
        for(int i= 0;i<num0;i++){
            JSONArray data = useDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = useDataList.getJSONArray(i-1).get(0).toString();
            	break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            usemem.add(point);
        }

        for(int i= 0;i<num1;i++){
            JSONArray data = shareDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = shareDataList.getJSONArray(i-1).get(0).toString();
                break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            sharemem.add(point);
        }
        for(int i= 0;i<num2;i++){
            JSONArray data = cacheDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = cacheDataList.getJSONArray(i-1).get(0).toString();
                break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            cachemem.add(point);
        }
        for(int i= 0;i<num3;i++){
            JSONArray data = bufferDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = bufferDataList.getJSONArray(i-1).get(0).toString();
                break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            buffermem.add(point);
        }
        for(int i= 0;i<num4;i++){
            JSONArray data = swapDataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (isNaN.equals("NaN")){
                isNaN = swapDataList.getJSONArray(i-1).get(0).toString();
                break;
            }
            DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
            swapmem.add(point);
        }

    }
    /**
     * 复用通用接口
     * @param target
     * @param mdp
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static List<DataPoint> getPointList(String target,DataPoint mdp)throws MalformedURLException,IOException{
        String result = "";
        BufferedReader in = null;
        URL url= new URL(target);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("accept","*/*");
        connection.setRequestProperty("connection","Keep-Alive");
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.connect();
        Map<String,List<String> > map = connection.getHeaderFields();
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while((line=in.readLine())!=null){
            result += line;
        }
        if (in != null) {
            in.close();
        }
        JSONArray array =  JSONArray.fromObject(result);
        List<DataPoint> list = new ArrayList<DataPoint>();
        JSONArray dataList = array.getJSONObject(0).getJSONArray("datapoints");
        if(mdp.getData()==1){//内存查询
            JSONArray totalmary =  array.getJSONObject(5).getJSONArray("datapoints");
            mdp.setData(Double.parseDouble(totalmary.getJSONArray(0).get(0).toString()));
        }else if(mdp.getData()==2){//cpu核数查询
            JSONArray totalCpu =  array.getJSONObject(1).getJSONArray("datapoints");
            mdp.setData(Double.parseDouble(totalCpu.getJSONArray(0).get(0).toString()));
            return null;
        }
       // System.out.println(dataList.size()+":"+dataList.toString());
        int num = (dataList.size()>240)?240:dataList.size();
        for(int i= 0;i<num;i++){
            JSONArray data = dataList.getJSONArray(i);
            String isNaN = data.get(0).toString();
            if (!isNaN.equals("NaN")){
                DataPoint point = new DataPoint(Double.parseDouble(isNaN),Long.parseLong(data.get(1).toString()));
                list.add(point);
            }
        }
        System.out.println(list.size()+":"+list.toString());
        return list;
    }


}

