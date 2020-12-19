package usaco;
import java.util.*;
import java.io.*;
public class Convention2 {
	public static class Pair{
		int arrival;
		long time;
		public Pair(int arrival, long time) {
			this.arrival = arrival;
			this.time = time;
		}
	}
	public static void main(String[] args)throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/convention2/convention2.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/convention2/convention2.out")));
		int n = Integer.parseInt(st.nextToken());
		ArrayList<Pair> q = new ArrayList<Pair>();
		long waitingMax = Long.MIN_VALUE;
		long smallest = Long.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int arrival = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			if(smallest > arrival) {
				smallest = arrival;
			}
			q.add(new Pair(arrival, time));
		}
		while(q.size() > 0) {
			for(int i = 0; i < q.size(); i++) {
				if(i == q.size() || i == -1) {
					break;
				}
				else {
					if(q.get(i).arrival <= smallest) {
						long waited = smallest - q.get(i).arrival;
						if(waited > waitingMax) {
							waitingMax = waited;
						}
						smallest += q.get(i).time;
						q.remove(i);
						i = -1;
					}
				}
			}
			smallest = Long.MAX_VALUE;
			for(Pair i : q) {
				if(smallest > i.arrival) {
					smallest = i.arrival;
				}
			}
		}
		out.println(waitingMax);
		in.close();
		out.close();
	}
}
