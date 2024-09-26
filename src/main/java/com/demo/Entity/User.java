package com.demo.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
        name = "group_members",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Group> groups;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Group> createdGroups;

    @OneToMany(mappedBy = "paidBy", cascade = CascadeType.ALL)
    private List<Expense> expensesPaid;

    @OneToMany(mappedBy = "paidBy", cascade = CascadeType.ALL)
    private List<Settlement> settlementsPaid;

    @OneToMany(mappedBy = "paidTo", cascade = CascadeType.ALL)
    private List<Settlement> settlementsReceived;

}
