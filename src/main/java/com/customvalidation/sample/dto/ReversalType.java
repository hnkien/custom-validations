package com.customvalidation.sample.dto;

public enum ReversalType {
    TIMEDOUT {
        public final String toString() {
            return "Timedout";
        }
    },
    CANCELLED {
        public final String toString() {
            return "Cancelled";
        }
    }
}