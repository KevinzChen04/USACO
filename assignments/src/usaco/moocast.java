package usaco;
import java.util.*;
import java.io.*;
public class moocast {
	public static int n;
	public static class triple{
		int x;
		int y;
		int power;
		public triple(int x, int y, int power){
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader infile = new BufferedReader(new FileReader("data/usacoData/moocast/moocast.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/moocast/moocast.out")));
		n = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] map = new ArrayList[n];
		triple[] cows = new triple[n];
		for(int i = 0; i < n; i++) {
			map[i] = new ArrayList<Integer>();
			st = new StringTokenizer(infile.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());
			cows[i] = new triple(x, y, power);
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(canReach(cows[i], cows[j]) && i != j) {
					map[i].add(j);
				}
			}
		}
		int maxConnections = 1;
		for(int i = 0; i < n; i++) {
			int connections = 1;
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); 
			int[] distances = new int[n];
			Arrays.fill(distances, -1);
			distances[i] = 0;
			pq.add(i);
			while(pq.size() > 0) {
				int node = pq.remove();
				for(int j : map[node]) {
					if(distances[j] == -1) {
						pq.add(j);
						connections++;
						distances[j] = distances[node] + 1;
					}
				}
			}
			if(connections > maxConnections) {
				maxConnections = connections;
			}
		}
		out.println(maxConnections);
		infile.close();
		out.close();
	}
	public static boolean canReach(triple a, triple b) {
		int differencex = a.x - b.x;
		int differencey = a.y - b.y;
		if(Math.pow(differencex, 2) + Math.pow(differencey, 2) <= Math.pow(a.power, 2)) {
			return true;
		}
		return false;
	}
}
