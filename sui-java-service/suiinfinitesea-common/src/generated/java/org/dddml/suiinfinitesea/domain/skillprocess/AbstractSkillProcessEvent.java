// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.skillprocess;

import java.util.*;
import org.dddml.suiinfinitesea.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.dddml.suiinfinitesea.specialization.*;
import org.dddml.suiinfinitesea.domain.AbstractEvent;

public abstract class AbstractSkillProcessEvent extends AbstractEvent implements SkillProcessEvent.SqlSkillProcessEvent, SuiEventEnvelope.MutableSuiEventEnvelope, SuiMoveEvent.MutableSuiMoveEvent, HasEventStatus.MutableHasEventStatus {
    private SkillProcessEventId skillProcessEventId = new SkillProcessEventId();

    public SkillProcessEventId getSkillProcessEventId() {
        return this.skillProcessEventId;
    }

    public void setSkillProcessEventId(SkillProcessEventId eventId) {
        this.skillProcessEventId = eventId;
    }
    
    public SkillProcessId getSkillProcessId() {
        return getSkillProcessEventId().getSkillProcessId();
    }

    public void setSkillProcessId(SkillProcessId skillProcessId) {
        getSkillProcessEventId().setSkillProcessId(skillProcessId);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    public BigInteger getVersion() {
        return getSkillProcessEventId().getVersion();
    }
    
    public void setVersion(BigInteger version) {
        getSkillProcessEventId().setVersion(version);
    }

    private String id_;

    public String getId_() {
        return this.id_;
    }
    
    public void setId_(String id) {
        this.id_ = id;
    }

    private Long suiTimestamp;

    public Long getSuiTimestamp() {
        return this.suiTimestamp;
    }
    
    public void setSuiTimestamp(Long suiTimestamp) {
        this.suiTimestamp = suiTimestamp;
    }

    private String suiTxDigest;

    public String getSuiTxDigest() {
        return this.suiTxDigest;
    }
    
    public void setSuiTxDigest(String suiTxDigest) {
        this.suiTxDigest = suiTxDigest;
    }

    private BigInteger suiEventSeq;

    public BigInteger getSuiEventSeq() {
        return this.suiEventSeq;
    }
    
    public void setSuiEventSeq(BigInteger suiEventSeq) {
        this.suiEventSeq = suiEventSeq;
    }

    private String suiPackageId;

    public String getSuiPackageId() {
        return this.suiPackageId;
    }
    
    public void setSuiPackageId(String suiPackageId) {
        this.suiPackageId = suiPackageId;
    }

    private String suiTransactionModule;

    public String getSuiTransactionModule() {
        return this.suiTransactionModule;
    }
    
    public void setSuiTransactionModule(String suiTransactionModule) {
        this.suiTransactionModule = suiTransactionModule;
    }

    private String suiSender;

    public String getSuiSender() {
        return this.suiSender;
    }
    
    public void setSuiSender(String suiSender) {
        this.suiSender = suiSender;
    }

    private String suiType;

    public String getSuiType() {
        return this.suiType;
    }
    
    public void setSuiType(String suiType) {
        this.suiType = suiType;
    }

    private String eventStatus;

    public String getEventStatus() {
        return this.eventStatus;
    }
    
    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    private String createdBy;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    private String commandId;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    private String commandType;

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    protected AbstractSkillProcessEvent() {
    }

    protected AbstractSkillProcessEvent(SkillProcessEventId eventId) {
        this.skillProcessEventId = eventId;
    }


    public abstract String getEventType();

    public static class SkillProcessClobEvent extends AbstractSkillProcessEvent {

        protected Map<String, Object> getDynamicProperties() {
            return dynamicProperties;
        }

        protected void setDynamicProperties(Map<String, Object> dynamicProperties) {
            if (dynamicProperties == null) {
                throw new IllegalArgumentException("dynamicProperties is null.");
            }
            this.dynamicProperties = dynamicProperties;
        }

        private Map<String, Object> dynamicProperties = new HashMap<>();

        protected String getDynamicPropertiesLob() {
            return ApplicationContext.current.getClobConverter().toString(getDynamicProperties());
        }

        protected void setDynamicPropertiesLob(String text) {
            getDynamicProperties().clear();
            Map<String, Object> ps = ApplicationContext.current.getClobConverter().parseLobProperties(text);
            if (ps != null) {
                for (Map.Entry<String, Object> kv : ps.entrySet()) {
                    getDynamicProperties().put(kv.getKey(), kv.getValue());
                }
            }
        }

        @Override
        public String getEventType() {
            return "SkillProcessClobEvent";
        }

    }

    public static class SkillProcessCreated extends SkillProcessClobEvent implements SkillProcessEvent.SkillProcessCreated {

        @Override
        public String getEventType() {
            return "SkillProcessCreated";
        }

    }

    public static class ProductionProcessStarted extends SkillProcessClobEvent implements SkillProcessEvent.ProductionProcessStarted {

        @Override
        public String getEventType() {
            return "ProductionProcessStarted";
        }

        public Long getItemId() {
            Object val = getDynamicProperties().get("itemId");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setItemId(Long value) {
            getDynamicProperties().put("itemId", value);
        }

        public BigInteger getEnergyCost() {
            Object val = getDynamicProperties().get("energyCost");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setEnergyCost(BigInteger value) {
            getDynamicProperties().put("energyCost", value);
        }

        public BigInteger getStartedAt() {
            Object val = getDynamicProperties().get("startedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setStartedAt(BigInteger value) {
            getDynamicProperties().put("startedAt", value);
        }

        public BigInteger getCreationTime() {
            Object val = getDynamicProperties().get("creationTime");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCreationTime(BigInteger value) {
            getDynamicProperties().put("creationTime", value);
        }

        public ItemIdQuantityPairs getProductionMaterials() {
            Object val = getDynamicProperties().get("productionMaterials");
            if (val instanceof ItemIdQuantityPairs) {
                return (ItemIdQuantityPairs) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, ItemIdQuantityPairs.class);
        }

        public void setProductionMaterials(ItemIdQuantityPairs value) {
            getDynamicProperties().put("productionMaterials", value);
        }

    }

    public static class ProductionProcessCompleted extends SkillProcessClobEvent implements SkillProcessEvent.ProductionProcessCompleted {

        @Override
        public String getEventType() {
            return "ProductionProcessCompleted";
        }

        public Long getItemId() {
            Object val = getDynamicProperties().get("itemId");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setItemId(Long value) {
            getDynamicProperties().put("itemId", value);
        }

        public BigInteger getStartedAt() {
            Object val = getDynamicProperties().get("startedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setStartedAt(BigInteger value) {
            getDynamicProperties().put("startedAt", value);
        }

        public BigInteger getCreationTime() {
            Object val = getDynamicProperties().get("creationTime");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCreationTime(BigInteger value) {
            getDynamicProperties().put("creationTime", value);
        }

        public BigInteger getEndedAt() {
            Object val = getDynamicProperties().get("endedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setEndedAt(BigInteger value) {
            getDynamicProperties().put("endedAt", value);
        }

        public Boolean getSuccessful() {
            Object val = getDynamicProperties().get("successful");
            if (val instanceof Boolean) {
                return (Boolean) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Boolean.class);
        }

        public void setSuccessful(Boolean value) {
            getDynamicProperties().put("successful", value);
        }

        public Long getQuantity() {
            Object val = getDynamicProperties().get("quantity");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setQuantity(Long value) {
            getDynamicProperties().put("quantity", value);
        }

        public Long getExperience() {
            Object val = getDynamicProperties().get("experience");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setExperience(Long value) {
            getDynamicProperties().put("experience", value);
        }

        public Integer getNewLevel() {
            Object val = getDynamicProperties().get("newLevel");
            if (val instanceof Integer) {
                return (Integer) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Integer.class);
        }

        public void setNewLevel(Integer value) {
            getDynamicProperties().put("newLevel", value);
        }

    }

    public static class ShipProductionProcessStarted extends SkillProcessClobEvent implements SkillProcessEvent.ShipProductionProcessStarted {

        @Override
        public String getEventType() {
            return "ShipProductionProcessStarted";
        }

        public Long getItemId() {
            Object val = getDynamicProperties().get("itemId");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setItemId(Long value) {
            getDynamicProperties().put("itemId", value);
        }

        public BigInteger getEnergyCost() {
            Object val = getDynamicProperties().get("energyCost");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setEnergyCost(BigInteger value) {
            getDynamicProperties().put("energyCost", value);
        }

        public BigInteger getStartedAt() {
            Object val = getDynamicProperties().get("startedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setStartedAt(BigInteger value) {
            getDynamicProperties().put("startedAt", value);
        }

        public BigInteger getCreationTime() {
            Object val = getDynamicProperties().get("creationTime");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCreationTime(BigInteger value) {
            getDynamicProperties().put("creationTime", value);
        }

        public ItemIdQuantityPairs getProductionMaterials() {
            Object val = getDynamicProperties().get("productionMaterials");
            if (val instanceof ItemIdQuantityPairs) {
                return (ItemIdQuantityPairs) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, ItemIdQuantityPairs.class);
        }

        public void setProductionMaterials(ItemIdQuantityPairs value) {
            getDynamicProperties().put("productionMaterials", value);
        }

    }

    public static class ShipProductionProcessCompleted extends SkillProcessClobEvent implements SkillProcessEvent.ShipProductionProcessCompleted {

        @Override
        public String getEventType() {
            return "ShipProductionProcessCompleted";
        }

        public Long getItemId() {
            Object val = getDynamicProperties().get("itemId");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setItemId(Long value) {
            getDynamicProperties().put("itemId", value);
        }

        public BigInteger getStartedAt() {
            Object val = getDynamicProperties().get("startedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setStartedAt(BigInteger value) {
            getDynamicProperties().put("startedAt", value);
        }

        public BigInteger getCreationTime() {
            Object val = getDynamicProperties().get("creationTime");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCreationTime(BigInteger value) {
            getDynamicProperties().put("creationTime", value);
        }

        public BigInteger getEndedAt() {
            Object val = getDynamicProperties().get("endedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setEndedAt(BigInteger value) {
            getDynamicProperties().put("endedAt", value);
        }

        public Boolean getSuccessful() {
            Object val = getDynamicProperties().get("successful");
            if (val instanceof Boolean) {
                return (Boolean) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Boolean.class);
        }

        public void setSuccessful(Boolean value) {
            getDynamicProperties().put("successful", value);
        }

        public Long getQuantity() {
            Object val = getDynamicProperties().get("quantity");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setQuantity(Long value) {
            getDynamicProperties().put("quantity", value);
        }

        public Long getExperience() {
            Object val = getDynamicProperties().get("experience");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setExperience(Long value) {
            getDynamicProperties().put("experience", value);
        }

        public Integer getNewLevel() {
            Object val = getDynamicProperties().get("newLevel");
            if (val instanceof Integer) {
                return (Integer) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Integer.class);
        }

        public void setNewLevel(Integer value) {
            getDynamicProperties().put("newLevel", value);
        }

    }

    public static class CreationProcessStarted extends SkillProcessClobEvent implements SkillProcessEvent.CreationProcessStarted {

        @Override
        public String getEventType() {
            return "CreationProcessStarted";
        }

        public Long getItemId() {
            Object val = getDynamicProperties().get("itemId");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setItemId(Long value) {
            getDynamicProperties().put("itemId", value);
        }

        public BigInteger getEnergyCost() {
            Object val = getDynamicProperties().get("energyCost");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setEnergyCost(BigInteger value) {
            getDynamicProperties().put("energyCost", value);
        }

        public Long getResourceCost() {
            Object val = getDynamicProperties().get("resourceCost");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setResourceCost(Long value) {
            getDynamicProperties().put("resourceCost", value);
        }

        public BigInteger getStartedAt() {
            Object val = getDynamicProperties().get("startedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setStartedAt(BigInteger value) {
            getDynamicProperties().put("startedAt", value);
        }

        public BigInteger getCreationTime() {
            Object val = getDynamicProperties().get("creationTime");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCreationTime(BigInteger value) {
            getDynamicProperties().put("creationTime", value);
        }

    }

    public static class CreationProcessCompleted extends SkillProcessClobEvent implements SkillProcessEvent.CreationProcessCompleted {

        @Override
        public String getEventType() {
            return "CreationProcessCompleted";
        }

        public Long getItemId() {
            Object val = getDynamicProperties().get("itemId");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setItemId(Long value) {
            getDynamicProperties().put("itemId", value);
        }

        public BigInteger getStartedAt() {
            Object val = getDynamicProperties().get("startedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setStartedAt(BigInteger value) {
            getDynamicProperties().put("startedAt", value);
        }

        public BigInteger getCreationTime() {
            Object val = getDynamicProperties().get("creationTime");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setCreationTime(BigInteger value) {
            getDynamicProperties().put("creationTime", value);
        }

        public BigInteger getEndedAt() {
            Object val = getDynamicProperties().get("endedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setEndedAt(BigInteger value) {
            getDynamicProperties().put("endedAt", value);
        }

        public Boolean getSuccessful() {
            Object val = getDynamicProperties().get("successful");
            if (val instanceof Boolean) {
                return (Boolean) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Boolean.class);
        }

        public void setSuccessful(Boolean value) {
            getDynamicProperties().put("successful", value);
        }

        public Long getQuantity() {
            Object val = getDynamicProperties().get("quantity");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setQuantity(Long value) {
            getDynamicProperties().put("quantity", value);
        }

        public Long getExperience() {
            Object val = getDynamicProperties().get("experience");
            if (val instanceof Long) {
                return (Long) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Long.class);
        }

        public void setExperience(Long value) {
            getDynamicProperties().put("experience", value);
        }

        public Integer getNewLevel() {
            Object val = getDynamicProperties().get("newLevel");
            if (val instanceof Integer) {
                return (Integer) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Integer.class);
        }

        public void setNewLevel(Integer value) {
            getDynamicProperties().put("newLevel", value);
        }

    }


}

