
public class Test {
	private static char[][] tmpArr;
	
	public static void main(String[] args) {
		String b = "aaaa";
		String a = "bbbb";
		char[][] chs = {a.toCharArray(), b.toCharArray()};
		tmpArr = new char[chs.length][];
		System.out.println(chs[0]);
		sort(chs, 0, 1);
		System.out.println(chs[0]);
	}
	
	private static void sort(char[][] data, int left, int right) {
		if (left >= right) {
			return;
		}
		int center = (left + right) / 2;
		sort(data, left, center);
		sort(data, center + 1, right);
		merge(data, left, center, right);
	}

	private static void merge(char[][] data, int left, int center, int right) {
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
            	System.out.println(true);
                tmpArr[third++] = data[left++];  
            } else {
            	System.out.println("false");
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
	
	private static boolean compare(char[] a, char[] b) {
		if(a.length < b.length) {
			return true;
		}
		
		if(b.length > a.length) {
			return false;
		}
		
		for(int i = 0; i < a.length; i++) {
			if(a[i] < b[i]) {
//					System.out.println(i + "" + a[i] + "" + b[i]);
				return true;
			}
		}
		System.out.println("false");
		return false;
	}
}	
