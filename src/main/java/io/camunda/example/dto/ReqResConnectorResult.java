package io.camunda.example.dto;

import java.util.Map;
public record ReqResConnectorResult(Map<String, Object> response) {}

//TODO use JSON as the response
//import com.fasterxml.jackson.databind.util.JSONPObject;
//public record NoaaConnectorResult(JSONPObject response) {}
//public record ReqResConnectorResult(JSONPObject response) {}
