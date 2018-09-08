package com.joyoungzhang.demo.java;

import java.io.Serializable;

import org.apache.spark.sql.SparkSession;

/**
 * 
 * @author jonezhang
 *
 */
public class Pretreatment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		boolean enableHiveSupport = Boolean.parseBoolean(args[0]);
		System.out.format("enableHiveSupport is %s", enableHiveSupport);
		SparkSession session = null;
		if(enableHiveSupport) {
			session = SparkSession.builder().enableHiveSupport().getOrCreate();
		}else {
			session = SparkSession.builder().getOrCreate();
		}
		System.out.format("spark.conf.get(\"spark.sql.catalogImplementation\") is %s", session.conf().get("spark.sql.catalogImplementation"));
		session.sql("show tables").show();
		session.sql("select * from pokes").show();
	}
	
}