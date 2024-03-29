package codingInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

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
		// System.out.println(q6(br.readLine()));
		// print(q7(genMatrix(Integer.parseInt(br.readLine()))));
		// print(q8(genMatrix(Integer.parseInt(br.readLine()))));
		System.out.println(q9(br.readLine(), br.readLine()));
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
	
	// 1회 편집만으로 str1 -> str2 으로 만들 수 있는지
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
	
	// 문자열 압축
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
	
	// 행렬 회전 (대각선 대칭이동이 아님..)
	static int[][] q7(int[][] matrix) { 
		print(matrix);
		int Maxdepth = matrix.length/2;
		int n = matrix.length-1;
		for(int depth = 0; depth < Maxdepth; depth++) {
			int length = n - depth;
			for(int index = depth; index < length; index++) {
				int tmp = matrix[depth][index];
				matrix[depth][index] = matrix[n-index][depth];
				matrix[n-index][depth] = matrix[n-depth][n-index];
				matrix[n-depth][n-index] = matrix[index][n-depth];
				matrix[index][n-depth] = tmp;
			}
		}
		return matrix;
	}
	
	// 원소 0, 행, 렬 모두 0으로 만들기
	static int[][] q8(int[][] matrix) {
		print(matrix);
		int[][] position = new int[matrix.length*matrix.length][2];
		int positionIndex = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++)
				if(matrix[i][j] == 0) position[positionIndex++] = new int[] {i, j};
		}
		for(int i = 0; i < positionIndex; i++) {
			int indexI = position[i][0], indexJ = position[i][1];
			for(int l = 0; l < matrix.length; l++) {
				matrix[indexI][l] = 0;
				matrix[l][indexJ] = 0;
			}
		}
		return matrix;
	}
	static int[][] genMatrix(int n) {
		int[][] matrix = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++)
				matrix[i][j] = (int) (Math.random()*10);
		}
		return matrix;
	}
	
	// str1이 str2를 회전으로 만들 수 있는 지 확인 (isSubString은 1회만 호출)
	static boolean q9(String str1, String str2) {
		// 이렇게 안하고 두번 붙여서 subString 확인만해도 된다..
		if(str1.length() != str2.length()) return false;
		char firstChar = str1.charAt(0);
		int start = str2.indexOf(firstChar);
		if(start == 0) return false;
		int end = start;
		for(int i = 1; i < str2.length(); i++) {
			if(str1.charAt(i) != str2.charAt((start+i) % str2.length())) {
				end = start + i - 1;
				break;
			}
		}
		String compare = str2.substring(end, str2.length()) + str2.substring(0, start);
		return isSubString(str1, compare);
	}
	static boolean isSubString(String totalString, String subString) {
		return totalString.contains(subString);
	}
}
