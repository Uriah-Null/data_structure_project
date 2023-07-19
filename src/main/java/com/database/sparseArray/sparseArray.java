package com.database.sparseArray;

import org.omg.Messaging.SyncScopeHelper;

import java.io.*;
import java.util.Scanner;

public class sparseArray {

    public static boolean createFile(String filename){
        try{
            File file = new File(filename);
            file.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeToFile(String filename, int[][] Array){
        try {
            FileWriter fileWriter = new FileWriter(filename);
            for(int arr[]:Array){
                for(int value:arr){
                    fileWriter.write(value + "\t");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        //create a 2-D array of size 11 by 11
        int[][] array = new int[11][11];

        //Assigns a value to the 2-D array.
        // 0:means no chess pieces.
        // 1:means black pieces.
        // 2:means blue pieces.
        array[1][2] = 1;
        array[2][3] = 2;

        //Prints the original 2-D array
        System.out.println("Original Array:");
        for(int[] row : array) {
            for(int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");

        //===========================================================================================================
        //The idea of converting the 2-D array to a sparse array
        //No.1:Traverse the 2-D array to obtain the number of non 0 data
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("The number of non o data: " + sum);
        System.out.println("--------------------------------");

        //No.2:Create a sparse array corresponding to this 2-D array
        int[][] sparseArray = new int[sum + 1][3];

        //Assigns a value to the sparse array
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //Traverse the 2-D array and put 0 data into this sparse array
        int count = 0;//Count is used to record the number of the non 0 data of this 2-D array
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }

        //Print the sparse array
        System.out.println();
        System.out.println("Sparse Array:");
        for (int[] row : sparseArray){
            for(int element : row) {
                System.out.print(element + "   ");
            }
            System.out.println();
        }

        System.out.println("--------------------------------");

        //===========================================================================================================
        //use functions to create a file and put data of the sparseArray into this file
        createFile("sparseArray.txt");
        writeToFile("sparseArray.txt", sparseArray);
        System.out.println("File written successfully!");
        System.out.println("--------------------------------");

        //===========================================================================================================
        //The idea of converting the sparse array to a 2-D array
        //No.1:Read the first row of a sparse array and create a 2-D array based on the data from the first row
        int[][] arrayTwo = new int[sparseArray[0][0]][sparseArray[0][0]];

        //===========================================================================================================
        //read data from file.
        int[][] sparseArrayTwo = new int[3][3];
        Scanner scanner = new Scanner(new File("sparseArray.txt"));
        while (scanner.hasNextLine()) {
            for (int i = 0; i < 3; i++) {
                String[] line = scanner.nextLine().split("\t");
                for (int j = 0; j < 3; j++) {
                    sparseArrayTwo[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        scanner.close();
        System.out.println("File read successful!");
        System.out.println("--------------------------------");

        //No.2:Travers the sparse array and put the number of non zero data into the new 2-D array
        for(int i = 1; i < sparseArrayTwo.length; i++){
            arrayTwo[sparseArrayTwo[i][0]][sparseArrayTwo[i][1]] = sparseArrayTwo[i][2];
        }

        //Print the new 2-D array
        System.out.println("The new Array:");
        for(int[] row : arrayTwo) {
            for(int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

}
