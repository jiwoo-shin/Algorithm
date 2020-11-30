package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
import common.Solution;

public class Baekjoon_12906 extends Solution {
	static int[][] move = {{0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}};
	static char[] compare = {'A', 'B', 'C'};
	
	@Override
	public void solution() throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n >> 음수는?
		StringTokenizer st;
		// boolean[][][] visited = new boolean[][][]; // 012: 3진수로 visited 표현 (0:A, 1: B, 2: C)// 2222222222 //이러면 최대로 C 가 10개일 때 3^10-1 까지 가능한데 [ 3^10-1][ 3^10-1][ 3^10-1] 은 가용 범위 밖이다.
		String[] init = {"", "", ""};
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if(num > 0) {
				String tmp = st.nextToken();
				for(int j = 0; j < num; j++) {
					init[i] += tmp.charAt(j);
				}
			}
		}
		if(!isEnd(init))System.out.println(move(init));
		else System.out.println(0);
		
		
	}
	static int move(String[] init) {
		int answer = 0;
		Queue<String[]> queue = new LinkedList<String[]>();//
		HashSet<String> visited = new HashSet<String>();
		queue.add(init);
		visited.add(init[0]+"."+init[1]+"."+init[2]);
		while(!queue.isEmpty()) {
			int size = queue.size();
			answer++;
			for(int t = 0; t < size; t++) {
				String[] tmp = queue.remove();
				for(int i = 0; i < 6; i++) {
					String[] next = {tmp[0], tmp[1], tmp[2]};
					if(next[move[i][0]].length() > 0 ) { // from 에 하나이상 있는 경우 옮긴다, to에 
						// move[i][0] -> move[i][1]
						int from_size = next[move[i][0]].length();
						next[move[i][1]] += next[move[i][0]].charAt(from_size-1);
						next[move[i][0]] = next[move[i][0]].substring(0, from_size-1);
						String next_tmp = next[0]+"."+next[1]+"."+next[2];
						if(isEnd(next)) {
							return answer;
						}
						if(!visited.contains(next_tmp)) {
							queue.add(next);
							visited.add(next_tmp);
						}
					}
				}
			}
		}
		return answer;
	}
	static boolean isEnd(String[] bars) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < bars[i].length(); j++)
				if(bars[i].charAt(j) != compare[i]) return false;
		}
		return true;
	}
	/*
	static int move(int[][] initial, char[][] initial_char, boolean[][][][] visited) {
		int answer = 0;
		Queue<char[][]> queue = new LinkedList<char[][]>();
		Queue<int[][]> queue_ = new LinkedList<int[][]>();
		queue.add(initial_char);
		queue_.add(initial);
		visited[0][initial[0][0]][initial[0][1]][initial[0][2]] = true;
		visited[1][initial[1][0]][initial[1][1]][initial[1][2]] = true;
		visited[2][initial[2][0]][initial[2][1]][initial[2][2]] = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			char[][] now = queue.remove();
			int[][] now_ = queue_.remove();
			answer++;
			for(int n = 0; n < size; n++) {
				for(int i = 0; i < 6; i++) {
					char[][] next = new char[][] {now[0], now[1], now[2]};
					int[][] next_ = new int[][] {now_[0], now_[1], now_[2]};
					for(int t = 0; t < 3; t++) {
						if(move[i][t] == -1) {
							next_[t][next[t][]]
						}
					}
					if(isOk(next[0]) && isOk(next[1]) && isOk(next[2]) && !visited[next[0]][next[1]][next[2]]) {
						if(next[0])
						queue.add(next);
						visited[next[0]][next[1]][next[2]] = true;
					}
				}
			}
		}
		
		return answer;
	}*/
	static boolean isOk(int n) {
		return n>=0 && n <= 10;
	}
}
