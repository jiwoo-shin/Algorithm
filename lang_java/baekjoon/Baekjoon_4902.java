package lang_java.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_4902 extends Solution {
	
    static int[] sum;
	
	@Override
	public void solution() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int line = Integer.parseInt(st.nextToken());
		int[] triangles;
		int case_num = 1;
		StringBuilder sb = new StringBuilder();
		while(line != 0) {
			int total = getLinetotal(line);
			triangles = new int[total];
			sum = new int[total];
			triangles[0] = Integer.parseInt(st.nextToken());
			sum[0] = triangles[0];
			for(int i = 1; i < total; i++) {
				triangles[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + triangles[i];
			}
			sb.append((case_num++)+". "+getMax(line, total, triangles)).append("\n");
			st = new StringTokenizer(br.readLine());
			line = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb.toString());
	}
	static int getMax(int line, int total, int[] triangles) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < total; i++) { // 모든 삼각형들에 대해 가능한 걸 해봄
			int step = getStep(i);
			int ableN = ableLine(i, line, step); // i 에서 가능한 최대 N
			int modify = i%2 == step%2?1: -1; // 역뱡향인지 정방향인지
			max = Math.max(max, getMaxTriangle(i, step, modify, ableN, triangles)); // 최대 N 인 경우 한번만 호출한다. getMaxTriangle 함수 내에서 ableN을 구할 떄 ableN보다 작은것들도 포함하여 구하기 때문에
		}
		return max;
	}
	static int ableLine(int i, int line, int step) { // 삼각형 i를 최상단 꼭지점이라 하였을 때 가능한 N의 최대를 구함
		// i : 현재 삼각형 위치, line: 전체 삼각형의 line 수(1~), step: 현재 i가 몇번째 줄에 있는지 (0~)
		if(i%2 == step%2) { // 정삼각형인 경우
			return line - step;
		} else { // 역삼각형인 경우
			int position = (i- getLinetotal(step))/2 + 1; // i 가 step 위치에서 몇번째에 있는지 (단, 역삼각형만 고려한 위치) (1~)
			int max_triangle = (step+1)/2; // step번째 줄(0~) 에서 최대한 크게 만들 수 있는 역삼각형
			if(step%2 == 0 && position > max_triangle) return max_triangle-(Math.abs(max_triangle-position)-1);
			else return max_triangle-Math.abs(max_triangle-position);
		}
	}
	static int getMaxTriangle(int i, int step, int modify, int ableN, int[] triangles) { // i : 현재 위치 삼각형, step: i가 몇번째 줄에 있는지 ,modify : 현재 줄을 모두 더하였을 때 다음줄로 어떻게 이동하는지, ableN : 가능한 최대 N
		int max = Integer.MIN_VALUE;
		int tmpsum = 0;
        int left = i;
        int right = i;
		for(int N = 1; N <= ableN; N++) { // 각 줄에 대해 구함
			tmpsum += sum[right] - sum[left] + triangles[left]; // 총 개수가 최대 16만개이므로 -> 전부 탐색하기에는 너무 많음.. 모든 triangles를 탐색하지 않고 구해두었던 합을 이용한다.	
            if(modify == 1) { // 정삼각형
                left = left + getStepTotal(step+N-1); // 아래줄의 왼쪽
                right = left + getStepTotal(N-1) +1; // 아래줄의 오른쪽
			} else { // 역삼각형인 경우
                right = right - getStepTotal(step-N); // 역삼각형은 거꾸로 올라감. 윗줄의 왼쪽
                left = right - getStepTotal(N)+1; // 윗줄의 오른쪽
			}
            
			max = Math.max(max, tmpsum); // 작은 N인 경우도 비교해보아야함
		}
		
		return max;
	}
	static int getLinetotal(int line) { // line줄 (1~) 있는 경우 삼각형의 개수
		return line*line;
	}
	static int getStepTotal(int step) { // step 번째 줄(0~)의 삼각형의 개수
		return 2*step+1;
	}
	static int getStep(int i) {
		return (int)Math.sqrt(i);
	}
}
