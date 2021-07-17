package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_1019 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] answer = new int[10];
		int unnecessaryZero = 0; // 해당 자리수에서 불필요하게 세어진 0의 개수..
		// 00019 같은.. 이게 19는 각 자리수에 1과 9가 들어가서 세어지는것은 괜찮은데 0이 불필요하게 세어짐. 
		// 19는 19인데 00019로 되어서 0이 그만큼 더 세어짐.
		
		for(int n = N, i = 1; n > 0; n /= 10, i*= 10) { // 숫자 N을 1의 자리부터 한자리씩 본다. 해당 자리수에 각 숫자가 몇번 올 수 있는지 계산한다. (k번째 자리수)
			int tmp = n % 10; // 숫자 N에서 내가 구할 자리수에 있는 숫자
			// N : 총 숫자, i : 내가 구할 자리수가 10의 몇승인지 , n: 내가 구할 자리까지만 자른 숫자
			
			int add = (n/10 + 1)*i; // tmp보다 작은 숫자인 경우 k번째 자리수에 몇 번 오는지  
			// (n/10 + 1) : tmp 기준 좌측 숫자들이 가능한 경우의 수-> 0~n/10까지 가능하므로, 
			// i : tmp 기준 우측 숫자들이 가능한 경우의 수 -> 좌측 숫자는 0-9까지 모두 가능하므로 i 번 가능
			
			
			int add2 = (n/10)*i; // tmp보다 큰 숫자인 경우 k번째 자리수에 몇 번 오는지 > 
			// (n/10) : tmp 보다 크므로 0~n/10-1 까지만 가능하다. k 번째 자리수에 n/10이 오면 숫자 N 보다 커지므로 불가
			// i : tmp 기준 우측 숫자들이 가능한 경우의 수 -> 우측 숫자로 인해 숫자가 N보다 절대 커지지 않으므로 좌측 숫자는 0-9까지 모두 가능하므로 i 번 가능
			
			int add3 = N%i + 1; // 숫자 tmp가 k번째 자리수에 몇번 오는지, 단 이때 k 기준 좌측 숫자는 (n/10)으로 고정. n/10보다 작은 경우는 add2에서 구했음
			// tmp는 왼쪽 숫자가 가장 커지면, tmp 우측 숫자가 0-9까지 모두 가능한게 아니라 0~N%i만큼만 가능하므로
			
			
			for(int j = 0; j < tmp; j++) {
				answer[j] += add;
			}
			for(int j = tmp; j < 10; j++) {
				answer[j] += add2;
			}
			answer[tmp] += add3;
			
			unnecessaryZero += i; // k 번째 자리에서 k 좌측 기준 0 고정, k 우측에 숫자가 몇번 오는지 
			//  좌측  0 고정 : 00019같이 앞에 0으로 가득찬 숫자 , 우측에 숫자가 몇번 오는지 : k 자리수에서 불필요하게 세어진 0의 개수만을 구해야하므로. 다른 자리수는 for문 돌면서 구함
		}
		
		answer[0] -= unnecessaryZero;
		
		for(int i = 0; i < 10; i++) {
			System.out.print(answer[i]+" ");
		}
	}
	
}