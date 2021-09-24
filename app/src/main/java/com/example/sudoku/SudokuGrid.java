package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuGrid extends View {

    private final Paint gridPaint = new Paint();
    private final Paint SelectedCell = new Paint();
    private final Paint CellRowAndColumn = new Paint();
    private final Paint CellTextPaint = new Paint();
    private final Paint SolvedCellPaint = new Paint();
    private final Rect CellBounds = new Rect();
    private final int gridColor, cellColor, rowAndColumnColor, CellTextColor, SolvedCellColor;
    private int dimensions = 0;
    private int cellSize;
    private Solver solver;

    public SudokuGrid(Context context, @Nullable AttributeSet attributes){
        super(context, attributes);
        gridColor = getResources().getColor(R.color.SudokuGrid);
        cellColor = getResources().getColor(R.color.SelectedCell);
        rowAndColumnColor = getResources().getColor(R.color.CellRowAndColumn);
        CellTextColor = getResources().getColor(R.color.CellText);
        SolvedCellColor = getResources().getColor(R.color.SolvedCell);
        solver = new Solver();
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        dimensions = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        setMeasuredDimension(dimensions, dimensions);
        cellSize = dimensions / 9;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(16);
        gridPaint.setColor(gridColor);
        gridPaint.setAntiAlias(true);

        SelectedCell.setStyle(Paint.Style.FILL);
        gridPaint.setAntiAlias(true);
        SelectedCell.setColor(cellColor);

        CellRowAndColumn.setStyle(Paint.Style.FILL);
        gridPaint.setAntiAlias(true);
        CellRowAndColumn.setColor(rowAndColumnColor);

        CellTextPaint.setStyle(Paint.Style.FILL);
        CellTextPaint.setAntiAlias(true);
        CellTextPaint.setColor(CellTextColor);

        collorCellRowAndColumn(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), gridPaint);
        drawGrid(canvas);
        drawNumbers(canvas);
    }

    @SuppressLint("ResourceAsColor")
    private void drawThickLine()
    {
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(10);
        gridPaint.setColor(gridColor);
    }

    @SuppressLint("ResourceAsColor")
    private void drawThinLine()
    {
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(4);
        gridPaint.setColor(gridColor);
    }


    private void drawGrid(Canvas canvas)
    {
        for (int i = 0; i<10; i++)
        {
            if (i%3 == 0)
            {
                drawThickLine();
            }
            else
            {
                drawThinLine();
            }
            canvas.drawLine(cellSize * i, 0, cellSize *i, getWidth(), gridPaint);
            canvas.drawLine(0, cellSize * i, getWidth(), cellSize * i, gridPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isValid = false;
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN)
        {
            solver.setSelectedColumn((int)Math.ceil(x/cellSize));
            solver.setSelectedRow((int)Math.ceil(y/cellSize));
            isValid = true;
        }
        return isValid;
    }

    private void collorCellRowAndColumn(Canvas canvas)
    {
        int row, column;
        row = solver.getSelectedRow();
        column = solver.getSelectedColumn();
        if (solver.isSelected())
        {
            canvas.drawRect((column-1)*cellSize, 0, column*cellSize, cellSize*9, CellRowAndColumn);
            canvas.drawRect(0, (row-1)*cellSize, 9*cellSize, row*cellSize, CellRowAndColumn);
            canvas.drawRect((column-1)*cellSize, (row-1)*cellSize, column*cellSize, row*cellSize, SelectedCell);
        }
        invalidate();
    }

    public Solver getSolver() {
        return this.solver;
    }

    public void setSolver(Solver solver) {
        this.solver = solver;
    }

    private void drawNumbers(Canvas canvas)
    {
        CellTextPaint.setTextSize(cellSize);

        for (int i =0; i<9; i++)
        {
            for (int j =0; j<9; j++)
            {
                if (solver.getSudoku()[i][j] != 0)
                {
                    String text = Integer.toString(solver.getSudoku()[i][j]);
                    float width, height;
                    CellTextPaint.getTextBounds(text, 0, text.length(), CellBounds);
                    width = CellTextPaint.measureText(text);
                    height = CellBounds.height();

                    canvas.drawText(text, ((i*cellSize )+ ((cellSize-width)/2)), (j*cellSize + cellSize) - (cellSize-height)/2, CellTextPaint);
                }
                else
                {
                    //there needs to be an implementation where the possibles are printed
                }
            }
        }
    }

}
