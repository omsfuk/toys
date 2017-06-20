package ui;

import model.GameState;

import java.awt.*;

import javax.swing.JFrame;

/**
 * Created by Administrator on 2016/11/26.
 */
@SuppressWarnings("serial")
public class JFrameGame extends JFrame {

	/**
	 * 窗口宽度
	 */
    private static final int windowWidth = 368 + 4;
    
    /**
     * 窗口高度
     */
    private static final int windowHeight = 448 + 30;

    private JPanelGame panelGame;
    
    /**
     * 构造方法
     */
    public JFrameGame(UIContext uiContext, GameState gameState) {
        panelGame = new JPanelGame(uiContext, gameState);
    	// 设置标题
    	this.setTitle("2048");
        // 设置默认关闭按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口大小
        setSize(windowWidth, windowHeight);
        // 设置不可变动大小
        setResizable(false);
        // 设置默认panel
        setContentPane(panelGame);
        // 设置窗口位置
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setLocation((width - this.getWidth()) / 2, (height - this.getHeight()) / 2);
        // 设置可见
        setVisible(true);
    }

    public void up(Point[][] offest) {
        panelGame.move(panelGame.getGraphics(), offest);
    }

    public void down(Point[][] offest) {
        panelGame.move(panelGame.getGraphics(), offest);
    }

    public void left(Point[][] offest) {
        panelGame.move(panelGame.getGraphics(), offest);
    }

    public void right(Point[][] offest) {
        panelGame.move(panelGame.getGraphics(), offest);
    }

    public void repaintContentPanel() {
        panelGame.repaint();
    }

    public void removeContextPanelKeyListener() {
        panelGame.removeKeyListener();
    }

    public void addContextPanelKeyListener() {panelGame.addKeyListener();}
}
