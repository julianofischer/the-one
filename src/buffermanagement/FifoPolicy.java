package buffermanagement;

import core.Message;
import routing.ActiveRouter;

import java.util.List;

/**
 * First-in First-out buffer management policy.
 * For this policy, local receiving time is used.
 */
public class FifoPolicy implements BufferManagementPolicy{

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
            else if (oldest.getReceiveTime() > m.getReceiveTime()) {
                oldest = m;
            }
        }

        return oldest;
    }


}
