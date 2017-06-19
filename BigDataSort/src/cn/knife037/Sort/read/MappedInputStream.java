package cn.knife037.Sort.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedInputStream extends InputStream {

	private MappedByteBuffer buff;
	
	private FileChannel channel;
	
	public MappedInputStream(String fileName) {
		
        FileInputStream in;
		try {
			in = new FileInputStream(new File(fileName));
			channel = in.getChannel();
			buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,  channel.size());
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	@Override
	public int read() throws IOException {
		if(buff.get)
		return 0;
	}

}
