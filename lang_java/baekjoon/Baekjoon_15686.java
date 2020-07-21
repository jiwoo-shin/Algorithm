package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15686 extends Solution {
	
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int house_num = 0; // 집의 개수
		int chicken_house_num = 0; //치킨집의 개수
		int[][] chicken_house_list = new int[13][2]; // 치킨집 (최대 13개)
		int[][] house_list = new int[100][2]; // 집 (최대 100개)
		int[][] distance_list; // 각 치킨집과 집과의 거리
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house_list[house_num++] = new int[]{i, j};
				} else if(map[i][j] == 2) {
					chicken_house_list[chicken_house_num++] = new int[]{i, j};
				}
			}
		}
		distance_list = getAllDistance(chicken_house_num, house_num, chicken_house_list, house_list);
		
		System.out.println(dfs(0, -1, M, new boolean[chicken_house_num], chicken_house_num, house_num, distance_list));
		
	}
	static int[][] getAllDistance(int chicken_house_num, int house_num, int[][] chicken_house_list, int[][] house_list) {
		int[][] distance_list = new int[chicken_house_num][house_num];
		
		for(int chicken_house = 0; chicken_house < chicken_house_num; chicken_house++) {
			for(int house = 0; house < house_num; house++) {
				distance_list[chicken_house][house] = 
						Math.abs(chicken_house_list[chicken_house][0]-house_list[house][0]) + 
						Math.abs(chicken_house_list[chicken_house][1]-house_list[house][1]);
			}
		}
		return distance_list;
	}
	static int dfs(int depth, int prev, int M, boolean[] visited, int chicken_house_num, int house_num, int[][] distance_list) {
		if(depth == M) return getDistance(visited, chicken_house_num, house_num, distance_list);
		//if(prev + M-depth > chicken_house_num) return Integer.MAX_VALUE;
		int answer = Integer.MAX_VALUE;
		for(int i = prev+1; i < chicken_house_num; i++) {
			visited[i] = true;
			answer = Math.min(answer, dfs(depth+1, i, M, visited, chicken_house_num, house_num, distance_list));
			visited[i] = false;
		}
		return answer; // prev + M-depth > chicken_house_num인 경우는 위 for문 안돌고 Max 반환하고 끝남
	}
	static int getDistance(boolean[] visited, int chicken_house_num, int house_num, int[][] distance_list) { 
		int distance = 0;
		for(int house = 0; house < house_num; house++) {
			int house_distance = Integer.MAX_VALUE;
			for(int chicken_house = 0; chicken_house < chicken_house_num; chicken_house++) {
				if(visited[chicken_house]) house_distance = Math.min(house_distance, distance_list[chicken_house][house]);
			}
			distance += house_distance;
		}
		return distance;
	}
	
}
