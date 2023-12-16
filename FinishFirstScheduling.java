import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class FinishFirstScheduling {

    public int startTime;
    public int endTime;

    //constructor for the finishFirst class that will create in interval instance with
    //start and end time
    public FinishFirstScheduling(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static List<FinishFirstScheduling> finishFirst(List<FinishFirstScheduling> intervals) {

        //the arraylist that needs to be returned (with the earliest end times)
        List<FinishFirstScheduling> selectedIntervals = new ArrayList<>();

        //sorting intervals based on the earliest end time
        Collections.sort(intervals, (a, b) -> a.endTime - b.endTime);

        while(!intervals.isEmpty()) {

            FinishFirstScheduling finishFirst = intervals.get(0);
            selectedIntervals.add(finishFirst);

            //removing the incompatible intervals
            Iterator<FinishFirstScheduling> incompatible = intervals.iterator();
            while (incompatible.hasNext()) {
                FinishFirstScheduling interval = incompatible.next();
                if (interval.startTime < finishFirst.endTime) {
                    incompatible.remove();
                }
            }
        }

        //returning thr arraylist of selected intervals
        return selectedIntervals;
    }

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt();
        scnr.nextLine();

        for (int i = 0; i < instances; i++) {
            int numberOfJobs = scnr.nextInt();
            scnr.nextLine();

            List<FinishFirstScheduling> intervals = new ArrayList<>();

            for (int j = 0; j < numberOfJobs; j++) {
                String[] line = scnr.nextLine().split(" ");
                int startTime = Integer.parseInt(line[0]);
                int endTime = Integer.parseInt(line[1]);
                intervals.add(new FinishFirstScheduling(startTime,endTime));
            }

            List<FinishFirstScheduling> selectedIntervals = finishFirst(intervals);
            System.out.println(selectedIntervals.size());
        }
    }
}
