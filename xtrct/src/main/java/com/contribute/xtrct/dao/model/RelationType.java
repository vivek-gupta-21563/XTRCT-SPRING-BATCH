package com.contribute.xtrct.dao.model;

public enum RelationType {
    S("S"),
    V("V"),
    N("N");

    private String relationTypeCode;

    RelationType(String relationTypeCode) {
        this.relationTypeCode = relationTypeCode;
    }

    public String getRelationTypeCode() {
        return relationTypeCode;
    }
}
