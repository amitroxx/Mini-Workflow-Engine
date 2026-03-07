package Source.service;

import Source.model.*;

public class WorkFlowService {

    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(Task task) {

        if (task == null)
            throw new DomainException("Task cannot be null");

        tasks[taskCount++] = task;
    }

    public Task findTask(String id) {

        for (int i = 0; i < taskCount; i++) {

            if (tasks[i].getTaskId().equals(id))
                return tasks[i];
        }

        throw new DomainException("Task not found");
    }

    public void completeTask(String id) {

        Task task = findTask(id);

        for (int i = 0; i < task.getDepCount(); i++) {

            Task dep = findTask(task.getDependencies()[i]);

            if (dep.getStatus() != Status.COMPLETED) {
                throw new DomainException("Dependency not completed");
            }
        }

        task.start();
        task.complete();
    }

    public void listTasks() {

        if (taskCount == 0) {
            System.out.println("(no tasks)");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            System.out.println(tasks[i]);
        }
    }

    public void addDependency(String taskId, String depId) {

        Task task = findTask(taskId);
        findTask(depId); // check existence

        task.addDependency(depId);
    }
}