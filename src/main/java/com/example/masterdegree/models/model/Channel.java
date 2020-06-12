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

    public boolean isTheSame(String searchedChannelName) {
//        return compareWithIgnoreHdInChannelName(channelName);
//        return getName().equalsIgnoreCase(channelName);
        return compareWithHdAndNonHdIsTheSame(searchedChannelName);
    }

    private boolean compareWithHdAndNonHdIsTheSame(String searchedChannelName) {
        if (isChannelNameLenghtLessThan(searchedChannelName)) {
            return channelWithoutHdAndWhiteSpaces(searchedChannelName).equalsIgnoreCase(removeAllWhiteSpaces(getName()));
        } else return getName().equalsIgnoreCase(searchedChannelName);
    }

    private boolean compareWithIgnoreHdInChannelName(String searchedChannelName) {
        if (isNotTheSameLength(searchedChannelName)) {
            String channelToCompareName = channelWithLongerName(searchedChannelName);
            return (isChannelsNameStartWithTheSame(channelToCompareName) && getHdDifference(channelToCompareName, getName()));
        } else return getName().equalsIgnoreCase(searchedChannelName);
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

    private boolean isChannelNameLenghtLessThan(String channelName) {
        return channelName.length() > getName().length();
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
