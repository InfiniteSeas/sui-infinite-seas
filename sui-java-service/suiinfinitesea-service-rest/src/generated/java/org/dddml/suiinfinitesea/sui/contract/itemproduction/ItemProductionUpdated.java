// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract.itemproduction;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.dddml.suiinfinitesea.sui.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ItemProductionUpdated {
    private String id;

    private SkillTypeItemIdPairForEvent itemProductionId;

    private BigInteger version;

    private ItemIdQuantityPairsForEvent productionMaterials;

    private Integer requirementsLevel;

    private Long baseQuantity;

    private Long baseExperience;

    private BigInteger baseCreationTime;

    private BigInteger energyCost;

    private Integer successRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SkillTypeItemIdPairForEvent getItemProductionId() {
        return itemProductionId;
    }

    public void setItemProductionId(SkillTypeItemIdPairForEvent itemProductionId) {
        this.itemProductionId = itemProductionId;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public ItemIdQuantityPairsForEvent getProductionMaterials() {
        return productionMaterials;
    }

    public void setProductionMaterials(ItemIdQuantityPairsForEvent productionMaterials) {
        this.productionMaterials = productionMaterials;
    }

    public Integer getRequirementsLevel() {
        return requirementsLevel;
    }

    public void setRequirementsLevel(Integer requirementsLevel) {
        this.requirementsLevel = requirementsLevel;
    }

    public Long getBaseQuantity() {
        return baseQuantity;
    }

    public void setBaseQuantity(Long baseQuantity) {
        this.baseQuantity = baseQuantity;
    }

    public Long getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Long baseExperience) {
        this.baseExperience = baseExperience;
    }

    public BigInteger getBaseCreationTime() {
        return baseCreationTime;
    }

    public void setBaseCreationTime(BigInteger baseCreationTime) {
        this.baseCreationTime = baseCreationTime;
    }

    public BigInteger getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(BigInteger energyCost) {
        this.energyCost = energyCost;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return "ItemProductionUpdated{" +
                "id=" + '\'' + id + '\'' +
                ", itemProductionId=" + itemProductionId +
                ", version=" + version +
                ", productionMaterials=" + productionMaterials +
                ", requirementsLevel=" + requirementsLevel +
                ", baseQuantity=" + baseQuantity +
                ", baseExperience=" + baseExperience +
                ", baseCreationTime=" + baseCreationTime +
                ", energyCost=" + energyCost +
                ", successRate=" + successRate +
                '}';
    }

}
