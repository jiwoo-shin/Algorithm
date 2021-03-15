package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_2008 extends Solution {
	
	static int N, M, a, b, X, Y;
	static int[] memo; // memo[i] : a ~ i 까지 가는 데 걸리는 최소 비용
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로선의 개수
		M = Integer.parseInt(st.nextToken()); // 가로선의 개수
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()); // 시작
		b = Integer.parseInt(st.nextToken()); // 도착
		X = Integer.parseInt(st.nextToken()); // 가로선을 지울 때 드는 비용
		Y = Integer.parseInt(st.nextToken()); // 가로선을 그릴 때 드는 비용
		int min = Math.min(X, Y);
		
		memo = new int[N];
		for(int i = 0; i < N; i++) {
			memo[i] = Math.abs(i-(a-1))*Y; // 다리가 하나도 없는 경우 (a-1은 memo가 0부터 시작하므로 보정을 위해)
		}
		
		int p = -1;
		for(int i = 0; i < M; i++) { // i 번째 가로선까지 있다고 가정하였을 때 최소비용
			p = Integer.parseInt(br.readLine())-1; //p, p+1을 잇는 가로 선.. memo[i]는 0부터이므로 보정한다.
			
			int tmp = memo[p];
			memo[p] = Math.min(memo[p+1], memo[p]+min); // memo[p] : p+1 -> p로 i 번째 가로선을 타고 이동하는 경우(memo[p+1]) : p+1 갔다가 p로,.. / i 번째 가로선을 사용하거나 제거하는 경우.( memo[p]+min) 
			memo[p+1] = Math.min(tmp, memo[p+1]+min);
			
			for(int j = 0; j < N; j++) { // 나머지 선도 p와 p+1에 의해 최소비용이 변경될 수 있음 p나 p+1을 거쳐서 가는 경우
				memo[j] = Math.min(memo[p] + Math.abs(j-p)*Y, Math.min(memo[p+1] + Math.abs(j-(p+1))*Y, memo[j])); // p 에서 j로 / p+1에서 j로(새로 생긴 선 i 로 인해 업데이트되었으므로 p 에서 j로 가는 경우 확인 필요) / i 를 제거하는게 아니고 i 랑 아예 상관없이 가는 것임.. 아예 가로선 i는 고려하지 않고 가는 경우... i 고려한 경우는 p나 p+1을 거치는 경우에서 확인함 
			}
		}
		
		System.out.println(memo[b-1]);
	}
}