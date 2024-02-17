import java.util.List;

public class MyDispatcher extends Dispatcher {
    public MyDispatcher(SchedulingAlgorithm algorithm, List<Host> hosts) {
        super(algorithm, hosts);
    }
    int id = 0;
    @Override
    // sincronizez metoda pentru a nu avea conflicte la accesarea ei
    public synchronized void addTask(Task task) {
        switch (algorithm) {
            case ROUND_ROBIN:
                hosts.get(id).addTask(task);
                id = (id + 1) % hosts.size();
                break;
            case SHORTEST_QUEUE:
                int final_id = 0;
                for (int i = 0; i < hosts.size(); i++) {
                    if(hosts.get(i).getQueueSize() < hosts.get(final_id).getQueueSize()) {
                        final_id = i;
                    }
                }
                hosts.get(final_id).addTask(task);
                break;
            case LEAST_WORK_LEFT:
                int final_id2 = 0;
                for (int i = 0; i < hosts.size(); i++) {
                    if(hosts.get(i).getWorkLeft() < hosts.get(final_id2).getWorkLeft()) {
                        final_id2 = i;
                    }
                }
                hosts.get(final_id2).addTask(task);
                break;
            case SIZE_INTERVAL_TASK_ASSIGNMENT:
                switch (task.getType()) {
                    case SHORT:
                        hosts.get(0).addTask(task);
                        break;
                    case MEDIUM:
                        hosts.get(1).addTask(task);
                        break;
                    case LONG:
                        hosts.get(2).addTask(task);
                        break;
                }
                break;
        
        }
    }
}
