package org.example;

import org.example.Constants.MetroCardConstants;
import org.example.Entities.Journey;
import org.example.EntityManager.CardManager;
import org.example.Enums.PassengerType;
import org.example.Enums.Station;
import org.example.Services.CardService;
import org.example.Services.JourneyService;
import org.example.Services.PrintingService;
import org.example.Services.implementation.CardServiceImplementation;
import org.example.Services.implementation.JourneyServiceImplementation;
import org.example.Services.implementation.PrintingServiceImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

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
            System.out.println("REACHING HERE!!");
            while(sc.hasNextLine()){
                String[] command = sc.nextLine().split(MetroCardConstants.SPACE, 2);
                switch (command[0]) {
                    case "BALANCE":
                        String[] cardProperties = command[1].split(MetroCardConstants.SPACE, 2);
//                        card number - cardProperties[0]
//                        card balance - Integer.parseInt(cardProperties[1])
                        cardService.addCard(cardProperties[0], Integer.parseInt(cardProperties[1]));
                        break;
                    case "CHECK_IN":
                        String[] journeyDetails = command[1].split(MetroCardConstants.SPACE, 3);
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
