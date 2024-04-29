// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.map;

import java.util.*;
import java.util.Date;
import java.math.BigInteger;
import org.dddml.suiinfinitesea.domain.*;
import org.dddml.suiinfinitesea.specialization.*;
import org.dddml.suiinfinitesea.domain.AbstractEvent;

public abstract class AbstractMapEvent extends AbstractEvent implements MapEvent.SqlMapEvent, SuiEventEnvelope.MutableSuiEventEnvelope, SuiMoveEvent.MutableSuiMoveEvent, HasEventStatus.MutableHasEventStatus {
    private MapEventId mapEventId = new MapEventId();

    public MapEventId getMapEventId() {
        return this.mapEventId;
    }

    public void setMapEventId(MapEventId eventId) {
        this.mapEventId = eventId;
    }
    
    public String getId() {
        return getMapEventId().getId();
    }

    public void setId(String id) {
        getMapEventId().setId(id);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    public BigInteger getVersion() {
        return getMapEventId().getVersion();
    }
    
    public void setVersion(BigInteger version) {
        getMapEventId().setVersion(version);
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

    protected AbstractMapEvent() {
    }

    protected AbstractMapEvent(MapEventId eventId) {
        this.mapEventId = eventId;
    }

    protected MapLocationEventDao getMapLocationEventDao() {
        return (MapLocationEventDao)ApplicationContext.current.get("mapLocationEventDao");
    }

    protected MapLocationEventId newMapLocationEventId(Coordinates coordinates)
    {
        MapLocationEventId eventId = new MapLocationEventId(this.getMapEventId().getId(), 
            coordinates, 
            this.getMapEventId().getVersion());
        return eventId;
    }

    protected void throwOnInconsistentEventIds(MapLocationEvent.SqlMapLocationEvent e)
    {
        throwOnInconsistentEventIds(this, e);
    }

    public static void throwOnInconsistentEventIds(MapEvent.SqlMapEvent oe, MapLocationEvent.SqlMapLocationEvent e)
    {
        if (!oe.getMapEventId().getId().equals(e.getMapLocationEventId().getMapId()))
        { 
            throw DomainError.named("inconsistentEventIds", "Outer Id Id %1$s but inner id MapId %2$s", 
                oe.getMapEventId().getId(), e.getMapLocationEventId().getMapId());
        }
    }


    public abstract String getEventType();

    public static class MapClobEvent extends AbstractMapEvent {

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
            return "MapClobEvent";
        }

    }

    public static class InitMapEvent extends MapClobEvent implements MapEvent.InitMapEvent {

        @Override
        public String getEventType() {
            return "InitMapEvent";
        }

    }

    public static class IslandAdded extends MapClobEvent implements MapEvent.IslandAdded {

        @Override
        public String getEventType() {
            return "IslandAdded";
        }

        public Coordinates getCoordinates() {
            Object val = getDynamicProperties().get("coordinates");
            if (val instanceof Coordinates) {
                return (Coordinates) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Coordinates.class);
        }

        public void setCoordinates(Coordinates value) {
            getDynamicProperties().put("coordinates", value);
        }

        public ItemIdQuantityPairs getResources() {
            Object val = getDynamicProperties().get("resources");
            if (val instanceof ItemIdQuantityPairs) {
                return (ItemIdQuantityPairs) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, ItemIdQuantityPairs.class);
        }

        public void setResources(ItemIdQuantityPairs value) {
            getDynamicProperties().put("resources", value);
        }

    }

    public static class MapIslandClaimed extends MapClobEvent implements MapEvent.MapIslandClaimed {

        @Override
        public String getEventType() {
            return "MapIslandClaimed";
        }

        public Coordinates getCoordinates() {
            Object val = getDynamicProperties().get("coordinates");
            if (val instanceof Coordinates) {
                return (Coordinates) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Coordinates.class);
        }

        public void setCoordinates(Coordinates value) {
            getDynamicProperties().put("coordinates", value);
        }

        public String getClaimedBy() {
            Object val = getDynamicProperties().get("claimedBy");
            if (val instanceof String) {
                return (String) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, String.class);
        }

        public void setClaimedBy(String value) {
            getDynamicProperties().put("claimedBy", value);
        }

        public BigInteger getClaimedAt() {
            Object val = getDynamicProperties().get("claimedAt");
            if (val instanceof BigInteger) {
                return (BigInteger) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, BigInteger.class);
        }

        public void setClaimedAt(BigInteger value) {
            getDynamicProperties().put("claimedAt", value);
        }

    }


}

