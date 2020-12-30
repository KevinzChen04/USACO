package usaco;
import java.io.*;
import java.util.*;
public class Lifeguards {
	static class State implements Comparable<State> {
		public int time, index;
		public State(int a, int b) {
			time=a;
			index=b;
		}
		public int compareTo(State s) {
			return time - s.time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		TreeSet<Integer> set = new TreeSet<Integer>();
		int n = Integer.parseInt(in.readLine());
		State[] l = new State[2*n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			l[2*i] = new State(start, i);
			l[2*i+1] = new State(end, i);
		}
		Arrays.sort(l);
		int actualCover = 0;
		int[] alone = new int[n];
		int last = 0;
		for(State times: l) {
			if(set.size() == 1) {
				alone[set.first()] += times.time - last;
			}
			if(!set.isEmpty()) {
				actualCover += times.time - last;
			}
			if(set.contains(times.index)) {
				set.remove(times.index);
			}
			else {
				set.add(times.index);
			}
			last = times.time;
		}
		int ret = 0;
		for(int sol: alone) {
			ret = Math.max(ret, actualCover - sol);
		}
		out.println(ret);
		in.close();
		out.close();
	}
}