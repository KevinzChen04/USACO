import java.io.*;
import java.util.*;
public class export {
	public static HashSet[] beenThere;
	public static int n;
	public static int k;
	public static int[] visited;
	public static int[] cows;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		beenThere = new HashSet[n];
		visited = new int[n];
		cows = new int[n];
		for(int i = 0; i < n; i++) {
			beenThere[i] = new HashSet<Integer>();
			beenThere[i].add(i);
			cows[i] = i;
		}
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			beenThere[cows[x]].add(y);
			beenThere[cows[y]].add(x);
			int temp = cows[x];
			cows[x] = cows[y];
			cows[y] = temp;
		}
		for(int i = 0; i < n; i++) {
			if(cows[i] == i) {
				visited[i] = beenThere[i].size();
				continue;
			}
		}
		for(int i = 0; i < n; i++) {
			if(visited[i] == 0) {
				HashSet<Integer> there = new HashSet<Integer>();
				there.add(i);
				dfs(i, there);
			}
		}
		for(int i : visited) {
			System.out.println(i);
		}
		in.close();
	}
	public static void dfs(int start, HashSet<Integer> there) {
		int next = cows[start];
		if(there.contains(next)) {
			HashSet<Integer> combine = new HashSet<Integer>();
			for(int i : there) {
				combine.addAll(beenThere[i]);
			}
			for(int i : there) {
				visited[i] = combine.size();
			}
		}
		else {
			there.add(next);
			dfs(next, there);
		}
	}
}