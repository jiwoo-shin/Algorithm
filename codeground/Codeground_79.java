package codeground;
import java.util.Scanner;

public class Codeground_79 extends Solution{
	/*  요약 : Pivot을 만족하는 수가 몇개 존재하는지 구하세요.
	 * 
		a 기준 왼쪽 : 모두 작은수, a 기준 오른쪽 : 모두 큰 수.. > a는 Pivot 조건 만족
		-> a 기준 왼쪽: 최소, 오른쪽 : 최대인 배열 구한 후 비교
	*/
	static int Answer = 0;
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int N = sc.nextInt();
			
			int[] array = new int[N];
			int[] max = new int[N];
			int[] min = new int[N];
			
			array[0] = sc.nextInt();
			max[0] = array[0];
			
			//max[i], min[i]를 구할 때 array[i]는 포함x >수정필요
			for(int i=1; i < N; i++) {
				array[i] = sc.nextInt();
				if(max[i-1] < array[i-1]) max[i] = array[i-1];
				else max[i] = max[i-1];
			}
			
			min[N-1] = array[N-1];
			
			for(int i = N-1; i > 0; i--) {
				if(min[i] > array[i-1]) min[i-1] = array[i-1];
				else min[i-1] = min[i];
			}
			
			for(int i = 0; i < N; i++) {
				//if(array[i])
			}
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
