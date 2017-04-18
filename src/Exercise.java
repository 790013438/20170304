public class Exercise {
	public static void main(String[]args){
		DigitSequence demo=new DigitSequence(1729);
		while(demo.hasNext()){
			System.out.println(demo.next());
		}
	}
}
interface IntSequence{
	boolean hasNext();
	int next();
	public static double average(IntSequence seq,int n){
		int count=0;
		double sum=0;
		while(seq.hasNext()&&count<n){			
			count++;
			sum+=seq.next();
		}
		return count==0?0:sum/count;
	}
}
class DigitSequence implements IntSequence{
	private int number;
	public DigitSequence(int n){
		number=n;
	}	
	public boolean hasNext(){
		return number!=0;
	}
	public int next(){
		int result=number%10;
		number/=10;
		return result;
	}
	public int rest(){
		return number;
	}
}