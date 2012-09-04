package backtrace;

/**
 * User: ${wa7chen}
 * Time: ����9:16
 */
//�������ݷ�
public class LoadingNoRecursion {


	public static int maxLoading(int[] w,int c,int[] bestx){
		int i = 1;
		int n = w.length - 1;
		int[] x = new int[n + 1];
		int bestw = 0;
		int cw = 0;
		int r = 0;

		for(int j = 1; j <= n;j++){
			r += w[j];
		}

		//��������
		while(true){
			while(i <= n && cw + w[i] <= c){
				//����������
				r -= w[i];
				cw += w[i];
				x[i] = 1;
				i++;
			}

			if(i > n){
				//����Ҷ�ӽڵ�
				for(int j = 1;j <= n;j++){
					bestx[j] = x[j];
				}
				bestw = cw;
			}

			else{
				//����������
				r -= w[i];
				x[i] = 0;
				i++;
			}

			while(cw + r <= bestw){
				//��֦����
				i--;
				while(i > 0 && x[i] == 0){
					//������������
					r += w[i];
				}
				if(i ==0 ) return bestw;

				//����������
				x[i] = 0;
				cw -= w[i];
				i++;
			}
		}
	}
}
