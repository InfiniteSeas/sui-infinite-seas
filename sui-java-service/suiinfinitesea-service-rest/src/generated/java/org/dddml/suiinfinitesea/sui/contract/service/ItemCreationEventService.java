// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract.service;

import com.github.wubuku.sui.bean.EventId;
import com.github.wubuku.sui.bean.Page;
import com.github.wubuku.sui.bean.PaginatedMoveEvents;
import com.github.wubuku.sui.bean.SuiMoveEventEnvelope;
import com.github.wubuku.sui.utils.SuiJsonRpcClient;
import org.dddml.suiinfinitesea.domain.itemcreation.AbstractItemCreationEvent;
import org.dddml.suiinfinitesea.sui.contract.ContractConstants;
import org.dddml.suiinfinitesea.sui.contract.DomainBeanUtils;
import org.dddml.suiinfinitesea.sui.contract.SuiPackage;
import org.dddml.suiinfinitesea.sui.contract.itemcreation.ItemCreationCreated;
import org.dddml.suiinfinitesea.sui.contract.itemcreation.ItemCreationUpdated;
import org.dddml.suiinfinitesea.sui.contract.repository.ItemCreationEventRepository;
import org.dddml.suiinfinitesea.sui.contract.repository.SuiPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemCreationEventService {

    @Autowired
    private SuiPackageRepository suiPackageRepository;

    @Autowired
    private SuiJsonRpcClient suiJsonRpcClient;

    @Autowired
    private ItemCreationEventRepository itemCreationEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractItemCreationEvent event) {
        event.setStatus("D");
        itemCreationEventRepository.save(event);
    }

    @Transactional
    public void pullItemCreationCreatedEvents() {
        String packageId = getCommonSuiPackageId();
        if (packageId == null) {
            return;
        }
        int limit = 1;
        EventId cursor = getItemCreationCreatedEventNextCursor();
        while (true) {
            PaginatedMoveEvents<ItemCreationCreated> eventPage = suiJsonRpcClient.queryMoveEvents(
                    packageId + "::" + ContractConstants.ITEM_CREATION_MODULE_ITEM_CREATION_CREATED,
                    cursor, limit, false, ItemCreationCreated.class);

            if (eventPage.getData() != null && !eventPage.getData().isEmpty()) {
                cursor = eventPage.getNextCursor();
                for (SuiMoveEventEnvelope<ItemCreationCreated> eventEnvelope : eventPage.getData()) {
                    saveItemCreationCreated(eventEnvelope);
                }
            } else {
                break;
            }
            if (!Page.hasNextPage(eventPage)) {
                break;
            }
        }
    }

    private EventId getItemCreationCreatedEventNextCursor() {
        AbstractItemCreationEvent lastEvent = itemCreationEventRepository.findFirstItemCreationCreatedByOrderBySuiTimestampDesc();
        return lastEvent != null ? new EventId(lastEvent.getSuiTxDigest(), lastEvent.getSuiEventSeq() + "") : null;
    }

    private void saveItemCreationCreated(SuiMoveEventEnvelope<ItemCreationCreated> eventEnvelope) {
        AbstractItemCreationEvent.ItemCreationCreated itemCreationCreated = DomainBeanUtils.toItemCreationCreated(eventEnvelope);
        if (itemCreationEventRepository.findById(itemCreationCreated.getItemCreationEventId()).isPresent()) {
            return;
        }
        itemCreationEventRepository.save(itemCreationCreated);
    }

    @Transactional
    public void pullItemCreationUpdatedEvents() {
        String packageId = getCommonSuiPackageId();
        if (packageId == null) {
            return;
        }
        int limit = 1;
        EventId cursor = getItemCreationUpdatedEventNextCursor();
        while (true) {
            PaginatedMoveEvents<ItemCreationUpdated> eventPage = suiJsonRpcClient.queryMoveEvents(
                    packageId + "::" + ContractConstants.ITEM_CREATION_MODULE_ITEM_CREATION_UPDATED,
                    cursor, limit, false, ItemCreationUpdated.class);

            if (eventPage.getData() != null && !eventPage.getData().isEmpty()) {
                cursor = eventPage.getNextCursor();
                for (SuiMoveEventEnvelope<ItemCreationUpdated> eventEnvelope : eventPage.getData()) {
                    saveItemCreationUpdated(eventEnvelope);
                }
            } else {
                break;
            }
            if (!Page.hasNextPage(eventPage)) {
                break;
            }
        }
    }

    private EventId getItemCreationUpdatedEventNextCursor() {
        AbstractItemCreationEvent lastEvent = itemCreationEventRepository.findFirstItemCreationUpdatedByOrderBySuiTimestampDesc();
        return lastEvent != null ? new EventId(lastEvent.getSuiTxDigest(), lastEvent.getSuiEventSeq() + "") : null;
    }

    private void saveItemCreationUpdated(SuiMoveEventEnvelope<ItemCreationUpdated> eventEnvelope) {
        AbstractItemCreationEvent.ItemCreationUpdated itemCreationUpdated = DomainBeanUtils.toItemCreationUpdated(eventEnvelope);
        if (itemCreationEventRepository.findById(itemCreationUpdated.getItemCreationEventId()).isPresent()) {
            return;
        }
        itemCreationEventRepository.save(itemCreationUpdated);
    }


    private String getCommonSuiPackageId() {
        return suiPackageRepository.findById(ContractConstants.COMMON_SUI_PACKAGE_NAME)
                .map(SuiPackage::getObjectId).orElse(null);
    }
}
