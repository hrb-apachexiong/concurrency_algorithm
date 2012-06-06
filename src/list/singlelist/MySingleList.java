package list.singlelist;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ������ѭ��
 * tail is the end of the list
 *ͨ��tail��������O(1)ʱ�䷶Χ�ڷ����׽���β���
 */
public class MySingleList<T> {
	private int size ;
	private int modCount;
	private Node<T> tail = new Node(null,null);

	/*
	��ʼ��ʱtailΪ�գ�nextyָ��ָ���Լ�
	 */
	public MySingleList(){
		tail.next = tail;
		size = 0;
	}

	/*
	���Ԫ��
	�ı���ָ��󣬽��µĽ�㸶Ϊtail���
	 */
	public boolean add(T element){
		//������Ϊ��,����ֻ�滻tail������
		if(size == 0){
			tail.element = element;
		}else{
			//�������ٴ���һ��Ԫ��
			Node<T> node = new Node(tail.next,element);
			tail.next = node;
			//���Ԫ�سɹ��� tailҲ�ı���
			tail = node;
		}
		size++;
		modCount++;
		return true;
	}
	/*
	�ҵ��󷵻ص�һ�����ϵ��±�ֵ�����򷵻�-1
	 */
	public int  indexOf(T element){
		//���׽�㸳��temp
		Node<T> temp = tail.next;
		for (int i = 1 ;i <= size ;i++){
			if(element == null){
				if(temp.element == null){
					return i;
				}
				temp = temp.next;
			}else{
				if(element.equals(temp.element)){
					return i;
				}
				temp = temp.next;
			}
		}
		return -1;
	}
	/*
	ͨ���±��ҽ��
	 */
	public Node<T> entry(int index){
		if(index < 0 || index > size)
			throw new NoSuchElementException();

		Node<T> temp = tail.next;
		if(index == 1) return temp;
		
		for(int i = 2;i <= index;i++){
			temp = temp.next;
		}
		return temp;
	}

	public boolean contain(T element ){
		return indexOf(element) != -1 ;
	}

	/*
	�������ɾ��������ʱ��Ҫ������һ�εĽ��
	Ҫ����ɾ������tail�������
	 */
	public boolean remove(T element){
		Node<T> previous = tail;
		Node<T> node = tail.next;
		Boolean flag = false;

		for(int i = 1;i <= size;i++){
			if(element == null){
				if(node.element == null){
					flag = true;
					break;
				}
			}else{
				if(element.equals(node.element)){
					flag = true;
					break;
				}
			}
			previous = node;
			node = node.next;
		}
		if(flag == true){
			//���ɾ������tail,��ô����һ����㸳Ϊtail
			if(node == tail){
				tail = previous;
			}
			previous.next = node.next;
			//��������
			node.element = null;
			node.next = node = null;
			size--;
			modCount++;
		}
		return  flag;
	}

	public T set(int index,T element){
		Node<T> node = entry(index);
		T oldValue = node.element;
		node.element = element;

		modCount++;
		return oldValue;
	}

	//������
	public Iterator iterator(){
		return new SinleListIterator();
	}

	private class SinleListIterator implements Iterator{
		private Node<T> current = tail.next;
		private Node<T> lastReturn = tail;
		private int nextIndext;
		private int expectedModCount = modCount;
		private boolean okToRemove;


		public boolean hasNext() {
			return nextIndext  != size  ;
		}

		public Object next() {
			if(nextIndext == size){
				throw new NoSuchElementException();
			}
			lastReturn = current;
			current = current.next;
			nextIndext++;

			return lastReturn.element;
		}

		public void remove() {
			Node<T> lastNext = lastReturn.next;
			try {
				MySingleList.this.remove(lastReturn.element);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			nextIndext--;
			expectedModCount++;
		}
	}

	private static class Node<T>{
		private Node<T> next;
		private T element;

		public Node(Node<T> next ,T element){
			this.next = next;
			this.element = element;
		}
	}


	public static void main(String[] args) {
		MySingleList list = new MySingleList();
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(7);
		list.set(1,8);
		System.out.println(list.remove(7) + "---");

		System.out.println(list.contain(5));
		System.out.println(list.contain(2));

		for(int i = 1;i <= list.size;i++){
			System.out.println(list.entry(i).element);
		}

		Iterator it = list.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}
}
