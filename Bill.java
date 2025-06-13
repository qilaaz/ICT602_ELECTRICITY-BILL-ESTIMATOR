package com.example.electricbill.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String month;
    public int units;
    public double rebate;
    public double totalCharges;
    public double finalCost;
}