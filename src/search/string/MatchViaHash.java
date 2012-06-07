package search.string;

/**
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-6-7 PM4:50
 */

/**
 * ����Hash�����ַ�����Rabin-Karp �㷨
 * ��������ΪM��Ŀ���ַ���������ΪN�ĸ��ַ���
 * ������Ҫ�����Ӵ���hash��Ȼ��ֱ��������ַ�����sub��hash��
 * ֻ��Ҫ����һ��O(M)�ĸ��ַ���hash1,��һλ�ִ���hash ���Ը���hash1����2�γ˷���1�μӷ���1һ�μ���
 *
 * �㷨���Ӷ� O(n + m)
 *
 */
public class MatchViaHash {
	public static long findString(String str ,String subStr){
		if(str == null || subStr == null ||  str.length() == 0 || subStr.length() == 0){
			return  -1;
		}
		long subHash = subStr.hashCode();
		long parentHash = str.substring(0,subStr.length()).hashCode();

		for(int i = 0 ;i < str.length() - subStr.length();i++){
			if(subHash == parentHash){
				return i;
			}
			else {
				parentHash = parentHash * 31 - (long)(str.charAt(i) * Math.pow(31,subStr.length())) + str.charAt(i + subStr.length());
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		String parent = "hello world!";
		String sub = "world";
		System.out.println(findString(parent,sub));
}

}
