package com.websocket.wstutorial.messaging.common;

public final class QueueNames {

    private QueueNames() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BETSLIPS_PROCESSED = "betslips_processed";
    public static final String BET_PROCESS = "bet_process";
    public static final String BET_CREATE = "bet_create";
    public static final String BOF_INFO = "bof_info";
    public static final String BET_LIST = "bet_list";
    public static final String BET_SETTLEMENT_STATISTIC = "bet_settlement_statistic";
    public static final String BET_SETTLEMENT_FOR_EVENTS = "bet_settlement_for_events";
}