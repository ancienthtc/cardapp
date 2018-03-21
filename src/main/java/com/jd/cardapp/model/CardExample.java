package com.jd.cardapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCardmailIsNull() {
            addCriterion("cardmail is null");
            return (Criteria) this;
        }

        public Criteria andCardmailIsNotNull() {
            addCriterion("cardmail is not null");
            return (Criteria) this;
        }

        public Criteria andCardmailEqualTo(String value) {
            addCriterion("cardmail =", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailNotEqualTo(String value) {
            addCriterion("cardmail <>", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailGreaterThan(String value) {
            addCriterion("cardmail >", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailGreaterThanOrEqualTo(String value) {
            addCriterion("cardmail >=", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailLessThan(String value) {
            addCriterion("cardmail <", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailLessThanOrEqualTo(String value) {
            addCriterion("cardmail <=", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailLike(String value) {
            addCriterion("cardmail like", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailNotLike(String value) {
            addCriterion("cardmail not like", value, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailIn(List<String> values) {
            addCriterion("cardmail in", values, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailNotIn(List<String> values) {
            addCriterion("cardmail not in", values, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailBetween(String value1, String value2) {
            addCriterion("cardmail between", value1, value2, "cardmail");
            return (Criteria) this;
        }

        public Criteria andCardmailNotBetween(String value1, String value2) {
            addCriterion("cardmail not between", value1, value2, "cardmail");
            return (Criteria) this;
        }

        public Criteria andWeburlIsNull() {
            addCriterion("weburl is null");
            return (Criteria) this;
        }

        public Criteria andWeburlIsNotNull() {
            addCriterion("weburl is not null");
            return (Criteria) this;
        }

        public Criteria andWeburlEqualTo(String value) {
            addCriterion("weburl =", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotEqualTo(String value) {
            addCriterion("weburl <>", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlGreaterThan(String value) {
            addCriterion("weburl >", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlGreaterThanOrEqualTo(String value) {
            addCriterion("weburl >=", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlLessThan(String value) {
            addCriterion("weburl <", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlLessThanOrEqualTo(String value) {
            addCriterion("weburl <=", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlLike(String value) {
            addCriterion("weburl like", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotLike(String value) {
            addCriterion("weburl not like", value, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlIn(List<String> values) {
            addCriterion("weburl in", values, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotIn(List<String> values) {
            addCriterion("weburl not in", values, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlBetween(String value1, String value2) {
            addCriterion("weburl between", value1, value2, "weburl");
            return (Criteria) this;
        }

        public Criteria andWeburlNotBetween(String value1, String value2) {
            addCriterion("weburl not between", value1, value2, "weburl");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andBelongIsNull() {
            addCriterion("belong is null");
            return (Criteria) this;
        }

        public Criteria andBelongIsNotNull() {
            addCriterion("belong is not null");
            return (Criteria) this;
        }

        public Criteria andBelongEqualTo(String value) {
            addCriterion("belong =", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotEqualTo(String value) {
            addCriterion("belong <>", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThan(String value) {
            addCriterion("belong >", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongGreaterThanOrEqualTo(String value) {
            addCriterion("belong >=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThan(String value) {
            addCriterion("belong <", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLessThanOrEqualTo(String value) {
            addCriterion("belong <=", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongLike(String value) {
            addCriterion("belong like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotLike(String value) {
            addCriterion("belong not like", value, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongIn(List<String> values) {
            addCriterion("belong in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotIn(List<String> values) {
            addCriterion("belong not in", values, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongBetween(String value1, String value2) {
            addCriterion("belong between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andBelongNotBetween(String value1, String value2) {
            addCriterion("belong not between", value1, value2, "belong");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNull() {
            addCriterion("goods is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNotNull() {
            addCriterion("goods is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEqualTo(String value) {
            addCriterion("goods =", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotEqualTo(String value) {
            addCriterion("goods <>", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThan(String value) {
            addCriterion("goods >", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("goods >=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThan(String value) {
            addCriterion("goods <", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThanOrEqualTo(String value) {
            addCriterion("goods <=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLike(String value) {
            addCriterion("goods like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotLike(String value) {
            addCriterion("goods not like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsIn(List<String> values) {
            addCriterion("goods in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotIn(List<String> values) {
            addCriterion("goods not in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsBetween(String value1, String value2) {
            addCriterion("goods between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotBetween(String value1, String value2) {
            addCriterion("goods not between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andContact1IsNull() {
            addCriterion("contact1 is null");
            return (Criteria) this;
        }

        public Criteria andContact1IsNotNull() {
            addCriterion("contact1 is not null");
            return (Criteria) this;
        }

        public Criteria andContact1EqualTo(String value) {
            addCriterion("contact1 =", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotEqualTo(String value) {
            addCriterion("contact1 <>", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1GreaterThan(String value) {
            addCriterion("contact1 >", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1GreaterThanOrEqualTo(String value) {
            addCriterion("contact1 >=", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1LessThan(String value) {
            addCriterion("contact1 <", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1LessThanOrEqualTo(String value) {
            addCriterion("contact1 <=", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1Like(String value) {
            addCriterion("contact1 like", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotLike(String value) {
            addCriterion("contact1 not like", value, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1In(List<String> values) {
            addCriterion("contact1 in", values, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotIn(List<String> values) {
            addCriterion("contact1 not in", values, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1Between(String value1, String value2) {
            addCriterion("contact1 between", value1, value2, "contact1");
            return (Criteria) this;
        }

        public Criteria andContact1NotBetween(String value1, String value2) {
            addCriterion("contact1 not between", value1, value2, "contact1");
            return (Criteria) this;
        }

        public Criteria andPosition1IsNull() {
            addCriterion("position1 is null");
            return (Criteria) this;
        }

        public Criteria andPosition1IsNotNull() {
            addCriterion("position1 is not null");
            return (Criteria) this;
        }

        public Criteria andPosition1EqualTo(String value) {
            addCriterion("position1 =", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1NotEqualTo(String value) {
            addCriterion("position1 <>", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1GreaterThan(String value) {
            addCriterion("position1 >", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1GreaterThanOrEqualTo(String value) {
            addCriterion("position1 >=", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1LessThan(String value) {
            addCriterion("position1 <", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1LessThanOrEqualTo(String value) {
            addCriterion("position1 <=", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1Like(String value) {
            addCriterion("position1 like", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1NotLike(String value) {
            addCriterion("position1 not like", value, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1In(List<String> values) {
            addCriterion("position1 in", values, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1NotIn(List<String> values) {
            addCriterion("position1 not in", values, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1Between(String value1, String value2) {
            addCriterion("position1 between", value1, value2, "position1");
            return (Criteria) this;
        }

        public Criteria andPosition1NotBetween(String value1, String value2) {
            addCriterion("position1 not between", value1, value2, "position1");
            return (Criteria) this;
        }

        public Criteria andTel1IsNull() {
            addCriterion("tel1 is null");
            return (Criteria) this;
        }

        public Criteria andTel1IsNotNull() {
            addCriterion("tel1 is not null");
            return (Criteria) this;
        }

        public Criteria andTel1EqualTo(String value) {
            addCriterion("tel1 =", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1NotEqualTo(String value) {
            addCriterion("tel1 <>", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1GreaterThan(String value) {
            addCriterion("tel1 >", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1GreaterThanOrEqualTo(String value) {
            addCriterion("tel1 >=", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1LessThan(String value) {
            addCriterion("tel1 <", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1LessThanOrEqualTo(String value) {
            addCriterion("tel1 <=", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1Like(String value) {
            addCriterion("tel1 like", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1NotLike(String value) {
            addCriterion("tel1 not like", value, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1In(List<String> values) {
            addCriterion("tel1 in", values, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1NotIn(List<String> values) {
            addCriterion("tel1 not in", values, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1Between(String value1, String value2) {
            addCriterion("tel1 between", value1, value2, "tel1");
            return (Criteria) this;
        }

        public Criteria andTel1NotBetween(String value1, String value2) {
            addCriterion("tel1 not between", value1, value2, "tel1");
            return (Criteria) this;
        }

        public Criteria andContact2IsNull() {
            addCriterion("contact2 is null");
            return (Criteria) this;
        }

        public Criteria andContact2IsNotNull() {
            addCriterion("contact2 is not null");
            return (Criteria) this;
        }

        public Criteria andContact2EqualTo(String value) {
            addCriterion("contact2 =", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotEqualTo(String value) {
            addCriterion("contact2 <>", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2GreaterThan(String value) {
            addCriterion("contact2 >", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2GreaterThanOrEqualTo(String value) {
            addCriterion("contact2 >=", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2LessThan(String value) {
            addCriterion("contact2 <", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2LessThanOrEqualTo(String value) {
            addCriterion("contact2 <=", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2Like(String value) {
            addCriterion("contact2 like", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotLike(String value) {
            addCriterion("contact2 not like", value, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2In(List<String> values) {
            addCriterion("contact2 in", values, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotIn(List<String> values) {
            addCriterion("contact2 not in", values, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2Between(String value1, String value2) {
            addCriterion("contact2 between", value1, value2, "contact2");
            return (Criteria) this;
        }

        public Criteria andContact2NotBetween(String value1, String value2) {
            addCriterion("contact2 not between", value1, value2, "contact2");
            return (Criteria) this;
        }

        public Criteria andPosition2IsNull() {
            addCriterion("position2 is null");
            return (Criteria) this;
        }

        public Criteria andPosition2IsNotNull() {
            addCriterion("position2 is not null");
            return (Criteria) this;
        }

        public Criteria andPosition2EqualTo(String value) {
            addCriterion("position2 =", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2NotEqualTo(String value) {
            addCriterion("position2 <>", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2GreaterThan(String value) {
            addCriterion("position2 >", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2GreaterThanOrEqualTo(String value) {
            addCriterion("position2 >=", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2LessThan(String value) {
            addCriterion("position2 <", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2LessThanOrEqualTo(String value) {
            addCriterion("position2 <=", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2Like(String value) {
            addCriterion("position2 like", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2NotLike(String value) {
            addCriterion("position2 not like", value, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2In(List<String> values) {
            addCriterion("position2 in", values, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2NotIn(List<String> values) {
            addCriterion("position2 not in", values, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2Between(String value1, String value2) {
            addCriterion("position2 between", value1, value2, "position2");
            return (Criteria) this;
        }

        public Criteria andPosition2NotBetween(String value1, String value2) {
            addCriterion("position2 not between", value1, value2, "position2");
            return (Criteria) this;
        }

        public Criteria andTel2IsNull() {
            addCriterion("tel2 is null");
            return (Criteria) this;
        }

        public Criteria andTel2IsNotNull() {
            addCriterion("tel2 is not null");
            return (Criteria) this;
        }

        public Criteria andTel2EqualTo(String value) {
            addCriterion("tel2 =", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2NotEqualTo(String value) {
            addCriterion("tel2 <>", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2GreaterThan(String value) {
            addCriterion("tel2 >", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2GreaterThanOrEqualTo(String value) {
            addCriterion("tel2 >=", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2LessThan(String value) {
            addCriterion("tel2 <", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2LessThanOrEqualTo(String value) {
            addCriterion("tel2 <=", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2Like(String value) {
            addCriterion("tel2 like", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2NotLike(String value) {
            addCriterion("tel2 not like", value, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2In(List<String> values) {
            addCriterion("tel2 in", values, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2NotIn(List<String> values) {
            addCriterion("tel2 not in", values, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2Between(String value1, String value2) {
            addCriterion("tel2 between", value1, value2, "tel2");
            return (Criteria) this;
        }

        public Criteria andTel2NotBetween(String value1, String value2) {
            addCriterion("tel2 not between", value1, value2, "tel2");
            return (Criteria) this;
        }

        public Criteria andContact3IsNull() {
            addCriterion("contact3 is null");
            return (Criteria) this;
        }

        public Criteria andContact3IsNotNull() {
            addCriterion("contact3 is not null");
            return (Criteria) this;
        }

        public Criteria andContact3EqualTo(String value) {
            addCriterion("contact3 =", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotEqualTo(String value) {
            addCriterion("contact3 <>", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3GreaterThan(String value) {
            addCriterion("contact3 >", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3GreaterThanOrEqualTo(String value) {
            addCriterion("contact3 >=", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3LessThan(String value) {
            addCriterion("contact3 <", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3LessThanOrEqualTo(String value) {
            addCriterion("contact3 <=", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3Like(String value) {
            addCriterion("contact3 like", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotLike(String value) {
            addCriterion("contact3 not like", value, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3In(List<String> values) {
            addCriterion("contact3 in", values, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotIn(List<String> values) {
            addCriterion("contact3 not in", values, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3Between(String value1, String value2) {
            addCriterion("contact3 between", value1, value2, "contact3");
            return (Criteria) this;
        }

        public Criteria andContact3NotBetween(String value1, String value2) {
            addCriterion("contact3 not between", value1, value2, "contact3");
            return (Criteria) this;
        }

        public Criteria andPosition3IsNull() {
            addCriterion("position3 is null");
            return (Criteria) this;
        }

        public Criteria andPosition3IsNotNull() {
            addCriterion("position3 is not null");
            return (Criteria) this;
        }

        public Criteria andPosition3EqualTo(String value) {
            addCriterion("position3 =", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3NotEqualTo(String value) {
            addCriterion("position3 <>", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3GreaterThan(String value) {
            addCriterion("position3 >", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3GreaterThanOrEqualTo(String value) {
            addCriterion("position3 >=", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3LessThan(String value) {
            addCriterion("position3 <", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3LessThanOrEqualTo(String value) {
            addCriterion("position3 <=", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3Like(String value) {
            addCriterion("position3 like", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3NotLike(String value) {
            addCriterion("position3 not like", value, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3In(List<String> values) {
            addCriterion("position3 in", values, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3NotIn(List<String> values) {
            addCriterion("position3 not in", values, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3Between(String value1, String value2) {
            addCriterion("position3 between", value1, value2, "position3");
            return (Criteria) this;
        }

        public Criteria andPosition3NotBetween(String value1, String value2) {
            addCriterion("position3 not between", value1, value2, "position3");
            return (Criteria) this;
        }

        public Criteria andTel3IsNull() {
            addCriterion("tel3 is null");
            return (Criteria) this;
        }

        public Criteria andTel3IsNotNull() {
            addCriterion("tel3 is not null");
            return (Criteria) this;
        }

        public Criteria andTel3EqualTo(String value) {
            addCriterion("tel3 =", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3NotEqualTo(String value) {
            addCriterion("tel3 <>", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3GreaterThan(String value) {
            addCriterion("tel3 >", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3GreaterThanOrEqualTo(String value) {
            addCriterion("tel3 >=", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3LessThan(String value) {
            addCriterion("tel3 <", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3LessThanOrEqualTo(String value) {
            addCriterion("tel3 <=", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3Like(String value) {
            addCriterion("tel3 like", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3NotLike(String value) {
            addCriterion("tel3 not like", value, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3In(List<String> values) {
            addCriterion("tel3 in", values, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3NotIn(List<String> values) {
            addCriterion("tel3 not in", values, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3Between(String value1, String value2) {
            addCriterion("tel3 between", value1, value2, "tel3");
            return (Criteria) this;
        }

        public Criteria andTel3NotBetween(String value1, String value2) {
            addCriterion("tel3 not between", value1, value2, "tel3");
            return (Criteria) this;
        }

        public Criteria andPic1IsNull() {
            addCriterion("pic1 is null");
            return (Criteria) this;
        }

        public Criteria andPic1IsNotNull() {
            addCriterion("pic1 is not null");
            return (Criteria) this;
        }

        public Criteria andPic1EqualTo(String value) {
            addCriterion("pic1 =", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1NotEqualTo(String value) {
            addCriterion("pic1 <>", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1GreaterThan(String value) {
            addCriterion("pic1 >", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1GreaterThanOrEqualTo(String value) {
            addCriterion("pic1 >=", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1LessThan(String value) {
            addCriterion("pic1 <", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1LessThanOrEqualTo(String value) {
            addCriterion("pic1 <=", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1Like(String value) {
            addCriterion("pic1 like", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1NotLike(String value) {
            addCriterion("pic1 not like", value, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1In(List<String> values) {
            addCriterion("pic1 in", values, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1NotIn(List<String> values) {
            addCriterion("pic1 not in", values, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1Between(String value1, String value2) {
            addCriterion("pic1 between", value1, value2, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic1NotBetween(String value1, String value2) {
            addCriterion("pic1 not between", value1, value2, "pic1");
            return (Criteria) this;
        }

        public Criteria andPic2IsNull() {
            addCriterion("pic2 is null");
            return (Criteria) this;
        }

        public Criteria andPic2IsNotNull() {
            addCriterion("pic2 is not null");
            return (Criteria) this;
        }

        public Criteria andPic2EqualTo(String value) {
            addCriterion("pic2 =", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotEqualTo(String value) {
            addCriterion("pic2 <>", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2GreaterThan(String value) {
            addCriterion("pic2 >", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2GreaterThanOrEqualTo(String value) {
            addCriterion("pic2 >=", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2LessThan(String value) {
            addCriterion("pic2 <", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2LessThanOrEqualTo(String value) {
            addCriterion("pic2 <=", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2Like(String value) {
            addCriterion("pic2 like", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotLike(String value) {
            addCriterion("pic2 not like", value, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2In(List<String> values) {
            addCriterion("pic2 in", values, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotIn(List<String> values) {
            addCriterion("pic2 not in", values, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2Between(String value1, String value2) {
            addCriterion("pic2 between", value1, value2, "pic2");
            return (Criteria) this;
        }

        public Criteria andPic2NotBetween(String value1, String value2) {
            addCriterion("pic2 not between", value1, value2, "pic2");
            return (Criteria) this;
        }

        public Criteria andFolder1IsNull() {
            addCriterion("folder1 is null");
            return (Criteria) this;
        }

        public Criteria andFolder1IsNotNull() {
            addCriterion("folder1 is not null");
            return (Criteria) this;
        }

        public Criteria andFolder1EqualTo(String value) {
            addCriterion("folder1 =", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1NotEqualTo(String value) {
            addCriterion("folder1 <>", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1GreaterThan(String value) {
            addCriterion("folder1 >", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1GreaterThanOrEqualTo(String value) {
            addCriterion("folder1 >=", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1LessThan(String value) {
            addCriterion("folder1 <", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1LessThanOrEqualTo(String value) {
            addCriterion("folder1 <=", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1Like(String value) {
            addCriterion("folder1 like", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1NotLike(String value) {
            addCriterion("folder1 not like", value, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1In(List<String> values) {
            addCriterion("folder1 in", values, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1NotIn(List<String> values) {
            addCriterion("folder1 not in", values, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1Between(String value1, String value2) {
            addCriterion("folder1 between", value1, value2, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder1NotBetween(String value1, String value2) {
            addCriterion("folder1 not between", value1, value2, "folder1");
            return (Criteria) this;
        }

        public Criteria andFolder2IsNull() {
            addCriterion("folder2 is null");
            return (Criteria) this;
        }

        public Criteria andFolder2IsNotNull() {
            addCriterion("folder2 is not null");
            return (Criteria) this;
        }

        public Criteria andFolder2EqualTo(String value) {
            addCriterion("folder2 =", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2NotEqualTo(String value) {
            addCriterion("folder2 <>", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2GreaterThan(String value) {
            addCriterion("folder2 >", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2GreaterThanOrEqualTo(String value) {
            addCriterion("folder2 >=", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2LessThan(String value) {
            addCriterion("folder2 <", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2LessThanOrEqualTo(String value) {
            addCriterion("folder2 <=", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2Like(String value) {
            addCriterion("folder2 like", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2NotLike(String value) {
            addCriterion("folder2 not like", value, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2In(List<String> values) {
            addCriterion("folder2 in", values, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2NotIn(List<String> values) {
            addCriterion("folder2 not in", values, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2Between(String value1, String value2) {
            addCriterion("folder2 between", value1, value2, "folder2");
            return (Criteria) this;
        }

        public Criteria andFolder2NotBetween(String value1, String value2) {
            addCriterion("folder2 not between", value1, value2, "folder2");
            return (Criteria) this;
        }

        public Criteria andBuyIsNull() {
            addCriterion("buy is null");
            return (Criteria) this;
        }

        public Criteria andBuyIsNotNull() {
            addCriterion("buy is not null");
            return (Criteria) this;
        }

        public Criteria andBuyEqualTo(Double value) {
            addCriterion("buy =", value, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyNotEqualTo(Double value) {
            addCriterion("buy <>", value, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyGreaterThan(Double value) {
            addCriterion("buy >", value, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyGreaterThanOrEqualTo(Double value) {
            addCriterion("buy >=", value, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyLessThan(Double value) {
            addCriterion("buy <", value, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyLessThanOrEqualTo(Double value) {
            addCriterion("buy <=", value, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyIn(List<Double> values) {
            addCriterion("buy in", values, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyNotIn(List<Double> values) {
            addCriterion("buy not in", values, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyBetween(Double value1, Double value2) {
            addCriterion("buy between", value1, value2, "buy");
            return (Criteria) this;
        }

        public Criteria andBuyNotBetween(Double value1, Double value2) {
            addCriterion("buy not between", value1, value2, "buy");
            return (Criteria) this;
        }

        public Criteria andReviewIsNull() {
            addCriterion("review is null");
            return (Criteria) this;
        }

        public Criteria andReviewIsNotNull() {
            addCriterion("review is not null");
            return (Criteria) this;
        }

        public Criteria andReviewEqualTo(Integer value) {
            addCriterion("review =", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotEqualTo(Integer value) {
            addCriterion("review <>", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewGreaterThan(Integer value) {
            addCriterion("review >", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewGreaterThanOrEqualTo(Integer value) {
            addCriterion("review >=", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewLessThan(Integer value) {
            addCriterion("review <", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewLessThanOrEqualTo(Integer value) {
            addCriterion("review <=", value, "review");
            return (Criteria) this;
        }

        public Criteria andReviewIn(List<Integer> values) {
            addCriterion("review in", values, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotIn(List<Integer> values) {
            addCriterion("review not in", values, "review");
            return (Criteria) this;
        }

        public Criteria andReviewBetween(Integer value1, Integer value2) {
            addCriterion("review between", value1, value2, "review");
            return (Criteria) this;
        }

        public Criteria andReviewNotBetween(Integer value1, Integer value2) {
            addCriterion("review not between", value1, value2, "review");
            return (Criteria) this;
        }

        public Criteria andViewtimesIsNull() {
            addCriterion("viewtimes is null");
            return (Criteria) this;
        }

        public Criteria andViewtimesIsNotNull() {
            addCriterion("viewtimes is not null");
            return (Criteria) this;
        }

        public Criteria andViewtimesEqualTo(Integer value) {
            addCriterion("viewtimes =", value, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesNotEqualTo(Integer value) {
            addCriterion("viewtimes <>", value, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesGreaterThan(Integer value) {
            addCriterion("viewtimes >", value, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewtimes >=", value, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesLessThan(Integer value) {
            addCriterion("viewtimes <", value, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesLessThanOrEqualTo(Integer value) {
            addCriterion("viewtimes <=", value, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesIn(List<Integer> values) {
            addCriterion("viewtimes in", values, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesNotIn(List<Integer> values) {
            addCriterion("viewtimes not in", values, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesBetween(Integer value1, Integer value2) {
            addCriterion("viewtimes between", value1, value2, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andViewtimesNotBetween(Integer value1, Integer value2) {
            addCriterion("viewtimes not between", value1, value2, "viewtimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesIsNull() {
            addCriterion("buytimes is null");
            return (Criteria) this;
        }

        public Criteria andBuytimesIsNotNull() {
            addCriterion("buytimes is not null");
            return (Criteria) this;
        }

        public Criteria andBuytimesEqualTo(Integer value) {
            addCriterion("buytimes =", value, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesNotEqualTo(Integer value) {
            addCriterion("buytimes <>", value, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesGreaterThan(Integer value) {
            addCriterion("buytimes >", value, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("buytimes >=", value, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesLessThan(Integer value) {
            addCriterion("buytimes <", value, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesLessThanOrEqualTo(Integer value) {
            addCriterion("buytimes <=", value, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesIn(List<Integer> values) {
            addCriterion("buytimes in", values, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesNotIn(List<Integer> values) {
            addCriterion("buytimes not in", values, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesBetween(Integer value1, Integer value2) {
            addCriterion("buytimes between", value1, value2, "buytimes");
            return (Criteria) this;
        }

        public Criteria andBuytimesNotBetween(Integer value1, Integer value2) {
            addCriterion("buytimes not between", value1, value2, "buytimes");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(Integer value) {
            addCriterion("user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(Integer value) {
            addCriterion("user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(Integer value) {
            addCriterion("user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(Integer value) {
            addCriterion("user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(Integer value) {
            addCriterion("user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<Integer> values) {
            addCriterion("user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<Integer> values) {
            addCriterion("user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(Integer value1, Integer value2) {
            addCriterion("user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(Integer value1, Integer value2) {
            addCriterion("user not between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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