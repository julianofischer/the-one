package routing;

import core.DTNHost;
import core.Message;
import core.Settings;

public class SprayAndWaitBlackHoleRouter extends SprayAndWaitRouter {

    public SprayAndWaitBlackHoleRouter(Settings s) {
        super(s);
    }

    protected SprayAndWaitBlackHoleRouter(SprayAndWaitRouter r) {
        super(r);
    }

    @Override
    public Message messageTransferred(String id, DTNHost from) {
        Message m = super.messageTransferred(id, from);
        this.deleteMessage(id, false);
        return m;
    }

    @Override
    public SprayAndWaitRouter replicate() {
        SprayAndWaitBlackHoleRouter r = new SprayAndWaitBlackHoleRouter(this);
        return r;
    }
}
