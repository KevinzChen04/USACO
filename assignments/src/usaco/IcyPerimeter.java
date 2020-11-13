package usaco;
import java.util.*;
import java.io.*;
public class IcyPerimeter {
	public static int[][] grid;
	public static boolean[][] area;
	public static boolean[][] perimeter;
	public static int[] rotatex = {1, 0, -1, 0};
	public static int[] rotatey = {0, 1, 0, -1};
	public static int n;
	public static int areaSol = 0;
	public static int perimeterSol = 0;
	
	static class Pair {
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void changeArea(int x) {
			this.x = x;
		}
		public void changePerimeter(int y) {
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader infile = new BufferedReader(new FileReader("data/usacoData/IcyPerimeter/perimeter.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/IcyPerimeter/perimeter.out")));
		n = Integer.parseInt(st.nextToken());
		grid = new int[n][n];
		area = new boolean[n][n];
		perimeter = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(infile.readLine());
			String temp = st.nextToken();
			String[] split = temp.split("");
			for(int j = 0; j < n; j++) {
				if(split[j].equals("#")) {
					grid[i][j] = 1;
				}
				else{
					grid[i][j] = 0;
				}
				area[i][j] = false;
				perimeter[i][j] = false;
			}
		}
		Pair max = new Pair(0, 0);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				areaSol = 0;
				perimeterSol = 0;
				if(area[i][j] == false && grid[i][j] == 1) {
					findArea(i, j);
				}
				if(perimeter[i][j] == false && grid[i][j] == 1) {
					findPerimeter(i, j);
				}
				if(max.x < areaSol) {
					max.changeArea(areaSol);
					max.changePerimeter(perimeterSol);
				}
				if(max.x == areaSol) {
					if(max.y > perimeterSol) {
						max.changePerimeter(perimeterSol);
					}
				}
			}
		}
		out.println(max.x + " " + max.y);
		infile.close();
		out.close();
	}
	public static void findArea(int x, int y) {
		areaSol++;
		area[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int switchx = rotatex[i];
			int switchy = rotatey[i];
			if(x + switchx == n || x + switchx < 0 || y + switchy == n || y + switchy < 0 || area[x + switchx][y + switchy] == true || grid[x + switchx][y + switchy] == 0) {
				continue;
			}
			else {
				findArea(x + switchx, y + switchy);
			}
		}
	}
	public static void findPerimeter(int x, int y) {
		perimeter[x][y] = true;
		for(int i = 0; i < 4; i++) {
			boolean possible = true;
			int switchx = rotatex[i];
			int switchy = rotatey[i];
			if(x + switchx == n) {
				perimeterSol++;
				possible = false;
			}
			if(x + switchx < 0) {
				perimeterSol++;
				possible = false;
			}
			if(y + switchy == n) {
				perimeterSol++;
				possible = false;
			}
			if(y + switchy < 0) {
				perimeterSol++;
				possible = false;
			}
			if(possible) {
				if(grid[x + switchx][y + switchy] == 0) {
					perimeterSol++;
				}
			}
			if(possible) {
				if(perimeter[x + switchx][y + switchy] == false && grid[x + switchx][y + switchy] == 1) {
					findPerimeter(x + switchx, y + switchy);				
				}
			}
		}
	}
}
