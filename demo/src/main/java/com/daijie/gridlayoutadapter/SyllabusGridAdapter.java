package com.daijie.gridlayoutadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daijie.gridlayoutbaseadapter.BaseGridAdapter;

import java.util.List;

/**
 * Created by liyujie on 2017/3/1.
 */

public class SyllabusGridAdapter extends BaseGridAdapter<SyllabusGridAdapter.ViewHolder> {

    List<List<SyllabusGrid>> mLessonGrids;

    public void setLessonGrids(List<List<SyllabusGrid>> lessonGrids) {
        mLessonGrids = lessonGrids;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int column, int row, int viewType) {
        SyllabusGrid syllabusGrid = mLessonGrids.get(column).get(row);
        holder.mTextView.setText(syllabusGrid.getName());
    }

    @Override
    public boolean isGridExist(int column, int row) {
        return mLessonGrids != null
                && mLessonGrids.get(column).get(row) != null
                && !mLessonGrids.get(column).get(row).getName().equals("NULL");
    }

    @Override
    public int getColumnCount() {
        return mLessonGrids == null ? 7 : mLessonGrids.size();
    }

    @Override
    public int getRawCount() {
        return 13;
    }

    @Override
    public int getRawSpan(int column, int row) {
        int rawSpan = 1;
        SyllabusGrid nowSyllabusGrid = mLessonGrids.get(column).get(row);
        for (int i = row + 1; i < getRawCount(); i++) {
            SyllabusGrid nextSyllabusGrid = mLessonGrids.get(column).get(i);
            if (nowSyllabusGrid.getName().equals(nextSyllabusGrid.getName())) {
                ++rawSpan;
            } else {
                break;
            }
        }
        return rawSpan;
    }

    @Override
    public int getColumnSpan(int column, int row) {
        return 1;
    }

    @Override
    public int getGridHeight() {
        return 160;
    }

    class ViewHolder extends BaseGridAdapter.ViewHolder {

        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
