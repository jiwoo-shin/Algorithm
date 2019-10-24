package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15652 extends Solution {
	
    static StringBuilder sb;
    static int N; // 숫자 범위
    static int M; //  수열 길이
    
	static void dfs(int length, String answer, int number) { //  length : 현재까지 생성된 배열 길이(추가되어야하는 배열의 인덱스), answer : 수열, number : 추가할 숫자
		answer += number+" "; // 여기서 추가해준다. for문 내에서 추가할 경우 다음 for문에서 이전 for문의 결과가 영향을 미치기 때문 
		//answer 는 지역변수 유지해야함. 경로가 끝까지 구해진 후 \n이 붙는데 전역인 경우 \n 붙고 다음 방문하는 노드들이 하나씩만 추가되기 때문(경로가 출력되지 않음..)
		//sb.append(number).append(" "); // ㄴㄴ
		
		if(length == M) { // 수열을 생성. 더 이상 이어지는 순열을 계산할 필요는 없음
			//for(int i = 0; i< length; i++)sb.append(array[i]).append(" "); // 이 for문은 줄일 수 있음,, 배열을 수열로 사용하는 대신 계산하는 answer에 바로 붙여줌
			sb.append(answer).append("\n");
			return;
		} else { // 다음 방문 노드를 더 계산해야함
			for(int i = number-1; i < N; i++) { // number+1이 아님 dfs에서 i+1이 되므로  (다음 노드는 이전노드보다 큰 것이 아니라 같거나 크므로 number-1부터 해주면 된다 (dfs 호출하면서 i+1이 되므로 number 노드부터 탐색됨))
				//answer += (i+1)+" "; // 여기서 answer에 추가해줄 경우 다음 for문에 현재 for문에서 추가된 경로가 붙어버려서 안됨 (1 2,  1 3 이렇게 되는게 아니라 1 2 3 이렇게 됨 >> 현재 추가되는 경로는 다음 재귀함수에서 추가.)
				dfs(length+1, answer, (i+1)); // 이 dfs를 재귀적으로 호출하면서 끝까지 이동함
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
		
		// 초반 노드들을 하나씩 방문해야한다. dfs 초반에 answer에 붙여버리기 때문에 시작 값을 다 돌려줘야함
		for(int i = 1; i < N+1; i++)dfs(1, "", i);
		
		System.out.print(sb.toString());
	}
}
