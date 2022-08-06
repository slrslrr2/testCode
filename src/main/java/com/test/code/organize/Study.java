package com.test.code.organize;

public class Study {
    private StudyStatus status; //= StudyStatus.DRAFT;
    private int limit;

    public Study(int limit) {
        this.limit = limit;
        if(limit < 0) throw new IllegalArgumentException("limit는 0보다 커야한다.");
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }
}
