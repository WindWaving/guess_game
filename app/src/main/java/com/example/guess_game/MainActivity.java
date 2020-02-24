package com.example.guess_game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity  {

    public int rand_num;
    Button check_btn;
    TextView tip_text;
    EditText guess;
    ImageView yes;
    ImageView no;
    ImageView high;
    ImageView low;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create a random integer from 1 to 9999
        Random r = new Random();
        rand_num = r.nextInt(9999) + 1;
        tip_text = (TextView) findViewById(R.id.tip);
        //get the icons
        yes=(ImageView) findViewById(R.id.yes_icon);
        no=(ImageView)findViewById(R.id.no_icon);
        high=(ImageView)findViewById(R.id.high_yes);
        low=(ImageView)findViewById(R.id.low_yes);
        //get the check button
        check_btn = (Button) findViewById(R.id.check);
        guess=(EditText)findViewById(R.id.guess_num);

        //check button listener, to check the guess
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_btn.setEnabled(false);
                guess = (EditText) ((TextView) findViewById(R.id.guess_num));
                String guess_str = guess.getText().toString();
                try {
                    String req = "[1-9]\\d{0,3}";
                    if (!Pattern.matches(req, guess_str)) {
                        throw new Exception();
                    } else {
                        if (Integer.parseInt(guess_str) > rand_num) {
                            tip_text.setText(R.string.high);
                            high.setVisibility(View.VISIBLE);
                            low.setVisibility(View.INVISIBLE);
                            check_btn.setVisibility(View.INVISIBLE);
                            yes.setVisibility(View.INVISIBLE);
                            no.setVisibility(View.VISIBLE);
                        } else if (Integer.parseInt(guess_str) < rand_num) {
                            tip_text.setText(R.string.low);
                            low.setVisibility(View.VISIBLE);
                            high.setVisibility(View.INVISIBLE);
                            check_btn.setVisibility(View.INVISIBLE);
                            yes.setVisibility(View.INVISIBLE);
                            no.setVisibility(View.VISIBLE);
                        } else {
                            tip_text.setText(R.string.equal);
                            high.setVisibility(View.INVISIBLE);
                            low.setVisibility(View.INVISIBLE);
                            no.setVisibility(View.INVISIBLE);
                            check_btn.setVisibility(View.INVISIBLE);
                            yes.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (Exception e) {
                    high.setVisibility(View.INVISIBLE);
                    low.setVisibility(View.INVISIBLE);
                    tip_text.setText(R.string.wrong_string);
                }

            }
        });

        //eidtText listenter,to enable the button
        guess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check_btn.setEnabled(true);
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);
                check_btn.setVisibility(View.VISIBLE);

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
