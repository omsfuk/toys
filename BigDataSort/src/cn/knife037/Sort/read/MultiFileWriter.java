package cn.knife037.Sort.read;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MultiFileWriter {

	private long num;
	
	private long count = 0;
	
	private String fileName = null;
	
	private BufferedWriter bw = null;
	
	public MultiFileWriter(long num) {
		this.num = num;
	}
	
	public void write(String str) {
		if(count % num == 0) {
			fileName = "data" + count / num + ".bak";
			try {
				if(bw != null) {
					bw.close();
				}
				bw = new BufferedWriter(new FileWriter("sorted/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		count ++;
		try {
			bw.write(str);
			bw.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void flush() {
		try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
