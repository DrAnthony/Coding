/*
 * 此类是程序的逻辑控制类
 * 用于将运算的结果交给GUI
 */
package team.bro.life_game;

import java.util.Random;

public class Control {
	private int[][] map;//地图
	private int row,col;//网格的大小
	private int age;//代数
	
	//设置地图的大小
	public void setRange(int r,int c) {
		row=r;
		col=c;
	}
	//获取地图的行数
	public int getRow() {
		return row;
	}
	//初始化地图
	public void initMap() {
		map=new int[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				map[i][j]=0;
			}
		}
	}
	//获取地图的列数
	public int getCol() {
		return col;
	}
	//外部获取map的接口
	public int[][] getMap(){
		return map;
	}
	//设置age
	public void setAge(int a) {
		if(a==0)
			age=0;
		else
			age++;
	}
	//外部获取age的接口
	public int getAge() {
		return age;
	}
	//外部设置地图的接口
	public void setMap(int r,int c) {
		map[r][c]=(map[r][c]==0)?1:0;
	}
	
	//随机细胞
	public void randomMap() {
		Random random=new Random();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				map[i][j]=random.nextInt(2);
			}
		}
	}
	//外部进行细胞存活计算的接口
	public void create() {
		run();
	}
	//计算细胞的死活
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
