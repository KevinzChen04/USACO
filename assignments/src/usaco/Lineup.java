package usaco;
import java.io.*;
import java.util.*;
public class Lineup {
	static class Pair {
		String first;
		String second;
		
		public Pair(String first, String second) {
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader infile = new BufferedReader(new FileReader("data/usacoData/lineup/lineup.in"));
		StringTokenizer st = new StringTokenizer(infile.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/lineup/lineup.out")));
		String[] cows = {"Beatrice","Belinda","Bella","Bessie","Betsy","Blue","Buttercup","Sue"};
		int n = Integer.parseInt(st.nextToken());
		Pair[] commands = new Pair[n];
		ArrayList<String[]> answers = new ArrayList<String[]>(); 
		for(int i = 0; i < n; i++){
			String[] temp = new String[6];
			st = new StringTokenizer(infile.readLine());
			for(int j = 0; j < 6; j++){
				temp[j] = st.nextToken();
			}
			commands[i] = new Pair(temp[0],temp[5]);
		}
		boolean[] check = new boolean[n];
		for(int i = 0; i < n; i++){
			check[i] = false;
		}
		
		for(int i = 0; i < 40319; i++){
			for(int j = 0; j < n; j++){
				check[j] = false;
			}
			for(int j = 0; j < n; j++){
				if(nextTo(cows, commands[j])){
					check[j] = true;
				}
			}
			String[] temp = new String[8];
			for(int j = 0; j < 8; j++){
				temp[j] = cows[j];
			}
			if(allTrue(check)){
				answers.add(temp);
			}
			nextPermutation(cows);
		}
		for(int i = 0; i < 8; i++){
			out.println(answers.get(0)[i]);
		}
		infile.close();
		out.close();
	}
	public static boolean nextTo(String[] cows, Pair command){
		for(int i = 0; i < cows.length - 1; i++){
			if((cows[i].equals(command.first) && cows[i+1].equals(command.second)) || (cows[i].equals(command.second) && cows[i+1].equals(command.first))){
				return true;
			}
		}
		return false;
	}
	public static boolean allTrue(boolean[] checks){
		int temp = 0;
		for(int i = 0; i < checks.length; i++){
			if(checks[i]){
				temp++;
			}
		}
		if(temp == checks.length){
			return true;
		}
		return false;
	}
	public static String[] nextPermutation(String[] cows){
		int p = -1;
		int q = -1;
		for(int i = cows.length-1; i > 0; i--){
			if(cows[i].compareTo(cows[i-1] ) > 0){
				p = i - 1;
				break;
			}
		}
	    for (int i = cows.length-1; i >= p+1; i--) {
	        if (cows[i].compareTo(cows[p]) > 0) {
	            q = i;
	            break;
	        }
	    }
	    cows = swap(cows, p, q);
	    ArrayList<String> temp = new ArrayList<String>();
	    for(int i = p + 1; i < cows.length; i++){
	    	temp.add(cows[i]);
	    }
	    Collections.reverse(temp);
	    int n = 0;
	    for(int i = p + 1; i < cows.length; i++){
	    	cows[i] = temp.get(n);
	    	n++;
	    }
		return cows;
	}
	public static String[] swap(String[] x, int a, int b){
		String temp = x[a];
		x[a] = x[b];
		x[b] = temp;
		return x;
	}
}
