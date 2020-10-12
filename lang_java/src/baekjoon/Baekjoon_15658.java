package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_15658 extends Solution {
	
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] operator_array; // 연산자가 몇개씩 있는지
	static int N;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operator_array = new int[4]; // 연산자를 입력받음
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		numbers[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++ ) {
			operator_array[i] = Integer.parseInt(st.nextToken()); // 각 순서대로 +, -, x, / 를 입력
		}
		get_operator(operator_array, 0, numbers[0]);
		System.out.println(max+"\n"+min);
	}
	//dfs는 n!의 시간복잡도 이지만 +, -,  *, / 를 해당 depth에서 각각 사용하는경우를 따지면 최대 4^n 의 시간복잡도(연산자 개수가 0 일경우는 계산 안함..최대인 경우는 연산자의 개수가 모두 같을 때)
	// dfs는 +++ 일 경우 123, 132, ... 해서 동일한 결과지만 6가지 계산이 필요
	// depth에서 어떤 연산자 사용하는지 다 해보는 경우는  +++ 일 경우는 +++ 하나만 계산해봄
	static void get_operator(int[] operators, int depth, int result) {// operators : 연산자들, depth : 연산자 길이, result : 현재까지 계산된 값
		if(depth == N-1) { // 연산자 길이가 N-1인 경우 더이상 안해도됨
			if(min > result) min = result;
			if(max < result) max = result;
			return;
		}
		// +, -, *, / 를 차례대로 넣어본다.
		for(int operator = 0; operator < 4; operator++) {
			if(operators[operator] > 0) { // 개수가 충분히 있는 경우
				operators[operator]--; // operator 사용하였으므로
				int next_result = 0;
				switch(operator) {
					case 0: 
						next_result = result + numbers[depth+1];
						break;
					case 1: 
						next_result = result - numbers[depth+1];
						break;
					case 2:
						next_result = result * numbers[depth+1];
						break;
					default: 
						next_result = result / numbers[depth+1];
				}
				get_operator(operators, depth+1, next_result);
				operators[operator]++; // 다음 for에는 영향을 주면 안됨, 즉, + 를 쓴 이후 다음은 -를 쓰는 경우 -> 이전에 쓴 + 도 여기서 쓴것처럼 되면 안된다.
			}
		}
	}
	
}
