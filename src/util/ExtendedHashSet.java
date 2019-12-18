package util;

import java.util.HashSet;

public class ExtendedHashSet {
    HashSet<String> hashSet;

    public ExtendedHashSet(){
        hashSet = new HashSet<>();
    }

    public void add(String msgId, int hostAddress){
        String s = mount(msgId, hostAddress);
        hashSet.add(s);
    }

    public boolean contains(String msgId, int hostAddress){
        String s = mount(msgId, hostAddress);
        return hashSet.contains(s);
    }

    private String mount(String msgId, int hostAddress){
        return msgId + "->" + hostAddress;
    }

}
