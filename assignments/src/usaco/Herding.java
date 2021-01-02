package usaco;
import java.util.*;
import java.io.*;
public class Herding {
	static int n;
	static ArrayList<Integer> cow = new ArrayList<Integer>();
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/herding/herding.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/herding/herding.out")));
		n = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int temp = Integer.parseInt(st.nextToken());
			cow.add(temp);
		}
		Collections.sort(cow);
		out.println(min());
		out.println(max());
		in.close();
		out.close();
	}
	public static int max() {
		return Math.max(cow.get(n - 2) - cow.get(0), cow.get(n - 1) - cow.get(1)) - n + 2;
	}
	public static int min() {
		int sol = 1;
		for(int i = 0; i < n - 1; i++) {
			int temp = 1;
			int j = i + 1;
			while(j != n && cow.get(j) - cow.get(i) < n) {
				temp++;
				j++;
			}
			if(temp > sol) {
				sol = temp;
			}
		}
		sol = n - sol;
		if(cow.get(n - 2) - cow.get(0) == n - 2 && cow.get(n - 1) - cow.get(n - 2) > 2) {
			return 2;
		}
		if(cow.get(n - 1) - cow.get(1) == n - 2 && cow.get(1) - cow.get(0) > 2) {
			return 2;
		}
		return sol;
	}
}
