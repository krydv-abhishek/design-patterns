package org.abhishek.structural;

/*
When to Use Bridge Method Design Pattern in Java
When you need to avoid a permanent binding between an abstraction and its implementation.
When both the abstraction and implementation should be extensible through subclassing.
When changes in the implementation of an abstraction should not impact the clients.
When you want to share an implementation among multiple objects and hide implementation details from the clients.
 */
class RemoteControl {

    protected Device device;

    RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}

//class AdvanceRemoteControl extends RemoteControl {
//    AdvanceRemoteControl(Device device) {
//        super(device);
//    }
//
//    void mute() {
//        device.setVolume(0);
//    }
//}


interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int updatedVolume);

    int getChannel();

    void setChannel(int updatedChannel);
}

class TV implements Device {
    boolean enabled;
    int volume;
    int channel;

    TV() {
        enabled = false;
        volume = 0;
        channel = 0;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enable() {
        System.out.println("TV is on");
        this.enabled = true;
    }

    @Override
    public void disable() {
        System.out.println("TV is off");
        this.enabled = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int updatedVolume) {
        System.out.println("Updated tv vol to " + updatedVolume);
        this.volume = updatedVolume;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int updatedChannel) {
        System.out.println("Updated tv channel to " + updatedChannel);
        this.channel = updatedChannel;
    }
}

class Radio implements Device {
    boolean enabled;
    int volume;
    int channel;

    Radio() {
        enabled = false;
        volume = 0;
        channel = 0;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enable() {
        System.out.println("Radio is on");
        this.enabled = true;
    }

    @Override
    public void disable() {
        System.out.println("Radio is off");
        this.enabled = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int updatedVolume) {
        System.out.println("Updated radio vol to " + updatedVolume);
        this.volume = updatedVolume;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int updatedChannel) {
        System.out.println("Updated radio channel to " + updatedChannel);
        this.channel = updatedChannel;
    }
}

public class Bridge {
    public static void main(String[] args) {
        TV tv = new TV();
        Radio radio = new Radio();
        RemoteControl tvRemote = new RemoteControl(tv);
        RemoteControl radioRemote = new RemoteControl(radio);
        tvRemote.togglePower();
        radioRemote.togglePower();

        tvRemote.volumeUp();
        radioRemote.volumeUp();

        tvRemote.channelUp();
        radioRemote.channelUp();

        tvRemote.volumeUp();
        radioRemote.volumeDown();

        tvRemote.channelDown();
        radioRemote.channelUp();

        tvRemote.togglePower();
        radioRemote.togglePower();

        tvRemote.channelUp();
        radioRemote.channelUp();

    }

}
