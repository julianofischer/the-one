package buffermanagement;

import core.Message;
import routing.ActiveRouter;

import java.util.List;

/**
 * Drops the oldest message (regarding creation time) first.
 */
public class OldestPolicy implements BufferManagementPolicy{

    @Override
    public Message getNextMessageToRemove(boolean excludeMsgBeingSent, List<Message> msgs, ActiveRouter router) {
        Message oldest = null;
        for (Message m : msgs) {

            if (excludeMsgBeingSent && router.isSending(m.getId())) {
                continue; // skip the message(s) that router is sending
            }

            if (oldest == null ) {
                oldest = m;
            }
            else if (oldest.getCreationTime() > m.getCreationTime()) {
                oldest = m;
            }
        }

        return oldest;
    }
}
