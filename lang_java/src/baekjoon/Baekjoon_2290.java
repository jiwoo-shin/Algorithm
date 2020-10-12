package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2290 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int len_v = 2*s+3;
		int len_h = s+2;
		String[][] number_text = genNumberText(s, len_v, len_h);
		
		StringBuilder answer = new StringBuilder();
		char[] input_numbers = st.nextToken().toCharArray();
		for(int i = 0; i <len_v; i++) {
			for(int j = 0; j < input_numbers.length; j++) {
				int number = input_numbers[j]-'0';
				answer.append(number_text[number][i]).append(' ');
			}
			answer.append('\n');
		}
		System.out.print(answer);
	}
	static String[][] genNumberText(int s, int len_v, int len_h){
		String[][] number_text = new String[10][len_v];

		StringBuilder line_h = new StringBuilder(" ");
		StringBuilder empty = new StringBuilder(" ");
		StringBuilder line_v_left = new StringBuilder("|");
		StringBuilder line_v_right = new StringBuilder(" ");
		StringBuilder line_v_two = new StringBuilder("|");
		for(int i = 1; i < len_h-1; i++) {
			line_h.append('-');
			empty.append(' ');
			line_v_two.append(' ');
			line_v_left.append(' ');
			line_v_right.append(' ');
		}
		line_v_right.append('|');
		line_h.append(' ');
		line_v_two.append('|');
		line_v_left.append(' ');
		empty.append(' ');
		
		for(int i = 0; i < len_v; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == 0) {
					if(j == 1 || j == 4) {
						number_text[j][i] = empty.toString();
					} else {
						number_text[j][i] = line_h.toString();
					}
				} else if(i <= s) {
					if(j == 1 || j == 3 || j == 7 || j ==2) number_text[j][i] = line_v_right.toString();
					else if(j == 5 || j == 6) number_text[j][i] = line_v_left.toString();
					else  number_text[j][i] = line_v_two.toString();
				} else if(i == s+1) {
					if(j == 1 || j == 7 || j == 0) number_text[j][i] = empty.toString();
					else number_text[j][i] = line_h.toString();
				} else if(i < len_v-1) {
					if(j == 1 || j == 3 || j == 4 || j == 5 || j == 7 || j == 9) number_text[j][i] = line_v_right.toString();
					else if(j == 2) number_text[j][i] = line_v_left.toString();
					else  number_text[j][i] = line_v_two.toString();
				} else {
					if(j == 1 || j == 4 || j == 7) number_text[j][i] = empty.toString();
					else number_text[j][i] = line_h.toString();
				}
			}
		}
		
		return number_text;
	}
}


