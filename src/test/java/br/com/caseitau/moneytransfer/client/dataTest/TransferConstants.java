package br.com.caseitau.moneytransfer.client.dataTest;

import java.math.BigDecimal;
import java.util.UUID;

public final class TransferConstants {
    public static final String FROM_CLIENT_ACCOUNT_NUMBER = "123456";
    public static final UUID FROM_CLIENT_ID = UUID.fromString("29c04915-f582-4a9e-8def-4836c9abb862");
    public static final String ORIGIN_CLIENT_ACCOUNT_NUMBER = "654321";
    public static final String ORIGIN_CLIENT_NAME = "JANE DOE";
    public static final UUID ORIGIN_CLIENT_ID = UUID.fromString("041dc2f1-f13a-4e0d-8d70-93897cfb2c24");
    public static final BigDecimal BALANCE = BigDecimal.valueOf(50);
}
