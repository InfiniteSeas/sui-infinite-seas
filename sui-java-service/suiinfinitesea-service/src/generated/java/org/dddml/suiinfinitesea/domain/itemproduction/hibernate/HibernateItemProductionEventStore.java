// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.itemproduction.hibernate;

import java.io.Serializable;
import java.util.*;
import org.dddml.suiinfinitesea.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.dddml.suiinfinitesea.specialization.*;
import org.dddml.suiinfinitesea.specialization.hibernate.AbstractHibernateEventStore;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.transaction.annotation.Transactional;
import org.dddml.suiinfinitesea.domain.itemproduction.*;

public class HibernateItemProductionEventStore extends AbstractHibernateEventStore {
    @Override
    protected Serializable getEventId(EventStoreAggregateId eventStoreAggregateId, long version)
    {
        return new ItemProductionEventId((SkillTypeItemIdPair) eventStoreAggregateId.getId(), BigInteger.valueOf(version));
    }

    @Override
    protected Class getSupportedEventType()
    {
        return AbstractItemProductionEvent.class;
    }

    @Transactional(readOnly = true)
    @Override
    public EventStream loadEventStream(Class eventType, EventStoreAggregateId eventStoreAggregateId, long version) {
        Class supportedEventType = AbstractItemProductionEvent.class;
        if (!eventType.isAssignableFrom(supportedEventType)) {
            throw new UnsupportedOperationException();
        }
        SkillTypeItemIdPair idObj = (SkillTypeItemIdPair) eventStoreAggregateId.getId();
        Criteria criteria = getCurrentSession().createCriteria(AbstractItemProductionEvent.class);
        criteria.add(Restrictions.eq("itemProductionEventId.itemProductionIdSkillType", idObj.getSkillType()));
        criteria.add(Restrictions.eq("itemProductionEventId.itemProductionIdItemId", idObj.getItemId()));
        criteria.add(Restrictions.le("itemProductionEventId.offChainVersion", version));
        criteria.addOrder(Order.asc("itemProductionEventId.offChainVersion"));
        List es = criteria.list();
        for (Object e : es) {
            ((AbstractItemProductionEvent) e).setEventReadOnly(true);
        }
        EventStream eventStream = new EventStream();
        if (es.size() > 0) {
            eventStream.setSteamVersion(((AbstractItemProductionEvent) es.get(es.size() - 1)).getItemProductionEventId().getVersion().longValue());
        } else {
            //todo?
        }
        eventStream.setEvents(es);
        return eventStream;
    }

}
