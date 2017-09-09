package buffermanagement;

import core.Message;
import routing.ActiveRouter;

import java.util.List;

/**
 * All buffer management policies must implement this interface.
 */
public interface BufferManagementPolicy {

    Message getNextMessageToRemove(boolean excludeMsgBeingSent, List<Message> msgs, ActiveRouter router);

}
