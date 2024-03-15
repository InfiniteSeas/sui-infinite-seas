// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract.service;

import com.github.wubuku.sui.bean.*;
import com.github.wubuku.sui.utils.*;
import org.dddml.suiinfinitesea.domain.experiencetable.*;
import org.dddml.suiinfinitesea.domain.*;
import org.dddml.suiinfinitesea.sui.contract.DomainBeanUtils;
import org.dddml.suiinfinitesea.sui.contract.ExperienceTable;

import java.util.*;
import java.math.*;
import java.util.function.*;

public class SuiExperienceTableStateRetriever {

    private SuiJsonRpcClient suiJsonRpcClient;

    private Function<String, ExperienceTableState.MutableExperienceTableState> experienceTableStateFactory;

    public SuiExperienceTableStateRetriever(SuiJsonRpcClient suiJsonRpcClient,
                                  Function<String, ExperienceTableState.MutableExperienceTableState> experienceTableStateFactory
    ) {
        this.suiJsonRpcClient = suiJsonRpcClient;
        this.experienceTableStateFactory = experienceTableStateFactory;
    }

    public ExperienceTableState retrieveExperienceTableState(String objectId) {
        SuiMoveObjectResponse<ExperienceTable> getObjectDataResponse = suiJsonRpcClient.getMoveObject(
                objectId, new SuiObjectDataOptions(true, true, true, true, true, true, true), ExperienceTable.class
        );
        if (getObjectDataResponse.getData() == null) {
            return null;
        }
        ExperienceTable experienceTable = getObjectDataResponse.getData().getContent().getFields();
        return toExperienceTableState(experienceTable);
    }

    private ExperienceTableState toExperienceTableState(ExperienceTable experienceTable) {
        ExperienceTableState.MutableExperienceTableState experienceTableState = experienceTableStateFactory.apply(experienceTable.getId().getId());
        experienceTableState.setVersion(experienceTable.getVersion());
        experienceTableState.setLevels(java.util.Arrays.stream(experienceTable.getLevels()).map(x -> DomainBeanUtils.toExperienceLevel(x)).collect(java.util.stream.Collectors.toList()));
        return experienceTableState;
    }

    
}
