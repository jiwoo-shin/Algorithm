package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_11022 extends Solution {
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int i = 1; i < T+1; i++) {
			String s = bf.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			bw.write("Case #"+i+": "+A+" + "+B+" = "+(A+B)+"\n");
		}
		bw.flush();
		bw.close();
	}
}
