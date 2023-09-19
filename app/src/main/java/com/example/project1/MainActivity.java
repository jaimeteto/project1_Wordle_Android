package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button aButton2;
    public GridView inputGrid;
    public GridView buttonGrid;
    char inputs[]={'1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5','1','2','3','4','5'};
    char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    EditText focusedEditText;

    public int inputLocation =0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //aButton2 = findViewById(R.id.aButton);
        //aButton2.setOnClickListener(this);
        inputLocation = 0;
        inputGrid = (GridView) findViewById(R.id.grid1); // init GridView
        buttonGrid = (GridView) findViewById(R.id.grid2); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), inputs, 1,inputGrid);
        CustomAdapter customAdapter2 = new CustomAdapter(getApplicationContext(), letters, 2,inputGrid);
        inputGrid.setAdapter(customAdapter);
        buttonGrid.setAdapter(customAdapter2);
//        buttonGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //on click get the letter of the button that was pressed and set the current focused edit text
//                String storedChar = ((Button) view).getText().toString();
//                EditText current = (EditText) inputGrid.getChildAt(inputLocation);
//                Toast.makeText(getApplicationContext(), "Item was pressed ", Toast.LENGTH_SHORT).show();
//                // current.setText("H");
//                inputLocation++;
//
//            }
//        });

    }

}