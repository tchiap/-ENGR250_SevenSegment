/**
 * Tom Chiapete
 * Engr 250 - Digital Logic
 * November 5, 2005
 * 
 * Project:
 * Truth Table for BCD-to-Seven-Segment Decoder
 * 
 * Purpose/Description:
 * 1) Generates a table of binary numbers from 0000 to 1001
 * 2) Since the machine understands boolean bits, 0's and 1's, convert all int 
 * 0's and 1's generated to boolean falseshoods and truths.  Store that in 
 * an array.
 * 3) Plug in all inputs, W, X, Y, Z's into the equation given from the 
 * Karnaugh Maps.  A two dimentional array will be used to store all the 
 * outputs based on the inputs.
 * 4) If the user chooses to, they can similate all the logic by opening up 
 * a clock.  The terminal will ask what the clock speed should be given in
 * milliseconds.
 * 
 * Known Bugs:
 * None
 * 
 * Imports Libraries:
 * - java.util.* for Scanner class input
 * - acm.* for simple graphic displays
 * - java.lang.Thread for system pauses
 * 
 * This class, BCDtoSevenSeg extends the GraphicsProgram class.
 * 
 */

// Imported Libraries
import java.util.*;
import acm.graphics.*;
import acm.program.*;
import java.lang.Thread;

public class BCDtoSevenSeg extends GraphicsProgram
{   
    // Default Constructor
    public BCDtoSevenSeg()
    {

    }
    
    /**
     * main() method
     * Builds Truth Table for BCD-to-Seven-Segment Decoder table
     * and clock to simulate logic
     */
    public static void main(String [] args)
    {
        int clockSpeed = -1;  // clock speed variable if not given in 
                              // command line arguments.
        
        // Check command line arguments.  First arg is clock speed
        // If nothing is supplied, ask user later.
        if (args.length == 1)
        {
            clockSpeed = Integer.parseInt(args[0]);   
        }
        BCDtoSevenSeg sevenSegWin = new BCDtoSevenSeg(); // Create a new
                                                // graphics window object
        
        String [] binaryCounter = new String [10]; // List binary counter in array
        
        // Convert 0000, 0001, 0010 ... to boolean equivalents and store in 2D array
        boolean [][] boolInputs = new boolean [10][4];
        
        // Initialize array to store
        boolean [][] boolOutputs = new boolean [10][7];
        
        // Declare an initialize boolean input variables.
        boolean W = false; // input W
        boolean X = false; // input X
        boolean Y = false; // input Y
        boolean Z = false; // input Z
        
        // Generate BCD inputs table (4-bit)
        for(int x = 0; x < 10; x++)
        {
            // Add extra 0's when needed to create a 4-bit number.
            Integer y = new Integer(x);
            String binary = y.toBinaryString(y);
            int z = binary.length();
            for (;z<4;z++)
            {
                binary = "0" + binary;
                
            }
            binaryCounter[x] = binary; // Add to binaryCounter array
            
        }

        // Generate BCD inputs table as truth/falsehood booleans.
        for (int m = 0; m < 10; m++)
        {
            for (int n = 0; n < 4; n++)
            {
                // Translate 0's and 1's to T/F booleans.
                if(binaryCounter[m].substring(n,n+1).equals("1"))
                    boolInputs[m][n] = true;
                else
                    boolInputs[m][n] = false;
            }
        }
        
        int segment = 0; // segment variable as a = 0, b = 1, c = 2 ...
        
        // Solve equations using inputs from 2D array created earlier.
        for (; segment < 7; segment++)
        {
            for (int p = 0; p < 10; p++)
            {
                // Plug in inputs as given from input 2D array
                W = boolInputs[p][0];
                X = boolInputs[p][1];
                Y = boolInputs[p][2];
                Z = boolInputs[p][3];
                
                // Solve equations
                if (segment == 0) // Segment a
                {
                    boolOutputs[p][segment] = ((!X && !Z) || (Y && Z) 
                        || (X && Z) || W) ? true : false;
                }
                else if (segment == 1) // Segment b
                {
                    boolOutputs[p][segment] = ((!Y && !Z) || (Y && Z) 
                        || !X) ? true : false; 
                }
                else if (segment == 2) // Segment c
                {
                    boolOutputs[p][segment] = (X || Z || !Y) ? true : false;   
                }
                else if (segment == 3) // Segment d
                {
                    boolOutputs[p][segment] = (X && !Y && Z) || (!X && !Z) ||
                        (!X && Y) || (Y && !Z) ? true : false;
                }
                else if (segment == 4) // Segment e
                {
                    boolOutputs[p][segment] = (!X && !Z) || (Y && !Z) 
                        ? true : false;
                }
                else if (segment == 5) // Segment f
                {
                    boolOutputs[p][segment] = ((!Y && !Z) || (X && !Y) || 
                        (X && !Z) || W) ? true : false;
                }
                else if (segment == 6) // Segment g
                {
                    boolOutputs[p][segment] = ((!X && Y) || (X && !Y) ||
                        (X && !Z) || W) ? true : false;
                }
            }  
        }
        
        // Table output
        // Calls private method to translate T/F back to neater 0's and 1's.
        System.out.println("Truth Table for BCD-to-Seven-Segment Decoder");
        System.out.println("--------------------------------------------");
        System.out.println("BCD Input      Seven-Segment Decoder");
        System.out.println("=========      =====================");
        System.out.println(" W X Y Z          a b c d e f g");
        System.out.println(" - - - -          - - - - - - -");
        for (int s = 0; s < 10; s++)
        {
            System.out.print(" "+booleanToBinary(boolInputs[s][0])+" ");
            System.out.print(booleanToBinary(boolInputs[s][1])+" ");
            System.out.print(booleanToBinary(boolInputs[s][2])+" ");
            System.out.print(booleanToBinary(boolInputs[s][3])+" ");
            
            System.out.print("         ");
            System.out.print(booleanToBinary(boolOutputs[s][0])+" ");
            System.out.print(booleanToBinary(boolOutputs[s][1])+" ");
            System.out.print(booleanToBinary(boolOutputs[s][2])+" ");
            System.out.print(booleanToBinary(boolOutputs[s][3])+" ");
            System.out.print(booleanToBinary(boolOutputs[s][4])+" ");
            System.out.print(booleanToBinary(boolOutputs[s][5])+" ");
            System.out.print(booleanToBinary(boolOutputs[s][6])+" ");
            System.out.println();
        }
        
        // Asks whether or not to turn on counter.
        int speed; // Clock speed variable to store user input speed.
        
        /**
         * If no command line argument was supplied earlier, ask the user whether or
         * not they want to turn on the clock.
         * If so, ask what speed they would like to set the clock at.
         * If not, end program.
         */
        if (clockSpeed == -1)
        {
            System.out.print("Enable counter? (Y/N): ");
            Scanner input = new Scanner(System.in);
            String answer = input.next();
            // Convert input to lowercase... accept "y" and "yes" as the open command 
            // for the clock.
            if (!(answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")))
            {    
                System.out.print("Finished...");
                System.exit(1); // Exit program.
            }
        
            // Speed input
            System.out.println();
            System.out.println("Enter counter speed in milliseconds: ");
            System.out.println("(1000 milliseconds = 1 second)");
            System.out.print(">>>  ");
            speed = input.nextInt();
        }
        else
            speed = clockSpeed;
        
        // Create a visual counter
        // Open window.
        sevenSegWin.start();
        sevenSegWin.resize(100,200);
        sevenSegWin.setTitle("7-Segment Display");
        
        // Draw line segment a.
        GLine a = new GLine(10,10,50,10);
        a.setColor(BLUE);
        sevenSegWin.add(a);
        
        // Draw line segment b.
        GLine b = new GLine(50,10,50,50);
        b.setColor(BLUE);
        sevenSegWin.add(b);
        
        // Draw line segment c.
        GLine c = new GLine(50,50,50,90);
        c.setColor(BLUE);
        sevenSegWin.add(c);
        
        // Draw line segment d.
        GLine d = new GLine(50,90,10,90);
        d.setColor(BLUE);
        sevenSegWin.add(d);
        
        // Draw line segment e.
        GLine e = new GLine(10,90,10,50);
        e.setColor(BLUE);
        sevenSegWin.add(e);
        
        // Draw line segment f.
        GLine f = new GLine(10,50,10,10);
        f.setColor(BLUE);
        sevenSegWin.add(f);
        
        // Draw line segment g.
        GLine g = new GLine(10,50,50,50);
        g.setColor(BLUE);
        sevenSegWin.add(g);
        
        // To make sure the counter goes from 0 to 1 to 2 to 3 ...
        // use the last digit of x as the digit displayed.
        String allPlaces = "";
        String onesPlace = "";
        int places = 0;
        int onesPlaceInt = 0;
        
        /**
         * 
         */
        for (int x = 0; x < 100000; x++)
        {
            Integer i = new Integer(x); // Create an Integer object
            allPlaces = i.toString(); // convert Integer object i to a string
            places = allPlaces.length(); // Find how many digits are in x
            onesPlace = allPlaces.substring(places-1,places); // Get last digit
            onesPlaceInt = Integer.parseInt(onesPlace); // Convert back to integer.
            
            // Set segments a through g to not visible.
            // For an updating clock, we need to clear previous results too.
            a.setVisible(false);
            b.setVisible(false);
            c.setVisible(false);
            d.setVisible(false);
            e.setVisible(false);
            f.setVisible(false);
            g.setVisible(false);
            
            // Checks each element to see if there is a truth.
            // A truth means we must turn on the segment.
            if (boolOutputs[onesPlaceInt][0])
                a.setVisible(true);   
            if (boolOutputs[onesPlaceInt][1])
                b.setVisible(true);   
            if (boolOutputs[onesPlaceInt][2])
                c.setVisible(true);   
            if (boolOutputs[onesPlaceInt][3])
                d.setVisible(true);   
            if (boolOutputs[onesPlaceInt][4])
                e.setVisible(true);  
            if (boolOutputs[onesPlaceInt][5])
                f.setVisible(true);   
            if (boolOutputs[onesPlaceInt][6])
                g.setVisible(true); 
            
            // Delay based on speed variable.
            try
            {
                Thread.sleep(speed);  // Sleep / delay method.
            }
            catch (Throwable t) 
            {
                //.. try/catch block required with Thread
            }
            // ... and loop again and again to simulate a counter.
        }
    }
    
    /**
     * booleanToBinary() method (private)
     * A very simple method that turns a boolean false or true 
     * into its equivalent 0 or 1.
     * The method was created to save space for a repeating action.
     */
    private static int booleanToBinary(boolean isTrue)
    {
        if(isTrue)
            return 1;
        return 0;
    }
}
