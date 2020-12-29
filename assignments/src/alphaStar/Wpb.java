package alphaStar;
import java.io.*;
import java.util.*;
public class Wpb {
	static HashSet<Integer> primes = new HashSet<Integer>();
	static int max = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int rightLength = 0;
		int leftLength = 0;
		int left = 0;
		int right = 0;
		boolean done = false;
		int counter = 0;
		while(!done) {
			String number = "" + (n + counter);
			int length = (int)Math.log10(n + counter) + 1;
			rightLength = 0;
			leftLength = 0;
			for(int i = length - d; i >= d; i--) {
				if(isPrime(Integer.parseInt(number.substring(0, i)))) {
					leftLength = i;
					rightLength = length - i;
					left = Integer.parseInt(number.substring(0, leftLength));
					right = Integer.parseInt(number.substring(leftLength));	
					leftLength = ("" + left).length();
					rightLength = ("" + right).length();
					break;
				}
			}
			if(leftLength < d || rightLength < d) {
				counter++;
				continue;
			}
			while(right < Math.pow(10, rightLength)) {
				if(isPrime(right)) {
					done = true;
					break;
				}
				else {
					right++;
				}
			}
			if(!done) {
				counter++;
			}
		}
		System.out.println(left * (int)Math.pow(10, rightLength) + right);
		in.close();
	}
	public static boolean isPrime(int x) {
		if(!primes.contains(x) && max > x) {
			return false;
		}	
		if(primes.contains(x) && max > x) {
			return true;
		}
		for(int i = 2; i <= (int)Math.sqrt(x); i++) {
			if(!primes.contains(i)) {
				primes.add(i);
			}
			if(i > max) {
				max = i;
			}
			if(x % i == 0) {
				return false;
			}
		}
		primes.add(x);
		return true;
	}
}
