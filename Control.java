/*
 * �����ǳ�����߼�������
 * ���ڽ�����Ľ������GUI
 */
package LG;

public class Control {
	private int[][] map;//�߼���������
	//���ϸ��
	public void randomMap(int[][] m,int row,int col) {
		Logic logic=new Logic();
		map=logic.random(m, row, col);
	}
	//�Զ���ϸ��
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
