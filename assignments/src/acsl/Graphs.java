package acsl;
import java.util.*;
import java.io.*;
public class Graphs {

	public static void main(String[] args) {
		String edges = "12 23 34 41 31";
		int num = 2;
		int sol = 0;
        int[][] graph = new int [10][10];
        HashSet<Integer> nodes = new HashSet<Integer>();
        String[] edgesSplit = edges.split(" ");
        for(String i : edgesSplit){
            String[] temp = i.split("");
            if(!nodes.contains(Integer.parseInt(temp[0]))){
                nodes.add(Integer.parseInt(temp[0]));
            }
            if(!nodes.contains(Integer.parseInt(temp[1]))){
                nodes.add(Integer.parseInt(temp[1]));
            }
            graph[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = 1;
        }
        Double limit = Math.pow(10, num);
        for(int i = 1; i < 10; i ++){
            if(!nodes.contains(i)){
                continue;
            }
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(i);
            while(queue.size() > 0){
                int node = queue.remove();
                if(node > limit){
                    sol += node;
                    continue;
                }
                String temp = "" + node;
                String[] digits = temp.split("");
                HashSet<Integer> visited = new HashSet<Integer>();
                for(int j = 0; j < digits.length; j++){
                    visited.add(Integer.parseInt(digits[j]));
                }
                int lastDigit = node % 10;
                int iteration = -1;
                for(int j : graph[lastDigit]){
                	iteration++;
                    if(j == 1){
                        if(!visited.contains(iteration)){
                            queue.add(node * 10 + iteration);
                        }
                    }
                }
            }
        }
        System.out.println(sol);
	}

}
