package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_14238 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int length = S.length();
		int[] num = new int[3]; //A,B,C의 개수
		for(int i = 0; i < length; i++) {
			num[S.charAt(i)-'A']++;
		}
		char[] answer = dp(num, length, 0, 0, 0, new boolean[length+1][num[0]+1][num[1]+1][4][4], 0, new char[length]);
		if(answer == null) System.out.print("-1");
		else System.out.print(answer);
	}
	// visited[현재문자열길이][사용한 A][사용한 B][바로 이전에 뭐왔는지][2번째 이전에 뭐왔는지] // 방문한 정점의 상세 내용은 다르지만, 바로이전, 2번째 이전 정보 & 사용한 ABC의 개수가 같다면 결국 미래에도 동일한 결과가 초래하기 때문에 굳이 불필요한 검색을 하지 않기 위해
	static char[] dp(int[] number, int length, int a, int b, int c, boolean[][][][][] visited, int i, char[] answer) {
		if(i > 2 && !visited[i-1][a][b][answer[i-2]-'A'][answer[i-3]-'A']) {
			visited[i-1][a][b][answer[i-2]-'A'][answer[i-3]-'A'] = true;
		} else if(i == 2 && !visited[i-1][a][b][answer[i-2]-'A'][3]) {
			visited[i-1][a][b][answer[i-2]-'A'][3] = true;
		} else if(i == 1 && !visited[i-1][a][b][3][3]) { 
			visited[i-1][a][b][3][3] = true; 
		} else if(i!=0) {
			return null;
		}
		// i를 구하기 전이므로 실제 문자열은 하나 작음
		
		
		if(i==length && a == number[0] && b ==number[1] && c== number[2]) {
			return answer;
		} else {
			if(number[0] > a) {
				answer[i] = 'A';
				if(dp(number, length, a+1, b, c, visited, i+1, answer)!=null) return answer;
			}
			
			if(number[1] > b) {	
				if(i == 0 || (answer[i-1] != 'B')) {
					answer[i] = 'B';
					if(dp(number, length, a, b+1, c, visited, i+1, answer)!=null) return answer;
				}
			}
			
			if(number[2] > c) {
				if(i == 0 || (i == 1 && (answer[i-1] != 'C') || (i >= 2 && answer[i-2] != 'C' && answer[i-1] != 'C'))) {
					answer[i] = 'C';
					if(dp(number, length, a, b, c+1, visited, i+1, answer)!=null) return answer;
				}
			}
			
		}
		
		return null; 
	}
}

