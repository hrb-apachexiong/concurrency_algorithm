package thinkInJava;

/**
 * Created by IntelliJ IDEA.
 * User: ${wa7chen}
 * Date: 11-4-3
 * Time: ����8:52
 */
public class Field {
	public int i = 0;
	public void go(){
		i++;
	}

	public static void main(String[] args) {
		Field field = new Field();
		field.go();
		System.out.println(field.i);
		String str = "����a";
		System.out.println(str.charAt(0));
		System.out.println(str.length());

		System.out.println("��".getBytes().length);
		/*
		һ���������ֽڣ��ַ����ĳ������ַ��ĳ��ȣ����ƽ��Ǻ��ֻ���Ӣ�ķ���
		������

		���ֱ�����ֽں�ƽ̨���
		���һ��������2 ���� 3����ƽ̨
1
��
3
3

		 */
	}
}
