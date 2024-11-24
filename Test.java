/*
file name:      Test.java
Authors:        Robbie Bennett
last modified:  10/29/2024
Class: CS231
How To Run: (Consult the README.txt file)
*/

public class Test {

    public static void main(String[] args) {
        //Main method for the Sudoku solve methods.
        
        int successRate1 = 0;
        int lossRate1 = 0;
        int successRate2 = 0;
        int lossRate2 = 0;
        int amountOfInitialCells =20;
        int trials = 50;

        //Test1 method for solve method 1 (do not uncomment.)
        
        // for(int i = 0; i < trials; i++){
        //     Sudoku sudokuEmpty = new Sudoku(amountOfInitialCells);
        //     if (sudokuEmpty.solve() == true && sudokuEmpty.solve() ) {
        //         successRate1 ++;
        //     } else {
        //         lossRate1 ++;
                
        //     }
        // }

        // System.out.println("The succuess rate for findCell1 with " + amountOfInitialCells + " initial values:  " + successRate1*1.0 / trials);
        // System.out.println();
        // System.out.print("The fail rate for findCell1 with " + amountOfInitialCells + " initial values:  " + lossRate1 * 1.0 / trials);
        // System.out.println();



        //Tester method for solve method 2 (do not uncomment.).
        
        for(int j = 0; j < trials; j++){
            Sudoku sudokuEmpty2 = new Sudoku(amountOfInitialCells);
            if (sudokuEmpty2.solve2() == true && sudokuEmpty2.solve() ) {
                successRate2 ++;
    
            } else {
                lossRate2 ++;
            }
        }

        // System.out.println("The succuess rate for findCell2 with " + amountOfInitialCells + " initial values:  " + successRate2*1.0 / trials);
        // System.out.println();
        // System.out.print("The fail rate for findCell2 with " + amountOfInitialCells + " initial values:  " + lossRate2 * 1.0 / trials);    



        //Tester method for solve method 2, counting the different rates of success for differnet amounts of initial values (do not uncomment.).
        
        // for(int q = 0; q < trials; q++){
        //     Sudoku sudokuEmpty2 = new Sudoku(amountOfInitialCells);
        //     if (sudokuEmpty2.solve2() == true && sudokuEmpty2.solve2() ) {
        //         successRate1 ++;
    
        //     } else {
        //         lossRate1 ++;
        //     }
        // }

        // System.out.println("The succuess rate for games with " + amountOfInitialCells + " initial values:  " + successRate1*1.0 / trials);
        // System.out.println("Number of wins:  " + successRate1);
        // System.out.println();
        // System.out.println("The fail rate for games with " + amountOfInitialCells + " initial values:  " + lossRate1 * 1.0 / trials);
        // System.out.println("Number of losses:  " + lossRate1);



        //Extension Test (do not uncomment.):

        //To change the amount of viable options the method should look for, change the int parameter in both solve3 methods down below (make sure they are the same.) (do not uncomment.)

    //     for(int i = 0; i < trials; i++){
    //         Sudoku sudokuEmpty = new Sudoku(amountOfInitialCells);
    //         if (sudokuEmpty.solve3(1) == true && sudokuEmpty.solve3(1) ) {
    //             successRate1 ++;
    //         } else {
    //             lossRate1 ++;
                
    //         }
    //     }

    //     System.out.println("The succuess rate for findCell1 with " + amountOfInitialCells + " initial values:  " + successRate1*1.0 / trials);
    //     System.out.println();
    //     System.out.print("The fail rate for findCell1 with " + amountOfInitialCells + " initial values:  " + lossRate1 * 1.0 / trials);
    //     System.out.println();
    
    
    }
}