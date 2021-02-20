package usaco;
import java.io.*;
import java.util.*;
public class NoTimeToPaint {
	public static final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();
		int[] forward = new int[n];
		int[] backward = new int[n];
		int[] paints = new int[n];
		st = new StringTokenizer(in.readLine());
		String[] x = st.nextToken().split("");
		for(int i = 0; i < n; i++) {
			String temp = x[i];
			for(int j = 0; j < 26; j++) {
				if(alphabet[j].equals(temp)) {
					paints[i] = j;
				}
			}
		}
		forward[0] = 1;
		seen.put(paints[0], 0);
		for(int i = 1; i < n; i++) {
			if(seen.containsKey(paints[i])) {
				boolean fail = false;
				for(int j = paints[i] - 1; j >= 0; j--) {
					if(seen.containsKey(j) && seen.get(j) > seen.get(paints[i])) {
						fail = true;
						break;
					}
				}
				if(fail) {
					forward[i] = forward[i - 1] + 1;
				}
				else {
					forward[i] = forward[i - 1];
				}
				seen.put(paints[i], i);
			}
			else {
				seen.put(paints[i], i);
				forward[i] = forward[i - 1] + 1;
			}
		}		
		seen.clear();
		backward[n - 1] = 1;
		seen.put(paints[n - 1], n - 1);
		for(int i = n - 2; i >= 0; i--) {
			if(seen.containsKey(paints[i])) {
				boolean fail = false;
				for(int j = paints[i] - 1; j >= 0; j--) {
					if(seen.containsKey(j) && seen.get(j) < seen.get(paints[i])) {
						fail = true;
						break;
					}
				}
				if(fail) {
					backward[i] = backward[i + 1] + 1;
				}
				else {
					backward[i] = backward[i + 1];
				}
				seen.put(paints[i], i);
			}
			else {
				seen.put(paints[i], i);
				backward[i] = backward[i + 1] + 1;
			}
		}
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start == 1 && end == n) {
				System.out.println(0);
				continue;
			}
			if(start == 1) {
				System.out.println(backward[end]);
				continue;
			}
			if(end == n) {
				System.out.println(forward[start - 2]);
				continue;
			}
			System.out.println(forward[start - 2] + backward[end]);
		}
	}
}
