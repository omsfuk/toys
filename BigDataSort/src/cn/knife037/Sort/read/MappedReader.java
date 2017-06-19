package cn.knife037.Sort.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedReader {

	private String filePath;
	
	private static final int LENGTH = 1024 * 1024 * 10;
	
	private long point = 0; 

	private long length = 0;
	
	private int cursor = 0;
	
	private byte[] cache = new byte[LENGTH];
	
	private String[] files;
	
	private MappedByteBuffer buff;
	
	private FileChannel channel;
	
	private int pos = 0;
	
	private StringBuilder sb;
	
	private char[] ans;
	
	private long nowLen;
	
	public MappedReader(String filePath) {
		this.filePath = filePath;
		init();
	}
	
	private void init() {
		  
		sb = new StringBuilder();
		
        try {
        	files = new File(filePath).list();
    		File file = new File(files[0]);
            FileInputStream in = new FileInputStream(file);  
            channel = in.getChannel();
			buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,  channel.size());
			length = file.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        fill();

	}
	
	public boolean hasNext() {
		if(pos >=) {
			
		}
	}
	
	public char[] readLine() {
		sb.delete(0, sb.length());
		while(pos < nowLen && cache[pos] != '\r') {
			sb.append(cache[pos]);
			pos ++;
		}
		if(pos >= nowLen) {
			pos = 0;
			fill();
			while(pos < nowLen && cache[pos] != '\r') {
				sb.append(cache[pos]);
				pos ++;
			}
			
		} else {
			pos += 2; 
		}
		sb.getChars(0, sb.length(), ans, 0);
		return ans;
	}
	
	
	private long fill() {
        
        if(point == length) {
        	cursor++;
        	if(cursor == files.length) {
        		return 0;
        	}
        	try {
        		// TODO 下面这句话可能没用。。。。
        		channel.close();
		    	File file = new File(filePath + files[cursor]);
		    	FileInputStream in = new FileInputStream(file);
		    	channel = in.getChannel();
		    	MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,  channel.size());
		    	length = file.length();
		    	point = 0;
        	} catch(IOException e) {
        		e.printStackTrace();
        	}
        }
        
        int remain = (int)(length - point);
        if(remain < LENGTH) {
        	buff.get(cache, 0, remain);
        	point = length;
        	return remain;
        } else {
        	buff.get(cache, 0, LENGTH);
        	point += LENGTH;
        	return LENGTH;
        }
	}
}
