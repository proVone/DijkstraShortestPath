import java.util.*;
import java.math.*;
import java.io.*;

public class DijkstraShortestPath {
    public static void main(String[] args) {
        //Create a graph
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        //Create a scanner object to read the user's input
        Scanner scanner = new Scanner(System.in);

        //Prompt the user to enter the cities and destinations
        System.out.println("Would you like to input data directly or from a .csv file? Type 'direct' or 'file':");

        String inputType = scanner.nextLine();

        try {
            if (inputType.equalsIgnoreCase("direct")) {
                System.out.println("Enter the cities in the format 'source, distance, destination', or type 'stop' to finish:");

                //Loop through and read inputs until stop is entered
                while (true) {
                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("stop")) {
                        break;
                    }

                    addDataToGraph(graph, input);
                }
            } else if (inputType.equalsIgnoreCase("file")) {
                System.out.println("Enter the .csv file path:");

                String filePath = scanner.nextLine();

                //Open the .csv file
                File file = new File(filePath);
                BufferedReader br = new BufferedReader(new FileReader(file));

                //Read each line
                String line;
                while ((line = br.readLine()) != null) {
                    addDataToGraph(graph, line);
                }

                br.close();
            } else {
                System.out.println("Invalid input. Please restart the program and choose 'direct' or 'file'.");
                return;
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found. Please check your file path and try again.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file. Please try again.");
        }

        //...
        //Continue with the rest of the program.
    }

    //Method to add data to graph
    private static void addDataToGraph(Map<String, Map<String, Integer>> graph, String input) {
        //Split up the string
        String[] parts = input.split(", ");
        //Little bit of validation for the inputs
        if (parts.length != 3) {
            System.out.println("Invalid input format. Please try again.");
            return;
        }

        //extract source destination and distance from the inputs
        String source = parts[0];
        String destination = parts[2];
        int distance;

        //Parse the distance as an integer
        try {
            distance = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid distance format. Please enter a valid integer distance.");
            return;
        }

        //Add the source and destination if there aren't any
        graph.putIfAbsent(source, new HashMap<>());
        graph.putIfAbsent(destination, new HashMap<>());
        graph.get(source).put(destination, distance);
    }
    //...
    //Continue with the rest of the program.
}
