package com.evi.teamfindernotifications.domain;

import com.evi.teamfindernotifications.security.model.User;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Where(clause = "deleted=false")
public class GroupRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "groupRooms",cascade = CascadeType.MERGE)
    private List<User> users = new ArrayList<>();


    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="groupLeader_id")
    private User groupLeader;


}
