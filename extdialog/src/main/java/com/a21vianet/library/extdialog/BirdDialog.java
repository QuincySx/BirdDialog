package com.a21vianet.library.extdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by quincysx on 17-6-2.
 * 自定义Dialog
 */

public class BirdDialog extends AlertDialog implements View.OnClickListener {
    private Button mBtnConfirm;
    private TextView mTvCancel;
    private ImageView mImgHead;
    private TextView mTvContent;

    private Context mContext;
    private OnClickCallback onCancelListener;

    private Drawable mDrawable;

    private Drawable mHeadDrawable;

    private String mContentText;
    private float mContentTextSize;
    @ColorInt
    private int mContentTextColor;

    private Drawable mConfirmBackground;
    private String mConfirmText;
    private float mConfirmTextSize;
    @ColorInt
    private int mConfirmTextColor;

    private String mCancelText;
    private float mCancelTextSize;
    @ColorInt
    private int mCancelTextColor;

    private BirdDialog(Builder builder) {
        super(builder.mContext, R.style.BirdDialog);
        mContext = builder.mContext;
        setOnCancelListener(builder.onCancelListener);
        mDrawable = builder.mDrawable;
        mHeadDrawable = builder.mHeadDrawable;
        mContentText = builder.mContentText;
        mContentTextSize = builder.mContentTextSize;
        mContentTextColor = builder.mContentTextColor;
        mConfirmBackground = builder.mConfirmBackground;
        mConfirmText = builder.mConfirmText;
        mConfirmTextSize = builder.mConfirmTextSize;
        mConfirmTextColor = builder.mConfirmTextColor;
        mCancelText = builder.mCancelText;
        mCancelTextSize = builder.mCancelTextSize;
        mCancelTextColor = builder.mCancelTextColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.dialog_common, null);
        setContentView(view);

        mTvContent = (TextView) view.findViewById(R.id.tv_dialog_content);
        mImgHead = (ImageView) view.findViewById(R.id.img_dialog_head);
        mBtnConfirm = (Button) view.findViewById(R.id.btn_dialog_confirm);
        mTvCancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);

        mBtnConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);

        setData(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = (int) mContext.getResources().getDimension(R.dimen.bird_dialog_layout_width);
        win.setAttributes(lp);
    }

    private void setData(View view) {
        if (mDrawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                view.setBackground(mDrawable);
            } else {
                view.setBackgroundDrawable(mDrawable);
            }
        }

        if (mHeadDrawable != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mImgHead.setBackground(mHeadDrawable);
            } else {
                mImgHead.setBackgroundDrawable(mHeadDrawable);
            }
        }

        if (mContentText != null) {
            mTvContent.setTextKeepState(mContentText);
        }
        if (mContentTextSize != -1) {
            mTvContent.setTextSize(mContentTextSize);
        }
        if (mContentTextColor != -1) {
            mTvContent.setTextColor(mContentTextColor);
        }

        if (mConfirmBackground != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mBtnConfirm.setBackground(mConfirmBackground);
            } else {
                mBtnConfirm.setBackgroundDrawable(mConfirmBackground);
            }
        }
        if (mConfirmText != null) {
            mBtnConfirm.setText(mConfirmText);
        }
        if (mConfirmTextSize != -1) {
            mBtnConfirm.setTextSize(mConfirmTextSize);
        }
        if (mConfirmTextColor != -1) {
            mBtnConfirm.setTextColor(mConfirmTextColor);
        }

        if (mCancelText != null) {
            mTvCancel.setText(mCancelText);
        }
        if (mCancelTextSize != -1) {
            mTvCancel.setTextSize(mCancelTextSize);
        }
        if (mCancelTextColor != -1) {
            mTvCancel.setTextColor(mCancelTextColor);
        }
    }

    public BirdDialog setOnCancelListener(OnClickCallback onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public BirdDialog setBirdCancelable(boolean flag) {
        setCancelable(flag);
        return this;
    }

    public BirdDialog setBirdCanceledOnTouchOutside(boolean flag) {
        setCanceledOnTouchOutside(flag);
        return this;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_dialog_confirm) {
            if (onCancelListener != null) {
                onCancelListener.onConfirm(this);
            }
        } else if (i == R.id.tv_dialog_cancel) {
            if (onCancelListener != null) {
                onCancelListener.onCancel(this);
            }
        }
    }

    public static final class Builder {
        private Context mContext = null;
        private OnClickCallback onCancelListener = null;
        private Drawable mDrawable = null;
        private Drawable mHeadDrawable = null;
        private String mContentText = null;
        private float mContentTextSize = -1;
        private int mContentTextColor = -1;
        private Drawable mConfirmBackground = null;
        private String mConfirmText = null;
        private float mConfirmTextSize = -1;
        private int mConfirmTextColor = -1;
        private String mCancelText = null;
        private float mCancelTextSize = -1;
        private int mCancelTextColor = -1;

        public Builder(Context val) {
            mContext = val;
        }

        public Builder setOnCancelListener(OnClickCallback val) {
            onCancelListener = val;
            return this;
        }

        public Builder setDrawable(Drawable val) {
            mDrawable = val;
            return this;
        }

        public Builder setDrawable(@DrawableRes int val) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mDrawable = mContext.getResources().getDrawable(val, null);
            } else {
                mDrawable = mContext.getResources().getDrawable(val);
            }
            return this;
        }

        public Builder setHeadDrawable(Drawable val) {
            mHeadDrawable = val;
            return this;
        }

        public Builder setHeadDrawable(@DrawableRes int val) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mHeadDrawable = mContext.getResources().getDrawable(val, null);
            } else {
                mHeadDrawable = mContext.getResources().getDrawable(val);
            }
            return this;
        }

        public Builder setContentText(String val) {
            mContentText = val;
            return this;
        }

        public Builder setContentText(@StringRes int val) {
            mContentText = mContext.getString(val);
            return this;
        }

        public Builder setContentTextSize(float val) {
            mContentTextSize = val;
            return this;
        }

        public Builder setContentTextColor(@ColorInt int val) {
            mContentTextColor = val;
            return this;
        }

        public Builder setContentTextColorRes(@ColorRes int val) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mContentTextColor = mContext.getResources().getColor(val, null);
            } else {
                mContentTextColor = mContext.getResources().getColor(val);
            }
            return this;
        }

        public Builder setConfirmBackground(Drawable val) {
            mConfirmBackground = val;
            return this;
        }

        public Builder setConfirmBackground(@DrawableRes int val) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mConfirmBackground = mContext.getResources().getDrawable(val, null);
            } else {
                mConfirmBackground = mContext.getResources().getDrawable(val);
            }
            return this;
        }

        public Builder setConfirmText(String val) {
            mConfirmText = val;
            return this;
        }

        public Builder setConfirmText(@StringRes int val) {
            mConfirmText = mContext.getString(val);
            return this;
        }

        public Builder setConfirmTextSize(float val) {
            mConfirmTextSize = val;
            return this;
        }

        public Builder setConfirmTextColor(@ColorInt int val) {
            mConfirmTextColor = val;
            return this;
        }

        public Builder setConfirmTextColorRes(@ColorRes int val) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mConfirmTextColor = mContext.getResources().getColor(val, null);
            } else {
                mConfirmTextColor = mContext.getResources().getColor(val);
            }
            return this;
        }

        public Builder setCancelText(String val) {
            mCancelText = val;
            return this;
        }

        public Builder setCancelText(@StringRes int val) {
            mCancelText = mContext.getString(val);
            return this;
        }

        public Builder setCancelTextSize(float val) {
            mCancelTextSize = val;
            return this;
        }

        public Builder setCancelTextColor(@ColorInt int val) {
            mCancelTextColor = val;
            return this;
        }

        public Builder setCancelTextColorRes(@ColorRes int val) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCancelTextColor = mContext.getResources().getColor(val, null);
            } else {
                mCancelTextColor = mContext.getResources().getColor(val);
            }
            return this;
        }

        public BirdDialog build() {
            return new BirdDialog(this);
        }
    }
}
