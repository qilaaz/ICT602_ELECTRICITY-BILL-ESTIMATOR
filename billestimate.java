package com.example.electricbill;

import android.os.Bundle;
import android.widget.*;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.electricbill.R;
import com.example.electricbill.DB.AppDatabase;
import com.example.electricbill.Model.Bill;

public class billestimate extends AppCompatActivity {

    Spinner spinnerMonth;
    EditText editUnits;
    SeekBar seekBarRebate;
    TextView rebatePercent, totalChargesText, finalCostText;
    Button calculateBtn, saveBtn, viewBtn;

    AppDatabase db;
    double totalCharges = 0, finalCost = 0;
    int units = 0;
    double rebate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatebill);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "billDB")
                .allowMainThreadQueries().build();

        spinnerMonth = findViewById(R.id.spinnerMonth);
        editUnits = findViewById(R.id.editTextUnits);
        seekBarRebate = findViewById(R.id.seekBarRebate);
        rebatePercent = findViewById(R.id.textViewRebatePercent);
        totalChargesText = findViewById(R.id.textViewTotalCharges);
        finalCostText = findViewById(R.id.textViewFinalCost);
        calculateBtn = findViewById(R.id.buttonCalculate);
        saveBtn = findViewById(R.id.buttonSave);
        viewBtn = findViewById(R.id.buttonViewHistory);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.months_array, android.R.layout.simple_spinner_item);
        spinnerMonth.setAdapter(adapter);

        seekBarRebate.setMax(5);
        seekBarRebate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rebate = progress / 100.0;
                rebatePercent.setText(progress + "%");
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        calculateBtn.setOnClickListener(v -> {
            try {
                units = Integer.parseInt(editUnits.getText().toString());
                totalCharges = calculateCharges(units);
                finalCost = totalCharges - (totalCharges * rebate);
                totalChargesText.setText("Total Charges: RM " + String.format("%.2f", totalCharges));
                finalCostText.setText("Final Cost: RM " + String.format("%.2f", finalCost));
            } catch (Exception e) {
                Toast.makeText(this, "Please enter valid units", Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(v -> {
            Bill bill = new Bill();
            bill.month = spinnerMonth.getSelectedItem().toString();
            bill.units = units;
            bill.rebate = rebate;
            bill.totalCharges = totalCharges;
            bill.finalCost = finalCost;
            db.billDao().insert(bill);
            Toast.makeText(this, "Saved to DB", Toast.LENGTH_SHORT).show();
        });

        viewBtn.setOnClickListener(v -> startActivity(new Intent(this, historybill.class)));

    }

    private double calculateCharges(int u) {
        double charge = 0;
        if (u <= 200) charge = u * 0.218;
        else if (u <= 300) charge = 200 * 0.218 + (u - 200) * 0.334;
        else if (u <= 600) charge = 200 * 0.218 + 100 * 0.334 + (u - 300) * 0.516;
        else charge = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (u - 600) * 0.546;
        return charge;
    }
}