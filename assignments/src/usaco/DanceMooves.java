package usaco;
import java.io.*;
import java.util.*;
public class DanceMooves {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] cows = new int[n];
		int[] visited = new int[n];
		HashSet[] beenThere = new HashSet[n];
		for(int i = 0; i < n; i++) {
			cows[i] = i;
			beenThere[i] = new HashSet<Integer>();
			beenThere[i].add(i);
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
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i] == 0) {
				boolean[] checked = new boolean[n];
				checked[i] = true;
				LinkedList<Integer> cycle = new LinkedList<Integer>();
				ArrayList<Integer> index = new ArrayList<Integer>();
				cycle.add(i);
				int loops = 0;
				while(cycle.size() > 0) {
					int node = cycle.poll();
					loops++;
					Iterator j = beenThere[node].iterator();
					index.add(node);
					while(j.hasNext()) {
						int temp = (int) j.next();
						if(checked[temp] == false) {
							cycle.add(temp);
							checked[temp] = true;
						}
					}
				}
				for(int j : index) {
					if(visited[j] == 0) {
						visited[j] = loops;
					}
				}
			}
		}
		for(int i : visited) {
			System.out.println(i);
		}
	}
}
