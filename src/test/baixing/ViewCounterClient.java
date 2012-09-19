package test.baixing;

/**
 * �����¼�ͻ���
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-9-18 PM6:20
 */
public class ViewCounterClient {
	private ViewCounterCache cache;

	/**
	 * ÿ�����һ��ҳ��
	 * @param id ҳ����
	 * @param old �ϴε������
	 * @return
	 */
	public int doRequest(int id,int old) {
		int count = cache.increment(id);
		//do some thing ...
		return count == old?++count:count;
	}
}
