package view.impl;

import dao.FeedbackDao;
import dao.impl.FeedbackDaoImpl;
import model.TopicThread;
import view.Menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminFeedbackMenu implements Menu {
    private FeedbackDao feedbackDao = new FeedbackDaoImpl();
    // TODO ask if it is possible to generalize this type Map<String, LinkedHashMap<String, TopicThread>>
    private Map<String, LinkedHashMap<String, ArrayList<TopicThread>>> inbox = feedbackDao.getAllFeedbacks();
    private String[] items = {"1. Inbox (" + inbox.size() + ")"};
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void show() {
        showItems(items);
        System.out.println("0. Exit");
        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    inboxSubMenu();
                    break;
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0); // TODO discuss with teammates and add impl
    }

    private void inboxSubMenu() {
        AtomicInteger index = new AtomicInteger(1);
        inbox.entrySet().forEach((entry) -> {
            System.out.println(index + ". " + entry.getKey());
            index.getAndIncrement();
        });
        while (scanner.hasNext()) {
            String username = scanner.nextLine();
            userFeedbackSubMenu(username);
            break;
        }

//        scanner.reset();
//        while (scanner.hasNext()) {
//            break;
//        }
    }

    private void userFeedbackSubMenu(String username) {
        LinkedHashMap<String, ArrayList<TopicThread>> feedbacks = feedbackDao.getUsernameFeedbacks(username);
        AtomicInteger index = new AtomicInteger(1);
        feedbacks.entrySet().forEach(entry -> {
            System.out.println(index + ". " + entry.getKey());
            index.getAndIncrement();
        });
        // TODO add exit handlers
        // TODO add null checkings
        while (scanner.hasNext()) {
            String threadTitle = scanner.nextLine();
            topicThreadSubMenu(username, threadTitle);
            break;
        }
    }

    private void topicThreadSubMenu(String username, String threadTitle) {
        ArrayList<TopicThread> thread = feedbackDao.getUsernameThread(username, threadTitle);
        for(TopicThread topicThread: thread) {
            System.out.println(); //TODO add thread output
        }
    }
}
