package com.justanalytics.query.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

public class ComparisonFilter extends SimpleFilter {
    private ComparisonOperator condition;
    private Object value;

    public ComparisonOperator getCondition() {
        return condition;
    }

    public void setCondition(ComparisonOperator condition) {
        this.condition = condition;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    @JsonIgnore
    public List<Object> getValues() {
        return Collections.singletonList(value);
    }

}
