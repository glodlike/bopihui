package com.wzsykj.wuyaojiu.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wzsykj.wuyaojiu.utils.LogUtils;

/**
 * view 间距
 */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int sPace;
    private int rowNum;
    private boolean haveTag;

    /**
     * @param sPace   间距 dp
     * @param rowNum  列
     * @param haveTag 复用的item false,不复用的item true
     */
    public GridDividerItemDecoration(int sPace, int rowNum, boolean haveTag) {
        this.sPace = sPace;
        this.rowNum = rowNum;
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

//        //判断 当前 view 是 左边 右边 中间
        if ((position + 1) % rowNum == 1) {
            LogUtils.d("左边＝＝＝＝");
            right = sPace / 2;

        } else if ((position + 1) % rowNum == 0) {
            LogUtils.d("右边＝＝＝＝");
            left = sPace / 2;
        } else {
            LogUtils.d("中间＝＝＝＝");
            left = sPace / 2;
            right = sPace / 2;
        }

        top = sPace / 2;
        bottom = sPace / 2;

        outRect.set(left, top, right, bottom);

        LogUtils.d(position + "item 间距 == left：" + outRect.left + "===right:" + outRect.right);
    }
}
