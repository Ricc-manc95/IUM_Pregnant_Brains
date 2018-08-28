// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.rosy.calendar.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

public final class cachedSize extends ExtendableMessageNano
{

    private String buildingId;
    private HomeOffice homeOffice;
    private OfficeLocation officeLocation;
    private int oneof_location_;
    private String timeZone;

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (buildingId != null)
        {
            i = j;
            if (!buildingId.equals(""))
            {
                String s = buildingId;
                i = CodedOutputByteBufferNano.computeRawVarint32Size(32);
                int k = CodedOutputByteBufferNano.encodedLength(s);
                i = j + (k + CodedOutputByteBufferNano.computeRawVarint32Size(k) + i);
            }
        }
        j = i;
        if (timeZone != null)
        {
            j = i;
            if (!timeZone.equals(""))
            {
                String s1 = timeZone;
                j = CodedOutputByteBufferNano.computeRawVarint32Size(40);
                int l = CodedOutputByteBufferNano.encodedLength(s1);
                j = i + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
            }
        }
        i = j;
        if (oneof_location_ == 0)
        {
            HomeOffice homeoffice = homeOffice;
            i = CodedOutputByteBufferNano.computeRawVarint32Size(48);
            int i1 = homeoffice.computeSerializedSize();
            homeoffice.cachedSize = i1;
            i = j + (CodedOutputByteBufferNano.computeRawVarint32Size(i1) + i1 + i);
        }
        j = i;
        if (oneof_location_ == 1)
        {
            OfficeLocation officelocation = officeLocation;
            j = CodedOutputByteBufferNano.computeRawVarint32Size(56);
            int j1 = officelocation.computeSerializedSize();
            officelocation.cachedSize = j1;
            j = i + (CodedOutputByteBufferNano.computeRawVarint32Size(j1) + j1 + j);
        }
        return j;
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

            case 34: // '"'
                buildingId = codedinputbytebuffernano.readString();
                break;

            case 42: // '*'
                timeZone = codedinputbytebuffernano.readString();
                break;

            case 50: // '2'
                class HomeOffice extends ExtendableMessageNano
                {

                    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano1)
                        throws IOException
                    {
_L3:
                        int j = codedinputbytebuffernano1.readTag();
                        j;
                        JVM INSTR tableswitch 0 0: default 24
                    //                                   0 33;
                           goto _L1 _L2
_L1:
                        if (super.storeUnknownField(codedinputbytebuffernano1, j)) goto _L3; else goto _L2
_L2:
                        return this;
                    }

            public HomeOffice()
            {
                unknownFieldData = null;
                cachedSize = -1;
            }
                }

                if (homeOffice == null)
                {
                    homeOffice = new HomeOffice();
                }
                codedinputbytebuffernano.readMessage(homeOffice);
                oneof_location_ = 0;
                break;

            case 58: // ':'
                class OfficeLocation extends ExtendableMessageNano
                {

                    private String buildingId;

                    protected final int computeSerializedSize()
                    {
                        int k = super.computeSerializedSize();
                        int j = k;
                        if (buildingId != null)
                        {
                            j = k;
                            if (!buildingId.equals(""))
                            {
                                String s = buildingId;
                                j = CodedOutputByteBufferNano.computeRawVarint32Size(8);
                                int l = CodedOutputByteBufferNano.encodedLength(s);
                                j = k + (l + CodedOutputByteBufferNano.computeRawVarint32Size(l) + j);
                            }
                        }
                        return j;
                    }

                    public final MessageNano mergeFrom(CodedInputByteBufferNano codedinputbytebuffernano1)
                        throws IOException
                    {
                        do
                        {
                            int j = codedinputbytebuffernano1.readTag();
                            switch (j)
                            {
                            default:
                                if (super.storeUnknownField(codedinputbytebuffernano1, j))
                                {
                                    continue;
                                }
                                // fall through

                            case 0: // '\0'
                                return this;

                            case 10: // '\n'
                                buildingId = codedinputbytebuffernano1.readString();
                                break;
                            }
                        } while (true);
                    }

                    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
                        throws IOException
                    {
                        if (buildingId != null && !buildingId.equals(""))
                        {
                            String s = buildingId;
                            codedoutputbytebuffernano.writeRawVarint32(10);
                            codedoutputbytebuffernano.writeStringNoTag(s);
                        }
                        super.writeTo(codedoutputbytebuffernano);
                    }

            public OfficeLocation()
            {
                buildingId = "";
                unknownFieldData = null;
                cachedSize = -1;
            }
                }

                if (officeLocation == null)
                {
                    officeLocation = new OfficeLocation();
                }
                codedinputbytebuffernano.readMessage(officeLocation);
                oneof_location_ = 1;
                break;
            }
        } while (true);
    }

    public final void writeTo(CodedOutputByteBufferNano codedoutputbytebuffernano)
        throws IOException
    {
        if (buildingId != null && !buildingId.equals(""))
        {
            String s = buildingId;
            codedoutputbytebuffernano.writeRawVarint32(34);
            codedoutputbytebuffernano.writeStringNoTag(s);
        }
        if (timeZone != null && !timeZone.equals(""))
        {
            String s1 = timeZone;
            codedoutputbytebuffernano.writeRawVarint32(42);
            codedoutputbytebuffernano.writeStringNoTag(s1);
        }
        if (oneof_location_ == 0)
        {
            HomeOffice homeoffice = homeOffice;
            codedoutputbytebuffernano.writeRawVarint32(50);
            if (((MessageNano) (homeoffice)).cachedSize < 0)
            {
                homeoffice.cachedSize = homeoffice.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (homeoffice)).cachedSize);
            homeoffice.writeTo(codedoutputbytebuffernano);
        }
        if (oneof_location_ == 1)
        {
            OfficeLocation officelocation = officeLocation;
            codedoutputbytebuffernano.writeRawVarint32(58);
            if (((MessageNano) (officelocation)).cachedSize < 0)
            {
                officelocation.cachedSize = officelocation.computeSerializedSize();
            }
            codedoutputbytebuffernano.writeRawVarint32(((MessageNano) (officelocation)).cachedSize);
            officelocation.writeTo(codedoutputbytebuffernano);
        }
        super.writeTo(codedoutputbytebuffernano);
    }

    public OfficeLocation()
    {
        oneof_location_ = -1;
        oneof_location_ = -1;
        homeOffice = null;
        oneof_location_ = -1;
        officeLocation = null;
        buildingId = "";
        timeZone = "";
        unknownFieldData = null;
        cachedSize = -1;
    }
}
