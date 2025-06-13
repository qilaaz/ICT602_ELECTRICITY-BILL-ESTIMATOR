package com.example.electricbill;

import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.electricbill.R;
import com.example.electricbill.DB.AppDatabase;
import com.example.electricbill.Model.Bill;

import java.util.List;

public class historybill extends AppCompatActivity {

    ListView listView;
    List<Bill> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historybill);

        listView = findViewById(R.id.listViewHistory);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "billDB")
                .allowMainThreadQueries().build();
        bills = db.billDao().getAll();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                bills.stream().map(b -> b.month + ": RM " + String.format("%.2f", b.finalCost)).toArray(String[]::new));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, detailsbill.class);
            intent.putExtra("id", bills.get(position).id);
            startActivity(intent);
        });
    }
}