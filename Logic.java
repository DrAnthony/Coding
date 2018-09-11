/*
 * ������߼�������
 */
package LG;

import java.util.Random;

public class Logic {
	
	/*
	 * Ϊ�����жϱ߽����������߼������ά����ʹ�ñȻ�ͼ���߼�����row+2,column+2������
	 * �˷������ڽ���ͼ����ת��Ϊ��Ӧ���߼���������
	 */
	public int[][] setMap(int[][] m,int r,int c) {
		int[][] temp=new int[r+2][c+2];
		for(int i=0;i<r+2;i++) {
			for(int j=0;j<c+2;j++) {
				if(i==0||i==r+1||j==0||j==c+1)
					temp[i][j]=0;
				else if(m[i-1][j-1]==1)
					temp[i][j]=1;
				else
					temp[i][j]=0;
			}
		}
		return temp;
	}
	//�������Map
	public int[][] random(int[][] m,int r,int c){
		Random random=new Random();
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				m[i][j]=random.nextInt(2);
			}
		}
		
		int[][] temp=new int[r+2][c+2];
		for(int i=0;i<r+2;i++) {
			for(int j=0;j<c+2;j++) {
				if(i==0||i==r+1||j==0||j==c+1)
					temp[i][j]=0;
				else if(m[i-1][j-1]==1)
					temp[i][j]=1;
				else
					temp[i][j]=0;
			}
		}
		return temp;
	}
	
	/*
	 * �������й����е���Ҫ�㷨�������жϸ�ϸ�����������洢��Ϣ
	 */
	public int[][] run(int[][] m,int row,int col){
		int[][] temp=new int[row][col];
		int mount=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(m[i][j]==1)
					mount++;
				if(m[i][j+1]==1)
					mount++;
				if(m[i][j+2]==1)
					mount++;
				if(m[i+1][j]==1)
					mount++;
				if(m[i+1][j+2]==1)
					mount++;
				if(m[i+2][j]==1)
					mount++;
				if(m[i+2][j+1]==1)
					mount++;
				if(m[i+2][j+2]==1)
					mount++;
				
				if(mount==3)
					temp[i][j]=1;
				else if(mount==2)
					temp[i][j]=m[i+1][j+1];
				else
					temp[i][j]=0;
				
				mount=0;
			}
		}
		return temp;
	}
}
