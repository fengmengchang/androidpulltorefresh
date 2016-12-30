/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-30上午10:32:04
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.R;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2016-12-30上午10:32:04
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PullToRefreshHeaderFooterGridView extends PullToRefreshAdapterViewBase<HeaderFooterGridView> {

    public PullToRefreshHeaderFooterGridView(Context context) {
        super(context);
    }

    public PullToRefreshHeaderFooterGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshHeaderFooterGridView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshHeaderFooterGridView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected final HeaderFooterGridView createRefreshableView(Context context, AttributeSet attrs) {
        final HeaderFooterGridView gv;
        if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
            gv = new InternalGridViewSDK9(context, attrs);
        } else {
            gv = new InternalGridView(context, attrs);
        }

        // Use Generated ID (from res/values/ids.xml)
        gv.setId(R.id.gridview);
        return gv;
    }

    class InternalGridView extends HeaderFooterGridView implements EmptyViewMethodAccessor {

        public InternalGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public void setEmptyView(View emptyView) {
        	PullToRefreshHeaderFooterGridView.this.setEmptyView(emptyView);
        }

        @Override
        public void setEmptyViewInternal(View emptyView) {
            super.setEmptyView(emptyView);
        }
    }

    @TargetApi(9)
    final class InternalGridViewSDK9 extends InternalGridView {

        public InternalGridViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                    scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshHeaderFooterGridView.this, deltaX, scrollX, deltaY, scrollY, isTouchEvent);

            return returnValue;
        }
    }
}