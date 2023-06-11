package p3_shawn_shahabi;

//Imports required classes
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener, ActionListener {

    //Creates variables
    int maxX = 1280;
    int maxY = 720;
    int pipe1Y1;
    int pipe1Y2;
    int pipe2Y1;
    int pipe2Y2;
    int pipeSpeed = 6;
    int birdX = 150;
    int birdY;
    int gravity;
    int birdSize = 50;
    int pipeX1;
    int pipeX2;
    int score;
    int speedY;
    Color birdColor;
    boolean alive;
    boolean secondPipe;
    Color dirtColor = new Color(222, 150, 100);
    Color pipeColor = new Color(10, 200, 10);
    Timer timer;
    Font gameFont = new Font("Ariel", Font.BOLD, 69);

    public Canvas(Color birdColor) {
        //Sets game parameters
        this.addKeyListener(this);
        this.setFocusable(true);
        this.timer = new Timer(17, this);
        timer.start();
        pipe1Y1 = (int) (Math.random() * (maxY - 380) + 100);
        pipe1Y2 = pipe1Y1 + 180;
        pipeX2 = maxX + (maxX / 2 + 50);
        pipe2Y1 = (int) (Math.random() * (maxY - 380) + 100);
        pipe2Y2 = pipe2Y1 + 180;
        pipeX1 = maxX;
        birdY = 100;//maxY / 2 + birdSize / 2;
        gravity = -1;
        score = 0;
        speedY = 5;
        alive = true;
        secondPipe = false;
        this.birdColor = birdColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            speedY = 10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint(); //Updates the game
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        this.requestFocus();

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Smoothens the game
        super.paintComponent(g); //Clears canvas before drawing updated positions

        //Moves the game
        speedY += gravity;
        pipeX1 -= pipeSpeed;
        pipeX2 -= pipeSpeed;
        birdY -= speedY;

        //Creates sky
        g2D.setColor(Color.CYAN);
        Rectangle2D.Double sky = new Rectangle2D.Double(0, 0, maxX, maxY);
        g2D.fill(sky);

        //Creates cloud
        g2D.setColor(Color.white);
        Ellipse2D.Double[] clouds = {
            new Ellipse2D.Double(maxX - 200, maxY - 150, 150, 160),
            new Ellipse2D.Double(maxX - 350, maxY - 230, 200, 240),
            new Ellipse2D.Double(maxX - 450, maxY - 170, 230, 180),
            new Ellipse2D.Double(maxX - 650, maxY - 210, 250, 220),
            new Ellipse2D.Double(maxX - 750, maxY - 190, 210, 200)
        };
        for (Ellipse2D.Double cloud : clouds) {
            g2D.fill(cloud);
        }

        //Draws first pipe
        g2D.setColor(pipeColor);
        Rectangle2D.Double[] pipe1 = {
            new Rectangle2D.Double(pipeX1, 0, 50, pipe1Y1),
            new Rectangle2D.Double(pipeX1, pipe1Y2, 50, maxY)
        };
        for (Rectangle2D.Double pipe : pipe1) {
            g2D.fill(pipe);
        }

        //Draws the second pipe
        Rectangle2D.Double[] pipe2 = {
            new Rectangle2D.Double(pipeX2, 0, 50, pipe2Y1),
            new Rectangle2D.Double(pipeX2, pipe2Y2, 50, maxY)
        };
        for (Rectangle2D.Double pipe : pipe2) {
            g2D.fill(pipe);
        }

        //Creates grass
        g2D.setColor(Color.GREEN);
        Rectangle2D.Double grass = new Rectangle2D.Double(0, maxY - 100, maxX, 20);
        g2D.fill(grass);

        //Creates dirt
        g2D.setColor(dirtColor);
        Rectangle2D.Double dirt = new Rectangle2D.Double(0, maxY - 80, maxX, 80);
        g2D.fill(dirt);

        //Runs the game while the bird is still alive
        if (alive == true) {

            //Resets the first pipe's position once it has reached the end of the screen
            if (pipeX1 < 1) {
                pipe1Y1 = (int) (Math.random() * (maxY - 380) + 100);
                pipe1Y2 = pipe1Y1 + 180;
                pipeX1 = maxX;
            }

            //Resets the second pipe's position once it has reached the end of the screen
            if (pipeX2 < 1) {
                pipe2Y1 = (int) (Math.random() * (maxY - 380) + 100);
                pipe2Y2 = pipe2Y1 + 180;
                pipeX2 = maxX;
            }

            //Increases the score once bird is in middle of pipe
            if ((pipeX1 >= 147 && pipeX1 <= 153) || (pipeX2 >= 147 && pipeX2 <= 153)) {
                score++;
            }

            //Prevents bird from flying off the screen
            if (birdY <= 0) {
                birdY = 1;
            }

            //Kills bird if it touches the ground and prevents it from falling through grass
            if (birdY + birdSize >= maxY - 100) {
                alive = false;
            }

            //Kills bird if it touches the pipe
            if ((((birdX + birdSize) >= pipeX1 && birdX <= (pipeX1 + 50)) && birdY <= pipe1Y1) || (((birdX + birdSize) >= pipeX1 && birdX <= (pipeX1 + 50)) && (birdY + birdSize) >= pipe1Y2) || (((birdX + birdSize) >= pipeX2 && birdX <= (pipeX2 + 50)) && birdY <= pipe2Y1) || (((birdX + birdSize) >= pipeX2 && birdX <= (pipeX2 + 50)) && (birdY + birdSize) >= pipe2Y2)) {
                alive = false;
            }
        }

        //If bird dies, the game stops and bird falls to the ground
        if (alive == false) {
            pipeSpeed = 0;

            if ((birdY + birdSize) < (maxY - 100)) {
                speedY = -15;
                gravity = 0;
            } else {
                speedY = 0;
                birdY = maxY - birdSize - 100;
            }

            //Adds game over message
            g2D.setColor(Color.orange);
            g2D.setFont(gameFont);
            g2D.drawString("Game Over", maxX / 3, maxY / 2);
        }
        
        //Displays score
        g2D.setColor(Color.white);
        g2D.setFont(gameFont);
        g2D.drawString("" + score, maxX / 2 - 40, 80);

        //Draws bird
        g2D.setColor(birdColor);
        Ellipse2D.Double bird = new Ellipse2D.Double(birdX, birdY, birdSize, birdSize);
        g2D.fill(bird);
    }
}
