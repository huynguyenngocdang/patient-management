package com.huynguyenngocdang.common;

import org.slf4j.MDC;

public record ResponseMetadata(String requestId, String requestDateTime) {

    public static ResponseMetadata getCurrentMetadata() {
        return new ResponseMetadata(MDC.get("requestId"), MDC.get("requestDateTime"));
    }
}
