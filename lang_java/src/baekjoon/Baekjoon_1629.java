package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1629 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long[] memo = new long[(int)(Math.log(B)/Math.log(2))+1];
		int answer = 1;
		int tmp = B;
		memo[0] = A%C;
		if(tmp%2 == 1) answer = (int) ((answer*memo[0])%C);
		tmp = B/2;
		
		for(int p = 1; tmp > 0; p++, tmp /= 2) {
			memo[p] = (memo[p-1]*memo[p-1])%C;
			if(tmp%2 == 1) answer = (int) ((answer*memo[p])%C);
		}
		
		for(int i = 0; i < memo.length; i++) System.out.print((long)memo[i]+"  ");
		System.out.println(answer);
	}
}