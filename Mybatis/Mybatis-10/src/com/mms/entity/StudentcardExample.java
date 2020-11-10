package com.mms.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentcardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentcardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCardnoIsNull() {
            addCriterion("cardno is null");
            return (Criteria) this;
        }

        public Criteria andCardnoIsNotNull() {
            addCriterion("cardno is not null");
            return (Criteria) this;
        }

        public Criteria andCardnoEqualTo(Integer value) {
            addCriterion("cardno =", value, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoNotEqualTo(Integer value) {
            addCriterion("cardno <>", value, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoGreaterThan(Integer value) {
            addCriterion("cardno >", value, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardno >=", value, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoLessThan(Integer value) {
            addCriterion("cardno <", value, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoLessThanOrEqualTo(Integer value) {
            addCriterion("cardno <=", value, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoIn(List<Integer> values) {
            addCriterion("cardno in", values, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoNotIn(List<Integer> values) {
            addCriterion("cardno not in", values, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoBetween(Integer value1, Integer value2) {
            addCriterion("cardno between", value1, value2, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardnoNotBetween(Integer value1, Integer value2) {
            addCriterion("cardno not between", value1, value2, "cardno");
            return (Criteria) this;
        }

        public Criteria andCardinfoIsNull() {
            addCriterion("cardinfo is null");
            return (Criteria) this;
        }

        public Criteria andCardinfoIsNotNull() {
            addCriterion("cardinfo is not null");
            return (Criteria) this;
        }

        public Criteria andCardinfoEqualTo(String value) {
            addCriterion("cardinfo =", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotEqualTo(String value) {
            addCriterion("cardinfo <>", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoGreaterThan(String value) {
            addCriterion("cardinfo >", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoGreaterThanOrEqualTo(String value) {
            addCriterion("cardinfo >=", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLessThan(String value) {
            addCriterion("cardinfo <", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLessThanOrEqualTo(String value) {
            addCriterion("cardinfo <=", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoLike(String value) {
            addCriterion("cardinfo like", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotLike(String value) {
            addCriterion("cardinfo not like", value, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoIn(List<String> values) {
            addCriterion("cardinfo in", values, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotIn(List<String> values) {
            addCriterion("cardinfo not in", values, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoBetween(String value1, String value2) {
            addCriterion("cardinfo between", value1, value2, "cardinfo");
            return (Criteria) this;
        }

        public Criteria andCardinfoNotBetween(String value1, String value2) {
            addCriterion("cardinfo not between", value1, value2, "cardinfo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}