import java.util.*;
import java.io.*;
public class export {
	static int n;
	static ArrayList<Pair> points = new ArrayList<Pair>();
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		n = Integer.parseInt(st.nextToken());
		long mod = (long) (Math.pow(10, 9) + 7);
		long sol = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			long tempx = Long.parseLong(st.nextToken());
			long tempy = Long.parseLong(st.nextToken());
			points.add(new Pair(tempx, tempy));
		}
		for(int i = 0; i < n; i++) {
			long xsum = 0;
			long ysum = 0;
			for(int j = 0; j < n; j++) {
				if(i == j) {
					continue;
				}
				if(points.get(j).f == points.get(i).f) {
					xsum += Math.abs(points.get(j).s - points.get(i).s);
				}
				if(points.get(j).s == points.get(i).s) {
					ysum += Math.abs(points.get(j).f - points.get(i).f);
				}
			}
			sol += xsum * ysum;
		}
		out.println(sol % mod);
		in.close();
		out.close();
	}
	public static class Pair{
		long f;
		long s;
		public Pair(long x, long y) {
			this.f = x;
			this.s = y;
		}
	}
}