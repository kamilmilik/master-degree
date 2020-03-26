package com.example.masterdegree.models.entity;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Channel {

    @NonNull
    private String name;
    private String desc;
    private String imgSrc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel)) return false;
        Channel channel = (Channel) o;
        return isTheSameChannel(channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName().toLowerCase());
    }

    private boolean isTheSameChannel(Channel channel) {
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
