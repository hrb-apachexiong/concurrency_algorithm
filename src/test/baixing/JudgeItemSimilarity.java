package test.baixing;

import search.string.MatchViaHash;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-9-18 PM8:39
 */
public class JudgeItemSimilarity {

	//�·�������Ϣ
	private Item item;
	//�Ѿ���������Ϣ
	private Item[] reportedItems;

	public JudgeItemSimilarity(Item item) {
		this.item = item;

		this.reportedItems = selectItemByUserID(item.getUserID());
	}

	/**
	 * ���ѷ�������Ϣ�Ƚ�
	 *
	 * @return false û���ظ���
	 *         true  ���ظ�
	 */
	public boolean check() {

		for (Item exist : reportedItems) {
			if (checkTags(item, exist)) {
				if (item.getDesc() == null || checkDesc(item, exist)) {
					if (item.getPicUrls() == null ||
							item.getPicUrls().length == 0 ||
							checkPics(item)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checkTags(Item item, Item reported) {
		//TODO ˳��Ƚ�ÿ��tag

		return true;
	}

	/**
	 * ȡ������������Ϣ�ڵ�10������Ӵ����������ѷ�����������Ϣƥ��
	 * ����Rabin-Karp �㷨,���Ӷ��㷨���Ӷ� O(n + m)��n ΪĿ�괮���ȣ�mΪ�����Ҵ�����
	 *
	 * @param item
	 * @param reported
	 * @return
	 */
	public static boolean checkDesc(Item item, Item reported) {
		//�����ȡ���Ӵ� �����������̳���
		final int minLength = (int)(item.getDesc().length() * 0.1);
		int unmatch = 0;
		for (int i = 0; i < 10; i++) {
			long isMatch;
			while (true) {
				Random r = new Random();
				int index1 = r.nextInt(item.getDesc().length());
				int index2 = r.nextInt(item.getDesc().length());
				//��֤�Ӵ�����
				if (index1 - index2 < minLength && index1 - index2 > -minLength)
					continue;
				if (index1 > index2)
					isMatch = MatchViaHash.findString(reported.getDesc(),
							item.getDesc().substring(index2, index1));
				else
					isMatch = MatchViaHash.findString(reported.getDesc(),
							item.getDesc().substring(index1, index2));

				break;
			}
			if (isMatch == -1) {
				unmatch++;
				if (unmatch >= 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkPics(Item item) {
		int unmatch = 0;
		String[] existPicMD5s = selectPicByUserID(item.getUserID());
		for (String url : item.getPicUrls()) {
			String stringMD5 = fetchPicMD5(url, item.getUserID());

			int result = Arrays.binarySearch(existPicMD5s, stringMD5);
			if (result == -1) {
				unmatch++;
				if (unmatch >= 1) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * ����user id ��ѯ��һ�췢�������б���
	 *
	 * @param userID
	 * @return
	 */
	public static Item[] selectItemByUserID(long userID) {
		return new Item[5];
	}

	/**
	 * �����û�id���һ�췢������������ͼƬMD5ֵ
	 *
	 * @param userID
	 * @return
	 */
	public static String[] selectPicByUserID(long userID) {
		return new String[10];
	}

	/**
	 * ���ڲ��ӿڣ���ȡ��Ӧurl��ͼƬ����
	 * Ӧ���аɡ�����
	 * ���û��ֱ�ӷ������������ȡ����
	 *
	 * @param url
	 * @return
	 */
	public static String fetchPicMD5(String url, long userID) {
		//���ڲ��ӿ�
		byte[] picData = queryPicData(url, userID);
		if (picData == null) {
			//û����������������
			picData = Utils.fetch(url);
		}
		byte[] md5Bytes = Utils.getMD5FromBytes(picData);

		return Utils.MD5Bytes2String(md5Bytes);
	}

	public static byte[] queryPicData(String url, long userID) {
		//TODO
		return new byte[10000];
	}

	class Item implements Serializable {
		private static final long serialVersionUID = 606828672775212363L;
		private long userID;
		//���Ա�ǩ
		private int tag1;
		private String tag2;
		private long tag3;

		//����
		private String desc;
		//ͼƬ����
		private String[] picUrls;

		Item(int tag1, String tag2, long tag3, String desc, String[] picUrls) {
			this.tag1 = tag1;
			this.tag2 = tag2;
			this.tag3 = tag3;
			this.desc = desc;
			this.picUrls = picUrls;

		}

		public long getUserID() {
			return userID;
		}

		public void setUserID(long userID) {
			this.userID = userID;
		}

		public int getTag1() {
			return tag1;
		}

		public void setTag1(int tag1) {
			this.tag1 = tag1;
		}

		public String getTag2() {
			return tag2;
		}

		public void setTag2(String tag2) {
			this.tag2 = tag2;
		}

		public long getTag3() {
			return tag3;
		}

		public void setTag3(long tag3) {
			this.tag3 = tag3;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String[] getPicUrls() {
			return picUrls;
		}

		public void setPicUrls(String[] picUrls) {
			this.picUrls = picUrls;
		}
	}
}
