package usaco;
import java.io.*;
import java.util.*;
public class Cowntagion {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		ArrayList[] grid = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			grid[i] = new ArrayList<Long>();
		}
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			grid[from].add(to);
			grid[to].add(from);
		}
		long days = 0;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(0);
		boolean[] visited = new boolean[n];
		visited[0] = true;
		while(q.size() > 0) {
			int node = q.remove();
			long temp = 0;
			for(int i = 0; i < grid[node].size(); i++) {
				int next = (int) grid[node].get(i);
				if(!visited[next]) {
					temp++;
					days++;
					q.add(next);
					visited[next] = true;
				}
			}
			long sick = 1;
			while(sick < temp + 1) {
				sick *= 2;
				days++;
			}
		}
		System.out.println(days);
		in.close();
	}
}
