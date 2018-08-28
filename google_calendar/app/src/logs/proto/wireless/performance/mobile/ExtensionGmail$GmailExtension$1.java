// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package logs.proto.wireless.performance.mobile;


final class 
    implements com.google.protobuf.l.GmailExtension._cls1
{

    public final Object convert(Object obj)
    {
        com.android.mail.perf.ation ation = com.android.mail.perf.ation.forNumber(((Integer)obj).intValue());
        obj = ation;
        if (ation == null)
        {
            obj = com.android.mail.perf.ation.UNKNOWN_ANNOTATION;
        }
        return obj;
    }

    ()
    {
    }
}
