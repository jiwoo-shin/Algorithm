package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11723 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int S = 0; // ¹è¿­
		int M = Integer.parseInt(br.readLine());
		int number;
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char operate = st.nextToken().charAt(1);
			switch(operate) {
				case 'd': //add
					number = Integer.parseInt(st.nextToken()) - 1;
					S = S | (1 << number);
					break;
				case 'e':// remove
					number = Integer.parseInt(st.nextToken()) - 1;
					S = S & ~(1 << number);
					break;
				case 'o'://toggle
					number = Integer.parseInt(st.nextToken()) - 1;
					S = S ^ (1 << number);
					break;
				case 'h': //check
					number = Integer.parseInt(st.nextToken()) - 1;
					sb.append((S >> number) & 1).append('\n');
					break;
				case 'l': //all
					number = 1 << 20;
					S = S | number - 1;
					break;
				default: //empty
					S = 0;
					break;
			}
			/*
			if(operate.equals("add")) {
				int number = Integer.parseInt(st.nextToken()) - 1;
				S = S | (1 << number);
			} else if(operate.equals("remove")) {
				int number = Integer.parseInt(st.nextToken()) - 1;
				S = S & ~ (1 << number);
			} else if(operate.equals("toggle")) {
				int number = Integer.parseInt(st.nextToken()) - 1;
				S = S ^ (1 << number);
			} else if(operate.equals("all")) {
				S = S | ((1 << 20) - 1);
			} else if(operate.equals("check")) {
				int number = Integer.parseInt(st.nextToken()) - 1;	
				sb.append(((S >> number) & 1)).append('\n');
			} else {
				S = 0;
			}*/
		}
		System.out.println(sb.toString());
	}
}
