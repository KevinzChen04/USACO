package usaco;
import java.util.*;
import java.io.*;
public class MooBuzz {
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/moobuzz/moobuzz.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/moobuzz/moobuzz.out")));
		n = Integer.parseInt(st.nextToken());
		long top = Long.MAX_VALUE;
		long bot = n;
		while(top > bot) {
			long mid = (top + bot) / 2;
			if(check(mid)) {
				top = mid;
			}
			else {
				bot = mid + 1;
			}
		}
		out.println(top);
		in.close();
		out.close();
	}
	public static boolean check(long mid) {
		long triple = mid / 3;
		long five = mid / 5;
		long fifthteenth = mid / 15;
		if(mid - triple - five + fifthteenth >= n) {
			return true;
		}
		return false;
	}
}
