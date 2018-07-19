package com.zhengchen.weight.ViewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 */
public class NoScrollViewPager extends ViewPager {
    private boolean noScroll = false;
    public interface OnMyTouchListener{
        public boolean onTouch(MotionEvent event);
    }
    public OnMyTouchListener mOnMyTouchListener;
    public void setOnMyTouchListener(OnMyTouchListener mOnMyTouchListener){
        this.mOnMyTouchListener=mOnMyTouchListener;
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }
    public boolean getNoScroll(){
        return  noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (noScroll)
            return false;
        else{
            boolean mreturn = false;
            if(mOnMyTouchListener!=null)mreturn=mOnMyTouchListener.onTouch(event);
            return mreturn?mreturn:super.onInterceptTouchEvent(event);
        }


    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }



}