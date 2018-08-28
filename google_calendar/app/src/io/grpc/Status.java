// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

// Referenced classes of package io.grpc:
//            StatusException, StatusRuntimeException

public final class Status
{
    public static final class Code extends Enum
    {

        private static final Code $VALUES[];
        public static final Code ABORTED;
        public static final Code ALREADY_EXISTS;
        public static final Code CANCELLED;
        public static final Code DATA_LOSS;
        public static final Code DEADLINE_EXCEEDED;
        public static final Code FAILED_PRECONDITION;
        public static final Code INTERNAL;
        public static final Code INVALID_ARGUMENT;
        public static final Code NOT_FOUND;
        public static final Code OK;
        public static final Code OUT_OF_RANGE;
        public static final Code PERMISSION_DENIED;
        public static final Code RESOURCE_EXHAUSTED;
        public static final Code UNAUTHENTICATED;
        public static final Code UNAVAILABLE;
        public static final Code UNIMPLEMENTED;
        public static final Code UNKNOWN;
        public final int value;
        public final byte valueAscii[];

        public static Code valueOf(String s)
        {
            return (Code)Enum.valueOf(io/grpc/Status$Code, s);
        }

        public static Code[] values()
        {
            return (Code[])$VALUES.clone();
        }

        static 
        {
            OK = new Code("OK", 0, 0);
            CANCELLED = new Code("CANCELLED", 1, 1);
            UNKNOWN = new Code("UNKNOWN", 2, 2);
            INVALID_ARGUMENT = new Code("INVALID_ARGUMENT", 3, 3);
            DEADLINE_EXCEEDED = new Code("DEADLINE_EXCEEDED", 4, 4);
            NOT_FOUND = new Code("NOT_FOUND", 5, 5);
            ALREADY_EXISTS = new Code("ALREADY_EXISTS", 6, 6);
            PERMISSION_DENIED = new Code("PERMISSION_DENIED", 7, 7);
            RESOURCE_EXHAUSTED = new Code("RESOURCE_EXHAUSTED", 8, 8);
            FAILED_PRECONDITION = new Code("FAILED_PRECONDITION", 9, 9);
            ABORTED = new Code("ABORTED", 10, 10);
            OUT_OF_RANGE = new Code("OUT_OF_RANGE", 11, 11);
            UNIMPLEMENTED = new Code("UNIMPLEMENTED", 12, 12);
            INTERNAL = new Code("INTERNAL", 13, 13);
            UNAVAILABLE = new Code("UNAVAILABLE", 14, 14);
            DATA_LOSS = new Code("DATA_LOSS", 15, 15);
            UNAUTHENTICATED = new Code("UNAUTHENTICATED", 16, 16);
            $VALUES = (new Code[] {
                OK, CANCELLED, UNKNOWN, INVALID_ARGUMENT, DEADLINE_EXCEEDED, NOT_FOUND, ALREADY_EXISTS, PERMISSION_DENIED, RESOURCE_EXHAUSTED, FAILED_PRECONDITION, 
                ABORTED, OUT_OF_RANGE, UNIMPLEMENTED, INTERNAL, UNAVAILABLE, DATA_LOSS, UNAUTHENTICATED
            });
        }

        private Code(String s, int i, int j)
        {
            super(s, i);
            value = j;
            valueAscii = Integer.toString(j).getBytes(Charsets.US_ASCII);
        }
    }


    public static final Status CANCELLED;
    public static final Metadata.Key CODE_KEY = Metadata.Key.of("grpc-status", false, new StatusCodeMarshaller());
    public static final Status DEADLINE_EXCEEDED;
    public static final Status INTERNAL;
    public static final Metadata.Key MESSAGE_KEY;
    public static final Status OK;
    public static final Status PERMISSION_DENIED;
    public static final Status RESOURCE_EXHAUSTED;
    public static final List STATUS_LIST;
    private static final Metadata.TrustedAsciiMarshaller STATUS_MESSAGE_MARSHALLER;
    public static final Status UNAUTHENTICATED;
    public static final Status UNAVAILABLE;
    public static final Status UNKNOWN;
    public final Throwable cause;
    public final Code code;
    public final String description;

    private Status(Code code1)
    {
        this(code1, null, null);
    }

    Status(Code code1, String s, Throwable throwable)
    {
        if (code1 == null)
        {
            throw new NullPointerException(String.valueOf("code"));
        } else
        {
            code = (Code)code1;
            description = s;
            cause = throwable;
            return;
        }
    }

    static String formatThrowableMessage(Status status)
    {
        if (status.description == null)
        {
            return status.code.toString();
        } else
        {
            String s = String.valueOf(status.code);
            status = status.description;
            return (new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(status).length())).append(s).append(": ").append(status).toString();
        }
    }

    public static Status fromCodeValue(int i)
    {
        if (i < 0 || i > STATUS_LIST.size())
        {
            Status status = UNKNOWN;
            String s = (new StringBuilder(24)).append("Unknown code ").append(i).toString();
            String s1 = status.description;
            if (s1 == s || s1 != null && s1.equals(s))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                return status;
            } else
            {
                return new Status(status.code, s, status.cause);
            }
        } else
        {
            return (Status)STATUS_LIST.get(i);
        }
    }

    static Status fromCodeValue(byte abyte0[])
    {
        boolean flag1;
        flag1 = false;
        if (abyte0.length == 1 && abyte0[0] == 48)
        {
            return OK;
        }
        abyte0.length;
        JVM INSTR tableswitch 1 2: default 44
    //                   1 227
    //                   2 113;
           goto _L1 _L2 _L3
_L1:
        int i;
        int j;
        Status status;
label0:
        {
            status = UNKNOWN;
            abyte0 = String.valueOf(new String(abyte0, Charsets.US_ASCII));
            String s;
            boolean flag;
            if (abyte0.length() != 0)
            {
                abyte0 = "Unknown code ".concat(abyte0);
            } else
            {
                abyte0 = new String("Unknown code ");
            }
            s = status.description;
            if (s != abyte0)
            {
                flag = flag1;
                if (s == null)
                {
                    break label0;
                }
                flag = flag1;
                if (!s.equals(abyte0))
                {
                    break label0;
                }
            }
            flag = true;
        }
        if (flag)
        {
            return status;
        } else
        {
            return new Status(status.code, abyte0, status.cause);
        }
_L3:
        if (abyte0[0] < 48 || abyte0[0] > 57) goto _L1; else goto _L4
_L4:
        i = (abyte0[0] - 48) * 10 + 0;
        j = 1;
_L5:
        if (abyte0[j] >= 48 && abyte0[j] <= 57)
        {
            i += abyte0[j] - 48;
            if (i < STATUS_LIST.size())
            {
                return (Status)STATUS_LIST.get(i);
            }
        }
          goto _L1
_L2:
        i = 0;
        j = 0;
          goto _L5
    }

    public static Status fromThrowable(Throwable throwable)
    {
        Object obj;
        if (throwable == null)
        {
            throw new NullPointerException(String.valueOf("t"));
        }
        obj = (Throwable)throwable;
_L8:
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (!(obj instanceof StatusException)) goto _L4; else goto _L3
_L3:
        obj = ((StatusException)obj).status;
_L6:
        return ((Status) (obj));
_L4:
        if (obj instanceof StatusRuntimeException)
        {
            return ((StatusRuntimeException)obj).status;
        }
        obj = ((Throwable) (obj)).getCause();
        continue; /* Loop/switch isn't completed */
_L2:
        Status status = UNKNOWN;
        obj = status.cause;
        boolean flag;
        if (obj == throwable || obj != null && obj.equals(throwable))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = status;
        if (flag) goto _L6; else goto _L5
_L5:
        return new Status(status.code, status.description, throwable);
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final Status augmentDescription(String s)
    {
        if (s == null)
        {
            return this;
        }
        if (description == null)
        {
            return new Status(code, s, cause);
        } else
        {
            Code code1 = code;
            String s1 = description;
            return new Status(code1, (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s).length())).append(s1).append("\n").append(s).toString(), cause);
        }
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("code", code.name()).add("description", description);
        Object obj;
        if (cause != null)
        {
            obj = Throwables.getStackTraceAsString(cause);
        } else
        {
            obj = cause;
        }
        return tostringhelper.add("cause", obj).toString();
    }

    public final Status withCause(Throwable throwable)
    {
        Throwable throwable1 = cause;
        boolean flag;
        if (throwable1 == throwable || throwable1 != null && throwable1.equals(throwable))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return this;
        } else
        {
            return new Status(code, description, throwable);
        }
    }

    public final Status withDescription(String s)
    {
        String s1 = description;
        boolean flag;
        if (s1 == s || s1 != null && s1.equals(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return this;
        } else
        {
            return new Status(code, s, cause);
        }
    }

    static 
    {
        Object obj2 = new TreeMap();
        Code acode[] = Code.values();
        int j = acode.length;
        for (int i = 0; i < j; i++)
        {
            Object obj = acode[i];
            Status status = (Status)((TreeMap) (obj2)).put(Integer.valueOf(((Code) (obj)).value), new Status(((Code) (obj))));
            if (status != null)
            {
                obj2 = status.code.name();
                obj = ((Code) (obj)).name();
                throw new IllegalStateException((new StringBuilder(String.valueOf(obj2).length() + 34 + String.valueOf(obj).length())).append("Code value duplication between ").append(((String) (obj2))).append(" & ").append(((String) (obj))).toString());
            }
        }

        STATUS_LIST = Collections.unmodifiableList(new ArrayList(((TreeMap) (obj2)).values()));
        Object obj1 = Code.OK;
        OK = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.CANCELLED;
        CANCELLED = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.UNKNOWN;
        UNKNOWN = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.INVALID_ARGUMENT;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.DEADLINE_EXCEEDED;
        DEADLINE_EXCEEDED = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.NOT_FOUND;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.ALREADY_EXISTS;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.PERMISSION_DENIED;
        PERMISSION_DENIED = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.UNAUTHENTICATED;
        UNAUTHENTICATED = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.RESOURCE_EXHAUSTED;
        RESOURCE_EXHAUSTED = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.FAILED_PRECONDITION;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.ABORTED;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.OUT_OF_RANGE;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.UNIMPLEMENTED;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.INTERNAL;
        INTERNAL = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.UNAVAILABLE;
        UNAVAILABLE = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        obj1 = Code.DATA_LOSS;
        obj1 = (Status)STATUS_LIST.get(((Code) (obj1)).value);
        STATUS_MESSAGE_MARSHALLER = new StatusMessageMarshaller();
        MESSAGE_KEY = Metadata.Key.of("grpc-message", false, STATUS_MESSAGE_MARSHALLER);
    }

    private class StatusCodeMarshaller
        implements Metadata.TrustedAsciiMarshaller
    {

        public final Object parseAsciiString(byte abyte0[])
        {
            return Status.fromCodeValue(abyte0);
        }

        public final byte[] toAsciiString(Object obj)
        {
            return ((Status)obj).code.valueAscii;
        }

        StatusCodeMarshaller()
        {
        }
    }


    private class StatusMessageMarshaller
        implements Metadata.TrustedAsciiMarshaller
    {

        private static final byte HEX[] = {
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
            65, 66, 67, 68, 69, 70
        };

        private static String parseAsciiStringSlow(byte abyte0[])
        {
            ByteBuffer bytebuffer;
            int i;
            bytebuffer = ByteBuffer.allocate(abyte0.length);
            i = 0;
_L2:
            if (i >= abyte0.length)
            {
                break; /* Loop/switch isn't completed */
            }
            if (abyte0[i] != 37 || i + 2 >= abyte0.length)
            {
                break MISSING_BLOCK_LABEL_64;
            }
            bytebuffer.put((byte)Integer.parseInt(new String(abyte0, i + 1, 2, Charsets.US_ASCII), 16));
            i += 3;
            continue; /* Loop/switch isn't completed */
            NumberFormatException numberformatexception;
            numberformatexception;
            bytebuffer.put(abyte0[i]);
            i++;
            if (true) goto _L2; else goto _L1
_L1:
            return new String(bytebuffer.array(), 0, bytebuffer.position(), Charsets.UTF_8);
        }

        public final Object parseAsciiString(byte abyte0[])
        {
            for (int i = 0; i < abyte0.length; i++)
            {
                byte byte0 = abyte0[i];
                if (byte0 < 32 || byte0 >= 126 || byte0 == 37 && i + 2 < abyte0.length)
                {
                    return parseAsciiStringSlow(abyte0);
                }
            }

            return new String(abyte0, 0);
        }

        public final byte[] toAsciiString(Object obj)
        {
            byte abyte0[] = ((String)obj).getBytes(Charsets.UTF_8);
            for (int i = 0; i < abyte0.length; i++)
            {
                int j = abyte0[i];
                if (j < 32 || j >= 126 || j == 37)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j != 0)
                {
                    obj = new byte[(abyte0.length - i) * 3 + i];
                    if (i != 0)
                    {
                        System.arraycopy(abyte0, 0, obj, 0, i);
                    }
                    j = i;
                    while (j < abyte0.length) 
                    {
                        byte byte0 = abyte0[j];
                        boolean flag;
                        if (byte0 < 32 || byte0 >= 126 || byte0 == 37)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            obj[i] = 37;
                            obj[i + 1] = HEX[byte0 >> 4 & 0xf];
                            obj[i + 2] = HEX[byte0 & 0xf];
                            i += 3;
                        } else
                        {
                            obj[i] = byte0;
                            i++;
                        }
                        j++;
                    }
                    abyte0 = new byte[i];
                    System.arraycopy(obj, 0, abyte0, 0, i);
                    return abyte0;
                }
            }

            return abyte0;
        }


        StatusMessageMarshaller()
        {
        }
    }

}
