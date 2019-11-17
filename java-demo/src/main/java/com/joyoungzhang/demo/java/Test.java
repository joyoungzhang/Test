package com.joyoungzhang.demo.java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//		String modelMetaPath = "372/472375/output/100677.20190710100102.model.meta";
//		String modelMetaPathS1 = "./100677.20190710100102.model.meta";
//		String modelMetaPathS2 = "../100677.20190710100102";
//		File modelMetaPathParent = new File(modelMetaPath).getParentFile();
//		System.out.println(new File(modelMetaPathParent, modelMetaPathS1).getCanonicalFile());
//		System.out.println(new File(modelMetaPathParent, modelMetaPathS2).getAbsoluteFile());
//		
//		
//		System.out.println(System.currentTimeMillis());
//		System.out.println("/ceph/10052/mig_share/rtml/73/model/1739/gauss_output/20181016235548.model".split("/")[7]);
//		 List<Integer> list = new ArrayList<Integer>();
//		 list.add(1);
//		 System.out.println(list.size());
//		 handle(list);
//		 System.out.println(list.size());
//		 System.out.println(new Date().getTime());
//		 
//		 String s = "hdfs://10.49.136.233:9000/user/hive/warehouse/t_em_bigdata_wx_feature_qquser_basic_new_m";
//		 URI uri = URI.create(s);
//		 System.out.println(uri.getRawSchemeSpecificPart());
//		 System.out.println(uri.getScheme());
//		 System.out.println(uri.getAuthority());
//		 System.out.println(uri.getScheme() + "://" + uri.getAuthority());
//		 
//		 int a  =1; Integer b =2;
//		 System.out.println((Integer)a);
//		 System.out.println((int)b);
		 
//		 Map<String, Long> testss = new HashMap<String, Long>();
//		 Long id1 = testss.get("sdf");
//		 System.out.println(id1);
//		 long id2 = testss.get("sdf");
//		 System.out.println(id2);
		 
//		String file = "/2421/234232/134133/fff4/ccvdf";
//		String tfModelHdfsPath = "/2421/234232/134133";
//		System.out.println(tfModelHdfsPath.length());
//		System.out.println(file.length() - tfModelHdfsPath.length());
//		String keyEnd = file.toString().substring(tfModelHdfsPath.length(), file.length());
//		System.out.println(keyEnd);
		
//		int diliverNum=3;//举例子的变量
//		int queryMailNum=9;//举例子的变量
//		// 创建一个数值格式化对象   
//		NumberFormat numberFormat = NumberFormat.getInstance();   
//		// 设置精确到小数点后2位   
//		numberFormat.setMaximumFractionDigits(2);   
//		String result = numberFormat.format((float)diliverNum/(float)queryMailNum*100);
//		System.out.println(Float.valueOf(result));
//		System.out.println("diliverNum和queryMailNum的百分比为:" + result + "%");  
//		
//		System.out.println(System.currentTimeMillis());
//        System.out.println(Calendar.getInstance().getTimeInMillis());
//        System.out.println(new Date().getTime());
//
//        // 精确到秒
//        // 获取当前时间戳
//        System.out.println(System.currentTimeMillis());
//        System.out.println(Calendar.getInstance().getTimeInMillis() / 1000);
//        System.out.println(new Date().getTime() / 1000);
//        
//        int i = 1565149961;
//        System.out.println(new Date(i));
//        
//        System.out.println(new Date(i * 1000L));
//        
//        System.out.println("2234.234234234".split(".").length);
//        System.out.println("2234.234234234".split("\\.").length);
//        
//        System.out.println(1345 << 32 | 45);
//        
//        System.out.println("adadf?dsfsdf".split("\\?")[1]);
//        String ss = "a	d";
//        System.out.println(ss.split("\\t")[1]);
//        System.out.println(ss.split("\t")[1]);
//        System.out.println(ss.split("\\\t")[1]);
//        
//		Map<String, Integer> pkCrossTypeMap = new HashMap<String, Integer>();
//		int xx = pkCrossTypeMap.get("ss");
		
		
		System.out.println( ((long)282867763 << 32) | (long)306240238);
		System.out.println( ((long)306240238 << 32) | (long)282867763);
		
		System.out.println((Map) null);
		
		
		Date d1 = parse("201910281210", new SimpleDateFormat("yyyyMMddHHmm"));
		Date d2 = parse("201910271250", new SimpleDateFormat("yyyyMMddHHmm"));
		Thread.sleep(1000L);
		Date d = new Date();
		System.out.println(getDistDates(d1, d));
		System.out.println(getDistDates(d2, d));
		
		System.out.println(10 * 60 * 1000 / 20);
		System.out.println(10 * 60 * 1000 / 10);
		System.out.println(10 * 60 * 1000 / 11);
		
		gg g = new gg();
		g.add();
		List<String> l = g.getList();
		setnull(g);
        System.out.println(l);
        System.out.println(g.getList());
	}
	
	
	
	private static void setnull(gg g) {
		g.setList(null);
		
	}



	public static Date parse(String dateStr, DateFormat format) {
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();//ignore
		}
		return null;
	}
	public static long getDistDates(Date startDate,Date endDate)      
    {    
        long totalDate = 0;    
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(startDate);    
        long timestart = calendar.getTimeInMillis();    
        calendar.setTime(endDate);    
        long timeend = calendar.getTimeInMillis();    
        totalDate = Math.abs((timeend - timestart))/(1000*60*60*24);    
        return totalDate;    
    }  
	
	static void handle(List<Integer> aa){
		aa.add(2);
		try {
			int i = 1/0;
		}catch(Exception e) {
			System.out.println(1);
			throw e;
		}finally {
			System.out.println(2);
		}
	}
}

class gg{
	 public List<String> list = new ArrayList<String>();
	 void add() {
		 list.add("12");
		 list.add("13");
	 }
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
}
