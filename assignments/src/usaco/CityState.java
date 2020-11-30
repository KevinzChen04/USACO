package usaco;
import java.util.*;
import java.io.*;
public class CityState {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("data/usacoData/cityState/citystate.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/usacoData/cityState/citystate.out")));
		int n = Integer.parseInt(st.nextToken());
		Map<String, Integer> cityStates = new HashMap<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			String city = st.nextToken().substring(0, 2);
			String state = st.nextToken();
			String key = city + state;
			if(!(city.equals(state))) {
				if(cityStates.containsKey(key)) {
					cityStates.put(key, cityStates.get(key) + 1);
				}
				else {
					cityStates.put(key, 1);
				}
			}
		}
		int sol = 0;
		for(String key: cityStates.keySet()) {
			String reciprocal = key.substring(2) + key.substring(0, 2);
			if(cityStates.containsKey(reciprocal)) {
				sol += cityStates.get(reciprocal) * cityStates.get(key);
			}
		}
		out.println(sol / 2);
		in.close();
		out.close();
	}
}
