import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class FarthestInFuture {

    public static int farthestInFuture(int cacheLength, int[] requestSequence) {

        //using a HashMap to store the key value pairs
        //the key is the page being stored in the cache
        //the value is the next occurrence of the page in the request sequence
        Map<Integer, Integer> cache = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < requestSequence.length; i++) {
            int page = requestSequence[i];

            //if the cache doesn't contain the page, then add the page to cache
            if (!cache.containsKey(page)) {
                pageFaults++;

                //if the cache becomes full, then remove the page whose request is furthest in the future
                if (cache.size() >= cacheLength) {
                    int pageToRemove = -1;
                    int furthestRequestIndex = Integer.MIN_VALUE;

                    for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
                        int cachedPage = entry.getKey();
                        int futureIndex = entry.getValue();

                        //if you find a request that's even further than the furthest one,
                        //then make that the furthest one
                        if (futureIndex > furthestRequestIndex) {
                            furthestRequestIndex = futureIndex;
                            pageToRemove = cachedPage;
                        }
                    }

                    //remove the page with the furthest request from the cache
                    cache.remove(pageToRemove);
                }
            }

            //to find the value from the key value pair to store it in the cache
            int nextIndex = Integer.MAX_VALUE;
            for (int j = i + 1; j < requestSequence.length; j++) {
                if (requestSequence[j] == page) {
                    nextIndex = j;
                    break;
                }
            }

            //storing it in the cache
            cache.put(page, nextIndex);
        }

        return pageFaults;
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt();
        scnr.nextLine();

        for (int i = 0; i < instances; i++) {
            int cacheLength = scnr.nextInt();
            int pageRequests = scnr.nextInt();
            scnr.nextLine();

            int[] requestSequence = new int[pageRequests];
            for (int j = 0; j < pageRequests; j++) {
                requestSequence[j] = scnr.nextInt();
            }
            scnr.nextLine();

            int pageFaults = farthestInFuture(cacheLength, requestSequence);
            System.out.println(pageFaults);
        }

        scnr.close();
    }
}
