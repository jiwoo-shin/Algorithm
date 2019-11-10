package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2739 extends Solution {
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < 10; i++) {
			sb.append(N).append(" * ").append(i).append(" = ").append(N*i).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

