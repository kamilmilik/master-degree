package com.example.masterdegree.models.model;

import lombok.*;

@Value
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"name"})
public class Channel {

    @NonNull
    private String name;
    private String desc;
    private String imgSrc;


//    public static Channel newChannel(String name, String desc, String imgSrc){ // To make object immutable. Factory method.
//        return new Channel(name, desc, imgSrc);
//    }

    public static Channel create(String name){
        return new Channel(name, null, null);
    }

    public static Channel create(String name, String imgSrc){ // To make object immutable. Factory method.
        return new Channel(name, null, imgSrc);
    }

    // TODO KM dodac na bazie id do channel, i rozrozniac jako osobno kanaly hd i nie hd
    public boolean isTheSame(Channel channel) {
        if (isTheSameLength(channel)) {
            String channelToCompareName = chooseProperChannelNameToCompareForSymmetryComparision(channel);
            return (isChannelsNameStartWithTheSame(channelToCompareName) && isHdChannelName(channelToCompareName));
        } else return getName().equalsIgnoreCase(channel.getName());
    }

    private boolean isTheSameLength(Channel channel) {
        return getName().length() != channel.getName().length();
    }

    private String chooseProperChannelNameToCompareForSymmetryComparision(Channel channel) {
        if (getName().length() > channel.getName().length()) {
            return getName().toLowerCase();
        } else {
            return channel.getName().toLowerCase();
        }
    }

    private boolean isHdChannelName(String channelName) {
        String secondPartOfChannelToCompareNameWithoutSpace = channelName.substring(getName().length()).replaceAll(" ", "");
        return secondPartOfChannelToCompareNameWithoutSpace.equals("hd");
    }

    private boolean isChannelsNameStartWithTheSame(String channelName) {
        return channelName.startsWith(getName().toLowerCase());
    }


}
