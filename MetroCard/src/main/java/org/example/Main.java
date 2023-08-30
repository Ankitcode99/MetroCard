package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MetroStationService metroStationService = new MetroStationService();
        System.out.print(args[0]);
        metroStationService.start(args);
    }
}