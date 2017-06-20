package control;

import ui.UIContext;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Administrator on 2016/11/26.
 */
public class KeyController extends KeyAdapter {

    private UIContext uiContext;

    private AI ai;

    public KeyController(UIContext uiContext, AI ai) {
        this.uiContext = uiContext;
        this.ai = ai;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO 反射机制
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: uiContext.up(); break;
            case KeyEvent.VK_DOWN: uiContext.down(); break;
            case KeyEvent.VK_LEFT: uiContext.left(); break;
            case KeyEvent.VK_RIGHT: uiContext.right(); break;
            case KeyEvent.VK_A: ai.execute();break;
            default: break;
        }
    }

}
