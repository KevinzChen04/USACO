package alphaStar;
import java.io.*;
import java.util.*;
public class Relay {
	public static void main(String[] args) throws IOException{
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(in.readLine());
		 int n = Integer.parseInt(st.nextToken());
		 int[] order = new int[n + 1];
		 int sol = 0;
		 LinkedList<Integer> unloopy = new LinkedList<Integer>();
		 for(int i = 1; i <= n ;i++) {
			 st = new StringTokenizer(in.readLine());
			 order[i] = Integer.parseInt(st.nextToken());
		 }
		 HashSet<Integer> checked = new HashSet<Integer>();
		 for(int i = 1; i <= n ;i++) {
			 if(order[i] == 0) {
				 sol++;
				 unloopy.add(i);
				 checked.add(i);
			 }
		 }
		 while(unloopy.size() > 0) {
			 int node = unloopy.remove();
			 for(int i = 1; i <= n; i++) {
				 if(checked.contains(i)) {
					 continue;
				 }
				 if(order[i] == node) {
					 unloopy.add(i);
					 checked.add(i);
					 sol++;
				 }
			 }
		 }
		 System.out.println(sol);
		 in.close();
	}
}
