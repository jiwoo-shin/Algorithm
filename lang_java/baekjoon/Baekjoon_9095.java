package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// 줄 세우기.. 숫자 1, 2, 3을 줄 세울 수 있는 경우의 수를 구하는 문제.
/*
 // 재귀함수 사용하지 않는 경우 > for문 횟수자체는 많지가 않ㅇㄴ데 나누기 가 너무 많음. 오래걸림,,
public class Baekjoon_9095 extends Solution {
	public static int c(int n, int m) {
		int c = 1;
		for(int i = 0; i < m; i++) {
			c *= (n-i);
			c /= (i+1);
		}
		
		return c;
	}
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //참고.. k += (1 - k)/(k + 1); //k가 0일 경우 1, 0아닐 경우 k
		
		for(int i = 0; i < T; i++) {
			
			int result = 0;
			int n = sc.nextInt();
			
			for(int j = 0; j <= n/3; j++) { //3의 개수.. 등호 있어야함
				
				for(int k = 0; k <= (n-j*3)/2; k++) { //2의 개수
					int nums = (n - 3*j - 2*k) + j + k; //숫자의 개수 (1의 개수 :(n - 3*j - 2*k) / 2의 개수 : j / 3의 개수 : k )
					
					result += c(nums, (n - 3*j - 2*k))* c(j+k, j); //숫자 개수 : 자리의 개수/ 일단 1이 들어갈 자리 뽑음 : c(nums, (n - 3*j - 2*k)) / 그 다음 2가 들어갈 자리 뽑음: 1이 뽑은 자리 제외하고 뽑을 수 있음./c(k, k)는 항상 1이므로 계산 불ㅍㄹ요.. 3은  항상 남은 자리에 꽉차게 들어감.
				}
			}
			System.out.println(result);
		}
		
	}
}*/

public class Baekjoon_9095 extends Solution {

	//return 값은 추가로 구해진 정답의 개수 > return 들의 합이 답이 되어야함
	static int sum(int current, int goal) {  //current : 현재까지 합, goal : 만들고자 하는 값, num : 1, 2, 3의 개수 
		if(goal == current) {
			return 1; //1개 더해져야함
		} else if(goal > current) {
			// 1, 2, 3 각각에 대한 정답의 개수를 저장해야함, 나중에 다 더해야하므로
			int temp_answer = 0; // 새로운 가지를 뻗을 때 마다 초기화해줘야함
			// 만약 sum(int current, int goal,int temp_answer)을 할 경우 temp_answer이 현재 까지구해진 답의 개수가 되는데 나중에 return할 경우 현재 까지 구해진 개수도 모두 더해지는 참사가 발생..> 추가로 구해진 경우만 더해져야하는데..
			for(int i = 1; i< 4; i++) { //1, 2, 3에 대해 각각을 더해봄..
				temp_answer += sum(current+i, goal);
			}
			return temp_answer;
		} else return 0; //current가 goal보다 커진 경우이므로 답이 아님
	}
	
	// dp (bottom-up)
	static int bottomUp(int[] memo, int n) {
		memo[0] = 1;
		if(n > 1) memo[1] = 2;
		if(n > 2) memo[2] = 4;
		// f(n) = f(n-1) 로 만든 후 1을 더하는 경우 + f(n-2)로 만든 후 2를 더하는 경우 + f(n-3)으로 더한 후 3을 더하는 경우
		for(int i = 3; i < n; i++) {
			memo[i] = memo[i-1] + memo[i-2] + memo[i-3];
		}
		return memo[n-1];
	}
	
	// dp (top-down)
	static int topDown(int[] memo, int n) {
		int index = n-1;
		if(memo[index] == 0) {
			if(index == 0) memo[index] = 1;
			else if(index == 1) memo[index] = 2;
			else if(index == 2) memo[index] = 4;
			else {
				memo[index] = topDown(memo, n-1) + topDown(memo, n-2) + topDown(memo, n-3);
			}
		}
		
		return memo[index];
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
	    StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			sb.append(bottomUp(new int[n], n)).append('\n');
			sb.append(topDown(new int[n], n)).append('\n');
			// sb.append(sum(0, n)).append('\n');
		}
		System.out.println(sb.toString());
		
	}
}
