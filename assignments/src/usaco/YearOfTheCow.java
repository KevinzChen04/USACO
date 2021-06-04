package usaco;
import java.io.*;
import java.util.*;
public class YearOfTheCow {
	public static int n;
	public static int k;
	public static ArrayList<Integer> years;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		years = new ArrayList<Integer>();
		double top = 0;
		double bot = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int temp = Integer.parseInt(st.nextToken());
			if(temp > top) {
				top = temp;
			}
			years.add(temp);
		}
		int sol = Integer.MAX_VALUE;
		Collections.sort(years);
		int mid = (int) ((top + bot) / 2);
		while(top > bot) {
			mid = (int) ((top + bot) / 2);
			int temp = check(mid);
			if(temp > 0) {
				top = mid;
				if(sol > temp) {
					sol = temp;
				}
			}
			else {
				bot = mid + 1;
			}
		}
		System.out.println(sol);
	}
	public static int check(double patience) {
		int time = 0;
		int start = years.get(0);
		int loops = 0;
		for(int i = 1; i < years.size(); i++) {
			if(start + patience < years.get(i)) {
				int end = years.get(i - 1);
				time += 12 * ((end + 12) / 12 - start / 12);
				loops++;
				if(loops == k - 1) {
					return -1;
				}
				start = years.get(i);
			}
		}
		int end = years.get(years.size() - 1);
		time += 12 * ((end + 12) / 12 - start / 12);
		return time;
	}
}
