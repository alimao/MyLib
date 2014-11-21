package WYHPriorityQueue;

import java.util.Arrays;

public class YoungMatrix {

	final int MAX=Integer.MAX_VALUE;
	int[][] matrix;
	int rows;
	int cols;
	public YoungMatrix(int rows, int cols){
		matrix=new int[rows][cols];
		this.rows=rows;
		this.cols=cols;
		for (int i=0;i<rows;i++)
			Arrays.fill(matrix[i], MAX);
	}
	
	public boolean insert(int val){
		if (!(rows>0 && cols>0 && matrix[rows-1][cols-1]!=MAX)){
			return false;
		}
		matrix[rows-1][cols-1]=val;
		swim(rows-1,cols-1);
		return true;
	}

	public int extractMin(){
		if (matrix[0][0]==MAX){
			return MAX;
		}
		int ans=matrix[0][0];
		matrix[0][0]=MAX;
		drop(0,0);
		return ans;
	}
	
	public boolean exist(int val){
		int i=rows-1;
		int j=0;
		while(i>=0 && j<cols){
			if (matrix[i][j]>val){
				i--;
			}else if (matrix[i][j]<val){
				j++;
			}else{
				return true;
			}
		}
		return false;
	}
	
	private void drop(int i, int j) {
		int nextI=i+1;
		int nextJ=j;
		if (nextI>=rows || matrix[nextI][nextJ]>matrix[i][j+1]){
			nextI=i;
			nextJ=j+1;
			if (nextJ>=cols){
				return;
			}
		}
		if (matrix[i][j]<=matrix[nextI][nextJ]){
			return;
		}
		int temp=matrix[nextI][nextJ];
		matrix[nextI][nextJ]=matrix[i][j];
		matrix[i][j]=temp;
		drop(nextI,nextJ);
		
	}

	private void swim(int i, int j) {
		int nextI=i-1;
		int nextJ=j;
		if (nextI<0 || matrix[nextI][nextJ]<matrix[i][j-1]){
			nextI=i;
			nextJ=j-1;
			if (nextJ<0){
				return;
			}
		}
		if (matrix[i][j]>=matrix[nextI][nextJ]){
			return;
		}
		int temp=matrix[nextI][nextJ];
		matrix[nextI][nextJ]=matrix[i][j];
		matrix[i][j]=temp;
		swim(nextI,nextJ);
	}
	
	
	
	
}
