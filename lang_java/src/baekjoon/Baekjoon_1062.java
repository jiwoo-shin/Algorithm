package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1062 extends Solution {
	
	// 기본으로 가르치는 글자들
	static int basicWord = (1 << 'a' - 'a') | (1 << 'c' - 'a') | (1 << 'i' - 'a') | (1 << 'n' - 'a') | (1 << 't' - 'a'); // 기본
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] words = new int[N];
		for(int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < tmp.length(); j++) {
				words[i] |= 1 << (tmp.charAt(j) - 'a'); 
			}
		}
		int answer = 0;
		int max = 1 << 26; // 2^26 
		for(int i = 0; i < max; i++) {
			if(check(i, M-5)) { // M-5 개 단어 가르친 경우 (antic 는 항상 포함) // 기본으로 가르치는 글자는 아예 제외하고 word에 저장하였으므로 M-5 개 여부에 대해서만 판별하면된다.
				answer = Math.max(answer, readWord(words, i, N));
			}
		}
		System.out.println(answer);
	} 
	static boolean check(int number, int M) {
		int count = 0;
		
		// basicWord(acinta) 를 제외한 5개 중에서 뽑아야 하므로.. 
		if((number & basicWord) != 0) return false;
		
		for(int i = 0; i < 26; i++) {
			if((number & (1<<i))  != 0) { 
				count++; // i 번째 알파벳을 가르친 경우
			}
			if(count > M) return false; // 안되는 경우. 가르칠 수 있는 글자수를 넘치는 경우
		}
		return count == M;
	}
	static int readWord(int[] words, int number, int N) {
		int answer = 0;
		int mask = number | basicWord; // 
		for(int i = 0; i < N; i++) {
			if((words[i] & mask) == words[i]) {
				 answer++; // 가르치지 않은 글자가 포함된 경우
			}
		}
		return answer;
	}
}