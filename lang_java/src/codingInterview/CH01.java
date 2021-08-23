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

	// 문자 중복 여부 확인
	static boolean q1(String str) {
		boolean[] use = new boolean[128]; // ascii 코드는 1바이트(8bit)로 문자를 표현함
		if (str.length() > 128)
			return true; // 아스키 코드가 128가지인데 문자열이 128보다 길 경우 증복이 될 수 밖에 없음.
		for (int i = 0; i < str.length(); i++) {
			if (use[str.charAt(i)])
				return true;
			use[str.charAt(i)] = true;
		}
		return false;
	}
}
