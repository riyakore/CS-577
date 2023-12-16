#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

int V; // Number of vertices
vector<vector<int>> graph;
vector<int> start;

// Use BFS to find an augmenting path in the residual graph
bool bfs(int source, int sink) {
    fill(start.begin(), start.end(), -1);
    vector<bool> visited(V, false);
    queue<int> q;
    q.push(source);
    visited[source] = true;

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int v = 0; v < V; v++) {
            if (!visited[v] && graph[u][v] > 0) {
                q.push(v);
                start[v] = u;
                visited[v] = true;
            }
        }
    }

    return visited[sink];
}

// Find the maximum flow from source to sink in the graph
int maxFlow(int source, int sink) {
    int maxFlow = 0;

    while (bfs(source, sink)) {
        int pathFlow = INT_MAX;
        int s = sink;

        while (s != source) {
            int u = start[s];
            pathFlow = min(pathFlow, graph[u][s]);
            s = u;
        }

        s = sink;

        while (s != source) {
            int u = start[s];
            graph[u][s] -= pathFlow;
            graph[s][u] += pathFlow;
            s = u;
        }

        maxFlow += pathFlow;
    }

    return maxFlow;
}

int main() {
    int t;
    cin >> t; // Number of instances

    while (t--) {
        int n, m;
        cin >> n >> m; // Number of nodes and edges
        V = n;
        graph.assign(n, vector<int>(n, 0));
        start.assign(n, 0);

        for (int j = 0; j < m; j++) {
            int u, v, capacity;
            cin >> u >> v >> capacity;
            u--; // Adjust for 0-based indexing
            v--; // Adjust for 0-based indexing
            graph[u][v] += capacity; // Add the edge
        }

        int source = 0; // Source node is always node 1 (0-based indexing)
        int sink = n - 1; // Sink node is always the last node (0-based indexing)
        int maxFlowValue = maxFlow(source, sink);

        cout << maxFlowValue << endl;
    }

    return 0;
}
