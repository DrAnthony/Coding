package LG;

import java.awt.Color;

import javax.swing.*;
/*
 * Ϊ�����߳������������ô��߳̽���UI����Ŀ���
 */
public class drawThread implements Runnable {
	JTable table;
	JPanel panel;
	JTextField text;
	int[][] mark;
	int count;
	public drawThread(JTable t,JPanel p,JTextField te,int[][] m,int c) {
		table=t;
		panel=p;
	    text=te;
		mark=m;
		count=c;
	}
	public void run() {
			EvenOddRenderer renderer=new EvenOddRenderer(mark,Color.BLACK);
			table.setDefaultRenderer(Object.class, renderer);
			text.setText("��"+count+"��");
			panel.validate();
			panel.repaint();
	}

}
