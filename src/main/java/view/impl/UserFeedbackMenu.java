package view.impl;

import dao.FeedbackDao;
import dao.impl.FeedbackDaoImpl;
import model.FeedbackStatus;
import model.TopicThread;
import model.UserRole;
import view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserFeedbackMenu implements Menu {
    private FeedbackDao feedbackDao = new FeedbackDaoImpl();
    private Map<String, Map<String, List<TopicThread>>> inbox = feedbackDao.getAllFeedbacks();
    private String[] items = {"1. New message", "2. Inbox"};
    private Scanner scanner;

    @Override
    public void show() throws IOException {
        showItems(items);
        System.out.println("0. Exit");
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createFeedbackSubMenu();
                    break;
                case 2:
                    inboxSubMenu();
                    exit();
                    break;
                case 0:
                    break;
            }
        }
    }
    private void inboxSubMenu() {
        int index = 1;

        for(Map.Entry<String, Map<String, List<TopicThread>>> entry : inbox.entrySet()) {
            System.out.println(index + ". " + entry.getKey());
            index++;
        }

        while (scanner.hasNext()) {
            String username = scanner.nextLine();
            userFeedbackSubMenu(username);
            break;
        }
    }
    private void userFeedbackSubMenu(String username) {
        Map<String, List<TopicThread>> feedbacks = feedbackDao.getUsernameFeedbacks(username);
        int index = 1;

        for(Map.Entry<String, List<TopicThread>> entry : feedbacks.entrySet()) {
            System.out.println(index + ". " + entry.getKey());
            index++;
        }
        while (scanner.hasNext()) {
            String threadTitle = scanner.nextLine();
            topicThreadSubMenu(username, threadTitle);
            break;
        }
    }
    private void topicThreadSubMenu(String username, String threadTitle) {
        List<TopicThread> thread = feedbackDao.getUsernameThread(username, threadTitle);
        for(TopicThread topicThread: thread) { // to find out what exactly this means
            System.out.println("name: " + topicThread.getTitle());
        }
    }
    private void createFeedbackSubMenu() throws IOException {
        String[] items = {"1. Create new thread", "2. Add to existing thread"};
        showItems(items);
        System.out.println("0. Exit");
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createThread();
                    break;
                case 2:
                    System.out.println("Please enter thread name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String threadName = reader.readLine();
                    System.out.println("Please enter user name");
                    String username = reader.readLine();
                    AddToThread(threadName, username);
                    break;
                case 0:
                    break;
            }
        }
    }
    public void createThread() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter thread name:");
        String name = reader.readLine();
        System.out.println("Please enter message text:");
        String message = reader.readLine();
        System.out.println("Please enter username:");
        String username = reader.readLine();
        Date date = new Date(System.currentTimeMillis());
        TopicThread thread = new TopicThread(name, message, username, date, FeedbackStatus.FEEDBACK, UserRole.REGULAR);
        feedbackDao.saveThread(thread);
        System.out.println("Thread Created!");
    }
    public void AddToThread(String thrName, String username) throws IOException {
        BufferedReader message = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a message to add to thread");
        String msg = "User: " + message.readLine() + "\n";
        List<TopicThread> thread = new FeedbackDaoImpl().getUsernameThread(username, thrName);
    }
    @Override
    public void exit() {
    }
}