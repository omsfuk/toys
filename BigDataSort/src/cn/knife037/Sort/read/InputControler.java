package cn.knife037.Sort.read;

import java.io.File;

/**
 * 假定是GBK
 * @author Administrator
 *
 */
public class InputControler {
	
	private int cursor = 0;
	
	private int lineCount = 0;
	
	private BufferedMultiFileReader reader = null;
	
	private char[][] ans = null;
	
	public InputControler() {
		// TODO 目录文件
		reader = new BufferedMultiFileReader("data/");
		calcCount();
		ans = new char[lineCount][];
	}
	
	public void calcCount() {
		int avgSize = reader.getAvgSize();
		long length = reader.getLength();
		long lines = length / avgSize;
		// TODO
		long spiceCount = length / 1024 / 1024 / 1;
		long ans = (lines / spiceCount);
		if(ans > Integer.MAX_VALUE) {
			ans = Integer.MAX_VALUE - 10;
		}
		lineCount = (int)ans;
	}
	
	public int getLineCount() {
		return lineCount;
	}
	
	public Spice getSpice() {
		
		String t = null;
		int num = 0;
		System.out.println(lineCount);
		while(num < lineCount && (t = reader.readLine()) != null) {
			ans[num] = t.toCharArray();
			num ++;
		}
		return new Spice(num, ans);
	}
}
