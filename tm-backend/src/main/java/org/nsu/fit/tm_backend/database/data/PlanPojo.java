package org.nsu.fit.tm_backend.database.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanPojo {
    @JsonProperty("id")
    public UUID id;

    @JsonProperty("ap_name")
    public String name;

    @JsonProperty("ap_details")
    public String details;

    @JsonProperty("ap_fee")
    public int fee;
}
