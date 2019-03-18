
public class Main {
	public static void main(String[] args)
	{
		int n = 100;
		for(int k = 0 ; k < 10 ; k++) {
			int res = 0;
			res = run(n);
			System.out.println("Initial sites: " + n + ", Pairs generated: " + res);
			n = n * 2;
	        
		}
	}
	public static int run(int n) {
		int total = 0;
		for(int time = 0 ; time < 1000 ; time++) {
			int count = 0;
			UF_HWQUPC hwqupc = new UF_HWQUPC(n);

			while(hwqupc.components() > 1) {
				int p = (int)(Math.random()*n);
				int q = (int)(Math.random()*n);
				while(p == q) q = (int)(Math.random()*n);
				//int[] pair = {p,q};
				count++;
				if(!hwqupc.connected(p, q))hwqupc.union(p, q);
			}
			total = total + count;
		}
		return (int)(total/1000);
	}
	
	
}
