package com.tailored.cli.pattern;

public class Client {

    public static void main(String[] args) {
        Device device = new Device("TV");
        Device stereo = new Device("Stereo");

        TurnOffCommand turnOffCommand = new TurnOffCommand(device);
        TurnOnCommand turnOnCommand = new TurnOnCommand(stereo);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressButton();
    }
}
