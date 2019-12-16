package com.demobank.oms.model;

public enum OrderStatus {
    pendingNew(false), newAccepted(false),
    newRejected(true), modifyAccepted(false),
    modifyRejected(true), cancelled(true);
    final boolean completed;

    OrderStatus(boolean isCompleted) {
        this.completed = isCompleted;
    }
}
