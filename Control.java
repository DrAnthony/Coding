/*
 * 此类是程序的逻辑控制类
 * 用于将运算的结果交给GUI
 */
package LG;

public class Control {
	private int[][] map;//逻辑运算数组
	//随机细胞
	public void randomMap(int[][] m,int row,int col) {
		Logic logic=new Logic();
		map=logic.random(m, row, col);
	}
	//自定义细胞
	public void creatMap(int[][] m,int row,int col) {
		Logic logic=new Logic();
		map=logic.setMap(m,row,col);
	}
	
	public int[][] creat(int r,int c) {
		int temp[][]=new int[r][c];
		Logic logic=new Logic();
		temp=logic.run(map, r, c);
		map=logic.setMap(temp, r, c);
		return temp;
	}
	public void map(int row,int col) {
		map=new int[row+2][col+2];
	}
}
