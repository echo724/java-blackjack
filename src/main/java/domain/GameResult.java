package domain;

public class GameResult {

    public static final int WIN = 1;
    public static final int DRAW = 0;
    public static final int LOSE = -1;
    private final int winCount;
    private final int loseCount;

    public GameResult(int winCount, int loseCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public static GameResult from(int compareResult) {
        if (compareResult == WIN) {
            return new GameResult(1, 0);
        }
        if (compareResult == DRAW) {
            return new GameResult(0, 0);
        }
        if (compareResult == LOSE) {
            return new GameResult(0, 1);
        }
        throw new IllegalArgumentException();
    }

    public GameResult add(GameResult result) {
        return new GameResult(this.winCount + result.loseCount, this.loseCount + result.winCount);
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    @Override
    public String toString() {
        return "GameResult{" +
            "winCount=" + winCount +
            ", loseCount=" + loseCount +
            '}';
    }
}