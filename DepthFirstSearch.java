import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DepthFirstSearch {
    static String output = "";
    public static void dfs(Map<String, List<String>> graph, List<String> visited, String currentNode) {

        visited.add(currentNode);
        output += currentNode + " ";

        //List<String> neighbors = graph.get(currentNode);

        for (String neighbor : graph.get(currentNode)) {
            if (!visited.contains(neighbor)) {

                //recursively calling the depth first search to mark the unvisited neighbors of the current
                //node as visited
                dfs(graph, visited, neighbor);
            }
        }

    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int numberOfInstances = scnr.nextInt();
        scnr.nextLine();

        for (int i = 0; i < numberOfInstances; i++) {

            output = "";

            int numberOfNodes = scnr.nextInt();
            scnr.nextLine();

            //this hashmap contains the list of nodes of the graph
            Map<String, List<String>> graph = new HashMap<>();

            //the arraylist contains all the adjacent nodes to a node
            List<String> adjacentNodes = new ArrayList<>();

            for (int j = 0; j < numberOfNodes; j++) {

                String line = scnr.nextLine();
                String[] listOfNodes = line.split(" ");
                String node = listOfNodes[0];

                adjacentNodes.add(node);
                graph.put(node, new ArrayList<>());

                for (int k = 1; k < listOfNodes.length; k++) {
                    graph.get(node).add(listOfNodes[k]);
                }
            }

            //this arraylist stores the visited nodes from the graph
            List<String> visited = new ArrayList<>();
            for (String node : adjacentNodes) {

                //if the node isn't visited, then perform depth first search on it
                if (!visited.contains(node)) {
                    dfs(graph, visited, node);
                }
            }

            //print the outut
            System.out.println(output.trim());
        }
        scnr.close();
    }
}
