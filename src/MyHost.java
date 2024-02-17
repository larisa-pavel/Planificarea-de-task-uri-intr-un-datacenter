import java.util.PriorityQueue;

public class MyHost extends Host {
    PriorityQueue<Task> coada = new PriorityQueue<>(new QueueComparator());
    boolean verify = true;
    @Override
    public void run() {
        // cat timp nu am primit comanda de shutdown
        while (verify) {
            // sincronizez coada pentru a nu avea conflicte la accesarea ei
            synchronized(coada){
                // iau primul task din coada
                Task my_task = coada.peek();
                if (my_task != null) {
                    // daca exista, atunci il rulez pentru 100 de milisecunde
                    try {
                    Thread.sleep(100);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // scad durata task-ului
                    my_task.setLeft(my_task.getLeft() - 100);
                    if (my_task.getLeft() <= 0) {
                        // daca durata task-ului a ajuns la 0, atunci il termin
                        // si il scot din coada
                        my_task.finish();
                        coada.poll();
                    }
                }     
            }
        }
    }

    @Override
    public void addTask(Task task) {
        if (task != null) {
            coada.add(task);
        }
    }

    @Override
    public int getQueueSize() {
        return coada.size();
    }

    @Override
    public long getWorkLeft() {
        long work_left = 0;
        for (Task task : coada) {
            work_left += task.getLeft();
        }
        return work_left;
    }

    @Override
    public void shutdown() {
        verify = false;
    }
}
