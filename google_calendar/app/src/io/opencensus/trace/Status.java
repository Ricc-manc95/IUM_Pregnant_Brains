// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public final class Status
{

    public static final Status ABORTED;
    public static final Status ALREADY_EXISTS;
    public static final Status CANCELLED;
    public static final Status DATA_LOSS;
    public static final Status DEADLINE_EXCEEDED;
    public static final Status FAILED_PRECONDITION;
    public static final Status INTERNAL;
    public static final Status INVALID_ARGUMENT;
    public static final Status NOT_FOUND;
    public static final Status OK;
    public static final Status OUT_OF_RANGE;
    public static final Status PERMISSION_DENIED;
    public static final Status RESOURCE_EXHAUSTED;
    public static final List STATUS_LIST;
    public static final Status UNAUTHENTICATED;
    public static final Status UNAVAILABLE;
    public static final Status UNIMPLEMENTED;
    public static final Status UNKNOWN;
    public final CanonicalCode canonicalCode;
    public final String description;

    public Status(CanonicalCode canonicalcode, String s)
    {
        if (canonicalcode == null)
        {
            throw new NullPointerException("canonicalCode");
        } else
        {
            canonicalCode = (CanonicalCode)canonicalcode;
            description = s;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof Status))
        {
            return false;
        }
        Object obj1 = (Status)obj;
        if (canonicalCode != ((Status) (obj1)).canonicalCode)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = description;
        obj1 = ((Status) (obj1)).description;
        boolean flag;
        if (obj == null)
        {
            if (obj1 == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        {
            flag = obj.equals(obj1);
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            canonicalCode, description
        });
    }

    public final String toString()
    {
        String s = String.valueOf(canonicalCode);
        String s1 = description;
        return (new StringBuilder(String.valueOf(s).length() + 36 + String.valueOf(s1).length())).append("Status{canonicalCode=").append(s).append(", description=").append(s1).append("}").toString();
    }

    static 
    {
        Object obj1 = new TreeMap();
        CanonicalCode acanonicalcode[] = CanonicalCode.values();
        int j = acanonicalcode.length;
        for (int i = 0; i < j; i++)
        {
            Object obj = acanonicalcode[i];
            Status status = (Status)((TreeMap) (obj1)).put(Integer.valueOf(((CanonicalCode) (obj)).value), new Status(((CanonicalCode) (obj)), null));
            if (status != null)
            {
                obj1 = status.canonicalCode.name();
                obj = ((CanonicalCode) (obj)).name();
                throw new IllegalStateException((new StringBuilder(String.valueOf(obj1).length() + 34 + String.valueOf(obj).length())).append("Code value duplication between ").append(((String) (obj1))).append(" & ").append(((String) (obj))).toString());
            }
        }

        STATUS_LIST = Collections.unmodifiableList(new ArrayList(((TreeMap) (obj1)).values()));
        CanonicalCode canonicalcode = CanonicalCode.OK;
        OK = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.CANCELLED;
        CANCELLED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.UNKNOWN;
        UNKNOWN = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.INVALID_ARGUMENT;
        INVALID_ARGUMENT = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.DEADLINE_EXCEEDED;
        DEADLINE_EXCEEDED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.NOT_FOUND;
        NOT_FOUND = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.ALREADY_EXISTS;
        ALREADY_EXISTS = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.PERMISSION_DENIED;
        PERMISSION_DENIED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.UNAUTHENTICATED;
        UNAUTHENTICATED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.RESOURCE_EXHAUSTED;
        RESOURCE_EXHAUSTED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.FAILED_PRECONDITION;
        FAILED_PRECONDITION = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.ABORTED;
        ABORTED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.OUT_OF_RANGE;
        OUT_OF_RANGE = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.UNIMPLEMENTED;
        UNIMPLEMENTED = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.INTERNAL;
        INTERNAL = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.UNAVAILABLE;
        UNAVAILABLE = (Status)STATUS_LIST.get(canonicalcode.value);
        canonicalcode = CanonicalCode.DATA_LOSS;
        DATA_LOSS = (Status)STATUS_LIST.get(canonicalcode.value);
    }

    private class CanonicalCode extends Enum
    {

        private static final CanonicalCode $VALUES[];
        public static final CanonicalCode ABORTED;
        public static final CanonicalCode ALREADY_EXISTS;
        public static final CanonicalCode CANCELLED;
        public static final CanonicalCode DATA_LOSS;
        public static final CanonicalCode DEADLINE_EXCEEDED;
        public static final CanonicalCode FAILED_PRECONDITION;
        public static final CanonicalCode INTERNAL;
        public static final CanonicalCode INVALID_ARGUMENT;
        public static final CanonicalCode NOT_FOUND;
        public static final CanonicalCode OK;
        public static final CanonicalCode OUT_OF_RANGE;
        public static final CanonicalCode PERMISSION_DENIED;
        public static final CanonicalCode RESOURCE_EXHAUSTED;
        public static final CanonicalCode UNAUTHENTICATED;
        public static final CanonicalCode UNAVAILABLE;
        public static final CanonicalCode UNIMPLEMENTED;
        public static final CanonicalCode UNKNOWN;
        public final int value;

        public static CanonicalCode[] values()
        {
            return (CanonicalCode[])$VALUES.clone();
        }

        static 
        {
            OK = new CanonicalCode("OK", 0, 0);
            CANCELLED = new CanonicalCode("CANCELLED", 1, 1);
            UNKNOWN = new CanonicalCode("UNKNOWN", 2, 2);
            INVALID_ARGUMENT = new CanonicalCode("INVALID_ARGUMENT", 3, 3);
            DEADLINE_EXCEEDED = new CanonicalCode("DEADLINE_EXCEEDED", 4, 4);
            NOT_FOUND = new CanonicalCode("NOT_FOUND", 5, 5);
            ALREADY_EXISTS = new CanonicalCode("ALREADY_EXISTS", 6, 6);
            PERMISSION_DENIED = new CanonicalCode("PERMISSION_DENIED", 7, 7);
            RESOURCE_EXHAUSTED = new CanonicalCode("RESOURCE_EXHAUSTED", 8, 8);
            FAILED_PRECONDITION = new CanonicalCode("FAILED_PRECONDITION", 9, 9);
            ABORTED = new CanonicalCode("ABORTED", 10, 10);
            OUT_OF_RANGE = new CanonicalCode("OUT_OF_RANGE", 11, 11);
            UNIMPLEMENTED = new CanonicalCode("UNIMPLEMENTED", 12, 12);
            INTERNAL = new CanonicalCode("INTERNAL", 13, 13);
            UNAVAILABLE = new CanonicalCode("UNAVAILABLE", 14, 14);
            DATA_LOSS = new CanonicalCode("DATA_LOSS", 15, 15);
            UNAUTHENTICATED = new CanonicalCode("UNAUTHENTICATED", 16, 16);
            $VALUES = (new CanonicalCode[] {
                OK, CANCELLED, UNKNOWN, INVALID_ARGUMENT, DEADLINE_EXCEEDED, NOT_FOUND, ALREADY_EXISTS, PERMISSION_DENIED, RESOURCE_EXHAUSTED, FAILED_PRECONDITION, 
                ABORTED, OUT_OF_RANGE, UNIMPLEMENTED, INTERNAL, UNAVAILABLE, DATA_LOSS, UNAUTHENTICATED
            });
        }

        private CanonicalCode(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

}
