package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_12904 extends Solution {
	
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());
		
		int tLength = T.length();
		int sLength = S.length();
		
		int answer = 0;
		
		// T를 S길이로 변경한다. 뒤에서만 알파벳을 제거할 수 있으므로 뒤에 오는 알파벳에 따라 T를 뒤집어야 하는지, 유지하고 제거하면 되는지가 정해져있음
		for(int i = tLength-1; i > sLength-1; i--) { // i는 t의 길이보다 하나 작으므로 i(=변경 후 tLength-1) > sLength-1
			char tmp = T.charAt(i);
			
			// 추가할 때 뒤집은 후 추가이므로 반대로 제거할 때에는 제거 후 뒤집어야함
			T = T.delete(i, tLength);
			if(tmp == 'B') {
				T = T.reverse();
			}
		}

		// S와 T가 같은지 비교
		if(S.toString().equals(T.toString())) answer = 1;
		
		System.out.println(answer);
		
	}
	
}