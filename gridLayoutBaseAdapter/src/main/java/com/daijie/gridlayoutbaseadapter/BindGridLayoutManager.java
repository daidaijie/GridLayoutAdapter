package com.daijie.gridlayoutbaseadapter;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;

/**
 * Created by liyujie on 2017/3/1.
 */

public class BindGridLayoutManager {

     static void addViews(final GridLayout gridLayout, final BaseGridAdapter baseGridAdapter, boolean isRemoteBefore) {
        if (gridLayout == null || baseGridAdapter == null) {
            return;
        }

        if (isRemoteBefore && gridLayout.getChildCount() > 0) {
            gridLayout.removeAllViews();
        }

        final int columnCount = baseGridAdapter.getColumnCount();
        final int rowCount = baseGridAdapter.getRawCount();
        if (columnCount == 0 || rowCount == 0) {
            return;
        }

        int gridLayoutWidth = gridLayout.getMeasuredWidth();
        int gridLayoutHeight = gridLayout.getMeasuredHeight();
        if (gridLayoutWidth == 0) {
            ViewTreeObserver vto = gridLayout.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    gridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int gridLayoutWidth = gridLayout.getMeasuredWidth();
                    int gridLayoutHeight = gridLayout.getMeasuredHeight();
                    addChildView(gridLayout, baseGridAdapter
                            , gridLayoutWidth, gridLayoutHeight
                            , columnCount, rowCount);
                }
            });
        } else {
            addChildView(gridLayout, baseGridAdapter
                    , gridLayoutWidth, gridLayoutHeight
                    , columnCount, rowCount);
        }
    }

    private static void addChildView(GridLayout gridLayout, BaseGridAdapter baseGridAdapter,
                                     int layoutWidth, int layoutHeight,
                                     int columnCount, int rawCount) {

        int gridWidth = baseGridAdapter.getGridWidth();
        int gridHeight = baseGridAdapter.getGridHeight();

        if (gridHeight == 0) {
            gridHeight = layoutHeight / rawCount;
        }

        if (gridWidth == 0) {
            gridWidth = layoutWidth / columnCount;
        }

        gridLayout.setRowCount(rawCount);
        gridLayout.setColumnCount(columnCount);

        //标记已渲染的位置
        boolean[][] markRender = new boolean[columnCount][rawCount];

        for (int i = 0; i < columnCount; i++) {
            for (int j = 0; j < rawCount; j++) {

                if (markRender[i][j]) {
                    continue;
                }

                int viewType = baseGridAdapter.getItemViewType(i, j);

                BaseGridAdapter.ViewHolder viewHolder =
                        baseGridAdapter.onCreateViewHolder(gridLayout, viewType);

                int rowSpan = 1;
                int columnSpan = 1;

                if (baseGridAdapter.isGridExist(i, j)) {
                    rowSpan = baseGridAdapter.getRawSpan(i, j);
                    columnSpan = baseGridAdapter.getColumnSpan(i, j);
                    baseGridAdapter.onBindViewHolder(viewHolder, i, j, viewType);
                } else {
                    viewHolder.itemView.setVisibility(View.INVISIBLE);
                }

                GridLayout.Spec columnSpec = GridLayout.spec(i, columnSpan);
                GridLayout.Spec rowSpec = GridLayout.spec(j, rowSpan);

                GridLayout.LayoutParams layoutParams
                        = new GridLayout.LayoutParams(rowSpec, columnSpec);
                layoutParams.width = gridWidth * columnSpan;
                layoutParams.height = gridHeight * rowSpan;

                gridLayout.addView(viewHolder.itemView, layoutParams);

                markRender(markRender, i, j, i + columnSpan, j + rowSpan);
            }
        }
    }

    private static void markRender(boolean[][] mark, int fromI, int fromJ, int toI, int toJ) {
        for (int i = fromI; i < toI; i++) {
            for (int j = fromJ; j < toJ; j++) {
                mark[i][j] = true;
            }
        }
    }

}
