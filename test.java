package exp_1;

import java.util.NoSuchElementException;

public class test {
	public static void main(String [] args) 
	{
		Queue<String> queue = new Queue<String>();
		
		//add
		for(int i=0; i<20; i++)
		{
			try {
				queue.add(String.valueOf(i));
			}
			catch(Exception ae) {
				System.out.print(ae.toString());
			}
		}
		
		System.out.println(queue.size());
		
		//offer
		for(int i=20; i<41; i++)
		{
			try {
				if(queue.offer(String.valueOf(i))) {
					
				}
				else
					break;
			}
			catch(Exception ae) {
				System.out.print(ae.toString());
			}
		}
		
		System.out.println(queue.size());
		
		//toString
		String str_1 = queue.toString();
		System.out.print(str_1);
		
		//clone
		@SuppressWarnings("unchecked")
		Queue<String> queue_2 = (Queue<String>) queue.clone();
		
		//equals
		System.out.println(queue_2.equals(queue));
		
		//remove
		String str = new String();
		for(int i=0;i<20;i++){
			try{
				str=queue.remove();
			}
			catch(NoSuchElementException ae){
				System.out.print(ae.toString( ));
			}
			System.out.print(str+" ");
		}
		
		//poll
		for(int i=0; i<10; i++)
		{
			str = queue.poll();
			if(str == null) {
				System.out.print("This is a empty queue");
				break;
			}
			System.out.print(str+" ");
		}
		
		System.out.print("\n");
		
		//peek
		System.out.print(queue.peek()+" ");
		
		//element
		try {
			System.out.println(queue.element());
		}
		catch(NoSuchElementException ae) {
			System.out.print(ae.toString());
		}
		
		System.out.println(queue_2);
		String str_2=queue.toString();
		System.out.println(str_2);
	}
}
