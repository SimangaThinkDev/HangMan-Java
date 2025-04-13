package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main( String[] args ) {

        String word = "";
        char guess;

        try ( Scanner scanner = new Scanner( System.in ) ) {

            ArrayList<Character> puzzle = new ArrayList<>();
            int wrongGuesses = 0;

            // Fill the array with underscores, This has to be the same length as the puzzle word
            
            System.out.println("-----------------------");
            System.out.println("Welcome to Java Hangman");
            System.out.println("-----------------------");
            
            String difficulty;
            System.out.print( """
                Select Difficulty Level
                1. Easy
                2. Medium
                3. Hard
                4. I'm Feeling Lucky
                >> """ );
                difficulty = scanner.nextLine();
                
                while ( word.equals("") ) {
                    
                    switch ( difficulty.toLowerCase() ) {
                        
                        case "1", "easy" -> word = getFile( "easy.txt" );
                        case "2", "medium" -> word = getFile( "medium.txt" );
                        case "3", "hard" -> word = getFile( "hard.txt" );
                        case "4", "lucky" -> word = getFile( "lucky.txt" );
                        default -> word = "";
                        
                    }
                }

                
            for ( int i = 0; i < word.length() ; i++ ) {
                
                puzzle.add('_');
                
            }
            
            
            System.out.print("Word: ");
            do {
    
                // Fill the array with underscores to represent unguessed letters
                for ( char letter : puzzle ) {
                    System.out.print( letter + " " );
                } System.out.println(); // for new line after word

                System.out.print( "Guess a letter: " );
                
                guess = scanner.next().toLowerCase().charAt(0);

                // Check to see if guess matches any letter in our word
                if ( word.indexOf( guess ) >= 0 ) 
                {
                    System.out.println( "correct guess" );

                    for ( int i = 0 ; i < word.length() ; i++ ) {

                        if ( word.charAt(i) == guess ) {
                            puzzle.set(i, guess); // Assign the letter to the empty space
                        }

                    }

                    if ( !puzzle.contains('_') ) {

                        System.out.println(currentAscii(wrongGuesses));
                        System.out.println("YOU WIN!");
                        System.out.println( "the word was: " + word );
                        break;

                    }

                } 

                else 
                {
                    System.out.println( "Wrong guess" );
                    wrongGuesses++;
                }

                System.out.println(currentAscii(wrongGuesses));
                
            } while ( wrongGuesses < 6 );

            
            if ( wrongGuesses >= 6 ) {
                
                System.out.println(currentAscii(wrongGuesses));
                System.out.println( "YOU LOSE!" );
                System.out.println( "the word was: " + word );

            }


        }

        // System.out.println(getFile("easy.txt"));

    }

    /*
     * Method to show current ascii art
     * @param int wrongGuesses ~ the amount of current wrong guesses
     * @returns the ascii of the current state of the game
     */
    static String currentAscii( int wrongGuesses ) {

        return switch ( wrongGuesses ) {

            case 0 -> """
            


            """;

            case 1 -> """
                      o



                    """;

            case 2 -> """
                      o
                      |

                    """;

            case 3 -> """
                      o
                     /|

                    """;

            case 4 -> """
                      o
                     /|\\

                    """;

            case 5 -> """
                      o
                     /|\\
                     /
                    """;

            case 6 -> """
                      o
                     /|\\
                     / \\
                    """;

            default -> "";
        };

    }

    /*
     * This method works as a word getter for us
     * @param path The path where the words are stored, or the name of the txt file
     * @return String the word chosen at random from the txt file
     */
    public static String getFile( String path ) {

        File myFile = new File( path );

        ArrayList<String> theWords = new ArrayList<>();

        try ( Scanner myReader = new Scanner(myFile) ) {

            while (myReader.hasNextLine()) {

                theWords.add(myReader.nextLine());
                
                
            }

        } 
        catch (FileNotFoundException e) {

            System.out.println("Seems we had a problem getting quiz answers");
            e.printStackTrace();

        }
        
        int size = theWords.size();
        return theWords.get( ( int ) (Math.random() * size) );

    }


}
