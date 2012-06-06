package chessBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ���̸�������
 * User: ${wa7chen}
 * Date: 11-3-14
 * Time: ����3:53
 */
public class ChessBoard {
	/*
	tileΪȫ�ֱ�������ʾL�͹��Ƶ���ţ�
	 */
	static int tile ;
	/*
	board ��ʾ����,�ö�ά�����ʾ
	 */
	static int[][] board = new int[4][4];
	/*
	���̸��ǵķ������õݹ�ʵ��
	2^size��ʾ�����̵ı߳�
	dr , dc ��ʾ���ⷽ������
	tr,tc��ʾ���ϽǷ�������
	 */
	public static void chessBoard(int tr,int tc,int dr,int dc,int size){
		/*
		sizeΪ1ʱ���ٽ����������ǵ����̾�ֻʣ��һ������
		 */
		if(size == 1) return;
		else {
			/*
			s��ʾ���̷ָ��ĳ���
			t��ʾ��ʱ��L�͹��Ƶ����
			 */
			int s = size / 2;
			int t = tile++;

			//�������Ͻ�������
			//������ⷽ�������Ͻ�
			if(dr < tr + s && dc < tc + s ){
				//�����ָ�
				chessBoard(tr,tc,dr,dc,s);
			}
			//������ⷽ���ڣ���ô�����½Ƿ��ù���
			else{
				board[tr + s - 1][tc + s - 1] = t;
				//�������෽��,��ʱ���ⷽ���Ǹոշ��ù��Ƶ�λ��
				chessBoard(tr,tc,tr + s - 1 ,tc + s - 1,s);
			}

			//�������Ͻ�������
			//������ⷽ�������Ͻ�
			if(dr < tr +  s && dc >= tc + s ){
				//�����ָ�,��ʱ��Ҫ�ı����Ͻ�λ�õ����꣨���Ͻ����̵����Ͻǣ�
				chessBoard(tr,tc + s,dr,dc,s);
			}
			//������ⷽ���ڣ���ô�����½Ƿ��ù���
			else{
				//ע���ʱ���½ǵ����� ������ͬ�ģ�������Ū��
				board[tr + s - 1][tc + s ] = t;
				//�������෽��
				chessBoard(tr,tc + s,tr + s -1,tc + s,s);
			}

			//�������½ǵ�����
			//������ⷽ�������½�
			if(dr >= tr + s  && dc < tc + s ){
				//�����ָ�,��ʱ��Ҫ�ı����Ͻ�λ�õ�����
				chessBoard(tr + s,tc,dr,dc,s);
			}
			//������ⷽ���ڣ���ô�����ϽǷ��ù���
			else{
				board[tr + s ][tc + s - 1 ] = t;
				//�������෽��
				chessBoard(tr + s,tc,tr + s ,tc + s - 1,s);
			}

			//�������½ǽǵ�����
			//������ⷽ�������½�
			if(dr >= tr + s  && dc >= tc + s ){
				//�����ָ�,��ʱ��Ҫ�ı����Ͻǽ�λ�õ�����
				chessBoard(tr + s,tc + s,dr,dc,s);
			}
			//������ⷽ���ڣ���ô�����ϽǷ��ù���
			else{
				board[tr + s ][tc + s ] = t;
				//�������෽��
				chessBoard(tr + s,tc + s,tr + s ,tc + s,s);
			}
		}


	}

	//�������̵ķ���
	public static int[] getBoard(){
		int size = 4;
		int [] location = new int[2];
		Random random = new Random();
		//�漴�������ⷽ���λ��
		int row = random.nextInt(size);
		int col = random.nextInt(size);
		//������λ�õ����괢�棬��Ϊ����ֵ����
		location[0] = row;
		location[1] = col;

		for(int i = 0; i < size;i++){
			for(int j = 0 ;j < size;j++){
				if(i == row && j == col) board[i][j] = -1;
				else{
					board[i][j] = 0;
				}
			}
		}
		return location;
	}
	/*
	��ʾ���̵ķ���
	 */
	public static void showBoard(int[][] boards){
		for(int i = 0 ; i < boards.length;i++){
			for(int j = 0 ;j < boards.length;j++){
				System.out.print(" " + boards[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] location = getBoard();
		System.out.println("ԭ����");
		showBoard(board);

		chessBoard(0,0,location[0],location[1],board.length);
		System.out.println("���ú������");
		showBoard(board);
	}
}
