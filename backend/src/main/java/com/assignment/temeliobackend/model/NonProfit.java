package com.assignment.temeliobackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "non-profit")
@Getter
@NoArgsConstructor
public class NonProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Column
    String name;

    @NotBlank
    @Pattern(regexp = "[\\w\\d\\.]+@[\\w]+\\.com")
    @Column(unique = true)
    String email;

    @NotBlank
    @Column
    String address;

    @Min(value = 0)
    @Column
    Integer fundReceived = 0;

    @JsonIgnoreProperties("nonProfit")
    @NotNull
    @OneToMany(mappedBy = "nonProfit", cascade = CascadeType.ALL)
    private List<Email> emailList = new ArrayList<>();

    public NonProfit(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void addFund(int fundAmount, Email email) {
        fundReceived += fundAmount;
    }
}
