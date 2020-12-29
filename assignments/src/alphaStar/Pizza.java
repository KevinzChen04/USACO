package alphaStar;
import java.io.*;
import java.util.*;
public class Pizza {
	static long[] constraints;
	static long sol;
	static int t;
	static int n;
	static ArrayList<Long>[] next;
	static ArrayList<Integer>[] sizes;
	static HashSet<Long> visited;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException{
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st = new StringTokenizer(in.readLine());
	     t = Integer.parseInt(st.nextToken());
	     n = Integer.parseInt(st.nextToken());
	     next = new ArrayList[t + 1];
	     sizes = new ArrayList[t + 1];
	     for(int i = 0; i <= t; i++) {
	    	 next[i] = new ArrayList<Long>();
	    	 sizes[i] = new ArrayList<Integer>();
	    	 if(i != 0) {
	    		 next[0].add((long) i);
	    	 }
	     }
	     constraints = new long[n];
	     sol = 1;
	     visited = new HashSet<Long>();
	     for(int i = 0; i < n; i++) {
	    	 st = new StringTokenizer(in.readLine());
	    	 int number = Integer.parseInt(st.nextToken());
	    	 TreeSet<Integer> temp = new TreeSet<Integer>();
	    	 for(int j = 0; j < number; j++) {
	    		 temp.add(Integer.parseInt(st.nextToken()));
	    	 }
	    	 int count = 0;
	    	 for(int j = 0; j < number; j++) {
	    		 count += temp.first() * Math.pow(10, temp.size() - 1);
	    		 temp.pollFirst();
	    	 }
	    	 constraints[i] = count;
	    	 sizes[(int)Math.log10(count) + 1].add(i);
	     }
	     
	     for(long i : next[0]) {
	    	 boolean check = true;
	    	 for(int j : sizes[1]) {
	    		 if(i == constraints[j]) {
	    			 check = false;
	    		 }
	    	 }
	    	 if(check) {
	    		sol++;
			 	next[1].add(i);
	    	 }
	     }
	     for(long i : next[1]) {
	    	 dfs(i);
	     }
	     System.out.println(sol);
	     in.close();
	}
	public static void dfs(long a) {
		String[] aString = ("" + a).split("");
		HashSet<String> temp = new HashSet<String>();
		for(String i : aString) {
			temp.add(i);
		}
		for(long i : next[1]) {
			if(temp.contains("" + i)) {
				continue;
			}
			long b = combine(a, i);
			temp.add("" + i);
			if(visited.contains(b)) {
				temp.remove("" + i);
				continue;
			}
			else {
				visited.add(b);
			}
			boolean work = true;
			for(int j = 2; j <= temp.size(); j++) {
				int check = 0;
				for(int k : sizes[j]) {
					String[] values = ("" + constraints[k]).split("");
					check = values.length;
					for(String l : values) {
						if(temp.contains(l)) {
							check--;
						}
					}
					if(check != 0) {
						continue;
					}
					else {
						work = false;
						break;
					}
				}
				if(!work) {
					break;
				}
			}
			if(work) {
				sol++;
				dfs(b);
			}
			temp.remove("" + i);
		}
	}
	public static long combine(long a, long b) {
		String[] aString = ("" + a).split("");
		String[] bString = ("" + b).split("");
		TreeSet<Integer> temp = new TreeSet<Integer>();
		for(int i = 0; i < aString.length; i++) {
			temp.add(Integer.parseInt(aString[i]));
		}
		for(int i = 0; i < bString.length; i++) {
			temp.add(Integer.parseInt(bString[i]));
		}
		long count = 0;
		while(temp.size() > 0) {
   		 	count += temp.first() * Math.pow(10, temp.size() - 1);
   		 	temp.pollFirst();
   	 	}
		return count;
	}
}
