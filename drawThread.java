package LG;

import java.awt.Color;

import javax.swing.*;
/*
 * 为避免线程阻塞，故设置此线程进行UI界面的控制
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
			text.setText("第"+count+"代");
			panel.validate();
			panel.repaint();
	}

}
