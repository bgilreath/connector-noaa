package io.camunda.example;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.example.dto.ReqResConnectorRequest;
import io.camunda.example.dto.ReqResConnectorResult;

import java.util.Map;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.fasterxml.jackson.databind.json.*;

@OutboundConnector(
    name = "NOAACONNECTOR", 
    inputVariables = {"noaa_auth", "user_site", "noaa_auth", "user_email", "noaa_grid", "grid_area", "noaa_grid", "grid"},
    type = "com.sailnail:noaa:1")
@ElementTemplate(
    id = "com.sailnail.connector.noaa.v1",
    name = "NOAA NWS Connector",
    version = 1,
    description = "Retrieves the NOAA forecast via api.weather.gov",
    icon = "icon.svg",
    documentationRef = "https://www.sailnail.com/",
    propertyGroups = {
      @ElementTemplate.PropertyGroup(id = "noaa_auth", label = "NOAA Authentication"),
      @ElementTemplate.PropertyGroup(id = "noaa_grid", label = "NOAA Grid Data")
    },
    inputDataClass = ReqResConnectorRequest.class)
public class ReqResConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReqResConnectorFunction.class);

  public static <T> T toObject(String json, Class<T> type) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(json, type);
  }

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var connectorRequest = context.bindVariables(ReqResConnectorRequest.class);
    return executeConnector(connectorRequest);
  }

  private ReqResConnectorResult executeConnector(final ReqResConnectorRequest connectorRequest) {
    LOGGER.info("Executing reqres connector with request {}", connectorRequest);

    //int page = connectorRequest.page();
    //int perPage = connectorRequest.per_page();
    String user_site = connectorRequest.user_site();
    String user_email = connectorRequest.user_email();
    String grid_area = connectorRequest.grid_area();
    String grid = connectorRequest.grid();

    //Removed because it should be validated

    URI uri = URI.create("https://api.weather.gov/gridpoints/"+grid_area+"/"+grid+"/forecast/hourly"); 
    HttpRequest httpRequest = HttpRequest.newBuilder() 
        .GET() 
        .uri(uri)
        .header("Accept", "application/ld+json")
        .header("User-Agent", "("+user_site+" | "+user_email+")") 
        .build();
 
    try (HttpClient httpClient = HttpClient.newHttpClient()) { 
     
      HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      LOGGER.info("Status Code: " + httpResponse.statusCode()); 
      LOGGER.info("Response Body: " + httpResponse.body()); 

      
      Map<String, Object> result = toObject(httpResponse.body(), Map.class);

      //return new ReqResConnectorResult(httpResponse.body());
      return new ReqResConnectorResult(result);

      //TODO handle JSON object natively?
      //JsonNode = mapper.readValue(mapper.writeValueAsString("Text-string"), JsonNode.class)
      //JSONParser parser = new JSONParser();
      //return new ReqResConnectorResult(httpResponse.body().);

    } catch (Exception e) { 
      e.printStackTrace();
      throw new ConnectorException("FAIL", e.getMessage());
    } 


   
  }
}
