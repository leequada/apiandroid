package com.namph.security.domain;


import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_loan")
    private Double totalLoan;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "bank_rate")
    private Integer bankRate;

    @Column(name = "remain_loan")
    private String remain_loan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "loan")
    private Set<MonthlyLoan> monthlyLoans;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalLoan() {
        return totalLoan;
    }

    public void setTotalLoan(Double totalLoan) {
        this.totalLoan = totalLoan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getBankRate() {
        return bankRate;
    }

    public void setBankRate(Integer bankRate) {
        this.bankRate = bankRate;
    }

    public String getRemain_loan() {
        return remain_loan;
    }

    public void setRemain_loan(String remain_loan) {
        this.remain_loan = remain_loan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MonthlyLoan> getMonthlyLoans() {
        return monthlyLoans;
    }

    public void setMonthlyLoans(Set<MonthlyLoan> monthlyLoans) {
        this.monthlyLoans = monthlyLoans;
    }
}
