package model;

/**
 * Created by Administrator on 2016/11/26.
 */
public class GameState {
    
    /**
     * 行数
     */
    public static final int ROW = 4;
    
    /**
     * 列数
     */
    public static final int COL = 4;
    
    /**
     * 当前分数
     */
    public int score = 0;

    /**
     * 是否失败
     */
    public boolean isLost = false;
    
    /**
     * 游戏地图
     */
    private int[][] gameMap = new int[COL][ROW];

    /**
     * 开始按钮状态
     */
    public int startButtonState = 0;
    
    public int[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(int[][] gameMap) {
        this.gameMap = gameMap;
    }
    
    /**
     * 通过不产生新DtoGame对象的方式来刷新游戏
     */
    public void refreshGameState() {
    	score = 0;
    	for (int col = 0; col < COL; col++) {
    		for (int row = 0; row < ROW; row++) {
    			gameMap[col][row] = 0;
    		}
    	}
    }
}
