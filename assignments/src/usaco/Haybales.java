package usaco;
import java.util.*;
import java.io.*;
public class Haybales {
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/haybales/haybales.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/haybales/haybales.out")));
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		TreeSet<Integer> cows = new TreeSet<Integer>();
		HashMap<Integer, Integer> positions = new HashMap<Integer, Integer>();
		int highest = 0;
		int lowest = Integer.MAX_VALUE;
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			cows.add(temp);
			if(temp > highest) {
				highest = temp;
			}
			if(temp < lowest) {
				lowest = temp;
			}
		}
		int count = 1;
		for(Integer i : cows) {
			positions.put(i, count);
			count++;
		}
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			if(min > highest || max < lowest) {
				out.println(0);
				continue;
			}
			int countMin = cows.ceiling(min);
			int countMax = cows.floor(max);
			int temp = positions.get(countMax) - positions.get(countMin);
			out.println(temp + 1);
		}
		in.close();
		out.close();
	}
}
