// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.InputStream;

// Referenced classes of package com.google.protobuf:
//            MutableMessageLite, Internal, CodedInputStream, ExtensionRegistryLite

public abstract class AbstractMutableMessageLite
    implements MutableMessageLite
{

    public AbstractMutableMessageLite()
    {
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        throw new UnsupportedOperationException("clone() should be implemented by subclasses.");
    }

    public boolean parseFrom(InputStream inputstream)
    {
        clear();
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new CodedInputStream.StreamDecoder(inputstream, 4096);
        }
        ExtensionRegistryLite.getEmptyRegistry();
        return mergeFrom$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONK6RR4CLI4IRJGELQ56T3ICLGMQEQCCDNMQBR7DTNMER355TO74RRKDTH7APHF8LS78PBEEDKMURIICLJMISRKE9SKOQBKCKTIIMG_0() && inputstream.getLastTag() == 0;
    }
}
