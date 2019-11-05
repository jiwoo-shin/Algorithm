package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15666 extends Solution {

    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
    static int[] numbers;

    //여러번 선택가능하므로 visited가 불필요함
    static void dfs(int length, int[] array) { // length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), array : 수열,
		if(length == M ) { //여기 &&compare으로하면 안됨
			for(int i = 0; i < length; i++) {
				sb.append(numbers[array[i]]).append(" ");
			}
			sb.append('\n');
		} else {
			int start = length==0? 0 : array[length-1];
            int prev_num = 0; // 이전에 추가된,, 
			for(int i = start; i < N; i++) { 
				if(prev_num < numbers[i]) { // 남은 숫자가 같으면 다음 경로도 모두 같게 나온다.(for문 도는게 의미가 이전 까지는 모두동일을 의미하므로 이 같으면 노노 -> 남은 숫자가 같으므로 완전히중복이ㅣㅁ..)
					array[length] = i; 
					dfs(length+1, array);
					prev_num = numbers[i];
				}
			}
		}
	}

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		// index를 수열로 만들어준다.
		dfs(0, new int[M]); 
		System.out.print(sb.toString());

	}
}