// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.skillprocess;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import org.dddml.suiinfinitesea.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.dddml.suiinfinitesea.specialization.Event;
import org.dddml.suiinfinitesea.domain.Command;

public interface SkillProcessApplicationService {
    void when(SkillProcessCommands.Create c);

    void when(SkillProcessCommands.CompleteProduction c);

    void when(SkillProcessCommands.CompleteMutexCreation c);

    SkillProcessState get(SkillTypePlayerIdPair id);

    Iterable<SkillProcessState> getAll(Integer firstResult, Integer maxResults);

    Iterable<SkillProcessState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<SkillProcessState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<SkillProcessState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    SkillProcessEvent getEvent(SkillTypePlayerIdPair skillProcessId, long version);

    SkillProcessState getHistoryState(SkillTypePlayerIdPair skillProcessId, long version);

}

