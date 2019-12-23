package dao.impl;

import dao.FeedbackDao;
import dao.UserDao;
import model.TopicThread;

import java.util.*;

public class FeedbackDaoImpl implements FeedbackDao {

    private static UserDao userDao = new UserDaoImpl();
    private static Map<String, Map<String, List<TopicThread>>> feedbacks = new HashMap<>();

    @Override
    public Map<String, Map<String, List<TopicThread>>> getAllFeedbacks() {
        return feedbacks;
    }

    @Override
    public Map<String, List<TopicThread>> getUsernameFeedbacks(String username) {
        return feedbacks.get(username);
    }

    @Override
    public List<TopicThread> getUsernameThread(String username, String threadTitle) {
        return feedbacks.get(username).get(threadTitle);
    }

    @Override
    public void saveThread(TopicThread thread) {
        feedbacks.put(thread.getTitle(), userDao.getByUsername(thread.getUsername()).getThreads());
    }
}
