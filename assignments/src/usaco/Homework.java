package usaco;
import java.util.*;
import java.io.*;
public class Homework {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/homework/homework.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/homework/homework.out")));
		int n = Integer.parseInt(st.nextToken());
		int[] scores = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		int smallest = Integer.MAX_VALUE;
		int[] prefixSum = new int[n];
		int[] worstScore = new int[n];
		int counter = 0;
		for(int i = n - 1; i >= 0; i--) {
			counter += scores[i];
			prefixSum[n - i - 1] = counter;
			if(smallest > scores[i]) {
				smallest = scores[i];
				worstScore[n - i - 1] = scores[i];
			}
			else {
				worstScore[n - i - 1] = worstScore[n - i - 2];
			}
		}
		double max = Integer.MIN_VALUE;
		for(int i = 1; i < n; i++) {
			int temp = prefixSum[i] - worstScore[i];
			double avg = (double) temp / (i);
			if(avg > max) {
				max = avg;
			}
		}
		ArrayList<Integer> sol = new ArrayList<Integer>();
		for(int i = 1; i < n; i++) {
			int temp = prefixSum[i] - worstScore[i];
			double avg = (double) temp / (i);
			if(avg == max) {
				sol.add(n - 1 - i);
			}
		}
		Collections.sort(sol);
		for(int i : sol) {
			if(i != 0) {
				out.println(i);
			}
		}
		in.close();
		out.close();
	}
}
