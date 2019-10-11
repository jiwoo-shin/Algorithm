package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1182 extends Solution {
	static int[] array;
	
	// 원소를 넣거나 빼거나 하여 
    static int partitial(int goal, int index, int current, int length, int limit) { //goal:  목표로하는 숫자, index : 현재  포함할지 말지 고민중인 숫자 위치, current : 여태까지의 합, length : 원소에서 넣을지 말지 어디까지 비교했는지, limit : 가능한 index
    	//goal == current로 답 여부 구하면 안됨. 원소가 0일수도 있고 원소가 음수인 경우도 있어서 goal == current라도 끝가지 들어가는지, 들어가지 않는지 여부 판단 후 확인 필요함.
    	if(length == limit) { // 답을 구한 경우 (length == limit)  비교가 필요한게 원소가 0일수도 있으므로 모든 원소에 대해비교가 필요, goal이 되는 순간 그 이후 비교를 안하는게 아님 0이 있을수도 있으므로
    		if(goal == current) return 1; //if(length == limit && goal == current) return 1; 보다 빠르다. 모두 구한 경우만 goal==current인지 확인하면 되니까.
    	} 
    	else if(index < limit) { // 계산이 필요한 경우
    		///int answer = 0;
    		//answer += partitial(goal, index+1, current+array[index], length+1, limit); //index 숫자 포함한 경우
    		//answer += partitial(goal, index+1, current, length+1, limit); //index 숫자 포함하지 않은 경우. 여기도 length를 더하는이유는 length가 현재까지 선택된원소의 개수가 아니라 비교한 원소의 개수이기 때문
    		return partitial(goal, index+1, current+array[index], length+1, limit) + partitial(goal, index+1, current, length+1, limit);
    	} 
    	return 0; // goal > current 인 경우는 불가능한 경우가 아님 원소가 음수일수도 있으므로
    }
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		array = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N;i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		int answer = partitial(S, 0, 0, 0, N);
		if(S == 0)answer--; //공집합은 제외
		System.out.println(answer);
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
