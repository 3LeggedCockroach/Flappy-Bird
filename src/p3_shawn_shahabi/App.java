package p3_shawn_shahabi;

//Imports required class
import java.awt.Color;
import javax.swing.*;

public class App extends JFrame {

    Color birdColor; //Allows birdColor to be passed from main method into other classes
            
    public App(Color birdColor) {
        super("Flappy Bird"); //Names the program
        
        //Displaying the canvas
        Canvas drawingCanvas = new Canvas(birdColor);
        setSize(1280, 720);
        setDefaultCloseOperation(3);
        add(drawingCanvas);
        setVisible(true);
        drawingCanvas.setFocusable(true);
    }
}
