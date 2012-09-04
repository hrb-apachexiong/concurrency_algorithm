package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 *�����׺���ʽ
 * ��׺ת��׺
 */
/*
������ֵ���Ҫ������Ǵ�char�����ж����� Ȼ����ȡ��ʱ�������͵�����
��סһ��char���͵����ּ���ʱ �������Ƕ�Ӧ����ֵ���㣬�����Ƿ���ֵ�����Ի�������
�ҵ������ǽ�char������-'0'�ַ�0����ʱ�õ��ľ���int��Ȼ���ٴ���stack
 */
public class UsesOfStack {
	private static char[] operators = {'*','+','-','/'};

	public static int countPostfix(char[] items){
		MyStack stack = new MyStack();
		for(int i = 0;i < items.length;i++){
			//���������ֱ������stack��
			if(Character.isDigit(items[i]) ){
				//�������ֻ���Ҫ���� ���ַ�ת��Ϊ����
				int digit = items[i] - '0';
				stack.push(digit);
			}else if(Arrays.binarySearch(operators,items[i]) != -1){
				//����ǲ�������ô��stack��ȡ���������м��㣬Ȼ����push
				//����Ҫע���Ⱥ�˳��
				int y = (Integer)stack.pop();
				int x = (Integer)stack.pop();
				int value =  0;
				switch(items[i]){
					case'*':value = x * y;break;
					case'+':value = x + y;break;
					case'-':value = x - y;break;
					case'/':value = x / y;break;
				}
				stack.push(value);
				//System.out.println(value);
			}
		}
		//ѭ����ֹʱ��pop��stack�����ֵ
		int total = (Integer)stack.pop();
		System.out.println(total);
		return total;
	}
	/*
	�����ж�ȡ�ַ���
	 */
	public static String getString() throws IOException{
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String string  = br.readLine();
		return string;
	}

	public static String infix2Posfix(String infix){
		MyStack stack = new MyStack();
		String posfix = "";
		for(int i = 0 ;i < infix.length();i++){
			char item = infix.charAt(i);
			//���������ֱ�����
			if(Character.isDigit(item)){
				posfix += item;
			}
			else {
				if(stack.size() == 0)
					stack.push(item);
				else {
					while(stack.size() >= 1){
						char last = (Character)stack.peek();
						//�����ж��Ƿ���')',��ô��pop������֮ǰ���е�
						if(item == ')'){
							while(true){
								char x = (Character)stack.pop();
								if(x != '(') {
									posfix += x;
								}else break;
							}  //ѭ���Ŀ��ƺ���Ҫ���������û��break����ô��Ҳ�����뵽ջ��
							break;
						}
						//���stack��Ԫ�ش��ڼ�����stack��Ԫ�أ���ô��ջ��Ԫ��pop����Ԫ��push
						while(stack.size() >= 1 && priority(last,item)){
							posfix += stack.pop();
							if(stack.size() > 0)
								last = (Character)stack.peek();
							else break;
						}
						stack.push(item);
						break;
					}
				}
			}
		}
		//ѭ��������pop���е�Ԫ��
		while(stack.size() != 0){
			System.out.println("popall");
			posfix += stack.pop();
		}
		return posfix;
	}

	//�ж϶�ȡ���¸����ŵ����ȼ�
	//����ȡ�ķ��ŵ����ȼ�������ջ���ķ��ŵ����ȼ���ջ�����
	//���������ջ���ǣ���ô���еķ��ž�push
	//flag��ʾ�Ƿ��ܳ�ջ
	public static boolean priority(char a,char b){
		boolean flag = false;
		switch (b){
			case '+':;
			case '-':flag = true;break;
			case '*':;
			case '/':if(a == '*' || a == '/') flag = true;break;
		}//'('Ĭ��Ϊ������ȼ�
		if(a == '(') flag = false;
		return flag;
	}

	public static void main(String[] args) throws IOException{
//���ַ���ת��Ϊ�ַ�����
		String string = "9-8*(4+9*7)-2";
		String posfix =  infix2Posfix(string)  ;
		System.out.println(posfix);

		char[] items = posfix.toCharArray();

		int value = countPostfix(items);
		System.out.println(value);

	}

}
