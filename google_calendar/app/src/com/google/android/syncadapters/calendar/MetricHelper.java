// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.content.SyncResult;
import com.google.common.base.Absent;
import com.google.common.base.Present;

// Referenced classes of package com.google.android.syncadapters.calendar:
//            DeviceIdleAndNetworkStatus

final class MetricHelper
{

    static com.google.calendar.v2a.android.util.metric.MetricUtils.Result getResult(boolean flag, DeviceIdleAndNetworkStatus deviceidleandnetworkstatus, SyncResult syncresult)
    {
        if (flag)
        {
            if (deviceidleandnetworkstatus == null)
            {
                return com.google.calendar.v2a.android.util.metric.MetricUtils.Result.CANCEL;
            }
            deviceidleandnetworkstatus = CancelSource.fromStatus(deviceidleandnetworkstatus);
            if (deviceidleandnetworkstatus == null)
            {
                throw new NullPointerException();
            } else
            {
                return com.google.calendar.v2a.android.util.metric.MetricUtils.Result.cancel(new Present(deviceidleandnetworkstatus), Absent.INSTANCE);
            }
        }
        if (!syncresult.hasError())
        {
            return com.google.calendar.v2a.android.util.metric.MetricUtils.Result.SUCCESS;
        }
        deviceidleandnetworkstatus = ErrorSource.fromResult(syncresult);
        if (deviceidleandnetworkstatus == null)
        {
            throw new NullPointerException();
        } else
        {
            return com.google.calendar.v2a.android.util.metric.MetricUtils.Result.failure(new Present(deviceidleandnetworkstatus), Absent.INSTANCE);
        }
    }

    private class CancelSource extends Enum
    {

        private static final CancelSource $VALUES[];
        private static final CancelSource DOZE;
        private static final CancelSource DOZE_LIGHT;
        private static final CancelSource OFFLINE;
        private static final CancelSource UNKNOWN;

        static CancelSource fromStatus(DeviceIdleAndNetworkStatus deviceidleandnetworkstatus)
        {
            if (Boolean.TRUE.equals(deviceidleandnetworkstatus.isDeviceIdleLight))
            {
                return DOZE_LIGHT;
            }
            if (Boolean.TRUE.equals(deviceidleandnetworkstatus.isDeviceIdle))
            {
                return DOZE;
            }
            if (Boolean.FALSE.equals(deviceidleandnetworkstatus.hasNetwork))
            {
                return OFFLINE;
            } else
            {
                return UNKNOWN;
            }
        }

        public static CancelSource[] values()
        {
            return (CancelSource[])$VALUES.clone();
        }

        static 
        {
            UNKNOWN = new CancelSource("UNKNOWN", 0);
            DOZE = new CancelSource("DOZE", 1);
            DOZE_LIGHT = new CancelSource("DOZE_LIGHT", 2);
            OFFLINE = new CancelSource("OFFLINE", 3);
            $VALUES = (new CancelSource[] {
                UNKNOWN, DOZE, DOZE_LIGHT, OFFLINE
            });
        }

        private CancelSource(String s, int i)
        {
            super(s, i);
        }
    }


    private class ErrorSource extends Enum
    {

        private static final ErrorSource $VALUES[];
        private static final ErrorSource AUTH_EXCEPTION;
        private static final ErrorSource IO_EXCEPTION;
        private static final ErrorSource PARSE_EXCEPTION;
        private static final ErrorSource UNKNOWN;

        static ErrorSource fromResult(SyncResult syncresult)
        {
            if (syncresult.stats.numAuthExceptions > 0L)
            {
                return AUTH_EXCEPTION;
            }
            if (syncresult.stats.numParseExceptions > 0L)
            {
                return PARSE_EXCEPTION;
            }
            if (syncresult.stats.numIoExceptions > 0L)
            {
                return IO_EXCEPTION;
            } else
            {
                return UNKNOWN;
            }
        }

        public static ErrorSource[] values()
        {
            return (ErrorSource[])$VALUES.clone();
        }

        static 
        {
            UNKNOWN = new ErrorSource("UNKNOWN", 0);
            AUTH_EXCEPTION = new ErrorSource("AUTH_EXCEPTION", 1);
            PARSE_EXCEPTION = new ErrorSource("PARSE_EXCEPTION", 2);
            IO_EXCEPTION = new ErrorSource("IO_EXCEPTION", 3);
            $VALUES = (new ErrorSource[] {
                UNKNOWN, AUTH_EXCEPTION, PARSE_EXCEPTION, IO_EXCEPTION
            });
        }

        private ErrorSource(String s, int i)
        {
            super(s, i);
        }
    }

}
