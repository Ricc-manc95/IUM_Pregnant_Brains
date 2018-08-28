// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Joiner;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Referenced classes of package io.grpc:
//            Decompressor

public final class DecompressorRegistry
{

    private static final Joiner ACCEPT_ENCODING_JOINER = new Joiner(String.valueOf(','));
    public static final DecompressorRegistry DEFAULT_INSTANCE;
    public final byte advertisedDecompressors[];
    public final Map decompressors;

    private DecompressorRegistry()
    {
        decompressors = new LinkedHashMap(0);
        advertisedDecompressors = new byte[0];
    }

    private DecompressorRegistry(Decompressor decompressor, boolean flag, DecompressorRegistry decompressorregistry)
    {
        Object obj = decompressor.getMessageEncoding();
        boolean flag1;
        if (!((String) (obj)).contains(","))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf("Comma is currently not allowed in message encoding"));
        }
        int j = decompressorregistry.decompressors.size();
        int i = j;
        if (!decompressorregistry.decompressors.containsKey(decompressor.getMessageEncoding()))
        {
            i = j + 1;
        }
        LinkedHashMap linkedhashmap = new LinkedHashMap(i);
        decompressorregistry = decompressorregistry.decompressors.values().iterator();
        do
        {
            if (!decompressorregistry.hasNext())
            {
                break;
            }
            DecompressorInfo decompressorinfo = (DecompressorInfo)decompressorregistry.next();
            String s = decompressorinfo.decompressor.getMessageEncoding();
            if (!s.equals(obj))
            {
                linkedhashmap.put(s, new DecompressorInfo(decompressorinfo.decompressor, decompressorinfo.advertised));
            }
        } while (true);
        linkedhashmap.put(obj, new DecompressorInfo(decompressor, flag));
        decompressors = Collections.unmodifiableMap(linkedhashmap);
        decompressor = ACCEPT_ENCODING_JOINER;
        decompressorregistry = new HashSet(decompressors.size());
        obj = decompressors.entrySet().iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            if (((DecompressorInfo)entry.getValue()).advertised)
            {
                decompressorregistry.add((String)entry.getKey());
            }
        } while (true);
        decompressorregistry = Collections.unmodifiableSet(decompressorregistry).iterator();
        advertisedDecompressors = decompressor.appendTo(new StringBuilder(), decompressorregistry).toString().getBytes(Charset.forName("US-ASCII"));
    }

    static 
    {
        DecompressorRegistry decompressorregistry = new DecompressorRegistry();
        decompressorregistry = new DecompressorRegistry(new Codec.Gzip(), true, decompressorregistry);
        DEFAULT_INSTANCE = new DecompressorRegistry(Codec.Identity.NONE, false, decompressorregistry);
    }

    private class DecompressorInfo
    {

        public final boolean advertised;
        public final Decompressor decompressor;

        DecompressorInfo(Decompressor decompressor1, boolean flag)
        {
            if (decompressor1 == null)
            {
                throw new NullPointerException(String.valueOf("decompressor"));
            } else
            {
                decompressor = (Decompressor)decompressor1;
                advertised = flag;
                return;
            }
        }
    }

}
