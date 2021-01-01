package alphaStar;
import java.io.*;
import java.util.*;
public class Paint2 {
	static int n;
	static int k;
	static int[][] grid;
	static Pair[] cows;
	static int sol = 0;
	public static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		grid = new int[n][n];
		cows = new Pair[k];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			cows[i] = new Pair(x, y);
		}
		for(Pair i : cows) {
			fillGrid(i);
		}
		System.out.println(sol);
	}
	public static void fillGrid(Pair position) {
		grid[position.x][position.y]++;
		if(grid[position.x][position.y] == k) {
			sol++;
		}
		for(int i = 0; i < n; i++) {
			if(i == position.y) {
				continue;
			}
			grid[position.x][i]++;
			if(grid[position.x][i] == k) {
				sol++;
			}
		}
		for(int i = 0; i < n; i++) {
			if(i == position.x) {
				continue;
			}
			grid[i][position.y]++;
			if(grid[i][position.y] == k) {
				sol++;
			}
		}
		int currentx = position.x + 1;
		int currenty = position.y + 1;
		while(-1 < currentx && currentx < n && -1 < currenty && currenty < n) {
			grid[currentx][currenty]++;
			if(grid[currentx][currenty] == k) {
				sol++;
			}
			currentx++;
			currenty++;
		}
		currentx = position.x + 1;
		currenty = position.y - 1;
		while(-1 < currentx && currentx < n && -1 < currenty && currenty < n) {
			grid[currentx][currenty]++;
			if(grid[currentx][currenty] == k) {
				sol++;
			}
			currentx++;
			currenty--;
		}
		currentx = position.x - 1;
		currenty = position.y - 1;
		while(-1 < currentx && currentx < n && -1 < currenty && currenty < n) {
			grid[currentx][currenty]++;
			if(grid[currentx][currenty] == k) {
				sol++;
			}
			currentx--;
			currenty--;
		}
		currentx = position.x - 1;
		currenty = position.y + 1;
		while(-1 < currentx && currentx < n && -1 < currenty && currenty < n) {
			grid[currentx][currenty]++;
			if(grid[currentx][currenty] == k) {
				sol++;
			}
			currentx--;
			currenty++;
		}
	}
}
