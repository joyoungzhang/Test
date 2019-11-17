package poc.mapr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 
 * @author jonezhang
 *
 */
public class TestMapRIO {
	
	private static final int bufferSize = 1000000;

	public static void main(String[] args) throws IOException {
		String clientType = args[0];
		String rw = args[1];
		long fileSize = Long.parseLong(args[2]);
		String baseDir = args[3];
		
		System.out.println(String.format("Start test maprclient %s use %s, filesize is %s, baseDir is %s", rw, clientType, fileSize, baseDir));
		
		test(clientType, rw, fileSize, baseDir);
	}

	private static void test(String clientType, String rw, long fileSize, String baseDir) throws IOException {
		String dataDir = baseDir + "/TestDFSIO/io_data";
		if("hdfs".equals(clientType)) {
			if("r".equals(rw)) {
				testHdfsRead(fileSize, dataDir);
			}else if("w".equals(rw)) {
				testHdfsWrite(fileSize, dataDir);
			}
		}else if("nfs".equals(clientType)) {
			if("r".equals(rw)) {
				testNfsRead(fileSize, dataDir);
			}else if("w".equals(rw)) {
				testNfsWrite(fileSize, dataDir);
			}
		}
	}

	private static void testNfsWrite(long totalSize, String dataDir) throws IOException {
		File file = new File("/mapr/tencent.mapr.cluster" + dataDir);
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();
		byte[] buffer = new byte[bufferSize];
		for(int i=0; i < bufferSize; i++) {
	        buffer[i] = (byte)('0' + i % 50);
		}
		long begin = System.currentTimeMillis();
		OutputStream out = new FileOutputStream(file);
	    // write to the file
		long nrRemaining;
		for (nrRemaining = totalSize; nrRemaining > 0; nrRemaining -= bufferSize) {
			int curSize = (bufferSize < nrRemaining) ? bufferSize : (int)nrRemaining;
			out.write(buffer, 0, curSize);
		}
		out.close();
		long end = System.currentTimeMillis();
		System.out.println(String.format("testNfsWrite cost %s", (end - begin)));
	}

	private static void testNfsRead(long totalSize, String dataDir) throws IOException {
		File file = new File("/mapr/tencent.mapr.cluster/" + dataDir);
		byte[] buffer = new byte[bufferSize];
		for(int i=0; i < bufferSize; i++) {
	        buffer[i] = (byte)('0' + i % 50);
		}
		long begin = System.currentTimeMillis();
		InputStream in = new FileInputStream(file);
		long actualSize = 0;
		while (actualSize < totalSize) {
	        int curSize = in.read(buffer, 0, bufferSize);
	        if(curSize < 0) break;
	        actualSize += curSize;
		}
		in.close();
		long end = System.currentTimeMillis();
		System.out.println(String.format("testNfsRead cost %s", (end - begin)));
	}

	private static void testHdfsWrite(long totalSize, String dataDir) throws IOException {
		FileSystem fs = FileSystem.get(new Configuration());
		OutputStream out = fs.create(new Path(dataDir), true, bufferSize);
		
		byte[] buffer = new byte[bufferSize];
		for(int i=0; i < bufferSize; i++) {
	        buffer[i] = (byte)('0' + i % 50);
		}
		long begin = System.currentTimeMillis();
	    // write to the file
		long nrRemaining;
		for (nrRemaining = totalSize; nrRemaining > 0; nrRemaining -= bufferSize) {
			int curSize = (bufferSize < nrRemaining) ? bufferSize : (int)nrRemaining;
			out.write(buffer, 0, curSize);
		}
		out.close();
		fs.close();
		long end = System.currentTimeMillis();
		System.out.println(String.format("testHdfsWrite cost %s", (end - begin)));
	}

	private static void testHdfsRead(long totalSize, String dataDir) throws IOException {
		FileSystem fs = FileSystem.get(new Configuration());
		InputStream in = fs.open(new Path(dataDir));
		
		byte[] buffer = new byte[bufferSize];
		for(int i=0; i < bufferSize; i++) {
	        buffer[i] = (byte)('0' + i % 50);
		}
		long begin = System.currentTimeMillis();
		long actualSize = 0;
		while (actualSize < totalSize) {
	        int curSize = in.read(buffer, 0, bufferSize);
	        if(curSize < 0) break;
	        actualSize += curSize;
		}
		in.close();
		fs.close();
		long end = System.currentTimeMillis();
		System.out.println(String.format("testHdfsRead cost %s", (end - begin)));
	}
	
}
