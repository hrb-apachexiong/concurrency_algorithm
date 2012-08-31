package tree;

/**
 * @author <a href="mailto:chenxiong_ch.pt@taobao.com">chenxiong</a>
 * @since 12-8-12 PM10:14
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class LevelTraverseBinaryTree {

	/**
	 * ���֮�� �ֲ����������
	 * ֮ǰ�Ѿ��ö���ʵ�ֹ��������Ĳ�α����������Ҫ��������У����Ҫ���ʲôʱ��Ҫ����
	 * ���ϵ���չ����Ҳ������˼����Ȼ�Ƿ������������һ��Ӧ������ջ
	 * ��ͬʱ��Ҫ��¼��ʱ���У�ÿ���ж��ٸ�Ԫ�أ���ֻ����ArrayListģ��һ����αջ��
	 */
	public static void main(String[] args) {
		/*
									__1__
								   /     \
								__2__     3__
							   /     \       \
							  4     __5__     6
								   7     8
				 */
		int[] src = { 1, 2, 3, 4, 5, 0, 6, 0, 0, 7, 8 };
		LevelTraverseBinaryTree data = new LevelTraverseBinaryTree();
		Node root = data.createTree(src);
		data.traverseByLevelFromTop(root);
		System.out.println();
		data.traverseByLevelFromBottom(root);
	}

	/*
		 �������·ֲ����
			1
			2 3
			4 5 6
			7 8
		 */
	public void traverseByLevelFromTop(Node node) {
		if (node == null) {
			return;
		}
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.addLast(node);
		int inCount = 1;
		int outCount = 0;
		while (!queue.isEmpty()) {
			Node curNode = queue.pollFirst();
			System.out.print(curNode.getData() + " ");
			outCount++;
			if (curNode.getLeft() != null) {
				queue.addLast(curNode.getLeft());
			}
			if (curNode.getRight() != null) {
				queue.addLast(curNode.getRight());
			}
			if (outCount == inCount) {
				System.out.println();
				inCount = queue.size();
				outCount = 0;
			}
		}
	}

	/*
		�������Ϸֲ����
			7 8
			4 5 6
			2 3
			1
		*/
	public void traverseByLevelFromBottom(Node node) {
		if (node == null) {
			return;
		}
		List<Node> list = new ArrayList<Node>();
		list.add(node);
		list.add(new Node(-1));     //-1��ʾһ���������ӡʱҪ����
		int i = 0;
		int size = list.size();
		while (i < size) {
			Node curNode = list.get(i);

			/*����������������������ʵ�������
							8 7
							6 5 4
							3 2
							1
						 */
			if (curNode.getRight() != null) {
				list.add(curNode.getRight());
			}
			if (curNode.getLeft() != null) {
				list.add(curNode.getLeft());
			}

			i++;
			if (i == size) {
				if (curNode.getData() != -1 && curNode.getLeft() == null && curNode.getRight() == null) {
					break;
				}
				size = list.size();
				list.add(new Node(-1));
			}
		}

		//�Ӻ���ǰ�������൱�ڡ�ջ��
		for (size = list.size(), i = size - 1; i >=0; i--) {
			int val = list.get(i).getData();
			if (val == -1) {
				System.out.println();
			} else {
				System.out.print(val + " ");
			}
		}
	}


	public Node createTree(int[] data){
		List<Node> nodeList=new ArrayList<Node>();
		for(int each:data){
			Node n=new Node(each);
			nodeList.add(n);
		}
		int lastRootIndex=data.length/2-1;
		for(int i=0;i<=lastRootIndex;i++){
			Node root=nodeList.get(i);
			Node left=nodeList.get(i*2+1);
			if(left.getData()!=0){
				root.setLeft(left);
			}else{
				root.setLeft(null);
			}
			if(i*2+2<data.length){
				Node right=nodeList.get(i*2+2);
				if(right.getData()!=0){
					root.setRight(right);
				}else{
					root.setRight(null);
				}
			}

		}
		Node root=nodeList.get(0);
		return root;
	}
}

class Node {

	int data;
	Node left;
	Node right;

	Node(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}

