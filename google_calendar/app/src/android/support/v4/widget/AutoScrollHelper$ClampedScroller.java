// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;


// Referenced classes of package android.support.v4.widget:
//            AutoScrollHelper

final class mDeltaY
{

    public long mDeltaTime;
    public int mDeltaX;
    public int mDeltaY;
    public int mEffectiveRampDown;
    public int mRampDownDuration;
    public int mRampUpDuration;
    public long mStartTime;
    public long mStopTime;
    public float mStopValue;
    public float mTargetVelocityX;
    public float mTargetVelocityY;

    final float getValueAt(long l)
    {
        if (l < mStartTime)
        {
            return 0.0F;
        }
        if (mStopTime < 0L || l < mStopTime)
        {
            return AutoScrollHelper.constrain((float)(l - mStartTime) / (float)mRampUpDuration, 0.0F, 1.0F) * 0.5F;
        } else
        {
            long l1 = mStopTime;
            float f = mStopValue;
            float f1 = mStopValue;
            return AutoScrollHelper.constrain((float)(l - l1) / (float)mEffectiveRampDown, 0.0F, 1.0F) * f1 + (1.0F - f);
        }
    }

    ()
    {
        mStartTime = 0x8000000000000000L;
        mStopTime = -1L;
        mDeltaTime = 0L;
        mDeltaX = 0;
        mDeltaY = 0;
    }
}
