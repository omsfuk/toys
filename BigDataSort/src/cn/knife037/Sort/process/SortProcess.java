package cn.knife037.Sort.process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cn.knife037.Sort.read.InputControler;
import cn.knife037.Sort.read.MultiFileWriter;
import cn.knife037.Sort.read.Spice;

public class SortProcess {

	private int LineNum = 0;
	
	private char[][] tmpArr;
	
	public SortProcess() {
		long startTime = System.currentTimeMillis();
		
		int num = MultiFileSort();
		mergeSortedFile(num);
		System.out.println("finished!");
		
		System.out.println("total time expended : " + (System.currentTimeMillis() - startTime) / 1000 + " Secs");
	}
	
	private void mergeSortedFile(int num) {
		BufferedReader[] brs = new BufferedReader[num];
		for(int i = 0; i < num; i++) {
			try {
				brs[i] = new BufferedReader(new FileReader("data" + Integer.toString(i + 1) + ".bak"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		String[] strings = new String[num];
		
		for(int i = 0; i < num; i++) {
			try {
				strings[i] = brs[i].readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		MultiFileWriter writer = new MultiFileWriter(1000000);
		
		long count = 0;
		
		do{
			count ++;
			boolean f = false;
			for(int i = 0; i < num; i++) {
				if(brs[i] != null) {
					f = true;
				}
			}
			
			if(f == false) {
				break;
			}
			
			String tmp = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
			int pos = 0;
			for(int i = 0; i < num; i++) {
				if(strings[i] != null && tmp.compareTo(strings[i]) > 0) {
					tmp = strings[i];
					pos = i;
				}
			}
			
			try {
				if((strings[pos] = brs[pos].readLine()) == null) {
					brs[pos].close();
					brs[pos] = null;
					strings[pos] = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			writer.write(tmp);
			
			if(count % 1000000 == 0) {
				System.out.println("has handled " + count + " records");
			}
			
		} while(true);
		
		writer.flush();
		
	}
	
	
	private int MultiFileSort() {
		InputControler input = new InputControler();
		int lineCount = input.getLineCount();
		tmpArr = new char[input.getLineCount()][];
		int num = 0;
		Spice spice = null;
		char[][] data = null;
		do {
			long time = System.currentTimeMillis();
			num ++;
			spice = input.getSpice();
			data = spice.strings;
			System.out.println("trying sort the " + Integer.toString(num) + " spice");
			System.out.println("SpiceLines : " + spice.length);
//			System.out.println(data[0]);
			sort(data, 0, spice.length - 1);
//			System.out.println(data[0]);
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("data" + Integer.toString(num)+ ".bak"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			for(int i = 0; i < spice.length; i++) {
				try {
					bw.write(data[i]);
					bw.write("\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				data[i] = null;
			}
			
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			data = null;
			System.out.println("time expended : " + (System.currentTimeMillis() - time) / 1000 + " Secs");
			System.gc();
		} while (spice.length == lineCount);
		
		
		
		return num;
	}

	private void sort(char[][] data, int left, int right) {
		if (left >= right) {
			return;
		}
		int center = (left + right) / 2;
		sort(data, left, center);
		sort(data, center + 1, right);
		merge(data, left, center, right);
	}

	private void merge(char[][] data, int left, int center, int right) {
        // 右数组第一个元素索引  
        int mid = center + 1;  
        // third 记录临时数组的索引  
        int third = left;  
        // 缓存左数组第一个元素的索引  
        int tmp = left;  
        while (left <= center && mid <= right) {  
            // 从两个数组中取出最小的放入临时数组
//        	System.out.println(data[left]);
//        	System.out.println(data[mid]);
            if (compare(data[left], data[mid])) { 
                tmpArr[third++] = data[left++];  
            } else {
                tmpArr[third++] = data[mid++];  
            }  
        }  
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）  
        while (mid <= right) {  
            tmpArr[third++] = data[mid++];  
        }  
        while (left <= center) {  
            tmpArr[third++] = data[left++];  
        }  
        // 将临时数组中的内容拷贝回原数组中  
        // （原left-right范围的内容被复制回原数组）  
        while (tmp <= right) {  
            data[tmp] = tmpArr[tmp++];  
        }  
		
	}
	
	private boolean compare(char[] a, char[] b) {
		if(a.length < b.length) {
			return true;
		}
		
		if(b.length > a.length) {
			return false;
		}
		
		for(int i = 0; i < a.length; i++) {
			if(a[i] < b[i]) {
				return true;
			} else {
				return false;
			}
		}
		System.out.println(false);
		return false;
	}

	public static void main(String[] args) {
		new SortProcess();
	}
}
