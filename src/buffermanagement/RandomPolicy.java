package buffermanagement;

import core.Message;
import core.Settings;
import core.SettingsError;
import routing.ActiveRouter;

import java.util.List;
import java.util.Random;

/**
 * Random buffer management policy.
 */
public class RandomPolicy implements BufferManagementPolicy {

    /** Random policy setting namespace*/
    public static final String RANDOMPOLICY_NS = "RandomPolicy";

    /**
     * Seed used to initialize the Random class.*/
    public static final String SEED ="seed";
    private long seed;

    private Random randomGenerator;

    public RandomPolicy(){
        Settings randomPolicySettings = new Settings(RANDOMPOLICY_NS);
        if (randomPolicySettings.contains(SEED)) {
            seed = randomPolicySettings.getLong(SEED);
            randomGenerator = new Random(seed);
        }else{
            throw new SettingsError("No seed provided to RandomPolicy. Please, make sure you provided RandomPolicy.seed setting.");
        }
    }

    @Override
    public Message getNextMessageToRemove(boolean excludeMsgBeingSent, List<Message> msgs, ActiveRouter router) {

        if (msgs.size() <= 0){
            return null;
        }

        int index = randomGenerator.nextInt(msgs.size());
        Message m = msgs.get(index);

        if (excludeMsgBeingSent && router.isSending(m.getId())) {
            msgs.remove(index);

            // try again! Only one message can be in sending state at time
            if (msgs.size() > 0){
                index = randomGenerator.nextInt(msgs.size());
                return msgs.get(index);
            }else{
                return null;
            }

        }else{
            return m;
        }
    }
}
