package com.contribute.xtrct.presentation.model;

import com.chromedata.commons.dsvio.DSVColumn;
import com.chromedata.commons.identity.IdentityGenerator;
import com.chromedata.commons.identity.utils.IdentityGenerators;

public class LUMarket implements Comparable<LUMarket>{

    private static final IdentityGenerator IDENTITY = IdentityGenerators.createIdentityGenerator(LUMarket.class);

    @DSVColumn(header = "MarketID", qualified = false)
    private Integer id;
    @DSVColumn(header = "Description")
    private String description;

    public LUMarket(final Integer id, final String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(final LUMarket o) {
        if (o == this) {
            return 0;
        }
        int comparison = this.getId().compareTo(o.getId());
        if (comparison != 0) {
            return comparison;
        }
        return this.getDescription().compareTo(o.getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        return IDENTITY.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return IDENTITY.getHashCode(this);
    }

    @Override
    public String toString() {
        return IDENTITY.toString(this);
    }
}
