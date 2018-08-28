// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;


// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor

public final class cessor extends cessor
{

    private CharSequence mBigText;

    public final void apply(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor)
    {
        (new android.app.(notificationbuilderwithbuilderaccessor.getBuilder())).ContentTitle(null).t(mBigText);
    }

    public final mBigText bigText(CharSequence charsequence)
    {
        CharSequence charsequence1 = charsequence;
        if (charsequence != null)
        {
            charsequence1 = charsequence;
            if (charsequence.length() > 5120)
            {
                charsequence1 = charsequence.subSequence(0, 5120);
            }
        }
        mBigText = charsequence1;
        return this;
    }

    public cessor()
    {
    }
}
