// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Arrays;
import java.util.Stack;

// Referenced classes of package com.google.protobuf:
//            ByteString, RopeByteString

final class 
{

    public final Stack prefixesStack = new Stack();

    final void doBalance(ByteString bytestring)
    {
        if (bytestring.isBalanced())
        {
            int i = bytestring.size();
            i = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
            if (i < 0)
            {
                i = -(i + 1) - 1;
            }
            int k = RopeByteString.minLengthByDepth[i + 1];
            if (prefixesStack.isEmpty() || ((ByteString)prefixesStack.peek()).size() >= k)
            {
                prefixesStack.push(bytestring);
                return;
            }
            i = RopeByteString.minLengthByDepth[i];
            Object obj;
            for (obj = (ByteString)prefixesStack.pop(); !prefixesStack.isEmpty() && ((ByteString)prefixesStack.peek()).size() < i; obj = new RopeByteString((ByteString)prefixesStack.pop(), ((ByteString) (obj)))) { }
            bytestring = new RopeByteString(((ByteString) (obj)), bytestring);
            do
            {
                if (prefixesStack.isEmpty())
                {
                    break;
                }
                int j = bytestring.size();
                int l = Arrays.binarySearch(RopeByteString.minLengthByDepth, j);
                j = l;
                if (l < 0)
                {
                    j = -(l + 1) - 1;
                }
                j = RopeByteString.minLengthByDepth[j + 1];
                if (((ByteString)prefixesStack.peek()).size() >= j)
                {
                    break;
                }
                bytestring = new RopeByteString((ByteString)prefixesStack.pop(), bytestring);
            } while (true);
            prefixesStack.push(bytestring);
            return;
        }
        if (bytestring instanceof RopeByteString)
        {
            bytestring = (RopeByteString)bytestring;
            doBalance(((RopeByteString) (bytestring)).left);
            doBalance(((RopeByteString) (bytestring)).right);
            return;
        } else
        {
            bytestring = String.valueOf(bytestring.getClass());
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(bytestring).length() + 49)).append("Has a new type of ByteString been created? Found ").append(bytestring).toString());
        }
    }

    ()
    {
    }
}
