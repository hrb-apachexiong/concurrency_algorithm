package thinkInJava;

/**
 * Created by IntelliJ IDEA.
 * User: ${wa7chen}
 * Date: 11-3-20
 * Time: ����7:20
 */
class Game{
	Game(int i){
		System.out.println("Game constructor");
	}
	private final  void go(){
		System.out.println("Game go !");
	}
}

class BoardGame extends Game{
	BoardGame(int i ){
		//�����Ĭ�ϵ��ó����Ĭ�Ϲ��췽�������û�оͱ�����ʾ�ĵ��ó�����вι��췽��
		super(i);
		System.out.println("BoardGame constructor");
	}
	// ����������Ǹ��Ǹ����go������privateȨ�������������
	private final void go(){
		System.out.println("BoardGame go !");
	}

}

public class Chess extends BoardGame{
	Chess(){
		super(1);
		System.out.println("Chess constructor");
	}
	private void play(){
		System.out.println("let's play chess!");
	}

	public static void main(String[] args) {
		BoardGame chess = new Chess();

		//chess.play(); ������Ȼʵ��������chess���󣬵�����������һ��BoardGame�������в��ܵ���chess���е�play���� 
	}
}
