package io.camunda.example.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;
//import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank; //added
import jakarta.validation.constraints.NotEmpty; //added
import jakarta.validation.constraints.NotNull; //added

public record ReqResConnectorRequest(
    //@Positive @TemplateProperty(group = "pagination", type = PropertyType.Text) int page, 
    //@Positive @TemplateProperty(group = "pagination", type = PropertyType.Text) int per_page,
    @NotEmpty @NotNull @NotBlank @TemplateProperty(group = "noaa_auth", type = PropertyType.Text) String user_site,
    @NotEmpty @NotNull @NotBlank @TemplateProperty(group = "noaa_auth", type = PropertyType.Text) String user_email,
    @NotEmpty @NotNull @NotBlank @TemplateProperty(group = "noaa_grid", type = PropertyType.Text) String grid_area,
    @NotEmpty @NotNull @NotBlank @TemplateProperty(group = "noaa_grid", type = PropertyType.Text) String grid
    )
    {}
