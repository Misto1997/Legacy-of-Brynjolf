package com.legacy.service;

import com.legacy.Model.BrynjolfStatus;
import com.legacy.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.legacy.constant.Status.LOSE;
import static com.legacy.constant.Status.WIN;

//implementation for Establishment Escape
public class EstablishmentImpl implements Escape {
    private static final Logger logger = LoggerFactory.getLogger(EstablishmentImpl.class);

    @Override
    public void findEscapePath(String[][] room, String inputSequence) throws IOException {
        Helper helper = new Helper();
        BrynjolfStatus brynjolfStatus;
        if (helper.isInputSequencePresentAndValid(inputSequence)) {
            brynjolfStatus = helper.getResultFollowedByInputSequence(room, inputSequence);
            helper.printRoomBluePrint(room);
            if (brynjolfStatus.getStatus() == WIN)
                logger.info("win: {}", brynjolfStatus.getSequence());
            else if (brynjolfStatus.getStatus() == LOSE)
                logger.info("lose: {}", brynjolfStatus.getSequence());
            else
                logger.info("undecided: {}", brynjolfStatus.getSequence());
            logger.info("executed {} moves of {}",
                    brynjolfStatus.getSequence().length(), inputSequence.length());
        } else {
            throw new IOException("Input sequence is mandatory for Establishment! \n" +
                    "Please try again with input sequence or select 2. Enlightenment if" +
                    "you want to play with Brynjolf mind");
        }
    }
}
