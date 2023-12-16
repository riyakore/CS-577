def knapsack(num_instances, instances):
    for instance in instances:
        num_items, capacity, weights, values = instance
        dp = [0] * (capacity + 1)

        for i in range(1, num_items + 1):
            for w in range(capacity, weights[i - 1] - 1, -1):
                dp[w] = max(dp[w], dp[w - weights[i - 1]] + values[i - 1])

        print(dp[capacity])

if __name__ == "__main__":
    num_instances = int(input())
    instances = []

    for _ in range(num_instances):
        num_items, capacity = map(int, input().split())
        weights = []
        values = []
        for _ in range(num_items):
            weight, value = map(int, input().split())
            weights.append(weight)
            values.append(value)
        instances.append((num_items, capacity, weights, values))

    knapsack(num_instances, instances)