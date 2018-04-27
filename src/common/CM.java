package common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.util.Shell;

public class CM
{
	/**
	 * 
	 * @return
	 */
	public static Map<String, String[]> getOSDInfo(String ip, String user, String pass)
	{
		Map<String, String[]> map = new LinkedHashMap<>();

		String infoResult = Shell.exec(ip, user, pass, 22, "ceph osd dump |grep osd");
		String[] infoArr = infoResult.split("\n");
		for (int i = 1; i < infoArr.length; i++)
		{
			String str = infoArr[i];
			String[] strArr = str.trim().split("\\s+");

			String id = strArr[0];
			id = id.substring(id.indexOf(".") + 1);

			String ip2 = strArr[13];
			ip2 = ip2.substring(0, ip2.indexOf(":"));

			String[] tempArr = new String[] { id, strArr[1], strArr[2], strArr[4], ip2 };

			map.put(id, tempArr);
		}

		Map<String, String[]> map2 = new LinkedHashMap<>();

		String infoResult2 = Shell.exec(ip, user, pass, 22, "ceph osd df");
		String[] infoArr2 = infoResult2.split("\n");

		for (int i = 1; i < infoArr2.length - 2; i++)
		{
			String str = infoArr2[i];
			String[] strArr = str.trim().split("\\s+");
			String[] tempArr = new String[] { strArr[0], strArr[3], strArr[4], strArr[5], strArr[6] };
			map2.put(strArr[0], tempArr);
		}

		Map<String, String[]> infoMap = new LinkedHashMap<>();

		for (String key : map.keySet())
		{
			String[] infoResultArr = new String[9];
			String[] value = map.get(key);
			String[] value2 = map2.get(key);
			infoResultArr[0] = value[0];
			infoResultArr[1] = value[1];
			infoResultArr[2] = value[2];
			infoResultArr[3] = value[3];
			infoResultArr[4] = value[4];
			infoResultArr[5] = value2[1];
			infoResultArr[6] = value2[2];
			infoResultArr[7] = value2[3];
			infoResultArr[8] = value2[4];

			infoMap.put(key, infoResultArr);
		}

		return infoMap;
	}
	/**
	 * 
	 * @return
	 */
	public static List<String[]> getPoolInfo(String ip, String user, String pass){
		String infoResult = Shell.exec(ip, user, pass, 22, "ceph osd dump |grep pool");
		String[] infoArr = infoResult.split("\n");
		List<String[]> list = new ArrayList<>();
		String str2="";
		for (int i = 0; i < infoArr.length; i++)
		{	
			String str = infoArr[i];
			String[] strArr = str.trim().split("\\s+");		
			str2=strArr[2].substring(1,strArr[2].length()-1);
			String[] tempArr = new String[] {strArr[1],str2,strArr[5],strArr[13],strArr[15]};
		    list.add(tempArr);
	
		
		}
		return list;
	}
}
