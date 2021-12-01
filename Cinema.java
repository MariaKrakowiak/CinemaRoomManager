package cinema;

import java.util.Scanner;

public class Cinema {

    private static final Scanner scanner = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }


    private static void printHeaderCinema(int rows) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i < rows + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int iloczyn(int rows, int seats) {
        return rows * seats;
    }

    private static String[][] createCinema(int rows, int seats) {
        String[][] cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }

        return cinema;
    }

    private static void printCinema(String[][] arr) {
        printHeaderCinema(arr[1].length);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < arr[1].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static int suma = 0;
    private static int licznik = 0;

    private static String[][] buyTicket(String[][] arr) throws ArrayIndexOutOfBoundsException {


        System.out.println("Enter a row number: ");
        int yPos = scanner.nextInt() - 1;
        System.out.println("Enter a seat number in that row:");
        int xPos = scanner.nextInt() - 1;


        if (yPos < 0 || yPos > arr[1].length || xPos < 0 || xPos > arr[1].length) {


            System.out.println("Wrong input!");
            buyTicket(arr);


        } else {

            try {
                if ((arr[yPos][xPos] != null) && arr[yPos][xPos].equals("S") && !arr[yPos][xPos].equals("B")) {
                    arr[yPos][xPos] = "B";
                    licznik++;

                    System.out.println("Ticket price: $" + getTicketPrice(arr.length, arr[1].length, yPos));
                    suma = suma + getTicketPrice(arr.length, arr[1].length, yPos);
                } else {


                    System.out.println("That ticket has already been purchased!");

                    buyTicket(arr);


                }


            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
                buyTicket(arr);
            }

        }
        return arr;
    }

    private static int getTicketPrice(int rows, int seats, int rowno) {
        if (rows <= 9 && seats <= 9) {
            if (rows * seats <= 60) {
                return 10;
            } else {

                double pol = Math.ceil(rows / 2) - 1;
                if (rowno <= pol) {
                    return 10;
                } else {
                    return 8;
                }

            }
        }
        return 8;
    }


    private static int getSumTicketPrice(int row, int seat) {
        if (row <= 9 && seat <= 9) {
            if (row * seat <= 60) {
                return (row * seat * 10);
            } else {
                double pol = Math.floor(row / 2);
                return (int) (pol * seat * 10 + (row - pol) * seat * 8);
            }


        }


        return 0;
    }

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        String[][] matrix = createCinema(rows, seats);
        printMenu();
        int choose = scanner.nextInt();
        while (choose != 0) {
            switch (choose) {
                case 1:
                    printCinema(matrix);
                    printMenu();
                    break;
                case 2:
                    buyTicket(matrix);

                    printMenu();
                    break;
                case 3:
                    System.out.println("Number of purchased tickets:" + " " + licznik);
                    double procent = ((double) licznik * 100) / iloczyn(rows, seats);

                    String str = "Percentage: %.2f%s";
                    str = str.replace(',', '.');
                    System.out.printf(str, procent, "%\n");
                    System.out.println("Current income:" + " " + "$" + suma);
                    int ilosc = getSumTicketPrice(rows, seats);
                    System.out.println("Total income:" + " " + "$" + ilosc);
                    printMenu();
                    break;

            }

            choose = scanner.nextInt();

        }
    }
}

