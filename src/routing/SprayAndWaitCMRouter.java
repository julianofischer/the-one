package routing;

import core.Connection;
import core.Message;
import core.Settings;
import util.ExtendedHashSet;

import java.util.List;

public class SprayAndWaitCMRouter<transferDone> extends SprayAndWaitRouter {

    private ExtendedHashSet hash;

    public SprayAndWaitCMRouter(Settings s) {
        super(s);
        hash = new ExtendedHashSet();
    }

    protected SprayAndWaitCMRouter(SprayAndWaitRouter r) {
        super(r);
        hash = new ExtendedHashSet();
    }

    @Override
    protected Message tryAllMessages(Connection con, List<Message> messages) {
        for (Message m : messages) {

            //ignore already sent messages
            if (hash.contains(m.getId(), con.getOtherNode(getHost()).getAddress())){
                continue;
            }

            int retVal = startTransfer(m, con);
            if (retVal == RCV_OK) {
                return m;	// accepted a message, don't try others
            }
            else if (retVal > 0) {
                return null; // should try later -> don't bother trying others
            }
        }

        return null; // no message was accepted
    }

    @Override
    protected void transferDone(Connection con) {
        super.transferDone(con);
        String msgId = con.getMessage().getId();
        int hostAddress = con.getOtherNode(getHost()).getAddress();
        hash.add(msgId, hostAddress);
    }

    @Override
    public SprayAndWaitRouter replicate() {
        SprayAndWaitCMRouter r = new SprayAndWaitCMRouter(this);
        return r;
    }

}
