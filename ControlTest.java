package team.bro.life_game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControlTest {
	Control c;
	int row,col;
	int[][] map;
	@Before
	public void setUp() throws Exception {
		c=new Control();
		row=40;
		col=40;
	}


	@Test
	public void testGetRow() {
		System.out.println("**********testGetRow()**********");
		c.setRange(row, col);
		System.out.println("testGetRow()="+c.getRow());
	}


	@Test
	public void testGetCol() {
		System.out.println("**********testGetCol()**********");
		c.setRange(row, col);
		System.out.println("testGetCol()="+c.getCol());
	}

	//@Test
	public void testSetAge() {
		System.out.println("**********testSetAge()**********");
		c.setAge(0);
		System.out.print(c.getAge()+" ");
		c.setAge(-1);
		System.out.print(c.getAge()+" ");
		c.setAge(1);
		System.out.print(c.getAge()+" ");;
	}

	@Test
	public void testSetMap() {
		System.out.println("**********testsetMap()**********");
		c.setRange(row, col);
		c.initMap();
		map=c.getMap();
		int count=0;
		for(int i = 0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map[i][j]==1)
					count++;
			}
		}
		System.out.println("数组中1的数量为"+count);
		c.setMap(0, 0);
		c.setMap(2, 2);
		c.setMap(39, 39);
		map=c.getMap();
		System.out.print("元素为1的数组的下标：");
		for(int i = 0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map[i][j]==1)
					System.out.print("("+i+","+j+")");
			}
		}
		System.out.println();
		c.randomMap();
		System.out.print("自定义之前：");
		System.out.println(map[0][0]+" "+map[2][2]+" "+map[39][39]);
		c.setMap(0, 0);
		c.setMap(2, 2);
		c.setMap(39, 39);
		map=c.getMap();
		System.out.print("自定义之后：");
		System.out.println(map[0][0]+" "+map[2][2]+" "+map[39][39]);
	}

	@Test
	public void testInitMap() {
		System.out.println("**********testInitMap()**********");
		c.setRange(10, 10);
		c.initMap();
		map=c.getMap();
		for(int i = 0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void testRandomMap() {
		System.out.println("**********testRandomMap()**********");
		c.setRange(10, 10);
		c.initMap();
		for(int i=0;i<3;i++) {
			c.randomMap();
			map=c.getMap();
			for(int m = 0;m<10;m++) {
				for(int j=0;j<10;j++) {
					System.out.print(map[m][j]+" ");
				}
				System.out.println();
			}
			System.out.println("++++++++++++++++++++++++");
		}
	}

	@Test
	public void testRun() {
		System.out.println("**********testRun()**********");
		c.setRange(row, col);
		c.initMap();
		c.setMap(0, 4);
		c.setMap(0, 6);
		c.setMap(1, 4);
		c.setMap(1, 5);
		c.setMap(2, 5);
		map=c.getMap();
		System.out.print("自定义数组为1的元素下标：");
		for(int i = 0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map[i][j]==1)
					System.out.print("("+i+","+j+")");
			}
		}
		System.out.println();
		for(int i=0;i<2;i++) {
			c.run();
			map=c.getMap();
			System.out.print("运行"+(i+1)+"次后数组元素为1的下标：");
			for(int m = 0;m<row;m++) {
				for(int j=0;j<col;j++) {
					if(map[m][j]==1)
						System.out.print("("+m+","+j+")");
				}
			}
			System.out.println();
		}
	}

}
