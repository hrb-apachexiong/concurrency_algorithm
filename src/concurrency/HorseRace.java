package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: ${wa7chen}
 * Time: PM3:56
 */
class Horse implements Runnable{
	private static int counter =0;
	private final int id = counter++;
	private int strides = 0;
	private static Random random = new Random(47);
	private static CyclicBarrier barrier;

	public Horse(CyclicBarrier barrier){
		this.barrier = barrier;
	}

	public synchronized int getStrides(){
		return strides;
	}

	@Override
	public String toString() {
		return "Horse " + id + " ";
	}

	public void run() {
		try{
			while (!Thread.interrupted()){
				synchronized (this){
						strides += random.nextInt(3);
				}
				//һֱ�ȵ�����barrier�����Բ����߶�������await(),դ������ʼִ�У�Ȼ����ѭ��
				barrier.await();
			}
		} catch (InterruptedException e){
			//
		} catch (BrokenBarrierException e){
			throw new RuntimeException(e);
		}

	}

	public String tracks(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getStrides(); i++) {
			sb.append("*");
		}
		sb.append(id);
		return sb.toString();
	}
}

public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private CyclicBarrier barrier;
	private ExecutorService service = Executors.newCachedThreadPool();

	public HorseRace(int nHorses ,final int pause) {
		//����CyclicBarrier��countֵ���Լ�դ���̣߳�������Ϊ=0ʱִ�У�Ȼ��ѭ����
		barrier = new CyclicBarrier(nHorses,new Runnable() {
			public void run() {
				StringBuilder sb = new StringBuilder();
				for(int i = 0;i < FINISH_LINE;i++){
					sb.append("=");
				}
				System.out.println(sb);
				for (Horse horse : horses) {
					System.out.println(horse.tracks());
				}
				for (Horse horse : horses) {
					if(horse.getStrides() >= FINISH_LINE){
						System.out.println(horse + "won!");
						service.shutdownNow();
						return;
					}
				}

				try{
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e){
					System.out.println("barrier sleep interrupted!");
				}
			}
		});
		for (int i = 0; i < nHorses; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			service.execute(horse);
		}
	}

	public static void main(String[] args) {
		int nHorse = 7;
		int pause = 200;
		new HorseRace(nHorse,pause);
	}
}
