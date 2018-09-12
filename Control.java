/*
 * �����ǳ�����߼�������
 * ���ڽ�����Ľ������GUI
 */
package team.bro.life_game;

import java.util.Random;

public class Control {
	private int[][] map;//��ͼ
	private int row,col;//����Ĵ�С
	private int age;//����
	
	//���õ�ͼ�Ĵ�С
	public void setRange(int r,int c) {
		row=r;
		col=c;
	}
	//��ȡ��ͼ������
	public int getRow() {
		return row;
	}
	//��ʼ����ͼ
	public void initMap() {
		map=new int[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				map[i][j]=0;
			}
		}
	}
	//��ȡ��ͼ������
	public int getCol() {
		return col;
	}
	//�ⲿ��ȡmap�Ľӿ�
	public int[][] getMap(){
		return map;
	}
	//����age
	public void setAge(int a) {
		if(a==0)
			age=0;
		else
			age++;
	}
	//�ⲿ��ȡage�Ľӿ�
	public int getAge() {
		return age;
	}
	//�ⲿ���õ�ͼ�Ľӿ�
	public void setMap(int r,int c) {
		map[r][c]=(map[r][c]==0)?1:0;
	}
	
	//���ϸ��
	public void randomMap() {
		Random random=new Random();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				map[i][j]=random.nextInt(2);
			}
		}
	}
	//�ⲿ����ϸ��������Ľӿ�
	public void create() {
		run();
	}
	//����ϸ��������
	public void run(){
		int[][] map_cal=new int[row+2][col+2];
		for(int i=0;i<row+2;i++) {
			for(int j=0;j<col+2;j++) {
				if(i==0||j==0||i==row+1||j==col+1)
					map_cal[i][j]=0;
				else
					map_cal[i][j]=map[i-1][j-1];
			}	
		}
		int mount=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map_cal[i][j]==1)
					mount++;
				if(map_cal[i][j+1]==1)
					mount++;
				if(map_cal[i][j+2]==1)
					mount++;
				if(map_cal[i+1][j]==1)
					mount++;
				if(map_cal[i+1][j+2]==1)
					mount++;
				if(map_cal[i+2][j]==1)
					mount++;
				if(map_cal[i+2][j+1]==1)
					mount++;
				if(map_cal[i+2][j+2]==1)
					mount++;
				
				if(mount==3)
					map[i][j]=1;
				else if(mount==2)
					map[i][j]=map_cal[i+1][j+1];
				else
					map[i][j]=0;
				
				mount=0;
			}
		}
	}
}
