package usaco;
import java.util.*;
import java.io.*;
public class Triangles {
	static int n;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/triangles/triangles.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/triangles/triangles.out")));
		n = Integer.parseInt(st.nextToken());
		ArrayList<Pair> xpoints = new ArrayList<Pair>();
		ArrayList<Pair> ypoints = new ArrayList<Pair>();
		long mod = (long) (Math.pow(10, 9) + 7);
		long sol = 0;
		HashMap<Pair, Long> grid = new HashMap<Pair, Long>();
		ArrayList[] hasYcoordinate = new ArrayList[20001];
		ArrayList[] hasXcoordinate = new ArrayList[20001];
		for(int i = 0; i < 20001; i++) {
			hasXcoordinate[i] = new ArrayList<Integer>();
			hasYcoordinate[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int tempx = Integer.parseInt(st.nextToken());
			int tempy = Integer.parseInt(st.nextToken());
			xpoints.add(new Pair(tempx, tempy));
			ypoints.add(new Pair(tempy, tempx));
		}
		Collections.sort(ypoints);
		Collections.sort(xpoints);
		for(int i = 0; i < n; i++) {
			hasYcoordinate[convert(xpoints.get(i).s)].add(convert(xpoints.get(i).f));
			hasXcoordinate[convert(ypoints.get(i).s)].add(convert(ypoints.get(i).f));
		}
		for(int i = 0; i < 20001; i++) {
			long[] row = new long[hasYcoordinate[i].size()];
			for(int j = 0; j < hasYcoordinate[i].size(); j++) {
				if(hasYcoordinate[i].size() == 1) {
					break;
				}
				if(row[0] == 0) {
					long sum = 0;
					for(int k = 0; k < hasYcoordinate[i].size(); k++) {
						sum += Math.abs(unconvert((int)hasYcoordinate[i].get(j)) - unconvert((int)hasYcoordinate[i].get(k)));
					}
					row[0] = sum;
					grid.put(new Pair(i, (int) hasYcoordinate[i].get(j)), row[0]);
				}
				else {
					row[j] = row[j - 1] + (2*j - hasYcoordinate[i].size()) * ((int)Math.abs(unconvert((int)hasYcoordinate[i].get(j)) - unconvert((int)hasYcoordinate[i].get(j - 1))));
					grid.put(new Pair(i, (int) hasYcoordinate[i].get(j)), row[j]);
				}
			}
		}
		for(int i = 0; i < 20001; i++) {
			long[] row = new long[hasXcoordinate[i].size()];
			for(int j = 0; j < hasXcoordinate[i].size(); j++) {
				if(hasXcoordinate[i].size() == 1) {
					break;
				}
				if(row[0] == 0) {
					long sum = 0;
					for(int k = 0; k < hasXcoordinate[i].size(); k++) {
						sum += Math.abs(unconvert((int)hasXcoordinate[i].get(j)) - unconvert((int)hasXcoordinate[i].get(k)));
					}
					row[0] = sum;
					Pair temp = new Pair((int) hasXcoordinate[i].get(j), i);
					if(grid.containsKey(temp) && grid.get(temp) > 0) {
						sol += grid.get(temp) * row[0];
					}
				}
				else {
					row[j] = row[j - 1] + (2*j - hasXcoordinate[i].size()) * ((int)Math.abs(unconvert((int)hasXcoordinate[i].get(j)) - unconvert((int)hasXcoordinate[i].get(j - 1))));
					Pair temp = new Pair((int) hasXcoordinate[i].get(j), i);
					if(grid.containsKey(temp) && grid.get(temp) > 0) {
						sol += grid.get(temp) * row[j];
					}
				}
			}
		}
		System.out.println(sol % mod);
		in.close();
		out.close();
	}
	public static int convert(int x) {
		return x + 10000;
	}
	public static int unconvert(int x) {
		return x - 10000;
	}
	public static class Pair implements Comparable<Pair>{
		int f;
		int s;
		public Pair(int x, int y) {
			f = x;
			s = y;
		}
		@Override
		public int compareTo(Pair o) {
			if(s > o.s) {
				return 1;
			}
			if(s == o.s) {
				if(f > o.f) {
					return 1;
				}
				else {
					return -1;
				}
			}
			return -1;
		}
	    @Override
	    public boolean equals(Object o) {  
	        if (o == this) { 
	            return true; 
	        } 
	        if (!(o instanceof Pair)) { 
	            return false; 
	        } 
	        Pair c = (Pair) o; 
	        return Integer.compare(f, c.f) == 0 && Integer.compare(s, c.s) == 0; 
	    } 
	    @Override
	    public int hashCode() {
	        return Objects.hash(f);
	    }
	}
}