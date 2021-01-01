package usaco;
import java.util.*;
import java.io.*;
public class Planting {
	static int n;
	static ArrayList<Integer>[] fields;
	static int[] type;
	static int max = 0;
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/planting/planting.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/planting/planting.out")));
		n = Integer.parseInt(st.nextToken());
		fields = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			fields[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			fields[a].add(b);
			fields[b].add(a);
		}
		type = new int[n];
		type[0] = 1;
		int firstGen = 0;
		int secondGen = 0;
		firstGen = 1;
		dfs(0, firstGen, secondGen);
		out.println(max);
		in.close();
		out.close();
	}
	public static void dfs(int x, int firstGen, int secondGen) {
		int count = 0;
		for(int i : fields[x]) {
			if(type[i] == 0) {
				count = smallest(count, firstGen, secondGen);
				type[i] = count;
				dfs(i, type[i], firstGen);
			}
		}
	}
	public static int smallest(int temp, int firstGen, int secondGen) {
		int count = temp + 1;
		while(true) {
			if(count != firstGen && count != secondGen ) {
				if(count > max) {
					max = count;
				}
				return count;
			}
			count++;
		}
	}
}

