
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
        // �������һ��Ԫ������  
        int mid = center + 1;  
        // third ��¼��ʱ���������  
        int third = left;  
        // �����������һ��Ԫ�ص�����  
        int tmp = left;  
        while (left <= center && mid <= right) {  
            // ������������ȡ����С�ķ�����ʱ����
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
        // ʣ�ಿ�����η�����ʱ���飨ʵ��������whileֻ��ִ������һ����  
        while (mid <= right) {  
            tmpArr[third++] = data[mid++];  
        }  
        while (left <= center) {  
            tmpArr[third++] = data[left++];  
        }  
        // ����ʱ�����е����ݿ�����ԭ������  
        // ��ԭleft-right��Χ�����ݱ����ƻ�ԭ���飩  
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
