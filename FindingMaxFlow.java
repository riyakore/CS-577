import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class FindingMaxFlow {
    public static int V; // Number of vertices
    public static int[][] graph;
    public static int[] parent;

    // Use BFS to find an augmenting path in the residual graph
    public static boolean bfs(int source, int sink) {
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[sink];
    }

    // Find the maximum flow from source to sink in the graph
    public static int maxFlow(int source, int sink) {
        int maxFlow = 0;

        while (bfs(source, sink)) {
            int pathFlow = Integer.MAX_VALUE;
            int s = sink;

            while (s != source) {
                int u = parent[s];
                pathFlow = Math.min(pathFlow, graph[u][s]);
                s = u;
            }

            s = sink;

            while (s != source) {
                int u = parent[s];
                graph[u][s] -= pathFlow;
                graph[s][u] += pathFlow;
                s = u;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of instances

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt(); // Number of nodes
            int m = sc.nextInt(); // Number of edges
            V = n;
            graph = new int[n][n];
            parent = new int[n];

            for (int j = 0; j < m; j++) {
                int u = sc.nextInt() - 1; // Adjust for 0-based indexing
                int v = sc.nextInt() - 1; // Adjust for 0-based indexing
                int capacity = sc.nextInt();
                graph[u][v] += capacity; // Add the edge
            }

            int source = 0; // Source node is always node 1 (0-based indexing)
            int sink = n - 1; // Sink node is always the last node (0-based indexing)
            int maxFlowValue = maxFlow(source, sink);

            System.out.println(maxFlowValue);
        }

        sc.close();
    }
}
