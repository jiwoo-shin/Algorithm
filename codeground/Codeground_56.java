package codeground;
import java.util.Scanner;

public class Codeground_56 extends Solution{
	/* 요약
	 * 4와 7로 이루어진 수 중 k 번째 숫자 구하기
	 * 
		1. 4와 7 > 2가지 숫자로만 이루어짐
			4->7->44->47->74->77->444->......
		2. 4와 7 로만 이루어져 있으므로 1과 0으로 이루어진 2진수와 유사
			단, n번째 2진수 = n번째 극단적인 수 인 것은 x
		3. k 번째 숫자가 몇자리 극단적인 숫자인지, 그 중에서도 몇번째인지 알아야함.
			k=2^j+t 일때, j 자리 극단적인수, 그 중에서 n 번째 자리
	*/
	static String Answer = "";
	
	@Override
	public void solution() {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int k = sc.nextInt();//k번째 숫자
			int j = k%2;
			int n = 0;
			int p = 2;
			while((k/=2)>=1) {
				n++;
				if(k!=1)j += p*(k%2);
				p*=2;
			}
			if(j==0)n--;
			p=p/2;
			j= (p-1)-p/2;
			System.out.println(n+"  "+j);
			for(int i=0;i<n||(j>0);i++) {
				Answer=(3*(j%2)+4)+Answer;
				j=j/2;
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}
