package com.dotcms.analytics.track.collectors;

/**
 * Represents the dotCMS event types for analytics
 * @author jsanca
 */
public enum EventType {
    VANITY_REQUEST("VANITY_REQUEST"),
    FILE_REQUEST("FILE_REQUEST"),
    PAGE_REQUEST("PAGE_REQUEST"),
    CUSTOM_USER_EVENT("CUSTOM_USER_EVENT"),
    URL_MAP("URL_MAP"),
    HTTP_RESPONSE("HTTP_RESPONSE");

    private final String type;
    private EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
