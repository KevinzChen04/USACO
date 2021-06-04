package acsl;
import java.util.*;
public class LexStrings {

	public static void main(String[] args) {
		String s = "This is an Example of Sorting an interesting string";
        String sol = "";
        ArrayList<String> temp = new ArrayList<String>();
        String[] characters = s.split("");
        ArrayList<String> list = new ArrayList<String>();
        list.add(characters[0]);
        for(int i = 1; i < s.length(); i++){
            boolean broken = false;
            for(int j = 0; j < list.size(); j++){
            	if(characters[i].charAt(0) < 48 || characters[i].charAt(0) < 65 && characters[i].charAt(0) > 57 || characters[i].charAt(0) < 97 && characters[i].charAt(0) > 90 || characters[i].charAt(0) > 122) {
            		broken = true;
            		break;
            	}
                if(!(characters[i].charAt(0) > list.get(j).charAt(0))){
                    list.add(j, characters[i]);
                    broken = true;
                    break;
                }
            }
            if(!broken) list.add(characters[i]);
        }
        TreeMap<String, Integer> blocks = new TreeMap<String, Integer>();
        for(int i = 0; i < list.size(); i++){
            if(blocks.containsKey(list.get(i))){
                blocks.put(list.get(i), blocks.get(list.get(i)) + 1);
            }
            else {
            	blocks.put(list.get(i), 1);
            }
        }
        for(int i = 100; i > 0; i--){
        	temp.add("");
        	temp.set(100 - i, temp.get(100 - i) + "" + i);
        	for(String j : blocks.keySet()) {
        		if(blocks.get(j) == i) {
        			temp.set(100 - i, temp.get(100 - i) + j);
        		}
        	}
        }
        boolean reverse = false;
        for(int i = 100; i > 0; i--) {
        	if(i < 10 && i >= 0) {
        		if(temp.get(100 - i).length() == 1) {
        			temp.set(100 - i, "");
        			continue;
        		}
        		String first = temp.get(100 - i).substring(0, 1);
        		String second = temp.get(100 - i).substring(1);
        		if(reverse) {
        			temp.set(100 - i, first + reverseString(second));
        		}
        		reverse = !reverse;
        	}
        	if(i < 100 && i > 9) {
        		if(temp.get(100 - i).length() == 2) {
        			temp.set(100 - i, "");
        			continue;
        		}
        		String first = temp.get(100 - i).substring(0, 2);
        		String second = temp.get(100 - i).substring(2);
        		if(reverse) {
        			temp.set(100 - i, first + reverseString(second));
        		}
        		reverse = !reverse;
        	}
        	if(i == 100) {
        		if(temp.get(100 - i).length() == 3) {
        			temp.set(100 - i, "");
        			continue;
        		}
        		String first = temp.get(100 - i).substring(0, 3);
        		String second = temp.get(100 - i).substring(3);
        		if(reverse) {
        			temp.set(100 - i, first + reverseString(second));
        		}
        		reverse = !reverse;
        	}
        }
        for(String i : temp) {
        	if(i.equals("")) continue;
        	sol = sol + i + ",";
        }
        sol = sol.substring(0, sol.length() - 1);
        System.out.println(sol);
	}
	public static String reverseString(String x) {
		String sol = "";
		for(int i  =  x.length(); i > 0; i--) {
			sol = sol + x.substring(i - 1, i);
		}
		return sol;
	}
}
