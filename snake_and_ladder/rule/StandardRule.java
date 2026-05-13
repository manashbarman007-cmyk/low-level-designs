package snake_and_ladder.rule;

import snake_and_ladder.board.Board;
import snake_and_ladder.boardentity.BoardEntity;
import snake_and_ladder.player.Player;
import java.util.Map;

public class StandardRule implements Rule{
    @Override
    public boolean isMoveValid(Player player, int diceRoll, Board board) {

        return player.getPos() + diceRoll <= board.getSize();
    }

    @Override
    public int calcNewPos(Player player, int diceRoll, Board board) {

        int playerNewPos = player.getPos() + diceRoll;

        Map<Integer, BoardEntity> mapEntities = board.getMap();

        if (mapEntities != null) {
            if (mapEntities.containsKey(playerNewPos)) {
                playerNewPos = mapEntities.get(playerNewPos).getEnd();
            }
        }

        return playerNewPos;
    }

    @Override
    public boolean checkWin(Player player, Board board) {

        return player.getPos() == board.getSize();
    }
}
