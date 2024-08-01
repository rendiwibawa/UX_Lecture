package com.example.gpt.PembayaranPilihJam;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.gpt.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECTED_WATCHES = 1;
    private List<Hour> hourList;
    private HourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button buttonShowSelected = findViewById(R.id.buttonShowSelected);

        hourList = new ArrayList<>();
        // Tambahkan beberapa Watch ke watchList untuk contoh
        hourList.add(new Hour("08:00 - 09:00", 150.00));
        hourList.add(new Hour("09:00 - 10:00", 150.00));
        hourList.add(new Hour("10:00 - 11:00", 150.00));

        adapter = new HourAdapter(hourList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonShowSelected.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SelectedHoursActivity.class);
            intent.putExtra("selectedWatches", new ArrayList<>(adapter.getSelectedWatches()));
            startActivityForResult(intent, REQUEST_CODE_SELECTED_WATCHES);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<Hour> updatedHours = (ArrayList<Hour>) data.getSerializableExtra("updatedWatches");
            adapter = new HourAdapter(updatedHours);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            List<Hour> newHours = (ArrayList<Hour>) data.getSerializableExtra("selectedWatches");
            if (newHours != null) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedWatches", new ArrayList<>(newHours));
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button action
            onBackPressed(); // This will navigate back to the previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}



