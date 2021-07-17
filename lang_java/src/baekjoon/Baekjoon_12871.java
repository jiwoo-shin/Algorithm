package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_12871 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		// abab ababab
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		int sLength = s.length();
		int tLength = t.length();

		int gcd = sLength, b = tLength;
		while (b != 0) {
			int r = gcd % b; 
			gcd = b;
			b = r;
		}
		int max = sLength*tLength/gcd;
		
		int answer = 1;
		for(int i = 0; i < max; i++) {
			if(s.charAt(i%sLength) != t.charAt(i%tLength)) {
				answer = 0;
				break;
			}
		}
		
		System.out.println(answer);
	}
	
}