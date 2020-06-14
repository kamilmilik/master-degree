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
        return compareWithIgnoreHdInChannelName(searchedChannelName);
//        return getName().equalsIgnoreCase(channelName);
//        return compareWithHdAndNonHdIsTheSame(searchedChannelName);
    }

    private boolean compareWithHdAndNonHdIsTheSame(String searchedChannelName) {
        if (isChannelNameLengthLessThan(searchedChannelName)) {
            return channelWithoutHdAndWhiteSpaces(searchedChannelName).equalsIgnoreCase(removeAllWhiteSpaces(getName()));
        } else return getName().equalsIgnoreCase(searchedChannelName);
    }

    private boolean compareWithIgnoreHdInChannelName(String searchedChannelName) {
        if (isNotTheSameLength(searchedChannelName)) {
            if (isHdFor(searchedChannelName)) {
                return compareWithoutSpaceIgnoreCase(getName(), removeHdFrom(searchedChannelName));
            } else if (isHdFor(getName())) {
                return compareWithoutSpaceIgnoreCase(removeHdFrom(getName()), searchedChannelName);
            } else {
                return compareWithoutSpaceIgnoreCase(getName(), searchedChannelName);
            }
        } else return compareWithoutSpaceIgnoreCase(getName(), searchedChannelName);
    }

    private boolean isHdFor(String channelName) {
        int hdIndex = channelName.toLowerCase().indexOf("hd");
        return hdIndex != -1;
    }

    private String removeHdFrom(String channelName) {
        return channelName.toLowerCase().replaceAll("hd", "");
    }

    private boolean compareWithoutSpaceIgnoreCase(String source, String destination) {
        return removeAllWhiteSpaces(source).equalsIgnoreCase(removeAllWhiteSpaces(destination));
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

    private boolean isChannelNameLengthLessThan(String channelName) {
        return channelName.length() > getName().length();
    }

    private boolean isNotTheSameLength(String channelName) {
        return removeAllWhiteSpaces(getName()).length() != removeAllWhiteSpaces(channelName).length();
    }

}
