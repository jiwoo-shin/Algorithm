package codingInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import common.Solution;

public class CH03 extends Solution {

	@Override
	public void solution() throws IOException {
		int[][] data = {{1,2,3}, {4,5,6,3,4}, {1}};
		q1(data);
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	}
	static void q1(int[][] data) {
		class MultipleStack {
			int[] stack; // 스택 여러개를 하나의 배열로
			int[] stackIndex;
			public MultipleStack(int[][] data) {
				int numStack = data.length;
				int maxLength = 0;
				for(int i = 0; i < numStack; i++) {
					if(maxLength < data[i].length) maxLength = data[i].length;
				}
				this.stack = new int[maxLength*numStack];
				this.stackIndex = new int[numStack];
				for(int i = 0; i < this.stack.length; i++) { // 스택의 인덱스
					for(int j = 0; j < numStack; j++) { // 몇번째 배열인지
						if(data[j].length > i) {
							this.stack[numStack*i + j] = data[j][i];
							this.stackIndex[j]++;
						}
					}
				}
			}
			public int pop(int numStack) {
				if(this.isEmpty(numStack)) return Integer.MIN_VALUE;
				int data = this.stack[getLastIndex(numStack)];
				this.stackIndex[numStack]--;
				return data;
			}
			public void push(int numStack, int item) {
				this.stackIndex[numStack]++;
				System.out.println("push");
				if(this.stack.length <= getLastIndex(numStack)) {
					this.stack = Arrays.copyOf(this.stack, this.stack.length*2);
				}
				this.stack[getLastIndex(numStack)] = item;
			}
			public int peek(int numStack) {
				if(this.isEmpty(numStack)) return Integer.MIN_VALUE;
				return this.stack[getLastIndex(numStack)];
			}
			public boolean isEmpty(int numStack) {
				return this.stackIndex[numStack] == 0;
			}
			int getLastIndex(int numStack) {
				if(numStack >= this.stackIndex.length) return -1;
				int stackIndex = this.stackIndex[numStack] - 1; // 해당 스택만 세었을 때 인덱스가 몇번째 까지 있는지
				return stackIndex*this.stackIndex.length + numStack;
			}
			public void print() {
				int numStack = this.stackIndex.length;
				
				for(int i = 0; i < numStack; i++) {
					System.out.print("stack "+i+" : ");
					for(int j = i; j < this.stackIndex[i]*numStack; j += numStack) {
						System.out.print(this.stack[j]+" ");
					}
					System.out.println();
				}
			}
		}
		MultipleStack ms = new MultipleStack(data);
		ms.print();
		System.out.println(ms.pop(1));
		System.out.println(ms.pop(2));
		ms.push(2,2);
		ms.push(2,3);
		ms.push(2,3);
		ms.push(2,3);
		ms.push(2,3);
		ms.push(2,3);
		ms.push(2,3);
		ms.push(2,3);
		ms.push(2,3);
		ms.print();
	}
}
