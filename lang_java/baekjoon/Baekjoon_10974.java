package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon_10974 extends Solution {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] array;
	static boolean[] visited;
	
	
	/* DFS를 사용하지 않고 change_index 를 바꾸면서 알맞게 정렬하는 방식
	 static void change (int change_index) {
		
		for(int k = 0; k < N-1 - change_index ; k++ ) {//k는 그저 횟수 (change_index에 대해 N-1 - change_index 번 반복 필요)
			if(change_index < N-2 )change(change_index+1); //change_index는 N-2 까지 가능하므로.
			int change = array[change_index];
			
			//change_index와 어디가 바뀌어야하 하는지 확인
			for(int i = N-1; i >= change_index+1; i-- ) {
				if(array[i] > change) { 
					array[change_index] = array[i]; 
					array[i] = change;
					
					int sort_limit = (N-change_index-2)/2;//((N-1)-(change_index+1))/2; 
					
					//숫자를 바꾼다. 
					for(int j = 0; j <= sort_limit; j++) {
						int tmp = array[change_index+1+j];
						array[change_index+1+j] = array[N-1-j];
						array[N-1-j] = tmp;
					}
					
					break;
				}
			}
			for(int i = 0; i < N; i++) {
				sb.append(array[i]).append(' ');
			}
			sb.append('\n');
		}
		if(change_index < N-2) change(change_index+1); // 마지막으로 바뀌는 자리수가 올라가기 전에 이전거 한번 더 해줘야한다.
	}*/

	static void change(int depth) {
		// depth(int) : 몇번째 깊이인지, N 번째일 경우 마지막
		// array(int[]) : 순열
		// visited(int[]) : 방문여부를 확인하기 위한 배열
		
		if(depth == N) { //depth == N 일 경우 모든 정점 탐색 완료, 더이상 탐색할 필요는 없음
			for(int i = 0; i < N; i++) {
				sb.append(array[i]).append(' ');
			}
			sb.append('\n');
			//System.out.println(sb.toString()); //꼭 여러줄을 노나서 출력할 필요는 없음
			
		} else {
			//depth : 몇번째로 방문한 정점인지
			//i : 실제로 방문하는 정점
			for(int i = 0; i < N; i++) { //모든 정점에 대해 확인 필요
				if(!visited[i]) { //(i+1)을 방문을 하지 않은 정점인 경우
					array[depth] = (i+1); //순열은1 ~ N이므로 i+1을 한다. //array[i]가 아님. change(depth,..)이므로 depth번째에는 어느 정점이올것인지 계산 필요. 따라서 array[depth]이다.
					visited[i] = true; //visited는 depth가 아님. 실제 방문한 정점은 i+1임.depth가아니고.
					change(depth+1); //depth를 1더한 후 다시 확인.
					visited[i] = false; //반복문이 다 돌면 아예 다른 경로가 되므로 viseted는 초기화 해줘야험, DFS라서 반복문은 다른 경로, change -> change(depth+1)이 같은 경로이다.
				}
			}
			
			
		}
	}
	
	@Override
	public void solution() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		
		/*// DFS 안쓰는 방식
		 * for(int i = 0; i<N;i++) {
			array[i] = i+1;
			sb.append(array[i]).append(' ');
		}
		sb.append('\n');
		
		// 모든 자리수에 대해 돌아가면서 change를 가짐.
		change(0);*/
		
		
		visited = new boolean[N];
		change(0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
