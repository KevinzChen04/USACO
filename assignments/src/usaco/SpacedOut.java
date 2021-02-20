package usaco;
import java.io.*;
import java.util.*;
public class SpacedOut {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] oddH = new int[n];
		int[] evenH = new int[n];
		int[] oddV = new int[n];
		int[] evenV = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(i % 2 == 0) {
					evenH[j] += temp;
				}
				else {
					oddH[j] += temp;
				}
				if(j % 2 == 0) {
					evenV[i] += temp;
				}
				else {
					oddV[i] += temp;
				}
			}
		}
		long maxH = 0;
		long maxV = 0;
		for(int i = 0; i < n; i++) {
			maxH += Math.max(oddH[i], evenH[i]);
			maxV += Math.max(oddV[i], evenV[i]);
		}
		System.out.println(Math.max(maxH, maxV));
	}
}
