package com.example.project1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    char[] inputs;
    int gridNum;
    public CustomAdapter(Context applicationContext, char[] inputs,int gridNum) {
        this.context = applicationContext;
        this.gridNum=gridNum;
        this.inputs=inputs;
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

        if(gridNum==1) {
            view = inflater.inflate(R.layout.input, null); // inflate the layout
            //get the view

            EditText input1 = (EditText) view.findViewById(R.id.input); // get the reference of ImageView
            input1.setText("__");

        } else if (gridNum==2) {
            view = inflater.inflate(R.layout.button, null); // inflate the layout
            //get the view

            Button myButton = (Button) view.findViewById(R.id.aButton);
            String sequence= String.valueOf(inputs[i]);

            myButton.setText(sequence);


        }
        return view;
    }
}
