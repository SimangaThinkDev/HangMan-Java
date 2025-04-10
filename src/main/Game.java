package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main( String[] args ) {

        String word = "anonymous";
        char guess;

        try ( Scanner scanner = new Scanner(System.in) ) {

            ArrayList<Character> puzzle = new ArrayList<>();
            int wrongGuesses = 0;

            // Fill the array with underscores, This has to be the same length as the puzzle word
            for ( int i = 0; i < word.length() ; i++ ) {
                puzzle.add('_');
            }

            System.out.println("-----------------------");
            System.out.println("Welcome to Java Hangman");
            System.out.println("-----------------------");
            
            do {
                System.out.print("Word: ");
    
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
                } 

                else 
                {
                    System.out.println( "Wrong guess" );
                    wrongGuesses++;
                }

                System.out.println(currentAscii(wrongGuesses));
                
            } while ( wrongGuesses < 6 );

            System.out.println(currentAscii(wrongGuesses));

            if ( wrongGuesses >= 6 ) {

                System.out.println( "YOU LOSE!" );
                System.out.println( "the word was: " + word );

            }


        }

    }

    // Method to show current ascii art
    // @param int wrongGuesses ~ the amount of current wrong guesses
    // @returns the ascii of the current state of the game
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


}
