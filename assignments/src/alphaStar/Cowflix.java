package alphaStar;
import java.io.*;
import java.util.*;
public class Cowflix {
	static int n;
	static int c;
	static int[] weights;
	static int max = Integer.MIN_VALUE;
	static HashSet<Integer> visited = new HashSet<Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		weights = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			visited.add(weights[i]);
		}
		for(int i : weights) {
			LinkedList<Integer> options = new LinkedList<Integer>();
			for(int j : weights) {
				if(i != j) options.add(j);
			}
			dfs(i, options);
			
		}
		System.out.println(max);
	}
	public static void dfs(int x, LinkedList<Integer> options) {
		while(options.size() > 0 && x <= c) {
			int check = options.poll();
			if(visited.contains(check + x) || x + check > c) {
				continue;
			}
			visited.add(x + check);
			if(x + check > max) {
				max = x + check;
			}
			dfs(x + check, options);
		}
	}
}
