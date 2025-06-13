package com.example.electricbill.DB;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.electricbill.Model.Bill;

import java.util.List;

@Dao
public interface BillDao {
    @Insert
    void insert(Bill bill);

    @Query("SELECT * FROM Bill ORDER BY id DESC")
    List<Bill> getAll();

    @Query("SELECT * FROM Bill WHERE id = :id")
    Bill getById(int id);
}