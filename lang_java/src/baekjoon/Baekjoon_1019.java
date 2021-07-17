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
		int unnecessaryZero = 0; // �ش� �ڸ������� ���ʿ��ϰ� ������ 0�� ����..
		// 00019 ����.. �̰� 19�� �� �ڸ����� 1�� 9�� ���� �������°��� �������� 0�� ���ʿ��ϰ� ������. 
		// 19�� 19�ε� 00019�� �Ǿ 0�� �׸�ŭ �� ������.
		
		for(int n = N, i = 1; n > 0; n /= 10, i*= 10) { // ���� N�� 1�� �ڸ����� ���ڸ��� ����. �ش� �ڸ����� �� ���ڰ� ��� �� �� �ִ��� ����Ѵ�. (k��° �ڸ���)
			int tmp = n % 10; // ���� N���� ���� ���� �ڸ����� �ִ� ����
			// N : �� ����, i : ���� ���� �ڸ����� 10�� ������� , n: ���� ���� �ڸ������� �ڸ� ����
			
			int add = (n/10 + 1)*i; // tmp���� ���� ������ ��� k��° �ڸ����� �� �� ������  
			// (n/10 + 1) : tmp ���� ���� ���ڵ��� ������ ����� ��-> 0~n/10���� �����ϹǷ�, 
			// i : tmp ���� ���� ���ڵ��� ������ ����� �� -> ���� ���ڴ� 0-9���� ��� �����ϹǷ� i �� ����
			
			
			int add2 = (n/10)*i; // tmp���� ū ������ ��� k��° �ڸ����� �� �� ������ > 
			// (n/10) : tmp ���� ũ�Ƿ� 0~n/10-1 ������ �����ϴ�. k ��° �ڸ����� n/10�� ���� ���� N ���� Ŀ���Ƿ� �Ұ�
			// i : tmp ���� ���� ���ڵ��� ������ ����� �� -> ���� ���ڷ� ���� ���ڰ� N���� ���� Ŀ���� �����Ƿ� ���� ���ڴ� 0-9���� ��� �����ϹǷ� i �� ����
			
			int add3 = N%i + 1; // ���� tmp�� k��° �ڸ����� ��� ������, �� �̶� k ���� ���� ���ڴ� (n/10)���� ����. n/10���� ���� ���� add2���� ������
			// tmp�� ���� ���ڰ� ���� Ŀ����, tmp ���� ���ڰ� 0-9���� ��� �����Ѱ� �ƴ϶� 0~N%i��ŭ�� �����ϹǷ�
			
			
			for(int j = 0; j < tmp; j++) {
				answer[j] += add;
			}
			for(int j = tmp; j < 10; j++) {
				answer[j] += add2;
			}
			answer[tmp] += add3;
			
			unnecessaryZero += i; // k ��° �ڸ����� k ���� ���� 0 ����, k ������ ���ڰ� ��� ������ 
			//  ����  0 ���� : 00019���� �տ� 0���� ������ ���� , ������ ���ڰ� ��� ������ : k �ڸ������� ���ʿ��ϰ� ������ 0�� �������� ���ؾ��ϹǷ�. �ٸ� �ڸ����� for�� ���鼭 ����
		}
		
		answer[0] -= unnecessaryZero;
		
		for(int i = 0; i < 10; i++) {
			System.out.print(answer[i]+" ");
		}
	}
	
}