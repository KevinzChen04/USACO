import java.util.*;
import java.io.*;
public class export {
	static int inf = 999999999;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("vacation.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacation.out")));
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[][] dist = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) {
					continue;
				}
				dist[i][j] = inf;
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int farmOne = Integer.parseInt(st.nextToken()) - 1;
			int farmTwo = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			dist[farmOne][farmTwo] = cost;
		}
		for(int l = 0; l < n; l++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					dist[i][j] = min(dist[i][j], dist[i][l] + dist[l][j]);
				}
			}
		}
		int possible = 0;
		long total = 0L;
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());
			int farmOne = Integer.parseInt(st.nextToken()) - 1;
			int farmTwo = Integer.parseInt(st.nextToken()) - 1;
			int sol = inf;
			for(int j = 0; j < k; j++) {
				int toHub = dist[farmOne][j];
				if(toHub == inf || dist[j][farmTwo] == inf) {
					continue;
				}
				if(sol > toHub + dist[j][farmTwo]) {
					sol = toHub + dist[j][farmTwo];
				}
			}
			if(sol < inf) {
				possible++;
				total += sol;
			}
		}
		out.println(possible);
		out.println(total);
		in.close();
		out.close();
	}
	public static int min(int one, int two) {
		if(one < two) {
			return one;
		}
		return two;
	}
}