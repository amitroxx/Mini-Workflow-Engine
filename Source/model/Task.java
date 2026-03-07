package Source.model;

public class Task implements WorkFlowItem {

    private final String taskId;
    private String name;
    private Status status;

    private String[] dependencies = new String[10];
    private int depCount = 0;

    public Task(String taskId, String name) {

        if (taskId == null || taskId.isBlank())
            throw new IllegalArgumentException("Task ID required");

        this.taskId = taskId.trim();
        this.name = name == null ? "" : name.trim();
        this.status = Status.PENDING;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void start() {
        if (status != Status.PENDING)
            throw new IllegalStateException("Task already started");

        status = Status.IN_PROGRESS;
    }

    public void complete() {
        if (status != Status.IN_PROGRESS)
            throw new IllegalStateException("Task must be started first");

        status = Status.COMPLETED;
    }

    public void addDependency(String taskId) {

        if (depCount >= dependencies.length)
            throw new IllegalStateException("Dependency list full");

        dependencies[depCount++] = taskId;
    }

    public String[] getDependencies() {
        return dependencies;
    }

    public int getDepCount() {
        return depCount;
    }

    @Override
    public boolean canStart() {
        return status == Status.PENDING;
    }

    @Override
    public String toString() {
        return taskId + " | " + name + " | " + status;
    }
}