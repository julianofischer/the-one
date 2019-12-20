package routing;

import core.DTNHost;
import core.Message;
import core.Settings;

public class ProphetV2BlackHoleRouter extends ProphetV2Router{

    public ProphetV2BlackHoleRouter(Settings s) {
        super(s);
    }

    protected ProphetV2BlackHoleRouter(ProphetV2BlackHoleRouter r) {
        super(r);
    }

    @Override
    public Message messageTransferred(String id, DTNHost from) {
        System.out.println("A message was transferred from "+from.getAddress()+" to "+getHost().getAddress());
        Message m = super.messageTransferred(id, from);
        this.deleteMessage(id, true);
        return m;
    }

    @Override
    public MessageRouter replicate() {
        ProphetV2BlackHoleRouter r = new ProphetV2BlackHoleRouter(this);
        return r;
    }
}
