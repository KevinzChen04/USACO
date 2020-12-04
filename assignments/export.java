import java.util.*;
import java.io.*;
public class export {
	static int n;
	static int q;
	static int[][] relavance;
	static ArrayList<Integer>[] adjList;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		relavance = new int[n][n];
		adjList = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int videoOne = Integer.parseInt(st.nextToken()) - 1;
			int videoTwo = Integer.parseInt(st.nextToken()) - 1;
			int relavent = Integer.parseInt(st.nextToken());
			adjList[videoOne].add(videoTwo);
			adjList[videoTwo].add(videoOne);
			relavance[videoOne][videoTwo] = relavent;
			relavance[videoTwo][videoOne] = relavent;
		}
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());
			int ki = Integer.parseInt(st.nextToken());
			int vi = Integer.parseInt(st.nextToken()) - 1;
			LinkedList<Integer> pq = new LinkedList<Integer>();
			boolean[] visited = new boolean[n];
			visited[vi] = true;
			int sol = 0;
			pq.add(vi);
			while(pq.size() > 0) {
				int temp = pq.remove();
				if(sol == n - 1) {
					break;
				}
				for(int j : adjList[temp]) {
					if(relavance[temp][j] >= ki && visited[j] == false) {
						visited[j] = true;
						sol++;
						pq.add(j);
					}
				}
			}
			out.println(sol);
		}
		in.close();
		out.close();
	}
}