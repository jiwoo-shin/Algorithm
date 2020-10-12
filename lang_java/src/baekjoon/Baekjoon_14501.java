package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import common.Solution;

public class Baekjoon_14501 extends Solution {
	static int[][] array;
	static int N;
    
	//profit은 today일에 상담을 하거나/ 하지 않거나 하였을 때의 이익을 계산하는 함수 > 따라서 next_day ==N 이거나 >N인 경우도 today가 포함되는지 안되는지 확인할 필요가 있음.
	static int profit(int today) { 
        //기본적으로 today 에 상담한 경우와 상담하지 않은 경우 중 이익이 최대가 되는 경우를 고름
		
		if(today >= N) return 0; // 상담 불가! ( ==이 아닌 >=로 비교하는 이유는 today에 다음 상담 가능한 일이 들어오는데 이게 항상 n에 맞춰서 나오는게 아니니까.)
		
		int next_day = today + array[today][0]; //next_day : today에 상담하였을 경우 상담 가능한 다음 날
		
		// next_day == N 인 경우는 today에 상담은 가능하지만 next_day에는 상담불가.. (today는 N-1 까지 가능하므로.(길이 N 배열의 인덱스니까.))
		if(next_day == N) {
			return Math.max(array[today][1], //today에 상담 가능하지만 그 다음은 상담 불가
					profit(today+1)); //today 에 상담 안하는 경우도 있음
		}else if(next_day > N) { // 상담 가능한 다음날이 N을 초과한 경우는 today 에 상담 불가능하다. 퇴사 이후까지 나와서 상담할수는 없으므로
			return profit(today+1); //today 에 상담 안하는 경우만 계산
        } else { //today, next_day 모두 상담 가능한 경우
        	return Math.max(
    				array[today][1] + profit(next_day), //today 에 상담한 경우(today 상담으로 인한 이득 + next_day 상담으로 인한 이득 ) //array[index][1]  값이 나오기 때문에 저 위에서 return이 뿐이라고 계속 0만 나오는게 아님..
    				profit(today+1)); //today 에 상담하지 않은 경우.. 가능한 next_day는 today+1 이다.
        }
	}
    
	@Override
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			array = new int[N][2];
			for(int i = 0; i<N;i++) {
				st = new StringTokenizer(br.readLine());
				array[i][0] = Integer.parseInt(st.nextToken()); //상담이 며칠 걸리는지
				array[i][1] = Integer.parseInt(st.nextToken()); //상담 후 얻는 이익
			}
			System.out.println(profit(0));
		}
	}
}
