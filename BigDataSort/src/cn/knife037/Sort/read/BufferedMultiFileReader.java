package cn.knife037.Sort.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 假定是一个目录
 * 当配置到真实环境中时，需要对此类进行修改
 * @author Administrator
 *
 */
public class BufferedMultiFileReader implements MultiFileReader {
	
	private static final int PREREAD_LINES = 1000;
	
	private int cursor = 0;
	
	private String filePath = null;
	
	private String[] files = null;
	
	private BufferedReader br = null;
	
	public BufferedMultiFileReader(String filePath) {
		this.filePath = filePath;
		try {
			init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private void init() throws FileNotFoundException {
		files = new File(filePath).list();
		br = new BufferedReader(new FileReader(new File(filePath + files[0])), 1024 * 1024 * 10);
	}
	
	@Override
	public long getLength() {
		long ans = 0;
		for(String f : files) {
			File file = new File(filePath + f);
			ans += file.length();
		}
		return ans;
	}
	
	@Override
	public int getAvgSize() {
		//取前1000条的长度平均值
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(filePath + files[0])), 1024 * 1024 * 10);
		} catch (FileNotFoundException e) {
			return -1;
		}
		String t = null;
		long num = 0;
		long ans = 0;
		try {
			while(num < PREREAD_LINES && (t = br.readLine()) != null) {
				num ++;
				ans += t.length();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (int)(ans / PREREAD_LINES);
	}
	
	@Override
	public String readLine() {
		String t = null;
		try {
			t = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(t == null) {
			
			if(cursor + 1 == files.length) {
				return null;
			} else {
				cursor ++;
				try {
					if(br != null) {
						br.close();
					}
					br = new BufferedReader(new FileReader(new File(filePath + files[cursor])), 1024 * 1024 * 10);
					t = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return t;
	}
}
