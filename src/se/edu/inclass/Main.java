package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        printDeadlinesWithStream(tasksData);

        System.out.println("Total number of deadlines (using stream): " +
                countDeadlinesWithStreams(tasksData));

        printDataWithStreams(tasksData);
    }

    private static int countDeadlinesWithStreams(ArrayList<Task> tasks) {
        System.out.println("Counting using streams");
        int count = (int)tasks.stream()
                .filter((t)-> t instanceof Deadline)
                .count();
        return count;
    }
    public static void printDataWithStreams(ArrayList<Task> tasksData) {
        System.out.println("With streams");
        tasksData.stream()
                .forEach(System.out::println);
    }
    public static void printDeadlinesWithStream(ArrayList<Task> tasks) {
        System.out.println("Deadlines with streams");
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }
}
