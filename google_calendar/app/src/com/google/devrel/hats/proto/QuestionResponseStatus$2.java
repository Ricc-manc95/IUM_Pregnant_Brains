// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.devrel.hats.proto;


// Referenced classes of package com.google.devrel.hats.proto:
//            QuestionResponseStatus

final class 
    implements com.google.protobuf.Status._cls2
{

    public final boolean isInRange(int i)
    {
        return QuestionResponseStatus.forNumber(i) != null;
    }

    ()
    {
    }
}
