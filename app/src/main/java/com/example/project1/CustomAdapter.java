package com.example.project1;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    char[] inputs;
    int gridNum;
    GridView grid;


    public CustomAdapter(Context applicationContext, char[] inputs, int gridNum, GridView grid) {
        this.context = applicationContext;
        this.gridNum = gridNum;
        this.inputs = inputs;
        this.grid = grid;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return inputs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (gridNum == 1) {
            view = inflater.inflate(R.layout.input, null); // inflate the layout
            //get the view

            EditText input1 = (EditText) view.findViewById(R.id.input); // get the reference of ImageView
            input1.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {}

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    //change focus to the following edit text
                    int nextEditText = i+1;
                    if(nextEditText<=inputs.length){
                        EditText next = ((LinearLayout) grid.getChildAt(nextEditText)).findViewById(R.id.input);
                        next.requestFocus();
                    }

                    }


            });


        } else if (gridNum == 2) {
            view = inflater.inflate(R.layout.button, null); // inflate the layout
            //get the view

            Button myButton = (Button) view.findViewById(R.id.myButton);
            String sequence = String.valueOf(inputs[i]);
            myButton.setText(sequence);
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context.getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                    String storedChar = ((Button) view).getText().toString();
                    //EditText current = ((LinearLayout) grid.getChildAt(0)).findViewById(R.id.input);
                    EditText current = ((LinearLayout) grid.getFocusedChild()).findViewById(R.id.input);
                    //Toast.makeText(context, "Item was pressed ", Toast.LENGTH_SHORT).show();
                    current.setText(storedChar);

                }


            });

        }

        // myButton.setText(sequence);


        return view;
    }
}
