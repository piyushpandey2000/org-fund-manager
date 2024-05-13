package com.assignment.temeliobackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column
    private String content;

    @JsonIgnoreProperties("emailList")
    @ManyToOne
    @JoinColumn(name = "non_profit_id", nullable = false)
    private NonProfit nonProfit;

    public static String getEmailContentToSave(String emailContent, NonProfit nonProfit, int fundAmount) {
        String emailContentToSave = emailContent.replace("$name$", nonProfit.getName());
        emailContentToSave = emailContentToSave.replace("$email$", nonProfit.getEmail());
        emailContentToSave = emailContentToSave.replace("$address$", nonProfit.getAddress());
        emailContentToSave = emailContentToSave.replace("$amount$", String.valueOf(fundAmount));

        return emailContentToSave;
    }
}
