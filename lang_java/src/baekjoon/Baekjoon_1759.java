package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_1759 extends Solution {
	static char[] array;
	static int L; //비밀번호 길이
	static int C; // 입력받는 문자 길이
    static StringBuilder sb;
    //static int[] num;
	
	// s : 기본 문자열
	// index : 추가할 문자 위치
	// length : 현재까지 만들어진 문자열 길이
	static void password(char[] s, int length, int index) {
		if(length == L) {
			//num = new int[26];
			int vowel_num = 0;//모음의 개수
			// 비밀번호가 가능한지 확인
			for(int i = 0; i < length; i++) { 
				//num[s[i]-'a']++;
				if(s[i]== 'a' || s[i] =='i' || s[i] =='u' || s[i] =='o' || s[i] =='e')vowel_num++;
			}
			//int vowel_num = num[0] + num[4] + num[8] + num[14] + num[20]; //모음의 개수
			if(vowel_num >=1 && length-vowel_num >= 2) //비밀번호가 가능한 경우 (모음의 개수가 1개 이상이고 자음은 2개 이상인 경우)
				sb.append(s).append("\n");
		}
		else {
			for(int i = index+1;i < C; i++) { //visited 가 없어도 되는 이유는 for문에서 이전 입력 이후로 추가하기 때문에 중복이 없음 -> 이게 가능한 이유는 항상 증가하기 때문, 
				
				s[length] = array[i];//s = s.substring(0, length)+array[i]; 
				password(s, length+1, i);
				// 기본 dfs와 다름 .. dfs : 2,1,3과 3,1,2 를 다르게 본다. 9
				// 암호 : 2,1,3과 3, 1,2 같음
				/*if(!visited[i]) {
					//s += array[i]; > 이러면 다음 for문에 영향을 준다.
					if(length > 1)s = s.substring(0, length)+array[i]; 
					else s = s+array[i];
					password(s, length+1, i);
					//visited[i] = false;
				}*/
			}
		}
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		array = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<C;i++) {
			array[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(array);
		char[] start = new char[L];
		password(start,0, -1);
		
		System.out.println(sb.toString());
	}
}
/*package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_1759 extends Solution{
	static String[] array;
	static int L; //비밀번호 길이
	static int C; // 입력받는 문자 길이
	static String answer = "";
	static boolean[] visited;
	
	// s : 기본 문자열
	// index : 추가할 문자 위치
	// length : 현재까지 만들어진 문자열 길이
	static void password(String s, int index, int length) {/*
		String answer="";
		System.out.println("test"+" "+index+" len "+length+" s="+s+" L = "+L+" C = "+C);
		if(length == L ) {
			visited[index] = false;
			return s+"\n";
		} else {
			for(int i = 0; i< C; i++) { // ->중복되는것은 없다!!!
				System.out.println("answer : "+answer+" answer/////"+length+" "+i);
				if(!visited[i])answer += password(s+array[i], i, length+1); //??
				visited[i] = true;
			}
		}
		return answer;///////
		
		//visited[index] = true; 
		//String answer = s + array[index];
		visited[index] = true; 
		System.out.println("test"+" "+index+"  s="+s+" ");
		if(length == L ) {
			System.out.println("return  "+s);
			visited[index] =false;
			answer+=s+"\n";
		} else {
			for(int i = 0; i< C; i++) { 
				System.out.println("answer : "+answer+" answer/////"+length+" "+i);
				if(!visited[i]) password(s+array[i], i, length+1);//if(!visited[i])answer += password(answer, i, length+1); //??
				//visited[i] = false; // password 를 끝까지 해서 끝내고 오면 한 패스워드에 대한 것은 끝 -> 이러면 이전 재귀함수에서 visited = true로 인해 방문하지 않은것도 방문하세된다.
			}
		}
		System.out.println("visited["+index+"] false");
		visited[index] =false;
	}
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		L = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		array = br.readLine().split(" ");
		visited = new boolean[C];
		password(array[0], 0, 1);
		for(int i = 0;i<C;i++) {
			password(array[i], 0, 1);
		}
		System.out.println(answer);
	}
}

*/
