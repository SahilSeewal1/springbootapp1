package com.example.UserMovieProject.models.tables;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(Ratings.class)
@Table(name = "RATING_TABLE")
public @Data class Ratings implements Serializable {
    @Id @Column(name = "USER_ID", nullable = false)
    int UserId;
    @Id @Column(name = "ITEM_ID", nullable = false)
    int itemId;
    @Column(name = "RATING", nullable = false)
    int rating;
    @Column(name = "TIMESTAMP", nullable = false)
    String timestamp;
}
