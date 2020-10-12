package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import common.Solution;

public class Baekjoon_2529 extends Solution {

	static StringBuilder answer;
	static boolean isEnd;
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
    	int length = k+1;
    	int[] min_numbers = new int[k+1];
    	int[] max_numbers = new int[k+1];
    	char[] inequalitys = br.readLine().replaceAll(" ", "").toCharArray();
    	for(int i = 0; i < k+1; i++) {
    		max_numbers[i] = 9-i;// max는 9~ k+1개 숫자로 만든다 -> 큰 숫자부터 가능한지 따진다.
    		min_numbers[i] = i;// min은 0~ k+1개 숫자로 만든다 -> 작은숫자부터 가능한지 따진다.
    	}
    	isEnd = false;
    	answer = new StringBuilder();
    	getNumbers(length, 0, max_numbers, new boolean[10], inequalitys, new int[k+1]);
    	answer.append("\n");
    	isEnd = false;
    	getNumbers(length, 0, min_numbers, new boolean[10], inequalitys, new int[k+1]);
    	System.out.println(answer);
    	
	}
	static void getNumbers (int length, int depth, int[] numbers, boolean[] visited, char[] inequalitys, int[] arrays) {
		if(depth == length) {
			for(int i = 0; i < length; i++) {
				answer.append(arrays[i]);
			}
			isEnd = true; // 만족하는 가장 큰/작은 숫자를 구하면 되므로 처음 한번 만족하는 배열이 나온 경우 최적의 경우이다. (max는 큰수부터, min은 작은수부터했으므로)
			return;
		} else if(!isEnd) {// isEnd 가 아닌 경우 
			for(int i = 0; i < length; i++) { 
				int next_number = numbers[i]; // 다음에 들어갈 숫자
                
                // 다음에 들어갈 숫자가 방문한적이 없는 숫자이고 현재 숫자 다음 올 수 있는 숫자라면
				if(depth == 0 || (!visited[next_number] && isAble(arrays[depth-1], next_number, inequalitys[depth-1]))) {
					visited[next_number] = true; // 방문
					arrays[depth] = next_number; //
					getNumbers(length, depth+1, numbers, visited, inequalitys, arrays);
					visited[next_number] = false;
				}
			}
		}
		
	}
	static boolean isAble(int present, int next, char inequality) { // present 다음에 next 숫자가 올 수 있는지 판별
		if(inequality == '<') return present < next;
		else return present > next;
	}
	
}