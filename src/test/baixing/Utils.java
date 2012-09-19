package test.baixing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-9-18 PM9:18
 */
public class Utils {
	private static char[] HEX_DIGITS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	/**
	 * ����http�����ȡͼƬ����
	 * @param url
	 * @return
	 */
	public static byte[] fetch(String url) {


		return new byte[100];
	}

	/**
	 * ���������16λ����byte����ת���ַ�����ʾ������
	 * @param md5Bytes 16λ���ı�ʾMD5��byte����
	 * @return 32λ���ȵ��ַ���
	 */
	public static String MD5Bytes2String(byte[] md5Bytes) {
		if(md5Bytes != null && md5Bytes.length == 16 ) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < md5Bytes.length; i++) {
				byte b = md5Bytes[i];
				s.append(HEX_DIGITS[b >>> 4 & 0x0f]);
				s.append(HEX_DIGITS[b & 0x0f]);
			}
			return s.toString();
		}

		return null;
	}

	/**
	 * ����MD5ֵ
	 * @param data
	 * @return
	 */
	public static byte[] getMD5FromBytes(byte[] data) {
		if( data != null && data.length > 0) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				return md5.digest(data);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
