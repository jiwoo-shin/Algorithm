package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1644 extends Solution {
	
	static int N;
	static boolean[] isNotPrime;
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		isNotPrime = new boolean[N+2];
		int limit = (int)Math.sqrt(N);
		isNotPrime[1] = true;
		for(int i = 2; i <= limit; i++) { // 이 숫자의 배수가 있는지 판단할 예정
			if(isNotPrime[i]) continue; // i가 소수가 아닌 경우 
			for(int j = i*2; j <= N; j +=i) { // j는 i의 약수
				isNotPrime[j] = true;
			}
		}
		System.out.println(get());
	}
	static int get() {
		int start = 2, end = 2, sum = 0;
		int answer = 0;
		while(end <= N || sum >= N) {
			if(sum > N) { // 숫자를 제거해야함
				sum -= start;
				start = getNextPrime(start);// 소수가 아닌 경우 패스
			} else if(sum < N) {// 숫자를 더 넣어야함
				sum += end;
				end = getNextPrime(end);
			} else { //초기화 필요
				answer++;
				start = getNextPrime(start);
				end = start;
				sum = 0;
			}
		}
		return answer;
	}
	static int getNextPrime(int number) {
		do {
			number++;
		} while(isNotPrime[number] && number <= N);  // 더이상 소수가 없는 경우 N+1을 반환한다.
		return number;
	}
}

