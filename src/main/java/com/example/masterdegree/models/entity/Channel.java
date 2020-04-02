package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.Objects;

@Builder
@Value
@EqualsAndHashCode(of = {"name"})
public class Channel {

    @NonNull
    @Getter
    private String name;
    @Getter
    private String desc;
    @Getter
    private String imgSrc;


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
