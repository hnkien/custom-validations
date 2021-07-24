package com.customvalidation.sample.dto;

public enum LocationCategory {

    RETAIL {
        public String toString() {
            return "retail";
        }
    },
    ECOMMERCE {
        public String toString() {
            return "ecommerce";
        }
    },
    CALLCENTER{
        public String toString() {
            return "callcenter";
        }
    }
}
