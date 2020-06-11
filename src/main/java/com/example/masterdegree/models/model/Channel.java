package com.example.masterdegree.models.model;

import lombok.*;

@Value
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"name"})
public class Channel {

    @NonNull String name;
    String desc;
    String imgSrc;

    public static Channel channel(String name) { // To make object immutable. Factory method.
        return new Channel(name, null, null);
    }

    public boolean isTheSame(String channelName) {
//        return compareWithIgnoreHdInChannelName(channelName);
//        return getName().equalsIgnoreCase(channelName);
        return compareWithHdAndNonHdIsTheSame(channelName);
    }

    private boolean compareWithHdAndNonHdIsTheSame(String channelName) {
        if (isChannelNameGreaterThan(channelName)) {
            return channelWithoutHdAndWhiteSpaces(getName()).equalsIgnoreCase(removeAllWhiteSpaces(channelName));
        } else return getName().equalsIgnoreCase(channelName);
    }

    private boolean compareWithIgnoreHdInChannelName(String channelName) {
        if (isNotTheSameLength(channelName)) {
            String channelToCompareName = channelWithLongerName(channelName);
            return (isChannelsNameStartWithTheSame(channelToCompareName) && getHdDifference(channelToCompareName, getName()));
        } else return getName().equalsIgnoreCase(channelName);
    }

    private String channelWithoutHdAndWhiteSpaces(String channelName) {
        return removeAllWhiteSpaces(channelNameWithoutHd(channelName.toLowerCase()));
    }

    private String removeAllWhiteSpaces(String channelName) {
        return channelName.replaceAll(" ", "");
    }

    private String channelNameWithoutHd(String channelName) {
        return channelName.replaceAll("hd", "");
    }

    private boolean isChannelNameGreaterThan(String channelName) {
        return getName().length() > channelName.length();
    }

    private boolean isNotTheSameLength(String channelName) {
        return getName().length() != channelName.length();
    }

    private String channelWithLongerName(String channelName) {
        if (getName().length() > channelName.length()) {
            return getName().toLowerCase();
        } else {
            return channelName.toLowerCase();
        }
    }

    private boolean getHdDifference(String sourceChannelName, String differenceChannelName) {
        String secondPartOfChannelToCompareNameWithoutSpace = sourceChannelName.substring(differenceChannelName.length()).replaceAll(" ", "");
        return secondPartOfChannelToCompareNameWithoutSpace.equalsIgnoreCase("hd");
    }

    private boolean isChannelsNameStartWithTheSame(String channelName) {
        return channelName.startsWith(getName().toLowerCase());
    }


}
