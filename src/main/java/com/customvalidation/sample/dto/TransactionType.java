package com.customvalidation.sample.dto;

public enum TransactionType {
    SALE {
        public String toString() {
            return "Sale";
        }
    },
    VOIDSALE {
        public String toString() {
            return "VoidSale";
        }
    },
}
