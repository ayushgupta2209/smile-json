package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * @author Ayush Gupta
 */

@Slf4j
public class SmileParser {
  final ObjectMapper mapper = new ObjectMapper(new SmileFactory());

  public byte[] jsonToSmile(String jsonValue) throws JsonProcessingException {
    try {
       return mapper.writeValueAsBytes(jsonValue);
    } catch (JsonProcessingException e) {
      log.error("Some error occurred in json parsing", e);
      throw e;
    }
  }

  public byte[] jsonToSmile(File jsonFile) throws IOException {
    try {
      JsonNode jsonNode = mapper.readValue(jsonFile, JsonNode.class);
      return mapper.writeValueAsBytes(jsonNode);
    } catch (IOException e) {
      log.error("Some error occurred in json parsing", e);
      throw e;
    }
  }

  public JsonNode smileToJSON(byte[] smileBytes) throws IOException {
    try {
      return mapper.readValue(smileBytes, JsonNode.class);
    } catch (IOException e) {
      log.error("Some error occurred in smile parsing", e);
      throw e;
    }
  }

  public JsonNode smileToJSON(File smileFile) throws IOException {
    try {
      return mapper.readValue(smileFile, JsonNode.class);
    } catch (IOException e) {
      log.error("Some error occurred in smile parsing", e);
      throw e;
    }
  }

}
