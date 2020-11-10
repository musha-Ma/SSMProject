package com.mms.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentclassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentclassExample() {
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

        public Criteria andClassnoIsNull() {
            addCriterion("classno is null");
            return (Criteria) this;
        }

        public Criteria andClassnoIsNotNull() {
            addCriterion("classno is not null");
            return (Criteria) this;
        }

        public Criteria andClassnoEqualTo(Integer value) {
            addCriterion("classno =", value, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoNotEqualTo(Integer value) {
            addCriterion("classno <>", value, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoGreaterThan(Integer value) {
            addCriterion("classno >", value, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("classno >=", value, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoLessThan(Integer value) {
            addCriterion("classno <", value, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoLessThanOrEqualTo(Integer value) {
            addCriterion("classno <=", value, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoIn(List<Integer> values) {
            addCriterion("classno in", values, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoNotIn(List<Integer> values) {
            addCriterion("classno not in", values, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoBetween(Integer value1, Integer value2) {
            addCriterion("classno between", value1, value2, "classno");
            return (Criteria) this;
        }

        public Criteria andClassnoNotBetween(Integer value1, Integer value2) {
            addCriterion("classno not between", value1, value2, "classno");
            return (Criteria) this;
        }

        public Criteria andClassinfoIsNull() {
            addCriterion("classinfo is null");
            return (Criteria) this;
        }

        public Criteria andClassinfoIsNotNull() {
            addCriterion("classinfo is not null");
            return (Criteria) this;
        }

        public Criteria andClassinfoEqualTo(String value) {
            addCriterion("classinfo =", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoNotEqualTo(String value) {
            addCriterion("classinfo <>", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoGreaterThan(String value) {
            addCriterion("classinfo >", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoGreaterThanOrEqualTo(String value) {
            addCriterion("classinfo >=", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoLessThan(String value) {
            addCriterion("classinfo <", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoLessThanOrEqualTo(String value) {
            addCriterion("classinfo <=", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoLike(String value) {
            addCriterion("classinfo like", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoNotLike(String value) {
            addCriterion("classinfo not like", value, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoIn(List<String> values) {
            addCriterion("classinfo in", values, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoNotIn(List<String> values) {
            addCriterion("classinfo not in", values, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoBetween(String value1, String value2) {
            addCriterion("classinfo between", value1, value2, "classinfo");
            return (Criteria) this;
        }

        public Criteria andClassinfoNotBetween(String value1, String value2) {
            addCriterion("classinfo not between", value1, value2, "classinfo");
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