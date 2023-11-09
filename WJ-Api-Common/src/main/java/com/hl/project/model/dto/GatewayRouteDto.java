package com.hl.project.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO
 */
@Data
public class GatewayRouteDto implements Serializable {

    private Long id;

    private String serviceId;

    private String uri;

    private String predicates;

    private String filters;

    private String order;

    private String remarks;

    private String delFlag;

}