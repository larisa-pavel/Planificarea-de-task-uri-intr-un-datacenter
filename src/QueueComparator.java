import java.util.Comparator;

public class QueueComparator implements Comparator<Task>{
    @Override
    public int compare(Task t1, Task t2) {
        if (t2.getDuration().equals(t2.getLeft())) {
            // daca t2 nu ruleaza, atunci inversez sau nu task-urile in coada
            // in functie de prioritate, iar daca au aceeasi prioritate, atunci
            // in functie de momentul sosirii in coada
            if (t1.getPriority() != t2.getPriority()) {
                return Integer.compare(t2.getPriority(), t1.getPriority());
            }
            return Integer.compare(t1.getStart(), t2.getStart());
        }
        if (t2.isPreemptible()) {
            // daca t2 este preemptibil, nu ma intereseaza daca ruleaza sau nu
            // deci task-ul 2 va intrerupe primul task daca are prioritate mai
            // mare sau egala cu acesta
            if (t1.getPriority() == t2.getPriority()) {
                return 1;
            }
            return Integer.compare(t2.getPriority(), t1.getPriority());
        }
        // daca t2 nu este preemptibil atunci nu se va intampla nimic
        return 0;
    }
}
