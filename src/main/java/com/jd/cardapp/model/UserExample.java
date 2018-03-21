package com.jd.cardapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Integer value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Integer value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Integer value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Integer value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Integer> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Integer> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Integer value1, Integer value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andSelfemailIsNull() {
            addCriterion("selfemail is null");
            return (Criteria) this;
        }

        public Criteria andSelfemailIsNotNull() {
            addCriterion("selfemail is not null");
            return (Criteria) this;
        }

        public Criteria andSelfemailEqualTo(String value) {
            addCriterion("selfemail =", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailNotEqualTo(String value) {
            addCriterion("selfemail <>", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailGreaterThan(String value) {
            addCriterion("selfemail >", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailGreaterThanOrEqualTo(String value) {
            addCriterion("selfemail >=", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailLessThan(String value) {
            addCriterion("selfemail <", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailLessThanOrEqualTo(String value) {
            addCriterion("selfemail <=", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailLike(String value) {
            addCriterion("selfemail like", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailNotLike(String value) {
            addCriterion("selfemail not like", value, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailIn(List<String> values) {
            addCriterion("selfemail in", values, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailNotIn(List<String> values) {
            addCriterion("selfemail not in", values, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailBetween(String value1, String value2) {
            addCriterion("selfemail between", value1, value2, "selfemail");
            return (Criteria) this;
        }

        public Criteria andSelfemailNotBetween(String value1, String value2) {
            addCriterion("selfemail not between", value1, value2, "selfemail");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andCoemailIsNull() {
            addCriterion("coemail is null");
            return (Criteria) this;
        }

        public Criteria andCoemailIsNotNull() {
            addCriterion("coemail is not null");
            return (Criteria) this;
        }

        public Criteria andCoemailEqualTo(String value) {
            addCriterion("coemail =", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailNotEqualTo(String value) {
            addCriterion("coemail <>", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailGreaterThan(String value) {
            addCriterion("coemail >", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailGreaterThanOrEqualTo(String value) {
            addCriterion("coemail >=", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailLessThan(String value) {
            addCriterion("coemail <", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailLessThanOrEqualTo(String value) {
            addCriterion("coemail <=", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailLike(String value) {
            addCriterion("coemail like", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailNotLike(String value) {
            addCriterion("coemail not like", value, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailIn(List<String> values) {
            addCriterion("coemail in", values, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailNotIn(List<String> values) {
            addCriterion("coemail not in", values, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailBetween(String value1, String value2) {
            addCriterion("coemail between", value1, value2, "coemail");
            return (Criteria) this;
        }

        public Criteria andCoemailNotBetween(String value1, String value2) {
            addCriterion("coemail not between", value1, value2, "coemail");
            return (Criteria) this;
        }

        public Criteria andConameIsNull() {
            addCriterion("coname is null");
            return (Criteria) this;
        }

        public Criteria andConameIsNotNull() {
            addCriterion("coname is not null");
            return (Criteria) this;
        }

        public Criteria andConameEqualTo(String value) {
            addCriterion("coname =", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameNotEqualTo(String value) {
            addCriterion("coname <>", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameGreaterThan(String value) {
            addCriterion("coname >", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameGreaterThanOrEqualTo(String value) {
            addCriterion("coname >=", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameLessThan(String value) {
            addCriterion("coname <", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameLessThanOrEqualTo(String value) {
            addCriterion("coname <=", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameLike(String value) {
            addCriterion("coname like", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameNotLike(String value) {
            addCriterion("coname not like", value, "coname");
            return (Criteria) this;
        }

        public Criteria andConameIn(List<String> values) {
            addCriterion("coname in", values, "coname");
            return (Criteria) this;
        }

        public Criteria andConameNotIn(List<String> values) {
            addCriterion("coname not in", values, "coname");
            return (Criteria) this;
        }

        public Criteria andConameBetween(String value1, String value2) {
            addCriterion("coname between", value1, value2, "coname");
            return (Criteria) this;
        }

        public Criteria andConameNotBetween(String value1, String value2) {
            addCriterion("coname not between", value1, value2, "coname");
            return (Criteria) this;
        }

        public Criteria andCoteamIsNull() {
            addCriterion("coteam is null");
            return (Criteria) this;
        }

        public Criteria andCoteamIsNotNull() {
            addCriterion("coteam is not null");
            return (Criteria) this;
        }

        public Criteria andCoteamEqualTo(Integer value) {
            addCriterion("coteam =", value, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamNotEqualTo(Integer value) {
            addCriterion("coteam <>", value, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamGreaterThan(Integer value) {
            addCriterion("coteam >", value, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamGreaterThanOrEqualTo(Integer value) {
            addCriterion("coteam >=", value, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamLessThan(Integer value) {
            addCriterion("coteam <", value, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamLessThanOrEqualTo(Integer value) {
            addCriterion("coteam <=", value, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamIn(List<Integer> values) {
            addCriterion("coteam in", values, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamNotIn(List<Integer> values) {
            addCriterion("coteam not in", values, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamBetween(Integer value1, Integer value2) {
            addCriterion("coteam between", value1, value2, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoteamNotBetween(Integer value1, Integer value2) {
            addCriterion("coteam not between", value1, value2, "coteam");
            return (Criteria) this;
        }

        public Criteria andCoaddressIsNull() {
            addCriterion("coaddress is null");
            return (Criteria) this;
        }

        public Criteria andCoaddressIsNotNull() {
            addCriterion("coaddress is not null");
            return (Criteria) this;
        }

        public Criteria andCoaddressEqualTo(String value) {
            addCriterion("coaddress =", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressNotEqualTo(String value) {
            addCriterion("coaddress <>", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressGreaterThan(String value) {
            addCriterion("coaddress >", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressGreaterThanOrEqualTo(String value) {
            addCriterion("coaddress >=", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressLessThan(String value) {
            addCriterion("coaddress <", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressLessThanOrEqualTo(String value) {
            addCriterion("coaddress <=", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressLike(String value) {
            addCriterion("coaddress like", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressNotLike(String value) {
            addCriterion("coaddress not like", value, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressIn(List<String> values) {
            addCriterion("coaddress in", values, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressNotIn(List<String> values) {
            addCriterion("coaddress not in", values, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressBetween(String value1, String value2) {
            addCriterion("coaddress between", value1, value2, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCoaddressNotBetween(String value1, String value2) {
            addCriterion("coaddress not between", value1, value2, "coaddress");
            return (Criteria) this;
        }

        public Criteria andCowwwIsNull() {
            addCriterion("cowww is null");
            return (Criteria) this;
        }

        public Criteria andCowwwIsNotNull() {
            addCriterion("cowww is not null");
            return (Criteria) this;
        }

        public Criteria andCowwwEqualTo(String value) {
            addCriterion("cowww =", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwNotEqualTo(String value) {
            addCriterion("cowww <>", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwGreaterThan(String value) {
            addCriterion("cowww >", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwGreaterThanOrEqualTo(String value) {
            addCriterion("cowww >=", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwLessThan(String value) {
            addCriterion("cowww <", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwLessThanOrEqualTo(String value) {
            addCriterion("cowww <=", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwLike(String value) {
            addCriterion("cowww like", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwNotLike(String value) {
            addCriterion("cowww not like", value, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwIn(List<String> values) {
            addCriterion("cowww in", values, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwNotIn(List<String> values) {
            addCriterion("cowww not in", values, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwBetween(String value1, String value2) {
            addCriterion("cowww between", value1, value2, "cowww");
            return (Criteria) this;
        }

        public Criteria andCowwwNotBetween(String value1, String value2) {
            addCriterion("cowww not between", value1, value2, "cowww");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Double value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Double value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Double value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Double value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Double value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Double> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Double> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Double value1, Double value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Double value1, Double value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andUlevelIsNull() {
            addCriterion("ulevel is null");
            return (Criteria) this;
        }

        public Criteria andUlevelIsNotNull() {
            addCriterion("ulevel is not null");
            return (Criteria) this;
        }

        public Criteria andUlevelEqualTo(Integer value) {
            addCriterion("ulevel =", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelNotEqualTo(Integer value) {
            addCriterion("ulevel <>", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelGreaterThan(Integer value) {
            addCriterion("ulevel >", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("ulevel >=", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelLessThan(Integer value) {
            addCriterion("ulevel <", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelLessThanOrEqualTo(Integer value) {
            addCriterion("ulevel <=", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelIn(List<Integer> values) {
            addCriterion("ulevel in", values, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelNotIn(List<Integer> values) {
            addCriterion("ulevel not in", values, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelBetween(Integer value1, Integer value2) {
            addCriterion("ulevel between", value1, value2, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("ulevel not between", value1, value2, "ulevel");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
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