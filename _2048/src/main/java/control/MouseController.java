package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Layer;
import ui.UIContext;

public class MouseController extends MouseAdapter{

	/**
	 * 核心控制模块
	 */
	private UIContext uiContext;
	
	/**
	 * 开始按钮图层
	 */
	private Layer layer;

	public MouseController(UIContext uiContext, Layer layer) {
		this.uiContext = uiContext;
		this.layer = layer;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getPoint().x;
		int y = e.getPoint().y;
		if(x >= layer.getX() && x <= layer.getX() + layer.getWidth() && y > layer.getY() && y < layer.getY() + layer.getHeight()) {
			uiContext.newGame();
		}
	}

}
