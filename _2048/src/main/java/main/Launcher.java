package main;

import model.GameState;
import ui.UIContext;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Launcher {
    public static void main(String[] args) {
        new UIContext(new GameState());
    }
}
