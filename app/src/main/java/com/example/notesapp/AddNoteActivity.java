package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AddNoteActivity extends AppCompatActivity {

    private EditText mTitle, mDesc;
    private Button mSave;

    public static final String EXTRA_TITLE = "com.example.notesapp.EXTRA_TITLE";
    public static final String EXTRA_ID = "com.example.notesapp.EXTRA_ID";
    public static final String EXTRA_DESC= "com.example.notesapp.EXTRA_DESC";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = findViewById(R.id.editTextTitle);
        mDesc = findViewById(R.id.editTextDesc);
        mSave = findViewById(R.id.saveButton);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent editIntent = getIntent();

        if(editIntent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            mTitle.setText(editIntent.getStringExtra(EXTRA_TITLE));
            mDesc.setText(editIntent.getStringExtra(EXTRA_DESC));

        }
        else {
            setTitle("Add Note");
        }

        mSave.setOnClickListener(view -> {
            String title = mTitle.getText().toString();
            String desc = mDesc.getText().toString();

            if(title.trim().isEmpty() || desc.trim().isEmpty()) {
                Toast.makeText(AddNoteActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent dataIntent = new Intent();
            dataIntent.putExtra(EXTRA_TITLE, title);
            dataIntent.putExtra(EXTRA_DESC, desc);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if(id != -1) {
                dataIntent.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, dataIntent);
            finish();
        });
    }
}
