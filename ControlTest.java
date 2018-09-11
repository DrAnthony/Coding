package LG;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ControlTest {
	public Control con;
	public int[][] m;
	public int row,col;
	@Before
	public void setUp() throws Exception {
		con=new Control();
		row=20;
		col=20;
		m=new int[row][col];
	}

	@Test
	public void testRandomMap() {
		con.randomMap(m, row, col);
		//fail("Not yet implemented");
	}

	@Test
	public void testCreatMap() {
		con.creatMap(m, row, col);
		//fail("Not yet implemented");
	}

	@Test
	public void testCreat() {
		con.map(row, col);
		con.creat(row, col);
		//fail("Not yet implemented");
	}

}
