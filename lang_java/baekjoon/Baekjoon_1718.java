package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1718 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int length = (int)Math.log10(N) + 1; //N이 몇자리 숫자인지
		int answer = (N-(int)Math.pow(10, length-1)+1)*length;
		
		// now : i 자리 숫자에서 가능한 숫자의 개수
		for(int i = 1, now = 9; i < length; i++, now *= 10) {
			answer += (now*i); //i 자리이므로 *i를 해야한다ㅏ.
		}
		
		System.out.println(answer);
	}
}