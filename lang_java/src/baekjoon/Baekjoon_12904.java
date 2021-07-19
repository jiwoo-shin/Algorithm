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
		
		// T�� S���̷� �����Ѵ�. �ڿ����� ���ĺ��� ������ �� �����Ƿ� �ڿ� ���� ���ĺ��� ���� T�� ������� �ϴ���, �����ϰ� �����ϸ� �Ǵ����� ����������
		for(int i = tLength-1; i > sLength-1; i--) { // i�� t�� ���̺��� �ϳ� �����Ƿ� i(=���� �� tLength-1) > sLength-1
			char tmp = T.charAt(i);
			
			// �߰��� �� ������ �� �߰��̹Ƿ� �ݴ�� ������ ������ ���� �� ���������
			T = T.delete(i, tLength);
			if(tmp == 'B') {
				T = T.reverse();
			}
		}

		// S�� T�� ������ ��
		if(S.toString().equals(T.toString())) answer = 1;
		
		System.out.println(answer);
		
	}
	
}