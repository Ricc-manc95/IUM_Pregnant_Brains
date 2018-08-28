// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.framework.logging.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

// Referenced classes of package com.google.android.gms.framework.logging.proto.nano:
//            GCoreClientInfo, EntryPointInfo, GCoreModuleInfo

public final class GCoreDimensions extends ExtendableMessageNano
{

    private GCoreClientInfo clientInfo;
    private EntryPointInfo entryPointInfo;
    private GCoreModuleInfo moduleInfo;

    public GCoreDimensions()
    {
        clientInfo = null;
        entryPointInfo = null;
        moduleInfo = null;
        cachedSize = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (clientInfo != null)
        {
            GCoreClientInfo gcoreclientinfo = clientInfo;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(8);
            int k = gcoreclientinfo.computeSerializedSize();
            gcoreclientinfo.cachedSize = k;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(k) + k + i);
        }
        j = i;
        if (entryPointInfo != null)
        {
            EntryPointInfo entrypointinfo = entryPointInfo;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(16);
            int l = entrypointinfo.computeSerializedSize();
            entrypointinfo.cachedSize = l;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(l) + l + j);
        }
        i = j;
        if (moduleInfo != null)
        {
            GCoreModuleInfo gcoremoduleinfo = moduleInfo;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(24);
            int i1 = gcoremoduleinfo.computeSerializedSize();
            gcoremoduleinfo.cachedSize = i1;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(i1) + i1 + i);
        }
        return i;
    }

    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano)
        throws IOException
    {
        do
        {
            int i = codedinputbytebuffernano.readTag();
            switch (i)
            {
            default:
                if (super.storeUnknownField(codedinputbytebuffernano, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 10: // '\n'
                if (clientInfo == null)
                {
                    clientInfo = new GCoreClientInfo();
                }
                codedinputbytebuffernano.readMessage(clientInfo);
                break;

            case 18: // '\022'
                if (entryPointInfo == null)
                {
                    entryPointInfo = new EntryPointInfo();
                }
                codedinputbytebuffernano.readMessage(entryPointInfo);
                break;

            case 26: // '\032'
                if (moduleInfo == null)
                {
                    moduleInfo = new GCoreModuleInfo();
                }
                codedinputbytebuffernano.readMessage(moduleInfo);
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (clientInfo != null)
        {
            GCoreClientInfo gcoreclientinfo = clientInfo;
            codedoutputbytebuffernano.writeRawVarint32(10);
            if (((MessageNano) (gcoreclientinfo)).cachedSize < 0)
            {
                gcoreclientinfo.cachedSize = gcoreclientinfo.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (gcoreclientinfo)).cachedSize);
            gcoreclientinfo.writeTo(codedoutputbytebuffernano);
        }
        if (entryPointInfo != null)
        {
            EntryPointInfo entrypointinfo = entryPointInfo;
            codedoutputbytebuffernano.writeRawVarint32(18);
            if (((MessageNano) (entrypointinfo)).cachedSize < 0)
            {
                entrypointinfo.cachedSize = entrypointinfo.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (entrypointinfo)).cachedSize);
            entrypointinfo.writeTo(codedoutputbytebuffernano);
        }
        if (moduleInfo != null)
        {
            GCoreModuleInfo gcoremoduleinfo = moduleInfo;
            codedoutputbytebuffernano.writeRawVarint32(26);
            if (((MessageNano) (gcoremoduleinfo)).cachedSize < 0)
            {
                gcoremoduleinfo.cachedSize = gcoremoduleinfo.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (gcoremoduleinfo)).cachedSize);
            gcoremoduleinfo.writeTo(codedoutputbytebuffernano);
        }
        super.writeTo(codedoutputbytebuffernano);
    }
}
