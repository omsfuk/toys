package ui;

import java.awt.Font;
import java.awt.Graphics;

import model.GameState;

/**
 * Created by Administrator on 2016/11/26.
 */
public class LayerScore extends Layer {

	/**
	 * Graphics绘图字体
	 */
	private Font font = new Font("Default",Font.ROMAN_BASELINE, 24);
	
	/**
	 * 数据访问对象
	 */
	private GameState gameState = null;
	
    /**
     * 构造方法
     * @param x 相对 x 坐标
     * @param y 相对 y 坐标
     * @param width 宽度
     * @param height 高度
     */
    public LayerScore(int x, int y, int width, int height, GameState gameState) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameState = gameState;
    }

    @Override
    public void paint(final Graphics g) {  
    	g.setFont(font);
    	g.drawImage(Img.SCORE, this.x, this.y, this.width, this.height, null);
    	g.drawString(Integer.toString(gameState.score), this.x + 50, this.y + 50);
    }
}
