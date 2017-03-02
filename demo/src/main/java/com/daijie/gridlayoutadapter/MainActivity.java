package com.daijie.gridlayoutadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridLayout mGridLayout;
    SyllabusGridAdapter mSyllabusGridAdapter;
    Button mButton;

    List<List<SyllabusGrid>> mLessonGrids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout);
        mButton = (Button) findViewById(R.id.button);
        mSyllabusGridAdapter = new SyllabusGridAdapter();

        mLessonGrids = new ArrayList<>();
        initLesson(2);

        mSyllabusGridAdapter.setLessonGrids(mLessonGrids);
        mSyllabusGridAdapter.attachTo(mGridLayout);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLesson((int) (Math.random() * 5 + 2));
                mSyllabusGridAdapter.notifyDataSetChanged();
            }
        });
    }


    public void initLesson(int seed) {
        mLessonGrids.clear();
        for (int i = 0; i < 7; i++) {
            List<SyllabusGrid> syllabusGrids = new ArrayList<>();
            for (int j = 0; j < 13; j++) {
                SyllabusGrid syllabusGrid = new SyllabusGrid();
                if ((i + j) % seed == 0) {
                    syllabusGrid.setName("这是一节课");
                } else if (j % 3 == 0) {
                    syllabusGrid.setName("这是一节课");
                } else {
                    syllabusGrid.setName("NULL");
                }
                syllabusGrids.add(syllabusGrid);
            }
            mLessonGrids.add(syllabusGrids);
        }
    }
}