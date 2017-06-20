package ui;

import model.GameState;
import service.GameService;

import java.awt.*;

/**
 * Created by omsfuk on 2017/6/19.
 */
public class UIContext {

    private GameService gameService;

    private JFrameGame frameGame;

    private GameState gameState;

    public UIContext(GameState gameState) {
        this.gameState = gameState;
        gameService = new GameService(gameState);
        frameGame = new JFrameGame(this, gameState);
    }

    public void newGame() {
        gameState.isLost = false;
        gameState.refreshGameState();
        gameService.start();
        frameGame.repaintContentPanel();
        frameGame.addContextPanelKeyListener();
        System.out.println("keyListener");
        frameGame.setFocusable(true);
    }

    public void up() {
        Point[][] offest = gameService.getUpCombine();
        frameGame.up(offest);
        nextAndUpdate(offest);
    }

    public void down() {
        Point[][] offest = gameService.getDownCombine();
        frameGame.down(offest);
        nextAndUpdate(offest);
    }

    public void left() {
        Point[][] offest = gameService.getLeftCombine();
        frameGame.left(offest);
        nextAndUpdate(offest);
    }

    public void right() {
        Point[][] offest = gameService.getRightCombine();
        frameGame.right(offest);
        nextAndUpdate(offest);
    }

    private void nextAndUpdate(Point[][] offest)
    {
        updateMap(offest);
        if (!gameService.nextBlock()) {
            gameState.isLost = true;
            frameGame.removeContextPanelKeyListener();
        }
        frameGame.repaintContentPanel();
    }

    /**
     * 更新地图
     * @param offest 目标位置映射
     */
    private void updateMap(Point[][] offest) {
        int[][] gameMap = gameState.getGameMap();
        int[][] gameMap0 = new int[4][4];
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                if(gameMap[col][row] == 0) {
                    continue;
                }
                if(gameMap0[offest[col][row].x][offest[col][row].y] != 0) {
                    gameMap0[offest[col][row].x][offest[col][row].y] ++;
                    gameState.score += 1 << gameMap0[offest[col][row].x][offest[col][row].y];
                } else {
                    gameMap0[offest[col][row].x][offest[col][row].y] = gameMap[col][row];
                }
            }
        }
        gameState.setGameMap(gameMap0);
    }


}
