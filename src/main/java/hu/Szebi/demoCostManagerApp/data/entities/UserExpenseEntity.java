package hu.Szebi.demoCostManagerApp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_expenses")
public class UserExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int cost;

    @Column
    private LocalDate expenseDate;

    @Column
    private String comment;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ExpenseCategoryEntity expenseCategory;
}
