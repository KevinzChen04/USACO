package practice;
import java.util.*;
import java.io.*;
public class Dijkstra {
	static ArrayList<pair>[] grid;
	public static class pair{
		int one;
		int two;
		public pair(int one, int two) {
			this.one = one;
			this.two = two;
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader infile = new BufferedReader(new FileReader("data/practiceData/Dijkstra/Dijkstra.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/practiceData/Dijkstra/Dijkstra.out")));
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		grid = new ArrayList[n];
		int[] dist = new int[n];
		for(int i = 0; i < n; i++) {
			grid[i] = new ArrayList<pair>();
			dist[i] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(infile.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			grid[a].add(new pair(b, weight));
		}
		LinkedList<pair> pq = new LinkedList<pair>();
		dist[0] = 0;
		pq.add(new pair(0, 0));
		while(pq.size() > 0) {
			pair temp = pq.remove();
			int current = temp.one;
			int current_dist = temp.two;
			if(dist[current] < current_dist) {
				continue;
			}
			for(pair i : grid[current]) {
				int neighbor = i.one;
				int neighbor_dist = current_dist + i.two;
				if(dist[neighbor] > neighbor_dist) {
					dist[neighbor] = neighbor_dist;
					pq.add(new pair(neighbor, neighbor_dist));
				}
			}
		}
		for(int i : dist) {
			out.println(i);
		}
		infile.close();
		out.close();
	}
}
