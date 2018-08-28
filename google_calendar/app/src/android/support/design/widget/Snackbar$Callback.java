// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;


// Referenced classes of package android.support.design.widget:
//            Snackbar

public class mBar.BaseCallback extends mBar.BaseCallback
{

    public void onDismissed(Snackbar snackbar, int i)
    {
    }

    public volatile void onDismissed(Object obj, int i)
    {
        onDismissed((Snackbar)obj, i);
    }

    public void onShown(Snackbar snackbar)
    {
    }

    public volatile void onShown(Object obj)
    {
        onShown((Snackbar)obj);
    }

    public mBar.BaseCallback()
    {
    }
}
