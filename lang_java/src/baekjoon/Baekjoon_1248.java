package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_1248 extends Solution {

	static int[][] marks;
	static int N;
	static int[] numbers;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
    	String tmp_marks = br.readLine();
    	marks = new int[N][N]; // marks[i][j] : i+1 ~ j+1 번째 숫자의 합
    	for(int i = 0, char_index = 0; i < N; i++) {
    		for(int j = i; j < N; j++) {
    			char tmp = tmp_marks.charAt(char_index++);
    			if(tmp == '0') marks[i][j] = 0;
    			else if(tmp == '-') marks[i][j] = -1;
    			else marks[i][j] = 1;
    		}
    	}
    	numbers = new int[N];
    	
    	getNumbers(0);
    	
    	StringBuilder answer = new StringBuilder();
		for(int i = 0; i < N; i++) {
			answer.append(numbers[i]).append(' ');
		}
    	System.out.println(answer);
	}
	
	static boolean isAble(int depth) {
		// number[depth]가 가능한 숫자인지 검토
		int sum = 0;
		for(int i = depth; i >= 0; i--) {
			int mark = marks[i][depth]; // i+1 ~ depth+1 번째 숫자의 합의 부호
			// 끝에서 부터 확인
			// depth, (depth-1 ~ depth)번째 합, (depth-2 ~ depth)번째 합 .... 이런식으로 구한다.
			sum += numbers[i]; // 합 
			if(mark == 0 && sum!=0) {
				return false;
			} else if(mark == -1 && sum >= 0) {
				return false;
			} else if(mark == 1 && sum <= 0) {
				return false;
			}
		}
		return true;
	}
	
	// 끝났는지 여부를 알기 위해 boolean 값 return
	static boolean getNumbers(int depth) {
		if(depth == N) {
			return true; // 
		} else {
			int mark = marks[depth][depth];
			int start = Math.min(1*mark, 10*mark); // 시작
			int end = Math.max(1*mark, 10*mark); // 끝
			for(int i = start; i <= end; i++) {
				numbers[depth] = i;
				if(isAble(depth) && getNumbers(depth+1)) return true;
				// if(isAble(depth)) return getNumbers(depth+1);
				// 이러면 안됨. 첫번째 가지에서 깊가 나가다 정답을 구하지 못해도 끝나버림
			}
		}
		return false;
	}
}