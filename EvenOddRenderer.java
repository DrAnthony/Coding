/*
 * 自定义单元格的渲染器
 */

package LG;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

//单独渲染一个单元格
class EvenOddRenderer implements TableCellRenderer {
private int[][] m;
private Color color;
public EvenOddRenderer(int[][] temp,Color c) {
	m=temp;
	color=c;
}
public static final DefaultTableCellRenderer DEFAULT_RENDERER =
new DefaultTableCellRenderer();

public Component getTableCellRendererComponent(JTable table, Object value,
boolean isSelected, boolean hasFocus, int row, int column) {
Component renderer =
DEFAULT_RENDERER.getTableCellRendererComponent(table, value,
isSelected, hasFocus, row, column);
Color foreground, background;
if (m[row][column]==1) {//根据GUI界面传入的鼠标的点击位置对应的mark数组的值进行单元格的渲染
	renderer.setBackground(color);
}
else {
	renderer.setBackground(Color.WHITE);
}
return renderer;
}
}
