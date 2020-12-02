import java.util.*;
import java.io.*;
public class export {
	static int n;
	static int Tmax;
	static int[] times;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		n = Integer.parseInt(st.nextToken());
		Tmax = Integer.parseInt(st.nextToken());
		times = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			times[i] = Integer.parseInt(st.nextToken());
		}
		int top = n;
		int bot = 1;
		while(top > bot) {
			int mid = (top + bot) / 2;
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
	public static boolean check(int stage) {
		TreeMap<Integer, Integer> cowsOnFloor = new TreeMap<Integer, Integer>();
		for(int i = 0; i < stage; i++) {
			if(cowsOnFloor.containsKey(times[i])) {
				cowsOnFloor.put(times[i], cowsOnFloor.get(times[i]) + 1);
			}
			else {
				cowsOnFloor.put(times[i], 1);
			}
		}
		for(int i = stage; i < n; i++) {
			int lowest = cowsOnFloor.firstKey();
			if(cowsOnFloor.get(lowest) != 1) {
				cowsOnFloor.put(lowest, cowsOnFloor.get(lowest) - 1);
			}
			else {
				cowsOnFloor.remove(lowest);
			}
			if(cowsOnFloor.containsKey(lowest + times[i])) {
				cowsOnFloor.put(times[i] + lowest, cowsOnFloor.get(times[i] + lowest) + 1);
			}
			else {
				cowsOnFloor.put(lowest + times[i], 1);
			}
		}
		if(cowsOnFloor.lastKey() > Tmax) {
			return false;
		}
		return true;
	}
}