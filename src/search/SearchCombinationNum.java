package search;

/**
 * ����һ�������һ�����֣��������в�����������ʹ�����ǵĺ�������������Ǹ�����
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-9-3 ����4:17
 */

/**
 * 1.����ʵ�ֵ������˼бƷ�,�ź�����O(N)
 */
public class SearchCombinationNum {
	/**
	 * ��������������Ƚ�������
	 *
	 * @param array ��������
	 * @param num Ŀ��ֵ
	 * @param offset ��������ȷ������߽�,���ٲ��Ҵ���
	 */
	public static void searchPress(int[] array, int num,int offset) {
		int left = 0;
		int right = array.length - 1;

		while (left < right) {
			int sum = array[left] + array[right];
			//���sum����ô�������٣����Ҷ˵�������
			if (sum > num) {
				right--;
			}
			//���sumС����ô������������˵�������
			else if (sum < num) {
				left++;
			}
			else {
				System.out.println("got it:" + array[left] + " + " + array[right]);
				return;
			}
		}
	}

	/**
	 * 2.bit hash��Ҳ��O(N),���Ǻķ�O(N)�Ŀռ临�Ӷ�
	 * @param array
	 */
	public static void searchHash(int[] array,int num,int offset) {

	}
	public static void main(String[] args) {

		int[] array = {1,3,5,7,8,13};
		searchPress(array,15,array.length);
	}
}
