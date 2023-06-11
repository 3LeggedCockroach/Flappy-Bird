package p3_shawn_shahabi;

//Imports required classes
import java.awt.Color;
import java.util.Scanner;

public class P3_Shawn_Shahabi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int birdColorOption = 0;
        Color birdColor = null;

        //Repeats user options until user chooses valid input and catches invalid inputs
        do {
            try {
                System.out.println("Please choose what colour you want your bird to be: \n1. Blue \n2. Green \n3. Yellow \n4. Red");
                birdColorOption = scanner.nextInt();
                
                if (birdColorOption > 4 || birdColorOption < 1){
                    System.out.println("Please choose a number within the number range");
                }
            } catch (Exception error) {
                System.out.println("Please choose a valid input");
                birdColorOption = 0;
                scanner.nextLine();
            }
        } while (!(birdColorOption <= 4 && birdColorOption >= 1));
        
        if (birdColorOption == 1){
            birdColor = Color.blue;
        } else if (birdColorOption == 2){
            birdColor = Color.green;
        } else if (birdColorOption == 3){
            birdColor = Color.yellow;
        } else if (birdColorOption == 4){
            birdColor = Color.red;
        }
        
        App flappyBird = new App(birdColor); //Launches game
        flappyBird.setAlwaysOnTop(true); //Sets frame to be always on top of other frames
    }

}
