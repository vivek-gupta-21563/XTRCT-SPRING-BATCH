package com.contribute.xtrct;import com.contribute.xtrct.business.model.DeliveryType;import com.contribute.xtrct.business.model.FinancialInstitution;import com.contribute.xtrct.business.model.Incentive;import com.contribute.xtrct.business.model.Variation;import com.contribute.xtrct.business.model.VehicleStatus;import java.util.Date;import java.util.List;public class MutableTestIncentive implements Incentive {    private String id;    private String type;    private String categoryId;    private String categoryGroup;    private String categoryDescription;    private String name;    private String code;    private String market;    private String targeting;    private String groupAffiliation;    private Date effectiveDate;    private Date expiryDate;    private String previousOwnership;    private String offerType;    private List<VehicleStatus> vehicleStatuses;    private List<FinancialInstitution> financialInstitutionList;    private List<DeliveryType> deliveryTypes;    private List<Variation> variationList;    private Integer signatureId;    private Integer signatureHistoryId;    private String endRecipient;    private String eligibility;    private String qualification;    private String source;    private Integer geographyId;    private String programNumber;    private String programText;    private String vehicleAttribute;    private String orderingCode;    private Integer taxRuleId;    private Integer marketId;    private Integer groupAffiliationId;    private Integer previousOwnershipId;    private String region;    private Integer endRecipientId;    private String taxRule;    private String previousOwnershipDetail;    private String groupAffiliationDetail;    @Override    public String getId() {        return id;    }    public void setId(String id) {        this.id = id;    }    @Override    public String getIncentiveType() {        return type;    }    public void setIncentiveType(String type) {        this.type = type;    }    @Override    public String getCategoryId() {        return categoryId;    }    public void setCategoryId(String categoryId) {        this.categoryId = categoryId;    }    @Override    public String getCategoryDescription() {        return categoryDescription;    }    public void setCategoryDescription(String categoryDescription) {        this.categoryDescription = categoryDescription;    }    @Override    public String getCategoryGroup() {        return categoryGroup;    }    public void setCategoryGroup(final String categoryGroup) {        this.categoryGroup = categoryGroup;    }    @Override    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public String getCode() {        return code;    }    public void setCode(String code) {        this.code = code;    }    @Override    public String getMarket() {        return market;    }    public void setMarket(String market) {        this.market = market;    }    public String getTargeting() {        return targeting;    }    public void setTargeting(String targeting) {        this.targeting = targeting;    }    @Override    public String getGroupAffiliation() {        return groupAffiliation;    }    public void setGroupAffiliation(String groupAffiliation) {        this.groupAffiliation = groupAffiliation;    }    @Override    public Date getEffectiveDate() {        return effectiveDate;    }    public void setEffectiveDate(Date effectiveDate) {        this.effectiveDate = effectiveDate;    }    @Override    public Date getExpiryDate() {        return expiryDate;    }    public void setExpiryDate(Date expiryDate) {        this.expiryDate = expiryDate;    }    @Override    public String getPreviousOwnership() {        return previousOwnership;    }    public void setPreviousOwnership(String previousOwnership) {        this.previousOwnership = previousOwnership;    }    @Override    public String getOfferType() {        return offerType;    }    public void setOfferType(String offerType) {        this.offerType = offerType;    }    @Override    public List<VehicleStatus> getVehicleStatuses() {        return vehicleStatuses;    }    public void setVehicleStatusList(List<VehicleStatus> vehicleStatusList) {        this.vehicleStatuses = vehicleStatusList;    }    @Override    public List<FinancialInstitution> getFinancialInstitutionList() {        return financialInstitutionList;    }    public void setFinancialInstitutionList(List<FinancialInstitution> financialInstitutionList) {        this.financialInstitutionList = financialInstitutionList;    }    @Override    public List<DeliveryType> getDeliveryTypes() {        return deliveryTypes;    }    public void setDeliveryTypes(final List<DeliveryType> deliveryTypes) {        this.deliveryTypes = deliveryTypes;    }    @Override    public List<Variation> getVariationList() {        return variationList;    }    public void setVariationList(List<Variation> variationList) {        this.variationList = variationList;    }    @Override    public Integer getSignatureId() {        return signatureId;    }    public void setSignatureId(Integer signatureId) {        this.signatureId = signatureId;    }    @Override    public Integer getSignatureHistoryId() {        return signatureHistoryId;    }    public void setSignatureHistoryId(Integer signatureHistoryId) {        this.signatureHistoryId = signatureHistoryId;    }    @Override    public String getEndRecipient() {        return endRecipient;    }    public void setEndRecipient(String endRecipient) {        this.endRecipient = endRecipient;    }    @Override    public String getEligibility() {        return eligibility;    }    public void setEligibility(String eligibility) {        this.eligibility = eligibility;    }    @Override    public String getQualification() {        return qualification;    }    public void setQualification(String qualification) {        this.qualification = qualification;    }    @Override    public String getSource() {        return source;    }    public void setSource(String source) {        this.source = source;    }    @Override    public Integer getGeographyId() {        return geographyId;    }    public void setGeographyId(Integer geographyId) {        this.geographyId = geographyId;    }    @Override    public String getProgramNumber() {        return programNumber;    }    public void setProgramNumber(String programNumber) {        this.programNumber = programNumber;    }    @Override    public String getProgramText() {        return programText;    }    public void setProgramText(String programText) {        this.programText = programText;    }    @Override    public String getVehicleAttribute() {        return vehicleAttribute;    }    public void setVehicleAttribute(String vehicleAttribute) {        this.vehicleAttribute = vehicleAttribute;    }    @Override    public String getOrderingCode() {        return orderingCode;    }    public void setOrderingCode(String orderingCode) {        this.orderingCode = orderingCode;    }    @Override    public Integer getTaxRuleId() {        return taxRuleId;    }    public void setTaxRuleId(Integer taxRuleId) {        this.taxRuleId = taxRuleId;    }    @Override    public boolean isStackableWith(Incentive incentive) {        return false;    }    @Override    public Integer getMarketId() {        return marketId;    }    public void setMarketId(final Integer marketId) {        this.marketId = marketId;    }    @Override    public Integer getGroupAffiliationId() {        return groupAffiliationId;    }    public void setGroupAffiliationId(final Integer groupAffiliationId) {        this.groupAffiliationId = groupAffiliationId;    }    @Override    public Integer getPreviousOwnershipId() {        return previousOwnershipId;    }    public void setPreviousOwnershipId(final Integer previousOwnershipId) {        this.previousOwnershipId = previousOwnershipId;    }    @Override    public String getRegion() {        return region;    }    public void setRegion(final String region) {        this.region = region;    }    @Override    public Integer getEndRecipientId() {        return endRecipientId;    }    public void setEndRecipientId(final Integer endRecipientId) {        this.endRecipientId = endRecipientId;    }    @Override    public String getTaxRule() {        return taxRule;    }    @Override    public String getGroupAffiliationDetail() {        return groupAffiliationDetail;    }    @Override    public String getPreviousOwnershipDetail() {        return previousOwnershipDetail;    }    public void setTaxRule(final String taxRule) {        this.taxRule = taxRule;    }    @Override    public String toString() {        return "BasicIncentive [categoryId=" + categoryId + ", " +                "categoryDescription=" + categoryDescription + ", " +                "name=" + name + ", code=" + code + ", market=" + market + "," +                " targeting=" + targeting + ", groupAffiliation=" +                groupAffiliation + ", effectiveDate=" + effectiveDate + ", " +                "expiryDate=" + expiryDate + ", previousOwnership=" +                previousOwnership + ", vehicleStatuses=" +                vehicleStatuses + ", offerType=" + offerType + ", deliveryTypes=" + deliveryTypes + ", variationList=" + variationList + "]";    }}