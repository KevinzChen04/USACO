import java.util.*;
import java.io.*;
public class export {
	static int n;
	static int l;
	static int[] w;
	static int[] x;
	static int[] d;
	
	static long t;
	static double average;
	static TreeMap<Integer, Integer> positive;
	static TreeMap<Integer, Integer> negative;
	static TreeMap<Integer, Integer> position;
	
	static class Pair implements Comparable<Pair>{
		int time;
		int weight;
		Pair(int a, int b){
			time = a;
			weight = b;
		}
		@Override
		public int compareTo(Pair o) {
			if(time > o.time) {
				return 1;
			}
			return -1;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		w = new int[n];
		x = new int[n];
		d = new int[n];
		positive = new TreeMap<Integer, Integer>(); 
		negative = new TreeMap<Integer, Integer>(); 
		position = new TreeMap<Integer, Integer>(); 
		int totalWeight = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			x[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
			if(d[i] == 1) {
				positive.put(x[i], w[i]);
			}
			else {
				negative.put(x[i], w[i]);
			}
			position.put(x[i], w[i]);
			totalWeight += w[i];
		}
		average = totalWeight / (double) 2;
		getTime();
		long col = 0;
		Pair[] sorted = new Pair[n];
		TreeSet<Integer> q = new TreeSet<Integer>();
		for(int i = 0; i < n; i++) {
			sorted[i] = new Pair(x[i], d[i]);
		}
		Arrays.sort(sorted);
		for(int i = 0; i < n; i++) {
			if(sorted[i].weight == -1) {
				while(q.size() > 0 && q.first() + 2 * t < sorted[i].time) {
					q.remove(q.first());
				}
				col += q.size();
			}
			else {
				q.add(sorted[i].time);
			}
		}	
		out.print(col);
		in.close();
		out.close();
	}
	public static int getTime() {
		long[] positiveTime = new long[negative.size()];
		long[] negativeTime = new long[positive.size()];
		int counter = 0;
		for(int i : negative.keySet()) {
			positiveTime[counter] = i;
			counter++;
		}
		Arrays.sort(positiveTime);
		counter = 0;
		for(int i : positive.keySet()) {
			negativeTime[counter] = l - i;
			counter++;
		}
		Arrays.sort(negativeTime);
		long weight = 0;
		int positiveCounter = 0;
		int negativeCounter = 0;
		t = 0;
		TreeMap<Integer, Integer> positionClone = (TreeMap<Integer, Integer>) position.clone();
		while(weight < average) {
			if(positiveTime[positiveCounter] > negativeTime[negativeCounter]) {
				t = negativeTime[negativeCounter];
				weight += positionClone.get(positionClone.lastKey()); 
				positionClone.remove(positionClone.lastKey());
				negativeCounter++;
				continue;
			}
			if(positiveTime[positiveCounter] < negativeTime[negativeCounter]) {
				t = positiveTime[positiveCounter];
				weight += positionClone.get(positionClone.firstKey()); 
				positiveCounter++;
				positionClone.remove(positionClone.firstKey());
				continue;
			}
			if(positiveTime[positiveCounter] == negativeTime[negativeCounter]) {
				t = negativeTime[negativeCounter];
				weight += positionClone.get(positionClone.firstKey()); 
				weight += positionClone.get(positionClone.lastKey()); 
				positiveCounter++;
				negativeCounter++;
				positionClone.remove(positionClone.lastKey());
				positionClone.remove(positionClone.firstKey());
			}
		}
		return 0;
	}
}