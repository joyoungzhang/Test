package com.joyoungzhang.demo.s3;

import java.io.File;
import java.util.List;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * 
 * @author jonezhang
 *
 */
public class S3Example {

	public static void main(String[] args) {
		String access_key = "9HSFXAJAB61IKDSHPSPG";
		String secret_key = "b1osIfzCpuWVwv1YGU7mE8f9cnSmOKz8Q7gxdWq4";
		AWSCredentials credentials = new BasicAWSCredentials(access_key, secret_key);
		// 内网目前只支持HTTP
		ClientConfiguration config = new ClientConfiguration();
		config.setProtocol(Protocol.HTTP);
		// 初始化连接
		String endpoint = "s3sz.sumeru.mig";
		String region = "default";
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(Boolean.FALSE)
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
				.withClientConfiguration(config).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

//		for (Bucket bucket2 : s3client.listBuckets()) {
//			// do something
//			System.out.println("bucketName:" + bucket2.getName());
//		}
		
//		try {
//		    String bucket_name = "model-alpha";
//		    String key_name = "test/1024/zookeeper-3.4.6.jar";
//		    String file_path = "/data/home/jonezhang/s3/java-demo/lib/zookeeper-3.4.6.jar";
//		    s3client.putObject(bucket_name, key_name, new File(file_path));
//		} catch(AmazonServiceException e) {
//		    e.printStackTrace();
//		}
//		
//		try {
//		    String bucket_name = "model";
//		    String key_name = "test/1025/11.234234";
//		    S3Bean bean = new S3Bean();bean.setSs("dsjpkewr");
//		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		    ObjectOutputStream oos = new ObjectOutputStream(baos);
//		    oos.writeObject(bean);
//		    oos.flush();
//		    oos.close();
//		    InputStream is = new ByteArrayInputStream(baos.toByteArray());	
//		    ObjectMetadata meta = new ObjectMetadata();
//		    s3client.putObject(bucket_name, key_name, is, meta);
//		} catch(Exception e) {
//		    e.printStackTrace();
//		}
		
		ObjectListing ol = s3client.listObjects("model-alpha");
		List<S3ObjectSummary> objects = ol.getObjectSummaries();
		for (S3ObjectSummary os : objects) {
			if(os.getKey().startsWith("372") && os.getKey().contains("gauss_output")) {
//				File file = new File(os.getKey());
//				if(file.getParentFile().getName().equals("gauss_output")) {
					System.out.println("key:" + os.getKey() + ", time:" + os.getLastModified());
//				}
			}
		}
	}

}
