package com.mars;

import static java.awt.SystemColor.text;

public class MarsRover {
    
    public static void main( String[] args ) {
        
        if ( args.length != 1 ) {
            System.out.println("Usage: " + MarsRover.class.getName() + " <File Location>");
            System.exit(1);
        }
        try {
            java.util.ArrayList<String> vLinesList = new java.util.ArrayList<>();
            try {
                java.io.File      vFile        = new java.io.File(args[0]);
                java.util.Scanner vScanner     = new java.util.Scanner(vFile);
                int               vLineCounter = 1;
                
                java.util.regex.Pattern vPlateauPattern      = java.util.regex.Pattern.compile("[0-9] [0-9]");
                java.util.regex.Pattern vInitPositionPattern = java.util.regex.Pattern.compile("[0-9] [0-9] [N|E|S|W]");
                
                while ( vScanner.hasNextLine() ) {
                    String                  vLine                = vScanner.nextLine();
                    java.util.regex.Pattern vDirectionsPattern   = java.util.regex.Pattern.compile("[RLM]+");
                    java.util.regex.Matcher vPlateauMatcher      = vPlateauPattern.matcher(vLine.trim());
                    java.util.regex.Matcher vDirectionsMatcher   = vDirectionsPattern.matcher(vLine.trim());
                    java.util.regex.Matcher vInitPositionMatcher = vInitPositionPattern.matcher(vLine.trim());
                    
                    if ( (vLineCounter == 1) && (!vPlateauMatcher.matches()) ) { // Plateau Size
                        throw new Exception("Line " + vLineCounter + " is an invalid plateau size. It should be <x> <y>");
                        
                    }else if ( (vLineCounter != 1 && (vLineCounter % 2 == 1)) && (!vDirectionsMatcher.matches()) ) { // Directions
                        throw new Exception("Line " + vLineCounter + " Contains a Letter other than L, R or M.");
                        
                    }else if ( (((vLineCounter % 2 == 0)) && !vInitPositionMatcher.matches()) ) { // Initial Position
                        throw new Exception("Line " + vLineCounter + ": " + vLine + " is an invalid Inital position. It should be <x> <y> <Compass direction>");
                    }
                    vLinesList.add(vLine);
                    vLineCounter++;
                }
                vScanner.close();
            }catch ( java.io.FileNotFoundException aFileNotFoundException ) {
                System.out.println(aFileNotFoundException.getMessage());
            }
            
            int vCounter     = 1;
            int vRoverNumber = 1;
            while ( vCounter + 2 <= vLinesList.size() ) {
                com.mars.Rover vRover = new com.mars.Rover("Rover " + vRoverNumber ,vLinesList.get(vCounter++));
                HandleInput(vRover ,vLinesList.get(vCounter++) ,vLinesList.get(0));
            }
        }catch ( Exception aException ) {
            System.out.println(aException.getMessage());
            System.exit(1);
        }
    }
    
    protected static void HandleInput( com.mars.Rover aRover ,String aCommandList ,String aPlateauSize )
    throws Exception {
        
        String[] vCommands = aCommandList.trim().split("");
        
        for ( String vCommand : vCommands ) {
            if ( "L".equalsIgnoreCase(vCommand) ) {
                aRover.TurnLeft();
            }else if ( "R".equalsIgnoreCase(vCommand) ) {
                aRover.TurnRight();
            }else {
                aRover.Forward();
            }
        }
        int aPlateauX = Integer.parseInt(aPlateauSize.substring(0 ,1));
        int aPlateauY = Integer.parseInt(aPlateauSize.substring(2 ,3));
        if ( (aRover.getX() > aPlateauX) || (aRover.getY() > aPlateauY) ) {
            throw new Exception("Rover: " + aRover.getName() + " invalid End position. Plateau: " + aPlateauSize + ", Rover position: " + aRover);
        }else {
            System.out.println(aRover);
        }
    }
}
