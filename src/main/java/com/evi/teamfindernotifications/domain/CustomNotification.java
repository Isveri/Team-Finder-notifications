package com.evi.teamfindernotifications.domain;

import com.evi.teamfindernotifications.security.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomNotification {
    public enum NotifType{
        REMOVED,
        INFO,
        WARNING,
        FRIENDREQUEST,
        PRIVATE_MESSAGE,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String msg;

    @NotNull
    private NotifType type;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private GroupRoom groupRoom;
}
