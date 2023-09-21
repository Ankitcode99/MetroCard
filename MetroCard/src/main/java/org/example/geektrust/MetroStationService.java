package org.example.geektrust;

import org.example.geektrust.Constants.MetroCardConstants;
import org.example.geektrust.Entities.Journey;
import org.example.geektrust.Enums.PassengerType;
import org.example.geektrust.Enums.Station;
import org.example.geektrust.Services.CardService;
import org.example.geektrust.Services.JourneyService;
import org.example.geektrust.Services.PrintingService;
import org.example.geektrust.Services.implementation.CardServiceImplementation;
import org.example.geektrust.Services.implementation.JourneyServiceImplementation;
import org.example.geektrust.Services.implementation.PrintingServiceImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.example.geektrust.Constants.MetroCardConstants.THREE;
import static org.example.geektrust.Constants.MetroCardConstants.TWO;

public class MetroStationService {

    private JourneyService journeyService;
    private PrintingService printingService;
    private CardService cardService;

    public MetroStationService() {
        journeyService = new JourneyServiceImplementation();
        printingService = new PrintingServiceImplementation();
        cardService = new CardServiceImplementation();
    }

    public void start(String[] args) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fileInputStream);
            while(sc.hasNextLine()){
                String[] command = sc.nextLine().split(MetroCardConstants.SPACE, TWO);
                switch (command[0]) {
                    case "BALANCE":
                        String[] cardProperties = command[1].split(MetroCardConstants.SPACE, TWO);
                        cardService.addCard(cardProperties[0], Integer.parseInt(cardProperties[1]));
                        break;
                    case "CHECK_IN":
                        String[] journeyDetails = command[1].split(MetroCardConstants.SPACE, THREE);
                        journeyService.checkInPassenger(new Journey(journeyDetails[0], PassengerType.valueOf(journeyDetails[1]), Station.valueOf(journeyDetails[2])));
                        break;
                    case "PRINT_SUMMARY":
                        printingService.printSummary();
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            throw new IOException("Error while reading input");
        }
    }
}
