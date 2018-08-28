// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.Context;
import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader
{

    public boolean mAbandoned;
    public boolean mContentChanged;
    public Context mContext;
    public int mId;
    public OnLoadCompleteListener mListener;
    public boolean mProcessingChange;
    public boolean mReset;
    public boolean mStarted;

    public Loader(Context context)
    {
        mStarted = false;
        mAbandoned = false;
        mReset = true;
        mContentChanged = false;
        mProcessingChange = false;
        mContext = context.getApplicationContext();
    }

    public void deliverResult(Object obj)
    {
        if (mListener != null)
        {
            mListener._mth51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7D66KOBMC4NMOOBECSNKUOJACLHN8EP9AO______0(obj);
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("mId=");
        printwriter.print(mId);
        printwriter.print(" mListener=");
        printwriter.println(mListener);
        if (mStarted || mContentChanged || mProcessingChange)
        {
            printwriter.print(s);
            printwriter.print("mStarted=");
            printwriter.print(mStarted);
            printwriter.print(" mContentChanged=");
            printwriter.print(mContentChanged);
            printwriter.print(" mProcessingChange=");
            printwriter.println(mProcessingChange);
        }
        if (mAbandoned || mReset)
        {
            printwriter.print(s);
            printwriter.print("mAbandoned=");
            printwriter.print(mAbandoned);
            printwriter.print(" mReset=");
            printwriter.println(mReset);
        }
    }

    public boolean onCancelLoad()
    {
        return false;
    }

    public void onForceLoad()
    {
    }

    public void onReset()
    {
    }

    public void onStartLoading()
    {
    }

    public void onStopLoading()
    {
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, stringbuilder);
        stringbuilder.append(" id=");
        stringbuilder.append(mId);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private class OnLoadCompleteListener
    {

        public abstract void onLoadComplete$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7D66KOBMC4NMOOBECSNKUOJACLHN8EP9AO______0(Object obj);
    }

}
