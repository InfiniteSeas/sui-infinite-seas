// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract.taskservice;

import org.dddml.suiinfinitesea.sui.contract.service.ItemEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullItemEventsTaskService {

    @Autowired
    private ItemEventService itemEventService;

    @Scheduled(fixedDelayString = "${sui.contract.pull-item-events.item-created.fixed-delay:5000}")
    public void pullItemCreatedEvents() {
        itemEventService.pullItemCreatedEvents();
    }

    @Scheduled(fixedDelayString = "${sui.contract.pull-item-events.item-updated.fixed-delay:5000}")
    public void pullItemUpdatedEvents() {
        itemEventService.pullItemUpdatedEvents();
    }

}