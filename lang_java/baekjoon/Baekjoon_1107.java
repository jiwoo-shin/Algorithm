package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1107 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        int[] trouble = new int[10]; // 고장난 버튼
        int N = Integer.parseInt(br.readLine()); // 목표점
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼의 개수
        int answer; // 숫자패드 누른 후 리ㅗㅁ콘으로 이동하는 경우
		int answer_100 = Math.abs(N-100); // 100에서 시작하여 숫자버튼은 누르지 않고 만드는 경우
		
        if(M > 0) { // 고장난 버튼의 개수가 0보다 큰 경우
        	st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
            	trouble[Integer.parseInt(st.nextToken())] = 1; // 고장난 버튼이면 1을 저장
            }
            int minus = N, plus = N;
            boolean plus_len = isContain(plus, trouble); // 더하는 경우 
            boolean minus_len = isContain(minus, trouble); // 빼는 경우

            for(answer = 0; answer < answer_100 && !(plus_len || minus_len); answer++) {
        		plus_len = isContain(++plus, trouble);
        		minus_len = isContain(--minus, trouble);
        		// 둘다 false인 경우(-> 고장난 버튼이 있는 경우)만 돈다.
            }
        	int len = 6; // 숫자판으로 입력하는 숫자의 길이 (최대 len = 6)
        	if(plus_len) len = length(plus); // plus로 가능한 경우
        	if(minus_len) len = Math.min(len, length(minus)); // minus로 가능한 경우
    		answer += len; // 버튼 입력횟수 : 숫자판 입력 길이 + 리모콘 상,하 버튼 입력 횟수
        } else { // 고장난 버튼이 하나도 없는 경우
        	answer = N==0? 1: (int)Math.log10(N) + 1; // 숫자판으로 바로 N을 만들 수 있다
        }
		answer = Math.min(answer_100, answer);
        System.out.println(answer);
	}
	static boolean isContain(int number, int[] trouble) { // number를 만들 수 있는 경우 true/ 만들 수 없는 경우(고장난 버튼이 있는 경우) false
		if(number < 1) return trouble[0] == 0; // 0이 고장난 버튼이면 false, 고장나지 않은 버튼이면 true
		else {
			while(number > 0) { // number의 모든 자리수에 대해 구함
				if(trouble[number%10] == 1) return false; // 고장난 버튼이 있는 경우
				number /= 10;
			}
			return true; // 고장난 버튼이 없는 경우 (자리수)
		}
	}
	static int length(int number) { // number의 자리수를 구함
		return number <= 0? 1: (int)Math.log10(number) + 1; // number < 0 인 경우는 number = 0으로 쳐야함 (number < 0이면 --를 하면 안되는데 하니까)
	}
}