package alphaStar;
import java.io.*;
import java.util.*;
public class Spiral {
	public static void main(String[] args)throws IOException{
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(in.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        long nsquared = n * n;
	        long[][] grid = new long[n][n];
	        int x = 0;
	        int y = 0;
	        grid[0][0] = 1;
	        int direction = 0;
	        for(long i = 2; i <= nsquared; i++){
	            if(direction == 0){
	                if(y + 1 == n || grid[x][y + 1] != 0) {
	                    direction++;
	                }
	                else if(grid[x][y + 1] == 0){
	                    y++;
	                    grid[x][y] = i;
	                }
	            }
	            if(direction == 1){
	                if(x + 1 == n || grid[x + 1][y] != 0){
	                    direction++;
	                }
	                else if(grid[x + 1][y] == 0){
	                    x++;
	                    grid[x][y] = i;
	                }
	            }
	            if(direction == 2){
	                if(y - 1 == -1 || grid[x][y - 1] != 0){
	                    direction++;
	                }
	                else if(grid[x][y - 1] == 0){
	                    y--;
	                    grid[x][y] = i;
	                }
	            }
	            if(direction == 3){
	                if(x - 1 == -1 || grid[x - 1][y] != 0){
	                    direction = 0;
	                    i--;
	                }
	                else if(grid[x - 1][y] == 0){
	                    x--;
	                    grid[x][y] = i;
	                }
	            }
	        }
	        for(long[] i : grid){
	            for(long j : i){
	                System.out.print(j + " ");
	            }
	            System.out.println();
	        }
	        in.close();
	    }
}
