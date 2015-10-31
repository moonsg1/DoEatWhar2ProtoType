package kms.prototype;

import android.support.v4.view.ViewPager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by KMS on 2015-10-31. 2015
 */
public class UnSwipableViewPager extends ViewPager {
    public UnSwipableViewPager(Context context) {
        super(context);
    }

    public UnSwipableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return false;
    }
}
