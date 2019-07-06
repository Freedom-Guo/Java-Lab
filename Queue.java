package exp_1;
import java.util.Stack;
import java.util.NoSuchElementException;
public class Queue<E> extends Stack<E>{
	private static final long serialVersionUID = 1L;
	static final int dump=1024;
	private Stack<E> stk;
	
	//构造函数 对泛型成员函数实例化
    public Queue() {
		stk = new Stack<>();
	}
    
    //获取队列大小
    @Override
	public int size() {
		int size = 0;
		size = super.size()+stk.size();
		return size;
	}
    
    //入队函数 向队尾插入相应元素 失败抛出栈错误
	@Override
	public boolean add(E e) throws IllegalStateException, ClassCastException, NullPointerException, IllegalArgumentException{
		if(super.size() >= dump && !stk.empty()) //队满
			throw new IllegalStateException();
		if(super.size() < dump) //入队栈没有满
			super.push(e);
		else //入队栈满了 出队栈没有元素
		{
			int temp = super.size();
			for(int i=temp-1; i>=0; i--)
			{
				stk.push(super.get(i));
			}
			super.clear();
			super.push(e);
		}
		return true;
	}
	
	//入队函数 向队尾插入相应元素 失败返回false
    public boolean offer(E e) throws ClassCastException, NullPointerException, IllegalArgumentException{
		if(super.size() >= dump && !stk.empty())
			return false;
		if(super.size() < dump)
			super.push(e);
		else
		{
			int temp =super.size();
			for(int i=temp-1; i>=0; i--)
			{
				stk.push(super.get(i));
			}
			super.clear();
			super.push(e);
		}
		return true;
	}
    
  //出队函数 将出队栈的栈顶元素出栈 失败抛出栈错误
  	public E remove() throws NoSuchElementException{
  		E temp;
  		if(super.empty()) 
  			throw new NoSuchElementException(); //入队栈为空
  		if(!stk.empty())
  			temp = stk.pop(); //出队栈不为空
  		else
  		{ //出队栈为空
  			int length = super.size();
  			for(int i=length-1; i>=0; i--)
  			{
  				stk.push(super.get(i));
  			}
  			super.clear();
  			temp = stk.pop();
  			for(int i=0; i<length-1; i++)
  			{
  				super.push(stk.pop());
  			}
  		}
  		return temp;
  	}
  	
  //出队函数 将出队栈的栈顶元素出栈 失败返回false
  	public E poll() {
  		E temp;
  		if(super.empty())
  			return null; //入队栈为空
  		if(!stk.empty())
  			temp=stk.pop(); //出队栈不为空
  		else
  		{ //出队栈为空
  			int length = super.size();
  			for(int i=length-1; i>=0; i--)
  			{
  				stk.push(super.get(i));
  			}
  			super.clear();
  			temp = stk.pop();
  			for(int i=0; i<length-1; i++)
  			{
  				super.push(stk.pop());
  			}
  		}
  		return temp;
  	}
  	
  //获取队首元素 空队列返回null
  	public E peek() {
  		if(super.empty())
			return null; //入队栈为空
    	E temp;
    	if(!stk.empty())
			temp=stk.peek(); //出队栈不为空
    	else{
    		int length = super.size();
			for(int i=length-1; i>=0; i--) {
				stk.push(super.get(i));
			}
			super.clear();
			temp = stk.peek();
			for(int i=0; i<=length-1; i++)
				super.push(stk.pop());
    	}
    	return temp;

  	}
    
  //获取队首元素 空队列抛出异常
  	public E element() throws NoSuchElementException{
  		E temp;
  		if(super.empty())
  			throw new NoSuchElementException(); //入队栈为空
  		if(!stk.empty())
  			temp=stk.peek(); //出队栈不为空
  		else
  		{ //出队栈为空
  			int length = super.size();
  			for(int i=length-1; i>=0; i--)
  			{
  				stk.push(super.get(i));
  			}
  			super.clear();
  			temp = stk.peek();
  			for(int i=0; i<=length-1; i++)
  			{
  				super.push(stk.pop());
  			}
  		}
  		return temp;
  	}
  	
    @SuppressWarnings("unchecked")
	
    //重写拷贝 深拷贝
    @Override
	public Object clone() {
		Queue<E> dl=new Queue<E>();
    	dl.addAll((Stack<E>) super.clone());
    	dl.stk=(Stack<E>) stk.clone();
    	return dl;
	}
    
    //打印队列中的元素 重写toString
    @Override
	public synchronized String toString()
	{
		StringBuffer buff = new StringBuffer();
		int length_1 = stk.size();
		int length_2 = super.size();
		for(int i=length_1-1; i>=0; i--)
			buff.append(stk.get(i)+" ");
		for(int i=0; i<length_2; i++)
			buff.append(super.get(i)+ " ");
		return buff.toString();
	}

  //重写equals
  	@Override
  	public boolean equals(Object ob) throws ClassCastException{
  		if(ob instanceof Queue) 
  			return this.toString().equals(ob.toString());
  		else
  			throw new ClassCastException();
  	}
}