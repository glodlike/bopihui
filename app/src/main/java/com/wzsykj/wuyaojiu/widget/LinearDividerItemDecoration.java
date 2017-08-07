package com.wzsykj.wuyaojiu.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * view 间距
 */
public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int sPace;
    private boolean haveTag;

    /**
     * @param sPace   间距 dp
     * @param haveTag 复用的item false,不复用的item true
     */
    public LinearDividerItemDecoration(int sPace, boolean haveTag) {
        this.sPace = sPace;
        this.haveTag = haveTag;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //已经 赋值过的 做标记  否则会叠加辅助,对于不复用的 item 使用,复用的 使用后 会归零
        if (haveTag) {
            if (view.getTag() != null) {
                return;
            }
            //已经 赋值过的 做标记  否则会叠加辅助
            view.setTag(true);
        }

        int position = parent.getChildPosition(view);
        int left = 0, right = 0, bottom = 0, top = 0;
        bottom = sPace;
        outRect.set(left, top, right, bottom);
    }
}
