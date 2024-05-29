package com.tailored.cli.example;


import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Data
public class Login implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "-user"}, arity = "0..1", description = "User name")
    private String user;

    @CommandLine.Option(names = {"-p", "-password"}, description = "Passphrase", arity = "0..1", interactive = true)
    private String password;

    @CommandLine.Option(names = {"-cp", "-checkPassword"}, description = "Check Password", arity = "0..1", interactive = true)
    private String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        if (password == null) {
            password = System.console().readLine("Enter value for -p: ");
        }
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u", "user123");
    }
}
