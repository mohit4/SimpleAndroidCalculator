package com.kirarapps.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    String temp = "0";
    Integer prev_id = R.id.button_add;
    TextView screen;
    ArrayList<Button> keypad = new ArrayList<>();
    ArrayList<Integer> keypad_values = new ArrayList<>(Arrays.asList(
            R.id.button_0,R.id.button_1,
            R.id.button_2,R.id.button_3,
            R.id.button_4,R.id.button_5,
            R.id.button_6,R.id.button_7,
            R.id.button_8,R.id.button_9,
            R.id.button_add,R.id.button_diff,
            R.id.button_div,R.id.button_mul,
            R.id.button_c,R.id.button_eq
    ));

    protected void calculate(String val1,String val2,Integer op)
    {
        //need to work on this
//        if(prev_id==R.id.button_eq)
//            temp = val2;
        Integer res = 0;
        switch(op)
        {
            case R.id.button_add : res = Integer.parseInt(val1) + Integer.parseInt(val2); break;
            case R.id.button_diff : res = Integer.parseInt(val1) - Integer.parseInt(val2); break;
            case R.id.button_div : res = Integer.parseInt(val1) / Integer.parseInt(val2); break;
            case R.id.button_mul : res = Integer.parseInt(val1) * Integer.parseInt(val2);
        }
        screen.setText(res.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //attaching the screen
        screen = (TextView)findViewById(R.id.textView);

        //allocating keypads
        for(int i=0;i<16;i++)
            keypad.add(i, (Button) findViewById(keypad_values.get(i)));

        //setting on click listeners
        for(int i=0;i<16;i++)
            keypad.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(v.getId())
                    {
                        case R.id.button_0: case R.id.button_1: case R.id.button_2: case R.id.button_3: case R.id.button_4:
                        case R.id.button_5: case R.id.button_6: case R.id.button_7: case R.id.button_8: case R.id.button_9:
                        screen.setText(screen.getText().toString() + keypad.get(keypad_values.indexOf(v.getId())).getText().toString());
                        break;

                        case R.id.button_c: screen.setText("0"); break;
                        case R.id.button_eq: calculate(temp,screen.getText().toString(),prev_id); break;
                        default: temp = screen.getText().toString(); prev_id = v.getId(); screen.setText("0");
                    }
                    //Toast.makeText(getApplicationContext(),keypad.get(keypad_values.indexOf(v.getId())).getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
