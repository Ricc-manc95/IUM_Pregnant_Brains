// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.contrib.grpc.metrics;

import io.opencensus.tags.TagKey;

public final class RpcMeasureConstants
{

    public static final io.opencensus.stats.Measure.MeasureLong RPC_CLIENT_ERROR_COUNT = io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/error_count", "RPC Errors", "1");
    public static final io.opencensus.stats.Measure.MeasureLong RPC_CLIENT_FINISHED_COUNT = io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/finished_count", "Number of client RPCs (streams) finished", "1");
    public static final io.opencensus.stats.Measure.MeasureDouble RPC_CLIENT_REQUEST_BYTES = io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/request_bytes", "Request bytes", "By");
    public static final io.opencensus.stats.Measure.MeasureLong RPC_CLIENT_REQUEST_COUNT = io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/request_count", "Number of client RPC request messages", "1");
    public static final io.opencensus.stats.Measure.MeasureDouble RPC_CLIENT_RESPONSE_BYTES = io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/response_bytes", "Response bytes", "By");
    public static final io.opencensus.stats.Measure.MeasureLong RPC_CLIENT_RESPONSE_COUNT = io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/response_count", "Number of client RPC response messages", "1");
    public static final io.opencensus.stats.Measure.MeasureDouble RPC_CLIENT_ROUNDTRIP_LATENCY = io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/roundtrip_latency", "RPC roundtrip latency msec", "ms");
    public static final io.opencensus.stats.Measure.MeasureLong RPC_CLIENT_STARTED_COUNT = io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/started_count", "Number of client RPCs (streams) started", "1");
    public static final io.opencensus.stats.Measure.MeasureDouble RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES = io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/uncompressed_request_bytes", "Uncompressed Request bytes", "By");
    public static final io.opencensus.stats.Measure.MeasureDouble RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES = io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/uncompressed_response_bytes", "Uncompressed Response bytes", "By");
    public static final TagKey RPC_METHOD = TagKey.create("method");
    public static final TagKey RPC_STATUS = TagKey.create("canonical_status");

    static 
    {
        TagKey.create("grpc_client_status");
        TagKey.create("grpc_server_status");
        TagKey.create("grpc_client_method");
        TagKey.create("grpc_server_method");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/server_elapsed_time", "Server elapsed time in msecs", "ms");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/sent_bytes_per_rpc", "Total bytes sent across all request messages per RPC", "By");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/received_bytes_per_rpc", "Total bytes received across all response messages per RPC", "By");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/roundtrip_latency", "Time between first byte of request sent to last byte of response received, or terminal error.", "ms");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/sent_messages_per_rpc", "Number of messages sent in the RPC", "1");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/received_messages_per_rpc", "Number of response messages received per RPC", "1");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/client/server_latency", "Server latency in msecs", "ms");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/client/started_rpcs", "Number of started client RPCs.", "1");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/error_count", "RPC Errors", "1");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/request_bytes", "Request bytes", "By");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/response_bytes", "Response bytes", "By");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/server_elapsed_time", "Server elapsed time in msecs", "ms");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/server_latency", "Latency in msecs", "ms");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/uncompressed_request_bytes", "Uncompressed Request bytes", "By");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/uncompressed_response_bytes", "Uncompressed Response bytes", "By");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/started_count", "Number of server RPCs (streams) started", "1");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/finished_count", "Number of server RPCs (streams) finished", "1");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/request_count", "Number of server RPC request messages", "1");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/response_count", "Number of server RPC response messages", "1");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/sent_bytes_per_rpc", "Total bytes sent across all response messages per RPC", "By");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/received_bytes_per_rpc", "Total bytes received across all messages per RPC", "By");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/sent_messages_per_rpc", "Number of messages sent in each RPC", "1");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/received_messages_per_rpc", "Number of messages received in each RPC", "1");
        io.opencensus.stats.Measure.MeasureDouble.create("grpc.io/server/server_latency", "Time between first byte of request received to last byte of response sent, or terminal error.", "ms");
        io.opencensus.stats.Measure.MeasureLong.create("grpc.io/server/started_rpcs", "Number of started server RPCs.", "1");
    }
}
