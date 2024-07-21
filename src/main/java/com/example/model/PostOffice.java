package com.example.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post_office")
@Getter
@Setter
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_office_id", unique = true)
    private String postOfficeId;

    @Column(name = "properties")
    private String properties;

    @Column(name = "index", unique = true)
    private String index;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address postOfficeAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostOffice that = (PostOffice) o;
        return Objects.equals(postOfficeId, that.postOfficeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postOfficeId);
    }

    public PostOffice() {
    }
}
