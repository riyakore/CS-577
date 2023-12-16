import java.util.Scanner;
import java.util.Arrays;

public class WeightedIntervalScheduling {

    //field for the start time of the job
    public int start;
    //field for the end time of the job
    public int end;
    //field for the weight of the job
    public int weight;

    //constructor for the class that includes start time, end time and the weight of the job
    public WeightedIntervalScheduling(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    /**
     * Reading the input and giving the expected output
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt();

        for (int j = 0; j < instances; j++) {
            int numberOfJobs = scnr.nextInt();
            WeightedIntervalScheduling[] jobs = new WeightedIntervalScheduling[numberOfJobs];

            for (int i = 0; i < numberOfJobs; i++) {
                int start = scnr.nextInt();
                int end = scnr.nextInt();
                int weight = scnr.nextInt();
                jobs[i] = new WeightedIntervalScheduling(start, end, weight);
            }

            long totalWeight = findMaxWeight(jobs);
            System.out.println(totalWeight);
        }
    }

    /**
     * This method helps to find the max weight for the sequence of jobs
     * @param jobs an array that has stored elements of the type WeightedIntervalScheduling
     * @return the max weight of the jobs in that instance
     */
    public static long findMaxWeight(WeightedIntervalScheduling[] jobs) {
        Arrays.sort(jobs, (a, b) -> a.end - b.end);

        long[] dynamic = new long[jobs.length];
        Arrays.fill(dynamic, -1); // Initialize with -1 to indicate not computed

        return dynamicProgramming(jobs, dynamic, jobs.length - 1);
    }

    /**
     * This method checks for the weights and decides if the weight for that job should be
     * included or excluded in the max weight based on dynamic programming.
     * @param jobs the array containing WeightedIntervalScheduling type elements
     * @param dynamic array to store the weights
     * @param i variable for the base case
     * @return the max value of the dynamic array
     */
    public static long dynamicProgramming(WeightedIntervalScheduling[] jobs, long[] dynamic, int i) {

        //base case 1
        if (i == 0) {
            return jobs[0].weight;
        }

        //base case 2
        if (dynamic[i] != -1) {
            return dynamic[i];
        }

        long includeJobs = jobs[i].weight;
        int prevCompatibleJob = findPreviousCompatibleJob(jobs, i);
        if (prevCompatibleJob != -1) {
            //recursive call to the method
            includeJobs += dynamicProgramming(jobs, dynamic, prevCompatibleJob);
        }

        //recursive call to the method
        long excludeJobs = dynamicProgramming(jobs, dynamic, i - 1);

        dynamic[i] = Math.max(includeJobs, excludeJobs);
        return dynamic[i];
    }

    /**
     * This method finds the previous compatible jobs in the WeightedIntervalScheduling array
     * using linear search
     * @param jobs the array containing WeightedIntervalScheduling type elements
     * @param currentIndex used to perform linear search on the jobs array
     * @return the current index
     */
    public static int findPreviousCompatibleJob(WeightedIntervalScheduling[] jobs, int currentIndex) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (jobs[i].end <= jobs[currentIndex].start) {
                return i;
            }
        }
        return -1;
    }
}