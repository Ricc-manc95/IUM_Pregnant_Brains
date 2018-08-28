// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;

public final class mInflater
{

    public ListAdapter mAdapter;
    public boolean mCancelable;
    public int mCheckedItem;
    public boolean mCheckedItems[];
    public final Context mContext;
    public View mCustomTitleView;
    public Drawable mIcon;
    private int mIconAttrId;
    private int mIconId;
    public final LayoutInflater mInflater;
    public boolean mIsMultiChoice;
    public boolean mIsSingleChoice;
    public CharSequence mItems[];
    public CharSequence mMessage;
    public android.content.ner mNegativeButtonListener;
    public CharSequence mNegativeButtonText;
    public android.content.ner mNeutralButtonListener;
    public CharSequence mNeutralButtonText;
    public android.content.ener mOnCancelListener;
    public android.content.eClickListener mOnCheckboxClickListener;
    public android.content.ner mOnClickListener;
    public android.content.r mOnKeyListener;
    public android.content.ner mPositiveButtonListener;
    public CharSequence mPositiveButtonText;
    public CharSequence mTitle;
    public View mView;
    public int mViewLayoutResId;
    public boolean mViewSpacingSpecified;

    public istener(Context context)
    {
        mIconId = 0;
        mIconAttrId = 0;
        mViewSpacingSpecified = false;
        mCheckedItem = -1;
        mContext = context;
        mCancelable = true;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
}
