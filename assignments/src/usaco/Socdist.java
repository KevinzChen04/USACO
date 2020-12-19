package usaco;
import java.io.*;
import java.util.*;
public class Socdist {
	long start;
	long end;
	static int n;
	static int m;
	static long smallest;
	static long largest;
	static TreeMap<Long, Long> fields;

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/socdist/socdist.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/socdist/socdist.out")));
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		smallest = Long.MAX_VALUE;
		largest = Long.MIN_VALUE;
		fields = new TreeMap<Long, Long>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			fields.put(start, end);
			if(start < smallest) {
				smallest = start;
			}
			if(end > largest) {
				largest = end;
			}
		}
		long top = largest;
		long bot = 1;
		long mid = 0;
		while(top > bot) {
			mid = (top + bot) / 2;
			if(check(mid)) {
				bot = mid + 1;
			}
			else {
				top = mid - 1;
			}
		}
		out.println(top);
		in.close();
		out.close();
	}
	public static boolean check(long dist) {
		long position = smallest;
		int cows = n;
		while(position <= largest && cows > 0) {
			Long currentFieldStart = fields.floorKey(position);
			Long currentFieldEnd = fields.get(currentFieldStart);
			if(currentFieldStart <= position && position <= currentFieldEnd) {
				cows--;
				position += dist;
			}
			else {
				if(fields.get(fields.lastKey()) < position) {
					break;
				}
				position = fields.ceilingKey(position);
			}
		}
		if(cows == 0) {
			return true;
		}
		return false;
	}
}
