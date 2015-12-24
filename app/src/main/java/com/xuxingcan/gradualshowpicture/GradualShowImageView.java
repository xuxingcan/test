package com.xuxingcan.gradualshowpicture;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by xuxingcan on 15-12-21.
 */
public class GradualShowImageView extends ImageView {
    private ClipDrawable mClipDrawable = null;
    private ObjectAnimator animator = null;
    private int length;
    public static final int SLIP_TOP = 1, SLIP_BOTTOM = 2, SLIP_LEFT = 3, SLIP_RIGHT = 4;
    private ValueAnimator.AnimatorUpdateListener mListener;
    private Drawable mDrawable;

    public GradualShowImageView(Context context) {
        this(context, null);
    }

    public GradualShowImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradualShowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawable = getDrawable();
    }

    public void startGradualShowAnimator(int slip, int mDuration) {
        startGradualShowAnimator(slip, mDuration, new LinearInterpolator(), null);
    }

    public void startGradualShowAnimator(int slip, int mDuration, TimeInterpolator mTimeInterpolator, Animator.AnimatorListener mAnimatorListener) {
        if (mDrawable != null) {
            mClipDrawable = null;
            animator = null;
            mListener = null;
            switch (slip) {
                case SLIP_BOTTOM:
                    mClipDrawable = new ClipDrawable(mDrawable, Gravity.BOTTOM, ClipDrawable.VERTICAL);
                    length = getHeight();
                    animator = ObjectAnimator.ofFloat(this, "writeSomeNoPointWordsSuchAsILoveRuby", 0F, length);
                    mListener = new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float value = (Float) animation.getAnimatedValue();
                            setTranslationY(value);
                            int level = (int) (value / length * 10000);
                            mClipDrawable.setLevel(level);
                        }
                    };
                    break;
                case SLIP_LEFT:
                    mClipDrawable = new ClipDrawable(mDrawable, Gravity.START, ClipDrawable.HORIZONTAL);
                    length = -getWidth();
                    animator = ObjectAnimator.ofFloat(this, "writeSomeNoPointWordsSuchAsILoveRuby", 0F, length);
                    mListener = new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float value = (Float) animation.getAnimatedValue();
                            setTranslationX(value);
                            int level = (int) (value / length * 10000);
                            mClipDrawable.setLevel(level);
                        }
                    };
                    break;
                case SLIP_RIGHT:
                    mClipDrawable = new ClipDrawable(mDrawable, Gravity.END, ClipDrawable.HORIZONTAL);
                    length = getWidth();
                    animator = ObjectAnimator.ofFloat(this, "writeSomeNoPointWordsSuchAsILoveRuby", 0F, length);
                    mListener = new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float value = (Float) animation.getAnimatedValue();
                            setTranslationX(value);
                            int level = (int) (value / length * 10000);
                            mClipDrawable.setLevel(level);
                        }
                    };
                    break;
                case SLIP_TOP:
                    mClipDrawable = new ClipDrawable(mDrawable, Gravity.TOP, ClipDrawable.VERTICAL);
                    length = -getHeight();
                    animator = ObjectAnimator.ofFloat(this, "writeSomeNoPointWordsSuchAsILoveRuby", 0F, length);
                    mListener = new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float value = (Float) animation.getAnimatedValue();
                            setTranslationY(value);
                            int level = (int) (value / length * 10000);
                            mClipDrawable.setLevel(level);
                        }
                    };
                    break;
                default:
                    return;
            }
            setTranslationY(0);
            setTranslationX(0);
            mClipDrawable.setLevel(0);
            setImageDrawable(mClipDrawable);
            animator.addUpdateListener(mListener);
            animator.setDuration(mDuration);
            animator.setInterpolator(mTimeInterpolator);
            if (mAnimatorListener != null) {
                animator.addListener(mAnimatorListener);
            }
            animator.start();
        }
    }
}
