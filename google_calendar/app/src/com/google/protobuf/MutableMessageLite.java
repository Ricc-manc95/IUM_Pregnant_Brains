// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.InputStream;

// Referenced classes of package com.google.protobuf:
//            MessageLite

public interface MutableMessageLite
    extends MessageLite, Cloneable
{

    public abstract MutableMessageLite clear();

    public abstract MutableMessageLite clone();

    public abstract boolean mergeFrom$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONK6RR4CLI4IRJGELQ56T3ICLGMQEQCCDNMQBR7DTNMER355TO74RRKDTH7APHF8LS78PBEEDKMURIICLJMISRKE9SKOQBKCKTIIMG_0();

    public abstract boolean parseFrom(InputStream inputstream);
}
