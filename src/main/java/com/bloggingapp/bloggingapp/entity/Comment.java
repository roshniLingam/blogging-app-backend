package com.bloggingapp.bloggingapp.entity;

import java.util.Date;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_comment")
    private String userComment;
    @Column(name = "comment_create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CurrentTimestamp
    private Date creatDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
}
