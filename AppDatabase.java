package com.example.electricbill.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.electricbill.Model.Bill;

@Database(entities = {Bill.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BillDao billDao();
}