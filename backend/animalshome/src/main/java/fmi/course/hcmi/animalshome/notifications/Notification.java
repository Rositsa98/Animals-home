package fmi.course.hcmi.animalshome.notifications;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private long userId;
    private String content;

    public Notification(long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
