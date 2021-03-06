package com.example.kokeilu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

   private static final String TAG = "MainActivity";

   DatabaseHelper  mDatabaseHelper;
   private Button btnAdd, btnViewData;
   private EditText editText, editVahvuus, editMaara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.EditText);
        btnViewData = findViewById(R.id.btnViewData);
        mDatabaseHelper = new DatabaseHelper(this);
        btnAdd = findViewById(R.id.btnAdd);
        editVahvuus = findViewById(R.id.editVahvuus);
        editMaara = findViewById(R.id.editMaara);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String vahvuus = editVahvuus.getText().toString();
                String maara = editMaara.getText().toString();

                if (editText.length() != 0 || editMaara.length() != 0 || editVahvuus.length() != 0){
                    AddData(newEntry, vahvuus, maara);
                    editText.setText("");
                    editVahvuus.setText("");
                    editMaara.setText("");
                }
                else {
                  Toast.makeText(getApplicationContext(),"You have to put some text here!",Toast.LENGTH_LONG).show();
                }
            }
        });
        
        btnViewData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent((MainActivity.this), ListDataActivity.class);
                startActivity(intent);
            }
        });
    }
        public void AddData(String newEntry, String vahvuus, String maara) {
                boolean insertData = mDatabaseHelper.addData(newEntry, vahvuus, maara);

                if (insertData) {
                    Toast.makeText(getApplicationContext(),"Data successfully inserted!",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Something went wrong.",Toast.LENGTH_LONG).show();
                }
        }
                /**
         * customizable toast
         * @param message
         */
        private void toastMessage (String message){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

}



