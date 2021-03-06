package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import common.Solution;

import java.io.InputStreamReader;

public class Baekjoon_14500 extends Solution {
	
	static int[][][] tetromino = {
			// |
			{{0, 0}, {1, 0}, {2, 0}, {3, 0}}, 
			{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, 
			
			 // 정사각형
			{{0, 0}, {1, 0}, {0, 1}, {1, 1}},
			
			// ㅜ //
			{{0, 0}, {0, 1}, {0, 2}, {-1, 1}}, 
			{{0, 0}, {0, 1}, {1, 1}, {0, 2}}, // 
			{{0, 0}, {1, 0}, {2, 0}, {1, 1}}, // 
			{{0, 0}, {1, 0}, {2, 0}, {1, -1}}, // 
			
			//ㄱ+ㄴ // 대칭필요
			{{0, 0}, {0, 1}, {1, 1}, {1, 2}}, 
			{{0, 0}, {0, 1}, {1, 0}, {-1, 1}}, // 
			// 대칭 (좌우)
			{{0, 0}, {0, -1}, {1, -1}, {1, -2}}, 
			{{0, 0}, {0, -1}, {1, 0}, {-1, -1}}, // 
			
			 // L // 대칭 필요
			{{0, 0}, {1, 0}, {2, 0}, {2, 1}},
			{{0, 0}, {0, 1}, {0, 2}, {1, 0}}, // 
			{{0, 0}, {0, 1}, {1, 1}, {2, 1}}, // 
			{{0, 0}, {0, 1}, {0, 2}, {-1, 2}}, // 
			// 대칭 (좌우)
			{{0, 0}, {1, 0}, {2, 0}, {2, -1}},
			{{0, 0}, {0, -1}, {0, -2}, {1, 0}}, // 
			{{0, 0}, {0, -1}, {1, -1}, {2, -1}}, // 
			{{0, 0}, {0, -1}, {0, -2}, {-1, -2}}, // 
	};

	static int getSum(int i, int j, int N, int M, int[][] numbers) {
		int answer = 0;
		int length = tetromino.length;
		for(int t = 0; t < length; t++) {
			int sum = 0;
			for(int k = 0; k < 4; k++) {
				int next_i = i + tetromino[t][k][0], next_j = j + tetromino[t][k][1];
				//if(i == 3 && j == 0 && t == 4) System.out.println(next_i+" "+next_j);
				if(next_i >=0 && next_j >= 0 && next_i < N && next_j < M) sum += numbers[next_i][next_j];
				else {
					sum = 0;
					break;
				}
			}
			//if(i == 3 && j == 0) System.out.println(sum);
			answer = Math.max(answer, sum);
		}
		return answer;
	}
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] numbers = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				numbers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				answer = Math.max(getSum(i, j, N, M, numbers), answer);
			}
		}
		System.out.println(answer);
		
		
	}
	static int arroundMax(int index_x, int index_y, int[][] array) {
		int max = 0;
		if(index_x-1 > 0)max = array[index_x-1][index_y];
		if(index_y-1 > 0)max = max < array[index_x][index_y-1]? array[index_x][index_y-1] : max;
		if(index_x+1 < array.length)max = max < array[index_x+1][index_y]? array[index_x+1][index_y] : max;
		if(index_y+1 < array.length)max = max < array[index_x][index_y+1]? array[index_x][index_y+1] : max;
		
		return max;
	}
	static int answer() throws IOException {
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pp = br.readLine();
		StringTokenizer st = new StringTokenizer(pp, " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] numbers = new int[N][M];
		
		for(int i = 0; i < N*M; i++) {
			pp = br.readLine();
			st = new StringTokenizer(pp, " ");
			numbers[i/M][i%M] = Integer.parseInt(st.nextToken()); //입력받음
			
		}

		int[][] arround = new int[16][3]; //주변값들
		int[][] exception = new int[4][3]; //값들
		int result = 0;
		
		
		for(int i = 0; i < N*M; i++) {
			int x = i/M;
			int y = i%M;
			
			int arround_i = 0;
			int arround_select = 0;
			
			//처음은 기본 설정
			exception[0][0] = numbers[x][y];
			exception[0][1] = x;
			exception[0][2] = y;
			int tmp_result = exception[0][0];
			//System.out.println(numbers[x][y]+" "+x+" "+y+" ");
			for(int exception_i = 1; exception_i < 4; exception_i++){//4개 선택될 때 까지반복
				boolean left = true, right = true, down = true, up = true;
				
				for(int p = 0; p < exception_i; p++) {//이미 선택된 방향은 제외, 이동 가능한지 여부 판별
					if(x-1 == exception[p][1] && y == exception[p][2] || (x <= 0))left = false;
					if(x+1 == exception[p][1] && y == exception[p][2] || (x >= N-1))right = false;
					if(x == exception[p][1] && y-1 == exception[p][2] || (y <= 0))up = false;
					if(x == exception[p][1] && y+1 == exception[p][2] || (y >= M-1))down = false;
				}
				//System.out.println(left+" "+right+" "+up+" "+down);
				// 주변 4개 최대값을구함
				if(left) { //x>0 &&left
					arround[arround_i][0] = numbers[x-1][y];
					arround[arround_i][1] = x-1;
					arround[arround_i][2] = y;
					if(exception[exception_i][0] < numbers[x-1][y]) {
						exception[exception_i][0] = numbers[x-1][y];
						exception[exception_i][1] = x-1;
						exception[exception_i][2] = y;
						arround_select = arround_i;
					}
					arround_i++;
				}
				if(up) {
					arround[arround_i][0] = numbers[x][y-1];
					arround[arround_i][1] = x;
					arround[arround_i][2] = y-1;
					if(exception[exception_i][0] < numbers[x][y-1]) {
						//exception[exception_i] = arround[arround_i];
						exception[exception_i][0] = numbers[x][y-1];
						exception[exception_i][1] = x;
						exception[exception_i][2] = y-1;
						arround_select = arround_i;
					}
					arround_i++;
				}
				if(right) {
					arround[arround_i][0] = numbers[x+1][y];
					arround[arround_i][1] = x+1;
					arround[arround_i][2] = y;
					if(exception[exception_i][0] < numbers[x+1][y]) {
						//exception[exception_i] = arround[arround_i];
						exception[exception_i][0] = numbers[x+1][y];
						exception[exception_i][1] = x+1;
						exception[exception_i][2] = y;
						arround_select = arround_i;
					}
					arround_i++;
				}
				if(down) { 
					arround[arround_i][0] = numbers[x][y+1];
					arround[arround_i][1] = x;
					arround[arround_i][2] = y+1;
					if(exception[exception_i][0] < numbers[x][y+1]) {
						//exception[exception_i] = arround[arround_i];
						exception[exception_i][0] = numbers[x][y+1];
						exception[exception_i][1] = x;
						exception[exception_i][2] = y+1;
						arround_select = arround_i;
					}
					arround_i++;
				}
				
				// 만약 주변 값이 이전에 선택된 값보다 같거나 크면 주변 값 바로 선택가능
				//현재 주변 값이 이전 선택값 보다 작은 경우 보다 작을 경우 arround 모두 비교해서 가장 큰 값 선택해야함.
				if(exception_i > 1 && exception[exception_i-1][0] > exception[exception_i][0]) {
					for(int q = 0; q < arround_i-1; q++) {  //마지막에 arround_i++ 하므로 arround_i-1 번째 까지
						if(exception[exception_i][0] < arround[q][0]) {
							exception[exception_i][0] = arround[q][0];
							exception[exception_i][1] = arround[q][1];
							exception[exception_i][2] = arround[q][2];
							arround_select = q;
						}
					}
				}
					
				//선택된 값 arround 에서 제거 필요
				arround[arround_select][0] = -1;
				
				x = exception[exception_i][1];
				y = exception[exception_i][2];
				
				tmp_result += exception[exception_i][0];
				//System.out.println(exception[exception_i][0]+" "+exception[exception_i][1]+" "+exception[exception_i][2]+" ");
			}
			if(tmp_result > result) result = tmp_result;

			for(int k = 0;k < 4; k++) {
				exception[k][0] = -1;
				exception[k][1] = -1;
				exception[k][2] = -1;
			}
			
			//System.out.println(result+"\n");
			
		}
		
		System.out.println(result);
		return result;
		
		
		/*//int[][] numbers = new int[N][M];
		int[] numbers = new int[N*M];
		int[] index = new int[N*M];
		index[0] = -1;
		
		int j = 0;
		int input;
		int max = 0;
		int result;
		
		for(int i = 0; i < N*M; i++) {
			input = sc.nextInt();
			numbers[i] = input;
			if(max < input) {
				max = input; //최대값을 찾음.
			}
		}
		
		
		for(int i = 0; i < N*M; i++) {
			if(numbers[i] == max) {
				index[j++] = i; //최대값의 위치를 저장함.
			}
		}

		int[][] arroud = new int[16][2]; //주변값들
		int[][] exception = new int[4][2]; //값들
		
		// 주변 4개 최대값을구함
		for(int k = 0; k < 4; k++) {
			if(k%M != 0) // 왼쪽 끝
				
			if((k+1)%M == 0) //오른쪽 끝
			if(k < M) //상단
			if(k > N*(M-1)) //하단
		}*/
	}
}
