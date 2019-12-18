package routing;

import core.Connection;
import core.Message;
import core.Settings;
import util.ExtendedHashSet;
import util.Tuple;

import java.util.List;

public class ProphetV2CMRouter extends ProphetV2Router {
    private ExtendedHashSet hash;

    public ProphetV2CMRouter(Settings s) {
        super(s);
        hash = new ExtendedHashSet();
    }

    protected ProphetV2CMRouter(ProphetV2Router r) {
        super(r);
        hash = new ExtendedHashSet();
    }

    @Override
    protected void transferDone(Connection con) {
        super.transferDone(con);
        String msgId = con.getMessage().getId();
        int hostAddress = con.getOtherNode(getHost()).getAddress();
        hash.add(msgId, hostAddress);
    }

    protected Tuple<Message, Connection> tryMessagesForConnected(
            List<Tuple<Message, Connection>> tuples) {
        if (tuples.size() == 0) {
            return null;
        }

        for (Tuple<Message, Connection> t : tuples) {
            Message m = t.getKey();
            Connection con = t.getValue();

            String msgId = m.getId();
            int hostAddress = con.getOtherNode(getHost()).getAddress();
            //ignore already sent messages
            if (hash.contains(msgId, hostAddress)){
                continue;
            }

            if (startTransfer(m, con) == RCV_OK) {
                return t;
            }
        }

        return null;
    }


}
