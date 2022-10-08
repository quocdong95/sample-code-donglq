package com.example.model;

import lombok.Getter;

/**
 * Define Order processing status
 */
@Getter
public enum ProcessingStatus {
    /**
     * Order status is new
     */
    NEW("NEW", "label.status.new"),
    /**
     * Order status is processing
     */
    PROCESSING("PROCESSING", "label.status.processing"),
    /**
     * Order is incomplete
     */
    INCOMPLETE("INCOMPLETE", "label.status.incomplete"),
    /**
     * Order status is completed
     */
    COMPLETED("COMPLETED", "label.status.completed"),

    /**
     * List of statuses for Picking Order
     */
    public static final ProcessingStatus[] FOR_ORDER = new ProcessingStatus[] {
        NEW,
        PROCESSING,
        INCOMPLETE,
        COMPLETED,
    };

    /**
     * unique key
     */
    private String key;

    /**
     * label for displaying in GUI
     */
    private String label;

    /**
     * Constructor
     *
     * @param key   unique key
     * @param label label
     */
    ProcessingStatus(String key, String label) {
        this.key = key;
        this.label = label;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * get status type by key
     *
     * @param key key
     * @return status type
     */
    public static ProcessingStatus typeOf(String key) {
        for (ProcessingStatus status : ProcessingStatus.values()) {
            if (status.getKey().equals(key)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }
}
