package com.legacy.service;

import com.legacy.Model.AllPositionWrapper;
import com.legacy.Model.BrynjolfStatus;
import com.legacy.Model.Position;
import com.legacy.Model.SequenceQueue;
import com.legacy.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.legacy.constant.Status.*;

//implementation for Enlightenment Escape
public class EnlightenmentImpl implements Escape {
    private static final Logger logger = LoggerFactory.getLogger(EnlightenmentImpl.class);
    private static final String[][] all4Direction = {{"-1", "1", "0", "0"}, {"0", "0", "-1", "1"}, {"U", "D", "L", "R"}};
    Helper helper;

    public EnlightenmentImpl(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void findEscapePath(String[][] room, String inputSequence) throws IOException {
        BrynjolfStatus brynjolfStatus = null;
        if (helper.isInputSequencePresentAndValid(inputSequence)) {
            brynjolfStatus = helper.getResultFollowedByInputSequence(room, inputSequence);
            if (brynjolfStatus.getStatus() == WIN)
                logger.info("win: {}", brynjolfStatus.getSequence());
            else if (brynjolfStatus.getStatus() == LOSE)
                logger.info("lose: {}", brynjolfStatus.getSequence());
        }
        if (brynjolfStatus == null || brynjolfStatus.getStatus() == UNDECIDED) {
            brynjolfStatus = moveAccordingToBrynjolfMind(room, helper);
            if (brynjolfStatus.getStatus() == WIN)
                logger.info("win: {}", brynjolfStatus.getSequence());
            else
                logger.info("stuck: no way to win");
        }
    }

    //Brynjolf mind game
    private BrynjolfStatus moveAccordingToBrynjolfMind(String[][] room, Helper helper) {
        AllPositionWrapper allPositionWrapper = new AllPositionWrapper(helper.getAllGuardsPosition(room),
                helper.getBrynjolfPosition(room), helper.getExitGatePosition(room));
        Queue<SequenceQueue> queue = new LinkedList<>();
        queue.add(new SequenceQueue(allPositionWrapper.getBrynjolfPosition(), "", allPositionWrapper.getAllGuardsPosition()));
        long moves = 0;

        while (!queue.isEmpty()) {
            SequenceQueue currentRoomState = queue.poll();
            moves++;
            if (currentRoomState.getBrynjolfPosition().getXAxis() == allPositionWrapper.getExitGatePosition().getXAxis() &&
                    currentRoomState.getBrynjolfPosition().getYAxis() == allPositionWrapper.getExitGatePosition().getYAxis()) {
                return new BrynjolfStatus(WIN, currentRoomState.getWinningSequence());
            } else if (moves == 1000000)
                break;
            for (int i = 0; i < 4; i++) {
                Position brynjolfNextPosition = new Position(currentRoomState.getBrynjolfPosition().getXAxis() + Integer.parseInt(all4Direction[0][i]),
                        currentRoomState.getBrynjolfPosition().getYAxis() + Integer.parseInt(all4Direction[1][i]));

                if (!helper.isWallPresentCaseBrynjolf(room, brynjolfNextPosition) && !helper.isGuardPresent(currentRoomState.getAllGuardsPositionInstance(), brynjolfNextPosition)) {
                    List<Position> allGuardsNextPosition = helper.getAllGuardNextPosition(room, currentRoomState.getAllGuardsPositionInstance(), Integer.parseInt(all4Direction[0][i]), Integer.parseInt(all4Direction[1][i]));
                    queue.add(new SequenceQueue(brynjolfNextPosition, currentRoomState.getWinningSequence() + all4Direction[2][i], allGuardsNextPosition));
                }
            }
        }
        return new BrynjolfStatus(STUCK, null);
    }
}
