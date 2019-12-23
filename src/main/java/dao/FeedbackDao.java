package dao;

import model.TopicThread;

import java.util.List;
import java.util.Map;

public interface FeedbackDao {
    Map<String, Map<String, List<TopicThread>>> getAllFeedbacks();
    Map<String, List<TopicThread>> getUsernameFeedbacks(String username);
    List<TopicThread> getUsernameThread(String username, String threadTitle);
    void saveThread(TopicThread thread);
}

