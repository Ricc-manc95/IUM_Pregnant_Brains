// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import com.google.protobuf.nano.MessageNano;
import social.graph.chips.nano.ChipsExtension;

// Referenced classes of package com.google.android.gms.chips:
//            GmsChipsClearcutLogger

static final class chipsExtension
    implements com.google.android.gms.clearcut.psExtension
{

    private ChipsExtension chipsExtension;

    public final byte[] toProtoBytes()
    {
        ChipsExtension chipsextension = chipsExtension;
        int i = chipsextension.computeSerializedSize();
        chipsextension.cachedSize = i;
        byte abyte0[] = new byte[i];
        MessageNano.toByteArray(chipsextension, abyte0, 0, abyte0.length);
        return abyte0;
    }

    (ChipsExtension chipsextension)
    {
        chipsExtension = chipsextension;
    }
}
