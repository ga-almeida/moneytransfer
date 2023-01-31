package br.com.caseitau.moneytransfer.client.domain.model;

public enum StatusEnum {
    SUCCESS,
    REJECTED_ORIGIN_NOT_EXISTS,
    REJECTED_FROM_NOT_EXISTS,
    REJECTED_ORIGIN_VALUE_HIGHER;
}
