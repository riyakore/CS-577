#include <iostream>
#include <vector>

using namespace std;

int knapsack(int numberOfIttems, int capacity, vector<int>& weights, vector<int>& values) {
    vector<int> dynamic(capacity + 1, 0);

    for (int i = 1; i <= numberOfIttems; i++) {
        for (int w = capacity; w >= weights[i - 1]; w--) {

            // using Bellman equation
            dynamic[w] = max(dynamic[w], dynamic[w - weights[i - 1]] + values[i - 1]);
        }
    }

    return dynamic[capacity];
}

int main() {
    int instances;
    cin >> instances;

    for (int i = 0; i < instances; i++) {
        int numberOfItems;
        int capacity;

        // Read the input
        cin >> numberOfItems >> capacity;

        vector<int> weights(numberOfItems);
        vector<int> values(numberOfItems);

        for (int j = 0; j < numberOfItems; j++) {
            cin >> weights[j] >> values[j];
        }

        int output = knapsack(numberOfItems, capacity, weights, values);
        cout << output << endl;
    }

    return 0;
}
