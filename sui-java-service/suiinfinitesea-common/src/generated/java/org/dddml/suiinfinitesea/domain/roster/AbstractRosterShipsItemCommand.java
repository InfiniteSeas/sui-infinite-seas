// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.roster;

import java.util.*;
import java.util.Date;
import org.dddml.suiinfinitesea.domain.*;
import org.dddml.suiinfinitesea.domain.AbstractCommand;

public abstract class AbstractRosterShipsItemCommand extends AbstractCommand implements RosterShipsItemCommand {

    private String key;

    public String getKey()
    {
        return this.key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    private RosterId rosterId;

    public RosterId getRosterId()
    {
        return this.rosterId;
    }

    public void setRosterId(RosterId rosterId)
    {
        this.rosterId = rosterId;
    }


}

