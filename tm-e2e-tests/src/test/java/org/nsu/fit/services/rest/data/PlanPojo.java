package org.nsu.fit.services.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanPojo {
    @JsonProperty("id")
    public UUID id;

    @JsonProperty("p_name")
    public String name;

    @JsonProperty("details")
    public String details;

    @JsonProperty("fee")
    public int fee;
}
