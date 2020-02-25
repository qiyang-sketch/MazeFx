package Eluvian.objects;

import java.util.Comparator;

public class PriorityComparator implements Comparator<PriorityAgent>{

    public int compare(PriorityAgent a, PriorityAgent b) {
        if (a.getPriority() < b.getPriority()) {
            return -1;
        } else {
            if (a.getPriority() > b.getPriority()) {
                return 1;
            }
        }
        return 0;
    }
}