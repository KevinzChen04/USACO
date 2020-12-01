package usaco;
import java.util.*;
import java.io.*;
public class Shuffle {

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/shuffle/shuffle.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/shuffle/shuffle.out")));
		int n = Integer.parseInt(st.nextToken());
		Map<Integer, Integer> shuffle = new HashMap<Integer, Integer>();
		Set<Integer> cycle = new HashSet<Integer>();
		Set<Integer> uncycle = new HashSet<Integer>();
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			shuffle.put(i + 1, temp);
		}
		for(int i = 1; i <= n; i++) {
			if(uncycle.contains(i) || cycle.contains(i)) {
				continue;
			}			
			Set<Integer> current = new HashSet<Integer>();
			current.add(i);
			int temp = shuffle.get(i);
			while(true) {
				if(uncycle.contains(temp) || cycle.contains(temp)) {
					uncycle.add(i);
					break;
				}	
				current.add(temp);
				if(!current.contains(shuffle.get(temp))) {
					temp = shuffle.get(temp);
				}
				else {
					if(shuffle.get(temp) == i) {
						cycle.addAll(current);
						break;
					}
					else {
						uncycle.add(i);
						break;
					}
				}
			}
		}
		out.println(cycle.size());
		in.close();
		out.close();
	}
}
