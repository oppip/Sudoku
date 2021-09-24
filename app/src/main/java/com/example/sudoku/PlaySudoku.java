package com.example.sudoku;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sudoku.Generator.*;

public class PlaySudoku extends AppCompatActivity {

    private SudokuGrid sudokuGrid;
    private Solver solver;
    private Button solveButton, hintButton;
    Generate generator;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sudoku);
        sudokuGrid = findViewById(R.id.PlaySudokuGrid);
        solver = sudokuGrid.getSolver();
        solveButton = findViewById(R.id.SolveButtonPlay);
        hintButton = findViewById(R.id.HintButtonPlay);

        generator = new Generate();

        solver.setSudoku(generator.generate(42));

        hintButton.setBackgroundColor(getColor(R.color.SelectedCell));
        solveButton.setBackgroundColor(getColor(R.color.SolvedCell));

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solveButton.getText().toString().equals(getString(R.string.Solve)))
                {
                    hintButton.setVisibility(View.INVISIBLE);
                    solveButton.setText(R.string.New);
                    solveButton.setBackgroundColor(getColor(R.color.SelectedCell));
                    solver.Solve();
                    sudokuGrid.invalidate();
                }
                else
                {
                    hintButton.setVisibility(View.VISIBLE);
                    solveButton.setText(R.string.Solve);
                    solveButton.setBackgroundColor(getColor(R.color.SolvedCell));
                    solver = new Solver();
                    generator = new Generate();
                    solver.setSudoku(generator.generate(45));
                    sudokuGrid.setSolver(solver);
                    sudokuGrid.invalidate();
                    //Toast.makeText(getApplicationContext(), "Good luck!", Toast.LENGTH_LONG).show();
                }
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!solver.HintCell())
                {
                    Toast.makeText(getApplicationContext(), "You need to select a cell", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Here you go, \n but sometimes figuring it out is more rewarding", Toast.LENGTH_LONG).show();
                }
                sudokuGrid.invalidate();

            }
        });

    }

    public void ButtonOnePressed(View view)
    {
        solver.setNumber(1);
        sudokuGrid.invalidate();
    }
    public void ButtonTwoPressed(View view)
    {
        solver.setNumber(2);
        sudokuGrid.invalidate();
    }
    public void ButtonThreePressed(View view)
    {
        solver.setNumber(3);
        sudokuGrid.invalidate();
    }
    public void ButtonFourPressed(View view)
    {
        solver.setNumber(4);
        sudokuGrid.invalidate();
    }
    public void ButtonFivePressed(View view)
    {
        solver.setNumber(5);
        sudokuGrid.invalidate();
    }
    public void ButtonSixPressed(View view)
    {
        solver.setNumber(6);
        sudokuGrid.invalidate();
    }
    public void ButtonSevenPressed(View view)
    {
        solver.setNumber(7);
        sudokuGrid.invalidate();
    }
    public void ButtonEightPressed(View view)
    {
        solver.setNumber(8);
        sudokuGrid.invalidate();
    }
    public void ButtonNinePressed(View view)
    {
        solver.setNumber(9);
        sudokuGrid.invalidate();
    }
}