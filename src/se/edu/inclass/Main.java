package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing sorted deadlines");
        printDeadlinesUsingStream(tasksData);
        printDeadlinesWithStream(tasksData);

        System.out.println("Total number of deadlines (using stream): " +
                countDeadlinesWithStreams(tasksData));

        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");
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

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t)-> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>)tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
    }

}
