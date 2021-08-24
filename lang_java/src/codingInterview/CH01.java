package codingInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import common.Solution;

public class CH01 extends Solution {

	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// System.out.println(q1(br.readLine()));
		//System.out.println(q2(br.readLine(), br.readLine()));
		//String tmp = br.readLine();
		//System.out.println(q3(Arrays.copyOf(tmp.toCharArray(), 3*tmp.length()), tmp.length()));
		System.out.println(q4(br.readLine()));
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
	
	// 순열 여부 확인
	static boolean q2(String str1, String str2) {
		if(str1.length() != str2.length()) return false;
		int[] str1Use = new int[128], str2Use = new int[128]; //
		// 이렇게 안하고 str1 사용만 저장, 확인 시에 str2 문자를 빼는 것으로 확인 가능하다.
		for(int i = 0; i < str1.length(); i++) {
			str1Use[str1.charAt(i)]++;
			str2Use[str2.charAt(i)]++;
		}
		for(int i = 0; i < str1.length(); i++) {
			int tmp = str1.charAt(i);
			if(str1Use[tmp] != str2Use[tmp]) return false;
		}
		return true;
	}
	
	// 문자열 공백 변경
	static char[] q3(char[] chars, int length) {
		int spaceCount = 0;
		for(int i = 0; i < length; i++) {
			if(chars[i] == ' ') spaceCount++;
		}
		int index = length + spaceCount*2 - 1;
		for(int i = length-1; i >= 0; i--) {
			if(chars[i] == ' ') {
				chars[index--] = '0';
				chars[index--] = '2';
				chars[index--] = '%';
			} else {
				chars[index--] = chars[i];
			}
			
		}
		return chars;
	}
	
	// 회문 순열 여부 검사 (회문 여부 아님!!!)
	static boolean q4(String str) {
		int length = str.length();
		int oddNumber = 0;
		int[] alphabet = new int[26];
		for(int i = 0; i < length; i++) {
			int tmp = str.charAt(i) - 'a';
			alphabet[tmp]++;
			if(alphabet[tmp]%2 != 0) oddNumber++; // 홀수 여부만 파악하면 되므로 비트 연산도 가능하다.
			else oddNumber--;
		}
		return length%2 == oddNumber;
	}
}
