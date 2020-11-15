package practice;
import java.util.*;
import java.io.*;
public class BFS {
	public static class test{
		ArrayList<Integer> innerArray;
		public test() {
			innerArray = new ArrayList<Integer>();
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader infile = new BufferedReader(new FileReader("data/practiceData/BFS/BFS.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/practiceData/BFS/BFS.out")));
		int n = Integer.parseInt(st.nextToken());
		ArrayList<test> map = new ArrayList<test>(2);
		int[] distance = new int[n + 1];
		map.add(new test());
		for(int i = 1; i <= n; i++) {
			distance[i] = -1;
			map.add(new test());
			st = new StringTokenizer(infile.readLine());
			while(st.hasMoreTokens()) {
				map.get(i).innerArray.add(Integer.parseInt(st.nextToken()));
			}
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int startingPoint = 1;
		pq.add(startingPoint);
		distance[startingPoint] = 0;
		while(pq.size() > 0) {
			int node = pq.remove();
			for(int i : map.get(node).innerArray) {
				if(distance[i] == -1) {
					pq.add(i);
					distance[i] = distance[node] + 1;
				}
			}
		}
		for(int i = 0; i < n; i++) {
			System.out.println(i + 1 + " " + distance[i + 1]);
		}
		infile.close();
		out.close();
	}
}
