package usaco;
import java.io.*;
import java.util.*;
public class ComfortableCows {
	public static int[] rr = {0, 1, 0, -1};
	public static int[] rc = {1, 0, -1, 0};
	public static boolean[][] grid = new boolean[1001][1001];
	public static HashSet<Point> cows = new HashSet<Point>();
	public static Point temp = new Point(0, 0);
	public static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	    @Override
	    public boolean equals(Object o) {  
	        if (o == this) { 
	            return true; 
	        } 
	        if (!(o instanceof Point)) { 
	            return false; 
	        } 
	        Point c = (Point) o; 
	        return Integer.compare(x, c.x) == 0 && Integer.compare(y, c.y) == 0; 
	    } 
	    @Override
	    public int hashCode() {
	        return Objects.hash(x);
	    }
	} 
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			grid[x][y] = true;
			if(cows.contains(new Point(x, y))) {
				cows.remove(new Point(x, y));
				System.out.println(cows.size());
				continue;
			}
			addPoint(x, y);
			System.out.println(cows.size());
		}
	}
	public static void addPoint(int x, int y) {
		boolean test = checkPoint(x, y);
		if(test) {
			cows.add(temp);
			addPoint(temp.x, temp.y);
		}
		for(int i = 0; i < 4; i++) {
			test = false;
			int newx = x + rr[i];
			int newy = y + rc[i];
			if(cows.contains(new Point(newx, newy))) {
				test = checkPoint(newx, newy);
				if(test) {
					cows.add(temp);
					addPoint(temp.x, temp.y);
				}
			}
			if(newx > 1000 ||  newx < 0 || newy > 1000 || newy < 0) continue;
			if(grid[newx][newy] == true) {
				test = checkPoint(newx, newy);
				if(test) {
					cows.add(temp);
					addPoint(temp.x, temp.y);
				}
			}
		}
	}
	public static boolean checkPoint(int x, int y) {
		int count = 0;
		for(int i = 0; i < 4; i++) {
			int newx = x + rr[i];
			int newy = y + rc[i];
			if(cows.contains(new Point(newx, newy))) {
				count++;
				continue;
			}
			if(newx > 1000 ||  newx < 0 || newy > 1000 || newy < 0) {
				temp = new Point(newx, newy);
				continue;
			}
			if(grid[newx][newy] == true) {
				count++;
				continue;
			}
			temp = new Point(newx, newy);
		}
		if(count == 3) {
			return true;
		}
		return false;
	}
}
