package com.example.demoapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Loan {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("totalLoan")
@Expose
private Double totalLoan;
@SerializedName("startDate")
@Expose
private String startDate;
@SerializedName("endDate")
@Expose
private String endDate;
@SerializedName("bankRate")
@Expose
private Integer bankRate;
@SerializedName("remain_loan")
@Expose
private String remainLoan;

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

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public Integer getBankRate() {
return bankRate;
}

public void setBankRate(Integer bankRate) {
this.bankRate = bankRate;
}

public String getRemainLoan() {
return remainLoan;
}

public void setRemainLoan(String remainLoan) {
this.remainLoan = remainLoan;
}

}