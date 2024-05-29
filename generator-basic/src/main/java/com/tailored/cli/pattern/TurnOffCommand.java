package com.tailored.cli.pattern;

import cn.hutool.core.io.file.visitor.DelVisitor;

public class TurnOffCommand implements Command{

    private Device device;

    public  TurnOffCommand(Device device){
        this.device = device;
    }
    @Override
    public void execute() {
        device.turnOff();
    }
}
