package usaco;
import java.util.*;
import java.io.*;
public class Loan {
	static long n;
	static long k;
	static long m;
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/loan/loan.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/loan/loan.out")));
		n = Long.parseLong(st.nextToken());
		k = Long.parseLong(st.nextToken());
		m = Long.parseLong(st.nextToken());
		long top = n;
		long bot = 1;
		boolean check = false;
		while(top > bot) {
			long preTop = top;
			long preBot = bot;
			long mid = (top + bot) / 2;
			if(check(mid)) {
				bot = mid;
			}
			else {
				top = mid;
			}
			if(preTop == top && preBot == bot) {
				check = true;
				break;
			}
		}
		if(check) {
			if(check(top)) {
				System.out.println(top);
			}
			else {
				System.out.println(bot);
			}
		}
		else{
			System.out.println(top);
		}
		in.close();
		out.close();
	}
	public static boolean check(long x) {
		long g = 0;
		long days = 0;
		long y = n / x;
		while(m < y && days != k && g < n) {
			y = (n - g) / x;
			long next = n - x * y;
			long maxTime = (next - g) / y + 1;
			if(maxTime + days > k) {
				maxTime = k - days;
			}
			days += maxTime;
			g += y * maxTime;
		}
		double temp = (n - g) / (double)m;
		long time = (long) Math.ceil(temp);
		if(time + days <= k) {
			return true;
		}
		return false;
	}
}
