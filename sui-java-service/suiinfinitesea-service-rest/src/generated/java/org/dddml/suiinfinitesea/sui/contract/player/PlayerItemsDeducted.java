// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract.player;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.dddml.suiinfinitesea.sui.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlayerItemsDeducted {
    private String id;

    private BigInteger version;

    private ProductionMaterial[] items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public ProductionMaterial[] getItems() {
        return items;
    }

    public void setItems(ProductionMaterial[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PlayerItemsDeducted{" +
                "id=" + '\'' + id + '\'' +
                ", version=" + version +
                ", items=" + Arrays.toString(items) +
                '}';
    }

}