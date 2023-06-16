import java.util.*;
import java.math.*;

public class DijkstraShortestPath {
    public static void main(String[] args) {
        //Create a graph
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        //Create a scanner object to read the user's input
        Scanner scanner = new Scanner(System.in);

        //Prompt the user to enter the cities and destinations
        System.out.println("Enter the cities in the format 'source, distance, destination', or type 'stop' to finish:");

        //Loop through and read inputs until stop is entered
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                break;
            }

            //Split up the string
            String[] parts = input.split(", ");
            //Little bit of validation for the inputs
            if (parts.length != 3) {
                System.out.println("Invalid input format. Please try again.");
                continue;
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
                continue;
            }

            //Add the source and destination if there aren't any
            graph.putIfAbsent(source, new HashMap<>());
            graph.putIfAbsent(destination, new HashMap<>());
            graph.get(source).put(destination, distance);
        }

        //set the start and finish
        String sourceNode = "s";
        String targetNode = "f";

        //Intialize variables for distance and other nodes
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(sourceNode, 0);

        //Keep track of visited nodes
        Set<String> visited = new HashSet<>();

        //Dijkstra's Algorithm to find the fastest (shortest) path 
        //Big copy pasta from Chat-GPT for this
        while (!visited.contains(targetNode)) {
            String currentNode = null;
            int minDistance = Integer.MAX_VALUE;

            for (String node : graph.keySet()) {
                if (!visited.contains(node) && distances.get(node) < minDistance) {
                    currentNode = node;
                    minDistance = distances.get(node);
                }
            }

            if (currentNode == null) {
                break;
            }

            visited.add(currentNode);

            for (Map.Entry<String, Integer> neighbor : graph.get(currentNode).entrySet()) {
                int newDistance = distances.get(currentNode) + neighbor.getValue();
                if (newDistance < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDistance);
                    previousNodes.put(neighbor.getKey(), currentNode);
                }
            }
        }

        //Print the shortest distance, the path, and the time taken if you move at 17km/hr
        if (distances.get(targetNode) != Integer.MAX_VALUE) {
            System.out.println("Shortest distance from s to f: " + distances.get(targetNode) + " km");
            System.out.println("Path: " + getPath(previousNodes, targetNode));
            Double timeTaken = distances.get(targetNode)/17.0;
            Integer minutesTaken = (int) Math.round(timeTaken * 60);
            BigDecimal roundedTimeTaken = new BigDecimal(timeTaken).setScale(2, RoundingMode.HALF_UP);
            System.out.println("It will take " + roundedTimeTaken + " hours to take this path or " + minutesTaken + " minutes." );

        } else {
            System.out.println("No path exists from s to f.");
        }
    }

    //Method to reconstruct the shortest path
    private static String getPath(Map<String, String> previousNodes, String targetNode) {
        List<String> path = new ArrayList<>();
        path.add(targetNode);

        String currentNode = targetNode;

        //Go through the previous nodes to construct the path
        while (previousNodes.containsKey(currentNode)) {
            currentNode = previousNodes.get(currentNode);
            path.add(currentNode);
        }

        //Reverse the path and join with arrows into a string
        Collections.reverse(path);
        return String.join(" -> ", path);
    }
}
