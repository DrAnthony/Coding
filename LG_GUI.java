/*
 * ���ļ���Ҫʵ�� GUI����Ŀ���
 * �������һ��ֻ��ʹ��һ��
 */
package LG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.*;
import javax.swing.table.*;
public class LG_GUI {
	public static Semaphore sem=new Semaphore(1);
	JFrame mainFrame;//�����
	JPanel mainPanel;//������
	JPanel ioPanel,tablePanel;
	JLabel rowLabel,colLabel,ageLabel;
	JTextField rowText,colText,ageText;
	volatile JButton start;
	JButton randomCell,creatMap,ensureCell,clear;
	static Control con=new Control();
	drawThread newDT;
	Go go;
	Thread thread;
	MouseListener m;//������������
	int row,col;
	volatile int[][] mark;//��������ϸ����ͼ��Ϣ���������ͼ�Լ����߼��ύ��Ϣ
	volatile int age;//�����������
	static JTable table;

	/*
	 * ����ѭ��ʱҪ���ߣ�δ�����������̣߳����ô��߳�
	 */
	class Go implements Runnable{
		public volatile boolean exit=false;
		public void run() {
			while(!exit) {
				age++;
			try {
					mark=con.creat(row, col);
					newDT=new drawThread(table,mainPanel,ageText,mark,age);
					SwingUtilities.invokeLater(newDT);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 * ��ʼ��������
	 */
	public void initialFrame() {
		mainFrame=new JFrame("LifeGame");
		mainFrame.setBounds(500, 100, 600, 750);
		mainPanel=new JPanel();//������
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * ��ʼ�������õ�������
	 */
	public void initData() {
		mark=null;
		age=0;
		ageText.setText("��"+age+"��");
		rowText.setText("40");
		colText.setText("40");
		rowText.setEditable(true);
		colText.setEditable(true);
		randomCell.setEnabled(true);
		creatMap.setEnabled(true);
		ageText.setEditable(false);
		ensureCell.setEnabled(false);
		start.setEnabled(false);
		clear.setEnabled(false);
		start.setText("��ʼ����");
	}
	/*
	 * Ϊ����������,չʾ���
	 * �����Ϊ������ �ϲ�ioPanel��������������Լ���ť
	 * ��ǩ��rowLable-���� colLabel-���� ageLabel-����
	 * �ı���rowText-�������� colText-�������� ageText-������������ɱ༭��
	 */
	public void showFrame() {
		mainPanel.setLayout(null);
		/*
		 * ���������������뼰��ʾ��Panel
		 */
		ioPanel=new JPanel();
		ioPanel.setLayout(null);
		ioPanel.setBounds(0,0,600,100);
		
		//ΪioPanel������
		rowLabel=new JLabel("������");
		colLabel=new JLabel("������");
		ageLabel=new JLabel("������");
		rowLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		rowLabel.setBounds(30, 10, 50, 30);
		colLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		colLabel.setBounds(230, 10, 50, 30);
		ageLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		ageLabel.setBounds(430, 10, 50, 30);
		
		rowText=new JTextField(2);
		colText=new JTextField(2);
		ageText=new JTextField(4);
		rowText.setFont(new Font("Dialog", Font.BOLD, 15));
		rowText.setBounds(100,10,80,30);
		colText.setFont(new Font("Dialog", Font.BOLD, 15));
		colText.setBounds(300,10,80,30);
		ageText.setFont(new Font("Dialog", Font.BOLD, 15));
		ageText.setBounds(500,10,80,30);
		randomCell=new JButton("���ϸ��");
		creatMap=new JButton("�Զ���ϸ��");
		ensureCell=new JButton("ȷ��ϸ��");
		start=new JButton();
		clear=new JButton("���");
		randomCell.setBounds(10,45,100,50);
		creatMap.setBounds(130,45,100,50);
		ensureCell.setBounds(250,45,100,50);
		start.setBounds(370,45,100,50);
		clear.setBounds(490,45,100,50);
		initData();
		ioPanel.add(rowLabel);
		ioPanel.add(rowText);
		ioPanel.add(colLabel);
		ioPanel.add(colText);
		ioPanel.add(ageLabel);
		ioPanel.add(ageText);
		ioPanel.add(randomCell);
		ioPanel.add(creatMap);
		ioPanel.add(ensureCell);
		ioPanel.add(start);
		ioPanel.add(clear);
		
		//������ɵ�ͼ
		randomCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!creatTable())
					return;
				con.randomMap(mark, row, col);
				randomCell.setEnabled(false);
				ensureCell.setEnabled(true);
				creatMap.setEnabled(false);
				rowText.setEditable(false);
				colText.setEditable(false);
				flushMap(Color.RED);
			}
			
		});
		//�Զ����ͼ
		creatMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!creatTable())
					return;
				randomCell.setEnabled(false);
				ensureCell.setEnabled(true);
				creatMap.setEnabled(false);
				rowText.setEditable(false);
				colText.setEditable(false);
			}
		});
		//�ύ��ͼ��Ϣ
		ensureCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con.creatMap(mark,row,col);
				start.setEnabled(true);
				ensureCell.setEnabled(false);
				flushMap(Color.BLACK);
			}
		});
       //��ʼ����
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(start.getText().equals("��ʼ����")) {
					start.setText("��ͣ����");
					go=new Go();
				    thread=new Thread(go);
					thread.start();
				}
				else if(start.getText().equals("��ͣ����")){
					clear.setEnabled(true);
					table.removeMouseListener(m);
					go.exit=true;
					start.setText("��������");
				}
				else {
					clear.setEnabled(false);
					start.setText("��ͣ����");
					go=new Go();
					thread=new Thread(go);
					thread.start();
				}
			}
		});
		//������
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablePanel.removeAll();
				initData();
				mainPanel.validate();
				mainPanel.repaint();
			}
		});
		/*
		 * ������ӱ���tablePanel
		 */
		tablePanel=new JPanel();
		tablePanel.setLayout(null);
		tablePanel.setBounds(0,100,600,650);
	   
		
		mainPanel.add(ioPanel);
		mainPanel.add(tablePanel);
		mainPanel.setVisible(true);
		mainPanel.validate();
		mainPanel.repaint();
	}
	/*
	 * ��������
	 */
	public boolean creatTable() {
		tablePanel.removeAll();
		if(!isValid()) {
			return false;
		}
		String column[]=new String[col];
		for(int i=0;i<col;i++)
			column[i]="100";
		Object[][] obj=new Object[row][col];
		mark=new int[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				obj[i][j]="";
				mark[i][j]=0;
			}
		}
		table=new JTable(obj,column) {
		public boolean isCellEditable(int row,int colummn) {
			return false;
		}
		};
		TableColumn Col = null;
		int tableWidth=600/col;
	    for(int i = 0; i < col; i++){
	    	  Col= table.getColumnModel().getColumn(i);  
	            /*����ÿһ�еĿ��*/  
	          Col.setPreferredWidth(tableWidth);  
	    }
	    table.setRowHeight(tableWidth);
	    table.setBounds(0,15,600,tableWidth*row);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
	    table.setVisible(true);
	    table.setCellSelectionEnabled(true);
	    table.setRowSelectionAllowed(false);
	    table.setColumnSelectionAllowed(false);   
	    
	  //Ϊ��������������
	    m=new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		int rowNum=table.getSelectedRow();
			    int colNum=table.getSelectedColumn();
			    mark[rowNum][colNum]=(mark[rowNum][colNum]==0)?1:0;
			    flushMap(Color.RED);
            }  
	    };
	    table.addMouseListener(m);
	    
	    tablePanel.add(table,BorderLayout.CENTER);
	    tablePanel.setVisible(true);
	    tablePanel.validate();
	    tablePanel.repaint();
	    return true;
	}
	/*
	 * ˢ�µ�ͼ
	 */
	public void flushMap(Color c) {
		EvenOddRenderer ren=new EvenOddRenderer(mark,c);
		table.setDefaultRenderer(Object.class, ren);
		mainPanel.validate();
		mainPanel.repaint();
	}
	/*
	 * �ж���������������Ƿ���Ч
	 */
	public boolean isValid() {
		try {
			row=Integer.parseInt(rowText.getText());
			col=Integer.parseInt(colText.getText());
		}catch(Exception e) {
			JLabel tip=new JLabel("��������Ч�����ݣ�");
			tip.setFont(new Font("Dialog", Font.BOLD, 15));
			tip.setBounds(200, 200, 200, 50);
			tip.setForeground(Color.RED);
			tablePanel.add(tip);
			tablePanel.validate();
			tablePanel.repaint();
			return false;
		}
		return true;
	}
	
	public static void main(String[]args) {
		LG_GUI gui=new LG_GUI();
		gui.initialFrame();
		gui.showFrame();
	}
}


