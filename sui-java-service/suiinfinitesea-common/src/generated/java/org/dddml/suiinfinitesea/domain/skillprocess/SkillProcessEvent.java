// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.skillprocess;

import java.util.*;
import org.dddml.suiinfinitesea.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.dddml.suiinfinitesea.specialization.Event;

public interface SkillProcessEvent extends Event, SuiEventEnvelope, SuiMoveEvent, HasEventStatus {

    interface SqlSkillProcessEvent extends SkillProcessEvent {
        SkillProcessEventId getSkillProcessEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface SkillProcessCreated extends SkillProcessEvent {
    }

    interface ProductionProcessStarted extends SkillProcessEvent {
        Long getItemId();

        void setItemId(Long value);

        BigInteger getEnergyCost();

        void setEnergyCost(BigInteger value);

        BigInteger getStartedAt();

        void setStartedAt(BigInteger value);

        BigInteger getCreationTime();

        void setCreationTime(BigInteger value);

        ItemIdQuantityPairs getProductionMaterials();

        void setProductionMaterials(ItemIdQuantityPairs value);

    }

    interface ProductionProcessCompleted extends SkillProcessEvent {
        Long getItemId();

        void setItemId(Long value);

        BigInteger getStartedAt();

        void setStartedAt(BigInteger value);

        BigInteger getCreationTime();

        void setCreationTime(BigInteger value);

        BigInteger getEndedAt();

        void setEndedAt(BigInteger value);

        Boolean getSuccessful();

        void setSuccessful(Boolean value);

        Long getQuantity();

        void setQuantity(Long value);

        Long getExperience();

        void setExperience(Long value);

        Integer getNewLevel();

        void setNewLevel(Integer value);

    }

    interface ShipProductionProcessStarted extends SkillProcessEvent {
        Long getItemId();

        void setItemId(Long value);

        BigInteger getEnergyCost();

        void setEnergyCost(BigInteger value);

        BigInteger getStartedAt();

        void setStartedAt(BigInteger value);

        BigInteger getCreationTime();

        void setCreationTime(BigInteger value);

        ItemIdQuantityPairs getProductionMaterials();

        void setProductionMaterials(ItemIdQuantityPairs value);

    }

    interface ShipProductionProcessCompleted extends SkillProcessEvent {
        Long getItemId();

        void setItemId(Long value);

        BigInteger getStartedAt();

        void setStartedAt(BigInteger value);

        BigInteger getCreationTime();

        void setCreationTime(BigInteger value);

        BigInteger getEndedAt();

        void setEndedAt(BigInteger value);

        Boolean getSuccessful();

        void setSuccessful(Boolean value);

        Long getQuantity();

        void setQuantity(Long value);

        Long getExperience();

        void setExperience(Long value);

        Integer getNewLevel();

        void setNewLevel(Integer value);

    }

    interface CreationProcessStarted extends SkillProcessEvent {
        Long getItemId();

        void setItemId(Long value);

        BigInteger getEnergyCost();

        void setEnergyCost(BigInteger value);

        Long getResourceCost();

        void setResourceCost(Long value);

        BigInteger getStartedAt();

        void setStartedAt(BigInteger value);

        BigInteger getCreationTime();

        void setCreationTime(BigInteger value);

    }

    interface CreationProcessCompleted extends SkillProcessEvent {
        Long getItemId();

        void setItemId(Long value);

        BigInteger getStartedAt();

        void setStartedAt(BigInteger value);

        BigInteger getCreationTime();

        void setCreationTime(BigInteger value);

        BigInteger getEndedAt();

        void setEndedAt(BigInteger value);

        Boolean getSuccessful();

        void setSuccessful(Boolean value);

        Long getQuantity();

        void setQuantity(Long value);

        Long getExperience();

        void setExperience(Long value);

        Integer getNewLevel();

        void setNewLevel(Integer value);

    }

    SkillProcessId getSkillProcessId();

    //void setSkillProcessId(SkillProcessId skillProcessId);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getId_();
    
    //void setId_(String id);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

