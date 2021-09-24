package com.example.sudoku;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sudoku.Generator.Generate;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private SudokuGrid sudokuGrid;
    private Solver solver;
    private Button helpButton, playButton, historyButton;
    Generate generator;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sudokuGrid = findViewById(R.id.SudokuGridMenu);
        solver = sudokuGrid.getSolver();
        helpButton = findViewById(R.id.SolveMenu);
        historyButton = findViewById(R.id.HistoryMenu);
        playButton = findViewById(R.id.PlayMenu);
        generator = new Generate();
        context = this.getApplicationContext();

        solver.setSudoku(generator.generate().getMatrix());

        helpButton.setBackgroundColor(getColor(R.color.SolvedCell));
        historyButton.setBackgroundColor(getColor(R.color.SolvedCell));
        playButton.setBackgroundColor(getColor(R.color.SolvedCell));

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SolveSudoku.class);
                startActivity(intent);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaySudoku.class);
                startActivity(intent);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaySudoku.class);
                startActivity(intent);
            }
        });

        Timer timer = new Timer();
        TimerTask fadeIn = new TimerTask() {
            @Override
            public void run() {
                Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                sudokuGrid.startAnimation(animFadeIn);
                generator = new Generate();
                solver.setSudoku(generator.generate().getMatrix());
                sudokuGrid.invalidate();
            }
        };

        TimerTask fadeOut = new TimerTask() {
            @Override
            public void run() {
                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                sudokuGrid.startAnimation(animFadeOut);
            }
        };

        timer.schedule(fadeOut, 2000, 6000);
        timer.schedule(fadeIn, 4000, 6000);


    }
}