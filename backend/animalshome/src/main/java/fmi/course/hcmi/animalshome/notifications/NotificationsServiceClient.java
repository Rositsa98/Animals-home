package fmi.course.hcmi.animalshome.notifications;

import java.util.List;

public interface NotificationsServiceClient {
    List<String> getNotifications(String jwt);
}
