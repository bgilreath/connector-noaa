package io.camunda.example;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.connector.api.error.ConnectorInputException;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import io.camunda.example.dto.ReqResConnectorRequest;
import org.junit.jupiter.api.Test;

public class ResReqRequestTest {

  ObjectMapper objectMapper = new ObjectMapper();

  /*
  Skipping this test too
  @Test
  void shouldFailWhenPageZero() throws JsonProcessingException {
    // given
    var input = new ReqResConnectorRequest("test.x.com","made@up.com", "SGX", "35,57");
    var context = OutboundConnectorContextBuilder.create().variables(objectMapper.writeValueAsString(input)).build();
    // when
    assertThatThrownBy(() -> context.bindVariables(ReqResConnectorRequest.class))
      // then
      .isInstanceOf(ConnectorInputException.class);
      //.hasMessageContaining("user_email");
  }
  */
}