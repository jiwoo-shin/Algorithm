package lang_java.codeground;
import java.util.Scanner;

public class Codeground_56 extends Solution{
	/* 요약
	 * 4와 7로 이루어진 수 중 k 번째 숫자 구하기
	 * 
		1. 4와 7 > 2가지 숫자로만 이루어짐
			4->7->44->47->74->77->444->......
			4 7/ 44 47 74 77/ 444 ...
		2. 4와 7 로만 이루어져 있으므로 1과 0으로 이루어진 2진수와 유사
			단, n번째 2진수 = n번째 극단적인 수 인 것은 x
		3. k 번째 숫자가 몇자리 극단적인 숫자인지, 그 중에서도 몇번째인지 알아야함.
			2+4+8+... -> 2부터 2^n까지의 합 ->2^(n+1)-2
			2부터 2^(n-1)까지의 합 < k-1 < 2부터 2^(n)까지의 합
				n이 자리수, 범위가 k-1인 것은 시작 숫자가 0(4) 부터이기 때문, 자리수도 2^n 숫자일 경우 n-1자리수..
				2^n-2 < k-1 < 2^(n+1)-2
				2^n < k+1 < 2^(n+1) //// --- (1)
	*/
	static String Answer = "";//초기값.. 꼭 주자..
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int k = sc.nextInt();//k번째 숫자
			k += 1; ///-----(1) 에 의해 k+1
			
			int originK = k;
			int j, n = 0;//n은 몇자리인지, j는 n자리숫자 중 몇번째인지
			do {
				n++;
				k /= 2;
			}while(k > 1);
			
			j = originK - (int)Math.pow(2, n);
			
			for(int i=0; i < n; i++){
				Answer = (3 * (j % 2) + 4) + Answer;//j%2==1이면 7로.. j%2==0이면 4로
				j /= 2;
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
			Answer = "";
		}
	}
}
