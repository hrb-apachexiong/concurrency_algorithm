package test.baixing;


import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �����¼�������
 *
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-9-18 PM6:21
 */
public class ViewCounterCache {

	//�������ݿ���ʱ��(ms)
	private static final int PEROID = 2000;
	final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
	final BlockingQueue<Integer> waitingRecord = new LinkedBlockingQueue<Integer>(200);
	final Executor service = Executors.newSingleThreadExecutor();
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	volatile HashMap<Integer, Integer> recordMap = new HashMap<Integer, Integer>(100000);

	public ViewCounterCache() {
		//ÿ��2s �������ݿ�
		scheduled.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				//TODO ����DB
			}
		}, 500, PEROID, TimeUnit.MILLISECONDS);

		service.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					//�����ڴ�
					updateCache();
				}

			}
		});

	}

	/**
	 * ÿ������������ӻ������
	 * �첽��������ȡ��ǰ�ļ�������
	 * ��εĸ��·��������
	 * ��д����
	 * @param uid
	 * @return
	 */
	public int increment(int uid) {
		int current = 1;
		try {
			lock.readLock().lock();
			if (recordMap.containsValue(uid)) {
				current = recordMap.get(uid);
			}
		} finally {
			lock.readLock().unlock();
		}

		try {
			waitingRecord.put(uid);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return current;
	}

	/**
	 * ���²���
	 * ��ȡ��������Ҫ���µļ�¼
	 */
	public void updateCache() {
		try {
			int uid = waitingRecord.take();

			if(uid == 0)
				return;

			lock.writeLock().lock();
			//���������¼
			if (recordMap.containsValue(uid)) {
				recordMap.put(uid, recordMap.get(uid) + 1);
			} else {
				recordMap.put(uid, 1);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}

	}
}
