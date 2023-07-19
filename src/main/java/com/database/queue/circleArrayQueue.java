package com.database.queue;

import java.util.Scanner;

public class circleArrayQueue {
    public static void main(String[] args) {
        //create a CircleArray queue
        CircleQueue arrayQueue =  new CircleQueue(4);//create a CircleArray valid size is 4
        char key = ' ';//received user input
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //create a list
        while(loop){
            System.out.println("----------------------------------------");
            System.out.println("1. Add data into the queue");
            System.out.println("2. Get data from the queue");
            System.out.println("3. Show data from the queue");
            System.out.println("4. Show data of the head of the queue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            key = scanner.next().charAt(0);//received user input
            switch (key){
                case '1':
                    System.out.print("Enter data: ");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case '2':
                    try{
                        System.out.println("Data: " + arrayQueue.getQueue());
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case '3':
                    arrayQueue.showQueue();
                    break;
                case '4':
                    try{
                        System.out.println("The data of the head: " + arrayQueue.headQueue());
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case '5':
                    scanner.close();//close the scanner
                    loop = false;//end of loop
                    break;
                default:
                    break;
            }
        }
        System.out.println("The process finished");
    }
}

class CircleQueue{
    private int maxSize;//means the max size of the array
    private int front;//define the front of the queue
    private int rear;//define the rear of the queue
    private int[] arr;//define the data of the array,compile the queue

    public CircleQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//print to the front of the head of the queue
        rear = 0;//print to the last of the queue,include the last data of the queue
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //get the initial size
    public int getSize(){
        return (rear + maxSize - front) % maxSize;
    }

    //add data into the queue
    public void addQueue(int n){
        //determine if the queue full
        if(isFull()){
            System.out.println("Queue is full! Can't add data into the quere.");
            return;
        }
        //add data into the queue
        arr[rear] = n;
        rear = (rear + 1) % maxSize;//move rear to the next space
    }

    //get data from the queue
    public int getQueue() {
        //determine if the queue is empty
        if (isEmpty()) {
            //by throw exception
            throw new RuntimeException("Queue is empty");
        }
        //define a temporary variable
        int temporary = arr[front];
        front = (front + 1) % maxSize;//move front to the next space
        return temporary;
    }

    //show all data in the queue
    public void showQueue(){
        //determine if the queue empty
        if(isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        for (int i = front; i < (front + getSize()); i++) {
            System.out.print(arr[i % maxSize] + " ");
        }
        System.out.println();
    }

    //show the data of the head of the queue
    public int headQueue(){
        //determine if the queue is empty
        if (isEmpty()) {
            //by throw exception
            throw new RuntimeException("Queue is empty");
        }
        return arr[front % maxSize];
    }
}