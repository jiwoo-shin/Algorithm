package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_14888 extends Solution {
	
	
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
	
	/*
	 * 
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] operator_array; // 연산자가 몇개씩 있는지
	static int N;
	static boolean[] visited;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		visited = new boolean[N];
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
		get_operator(0, new int[N-1]);
		System.out.println(max+"\n"+min);

	}
	// dfs 방식으로 연산자의 위치를 구한다, 총 연산자는 N-1개 -> 이렇게 연산자 위치로 안하고 해당 위치에서 +, -, *, / 중 어느것을 쓸건지 하는게 더 빠름, (4^n 의 복잡도를 가지므로).. 현재는 순열을 생성하는 코드라 n! 의 복잡도를 가짐.
	static void get_operator(int depth, int[] operators) { //depth는 연산자 결과 길이, operators : 현재까지 생성된 연산자
		
		// 숫자가 N개일 때 연산자는 N-1개 사용
		if(depth == N-1) {
			int tmp = calculate(operators, numbers);
			if(tmp < min) min = tmp;
			if(tmp > max) max = tmp;
			}
		else { // 추가로 연산자 위치 계산이 필요한 경우
			for(int i = 0; i < N-1; i++) {
				if(!visited[i]) {
					visited[i] = true; // visited[depth] 아님. visited[]
					operators[depth] = i; //operators[i] 가 아님. //depth 번째 순열에 i 노드추가
					get_operator(depth+1, operators); 
					visited[i] = false;
				}
				
			}
		}
		
	}
	
	// 연산자 배열과 숫자를 입력받아 계산
	static int calculate(int[] operators, int[] numbers) { //int[] operators : 연산자의 위치가 담긴 배열, int[] numbers: 숫자들이 담긴 배열
		// operators 내의 연산자가 +, - x, / 중에 뭔지 알아야함
		
		int[] operators_limit = new int[4]; // 각 연산자가 어디까지 가능한지
		operators_limit[0] = operator_array[0]-1; //index이므로 -1, 0일 경우는 -1로 되어야한다.
		for(int i = 1; i < 4; i++) {
			operators_limit [i] = operators_limit [i-1] + operator_array[i];
		}
		
		//계산 결과
		int result = numbers[0];
		for(int i = 1; i < N; i++) {
			
			// 연산자가  + , - , x , / 중에 어떤건지 계산
			int operator = 0;
			
			while(operators[i-1] > operators_limit[operator] && operator < 3){ //operator 최대값은 3
				operator++;
			}
			switch(operator) {
				case 0: 
					result += numbers[i];
					break;
				case 1: 
					result -= numbers[i];
					break;
				case 2:
					result *= numbers[i];
					break;
				default: 
					result /= numbers[i];
			}
		}
		
		return result;
	}*/

	/*
	 * 
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] numbers;
	static int[] operators;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operators = new int[4];
		//+, -, x, / 순서
		

		// 0~N-1 순열을 정렬한다. // 연산자 순서를 나타내는 순열
		int[] operator_array = new int[N-1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		numbers[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			operator_array[i-1]  = i-1;
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		int result = calculate(operator_array);

		if(result > max) max = result;
		if(result < min)min = result;
		change(operator_array, 0);
		System.out.println(max+"\n" +min);

	}
	static int calculate(int[] operator_array) {
		int result = numbers[0];
		int N = numbers.length;
		
		int[] operators_limit = {operators[0]-1, operators[0]+operators[1]-1,  operators[0]+operators[1]+operators[2]-1, operators[0]+operators[1]+operators[2]+operators[3]-1 };
		
		for(int i = 0; i < N-1; i++) {
			int operator = 0;
			for(;operator < 4; operator++) {
				if(operator_array[i] <= operators_limit[operator])break;
			}
			switch(operator) {
				case 0: 
					result += numbers[i+1];
					break;
				case 1: 
					result -= numbers[i+1];
					break;
				case 2:
					result *= numbers[i+1];
					break;
				default: 
					result /= numbers[i+1];
			}
		}

		return result;
	}
    // 모든 순열 활용
	static int[] change(int[] array, int change_index) {
			
			int N = array.length;
			int tmp;
			for(int k = 0; k < N-1 - change_index ; k++ ) {//k는 그저 횟수 (change_index에 대해 N-1 - change_index 번 반복 필요)
				if(change_index < N-2 )array = change(array, change_index+1); //change_index는 N-2 까지 가능하므로.
				int change = array[change_index];
				
				//change_index와 어디가 바뀌어야하 하는지 확인
				for(int i = N-1; i >= change_index+1; i-- ) {
					if(array[i] > change) { 
						array[change_index] = array[i]; 
						array[i] = change;
						
						int sort_limit = ((N-1)-(change_index+1))/2; 
						
						//숫자를 바꾼다. 
						for(int j = 0; j <= sort_limit; j++) {
							tmp = array[change_index+1+j];
							array[change_index+1+j] = array[N-1-j];
							array[N-1-j] = tmp;
						}
						
						break;
					}
				}
				int result = calculate(array);
				if(result > max) max = result;
				else if(result < min)min = result;
			}
			if(change_index < N-2)array = change(array, change_index+1); // 마지막으로 바뀌는 자리수가 올라가기 전에 이전거 한번 더 해줘야한다.
			return array;
		}
	 */
}
