import java.util.*;
import java.io.*;
public class export {
	static int n = 0;
	static int m = 0;
	static int c = 0;
	static int[] cows;
	public static void main(String[] args) throws IOException{
		BufferedReader infile = new BufferedReader(new FileReader("convention.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(infile.readLine());
		cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
		}
        Arrays.sort(cows);
        int max = cows.length - 1;
        int bot = 0;
        int top = cows[cows.length - 1];
        int sol = Integer.MAX_VALUE;
        while(top > bot) {
        	int middle = (top + bot) / 2;
        	int check = works(middle);
        	if(check > 0) {
        		top = middle;
        		if(sol > check) {
        			sol = check;
        		}
        	}
        	else {
        		bot = middle + 1;
        	}
        }
        if(c == 1) {
        	out.println(0);
        }
        else {
        	out.println(sol);
        }
		infile.close();
		out.close();
	}
    public static int works(int waitingTime) {
		int counter = 0;
		int buses = m;
		while(true) {
			if(counter == cows.length) return waitingTime;
			if(buses == 0) {
				break;
			}
			int over = 0;
			if(counter + c - 1 >= n) {
				over = n - counter;
			}
			else {
				over = c;
			}
			int temp = 1;
			for(int i = 1; i < over; i++) {
				temp = i;
				if(cows[counter + i] - cows[counter] <= waitingTime) {
					continue;
				}
				else {
					temp--;
					break;
				}
			}
			counter += temp + 1;
			buses--;
		}
		return -1;
	}
}