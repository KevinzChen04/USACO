import java.util.*;
import java.io.*;
public class export {
	static final int[] rotatex = {-1,0,1,0};
	static final int[] rotatey = {0,1,0,-1};
	static int n = 0;
	static int k = 0;
	static int r = 0;
	static int[][] grid;
	static pair[] cows;
	static pairOfPair[] walls;
	static int count = 1;
	static class pair {
		int x;
		int y;
		public pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equalTo(pair temp) {
			if(x == temp.x && y == temp.y) return true;
			return false;
		}
	}
	static class pairOfPair {
		pair one;
		pair two;
		public pairOfPair(pair one, pair two){
			this.one = one;
			this.two = two;
		}
		public boolean equalTo(pairOfPair temp) {
			if(one.equalTo(temp.one) && two.equalTo(temp.two)) return true;
			if(one.equalTo(temp.two) && two.equalTo(temp.one)) return true;
			return false;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader infile = new BufferedReader(new FileReader("countcross.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		grid = new int[n][n];
		walls = new pairOfPair[r];
		cows = new pair[k];
		int total = 0;
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(infile.readLine());
			int firstX = Integer.parseInt(st.nextToken()) - 1;
			int firstY = Integer.parseInt(st.nextToken()) - 1;
			int secondX = Integer.parseInt(st.nextToken()) - 1;
			int secondY = Integer.parseInt(st.nextToken()) - 1;
			pair one = new pair(firstX, firstY);
			pair two = new pair(secondX, secondY);
			walls[i] = new pairOfPair(one, two);
		}
		for(int i = 0 ; i < k; i++) {
			st = new StringTokenizer(infile.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			cows[i] = new pair(x, y);
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 0) {
					fill(i, j);
					count++;
				}
			}
		}
		for(int i = 0; i < cows.length - 1; i++) {
			for(int j = i + 1; j < cows.length; j++) {
				int one = grid[cows[i].x][cows[i].y];
				int two = grid[cows[j].x][cows[j].y];
				if(one != two) total++;
			}
		}
		out.println(total);
		infile.close();
		out.close();
	}
	static void fill(int x, int y) {
		grid[x][y] = count;
		for(int i = 0; i < 4; i++) {
			int newX = x + rotatex[i];
			int newY = y + rotatey[i];
			pair past = new pair(x, y);
			pair current = new pair(newX, newY);
			pairOfPair temp = new pairOfPair(past, current);
			boolean wall = false;
			if(newX == -1 || newX == n || newY == -1 || newY == n) {
				continue;
			}
			else if(grid[newX][newY] == 0) {
				for(pairOfPair j : walls) {
					if(temp.equalTo(j)) {
						wall = true;
					}
				}
				if(!wall) fill(newX, newY);
			}
		}
	}
}