package routing;

import core.DTNHost;
import core.Message;
import core.Settings;

public class ProphetV2BlackHoleRouter extends ProphetV2Router{

    public ProphetV2BlackHoleRouter(Settings s) {
        super(s);
    }

    protected ProphetV2BlackHoleRouter(ProphetV2Router r) {
        super(r);
    }

    @Override
    public Message messageTransferred(String id, DTNHost from) {
        Message m = super.messageTransferred(id, from);
        this.deleteMessage(id, false);
        return m;
    }
}
