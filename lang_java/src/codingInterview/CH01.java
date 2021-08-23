package codingInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class CH01 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(q1(br.readLine()));
	}

	// ���� �ߺ� ���� Ȯ��
	static boolean q1(String str) {
		boolean[] use = new boolean[128]; // ascii �ڵ�� 1����Ʈ(8bit)�� ���ڸ� ǥ����
		if (str.length() > 128)
			return true; // �ƽ�Ű �ڵ尡 128�����ε� ���ڿ��� 128���� �� ��� ������ �� �� �ۿ� ����.
		for (int i = 0; i < str.length(); i++) {
			if (use[str.charAt(i)])
				return true;
			use[str.charAt(i)] = true;
		}
		return false;
	}
}
