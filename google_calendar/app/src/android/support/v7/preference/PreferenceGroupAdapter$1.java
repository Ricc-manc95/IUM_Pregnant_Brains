// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;


// Referenced classes of package android.support.v7.preference:
//            PreferenceGroupAdapter

final class this._cls0
    implements Runnable
{

    private final PreferenceGroupAdapter this$0;

    public final void run()
    {
        syncMyPreferences();
    }

    ()
    {
        this$0 = PreferenceGroupAdapter.this;
        super();
    }
}
