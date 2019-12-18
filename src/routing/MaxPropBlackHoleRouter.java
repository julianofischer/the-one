package routing;

import core.DTNHost;
import core.Message;
import core.Settings;

public class MaxPropBlackHoleRouter extends MaxPropRouter{

    public MaxPropBlackHoleRouter(Settings settings) {
        super(settings);
    }

    protected MaxPropBlackHoleRouter(MaxPropRouter r) {
        super(r);
    }

    @Override
    public Message messageTransferred(String id, DTNHost from) {
        Message m = super.messageTransferred(id, from);
        this.deleteMessage(id, false);
        return m;
    }

}
