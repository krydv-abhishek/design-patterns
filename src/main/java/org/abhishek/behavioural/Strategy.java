package org.abhishek.behavioural;

/*
Strategy design pattern is behavioral design pattern where we choose a specific implementation
of algorithm or task in run time – out of multiple other implementations for same task.

Design Participants:

1) IStrategy – The interface which abstract the operation.
2) Context – The context which determines the implementation.
3) Implementations – Various implementations of IStrategy. E.g. StrategyOne, StrategyTwo

Example:
1) Share media to multiple Apps
2) Export video in video editor in multiple formats

 */

interface ISocialMediaStrategy {
    void connectTo(String friendName);
}

class FacebookStrategy implements ISocialMediaStrategy {
    @Override
    public void connectTo(String friendName) {
        System.out.println("Connect to " + friendName + " on facebook");
    }
}

class InstagramStrategy implements ISocialMediaStrategy {
    @Override
    public void connectTo(String friendName) {
        System.out.println("Connect to " + friendName + " on instagram");
    }
}

class SocialMediaContext {
    private ISocialMediaStrategy socialMediaStrategy;

    public void setSocialMediaStrategy(ISocialMediaStrategy smStrategy) {
        this.socialMediaStrategy = smStrategy;
    }

    public void connect(String name) {
        socialMediaStrategy.connectTo(name);
    }
}

public class Strategy {

    public static void main(String[] args) {
        SocialMediaContext socialMediaContext = new SocialMediaContext();
        socialMediaContext.setSocialMediaStrategy(new FacebookStrategy());
        socialMediaContext.connect("friend 1");

        socialMediaContext.setSocialMediaStrategy(new InstagramStrategy());
        socialMediaContext.connect("friend 2");

    }
}
