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
		//System.out.println(q4(br.readLine()));
		// System.out.println(q5(br.readLine(), br.readLine()));
		System.out.println(q6(br.readLine()));
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
	
	// ���� ���� Ȯ��
	static boolean q2(String str1, String str2) {
		if(str1.length() != str2.length()) return false;
		int[] str1Use = new int[128], str2Use = new int[128]; //
		// �̷��� ���ϰ� str1 ��븸 ����, Ȯ�� �ÿ� str2 ���ڸ� ���� ������ Ȯ�� �����ϴ�.
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
	
	// ���ڿ� ���� ����
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
	
	// ȸ�� ���� ���� �˻� (ȸ�� ���� �ƴ�!!!)
	static boolean q4(String str) {
		int length = str.length();
		int oddNumber = 0;
		int[] alphabet = new int[26];
		for(int i = 0; i < length; i++) {
			int tmp = str.charAt(i) - 'a';
			alphabet[tmp]++;
			if(alphabet[tmp]%2 != 0) oddNumber++; // Ȧ�� ���θ� �ľ��ϸ� �ǹǷ� ��Ʈ ���굵 �����ϴ�.
			else oddNumber--;
		}
		return length%2 == oddNumber;
	}
	
	// 1ȸ ���������� str1 -> str2 ���� ���� �� �ִ���
	static boolean q5(String str1, String str2) {
		int length1 = str1.length(), length2 = str2.length();
		if(Math.abs(length1 - length2) > 1) return false;
		int add1 = length1 >= length2 ? 1 : 0, add2 = length2 >= length1 ? 1 : 0;
		int index1 = 0, index2 = 0;
		int diff = 0;
		while(index1 < length1 && index2 < length2) {
			if(str1.charAt(index1) != str2.charAt(index2)) {
				index1 += add1;
				index2 += add2;
				diff++;
			} else {
				index1++;
				index2++;
			}
			if(diff >= 2) return false; 
		}
		return true;
	}
	
	// ���ڿ� ����
	static String q6(String str) {
		StringBuilder sb = new StringBuilder();
		int sameIndex = 1;
		
		/*int index = 0;
		 * while(index < str.length()) {
			char tmp = str.charAt(index);
			if(sb.charAt(sb.length() -1) != tmp) {
				sameIndex = 0;
				sb.append(tmp);
				do {
					index++;
					sameIndex++;
				} while (index < str.length() && str.charAt(index) == tmp);
				sb.append(sameIndex);
			}
		}
		sb.replace(0, 1, "");*/
		
		for(int i = 0; i < str.length() - 1; i++) {
			if(str.charAt(i) != str.charAt(i+1)) {
				sb.append(str.charAt(i)).append(sameIndex);
				sameIndex = 1;
			} else {
				sameIndex++;
			}
		}
		sb.append(str.charAt(str.length()-1)).append(sameIndex);
		// System.out.println(sb.toString()+" "+str);
		if(sb.length() > str.length()) return str;
		return sb.toString();
	}
}
