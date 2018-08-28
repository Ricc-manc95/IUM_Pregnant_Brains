// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;

public final class QuestionMetrics
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public long delayEndMs;
    public long delayStartMs;

    public QuestionMetrics()
    {
        delayStartMs = -1L;
        delayEndMs = -1L;
    }

    QuestionMetrics(Parcel parcel)
    {
        delayStartMs = parcel.readLong();
        delayEndMs = parcel.readLong();
    }

    public final int describeContents()
    {
        return 0;
    }

    public final long getDelayMs()
    {
        boolean flag;
        if (delayEndMs >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return delayEndMs - delayStartMs;
        } else
        {
            return -1L;
        }
    }

    public final void markAsAnswered()
    {
        boolean flag2 = true;
        boolean flag;
        if (delayStartMs >= 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            Log.e("HatsLibQuestionMetrics", "Question was marked as answered but was never marked as shown.");
        } else
        {
            boolean flag1;
            if (delayEndMs >= 0L)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                delayEndMs = SystemClock.elapsedRealtime();
                return;
            }
        }
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(delayStartMs);
        parcel.writeLong(delayEndMs);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new QuestionMetrics(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new QuestionMetrics[i];
        }

        _cls1()
        {
        }
    }

}
