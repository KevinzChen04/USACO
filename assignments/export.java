import java.util.*;
import java.io.*;
public class export {
	static int n;
	static int l;
	static long t;
	static double average;
	static TreeMap<Integer, Integer> positive;
	static TreeMap<Integer, Integer> negative;
	static TreeMap<Integer, Integer> position;
	static class Pair {
		int weight;
		int direction;
		Pair(int a, int b){
			weight = a;
			direction = b;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		positive = new TreeMap<Integer, Integer>(); 
		negative = new TreeMap<Integer, Integer>(); 
		position = new TreeMap<Integer, Integer>(); 
		long totalWeight = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int w = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(d == 1) {
				positive.put(x, w);
			}
			else {
				negative.put(x, w);
			}
			position.put(x, w);
			totalWeight += w;
		}
		average = totalWeight / (double) 2;
		getTime();
		long col = 0;
		for(int i : positive.keySet()) {
			for(int j : negative.keySet()) {
				if(i < j && i + 2 * t > j) {
					col++;
				}
			}
		}
		out.println(col);
		in.close();
		out.close();
	}
	public static void getTime() {
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
	}
}