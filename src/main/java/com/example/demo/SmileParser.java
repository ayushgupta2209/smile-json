package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * @author Ayush Gupta
 */

@Slf4j
@Component
public class SmileParser {

  SmileFactory smileFactory;
  final ObjectMapper mapper;
  final ObjectMapper smileMapper;

  SmileParser() {
    smileFactory = new SmileFactory();

    /***
     * By default only field names(keys) are back referenced in SmileGenerator through a flag.
     * But we can enable field values back referencing as well using the following flag:
     * CHECK_SHARED_STRING_VALUES: Whether generator should check if it can "share" short (at most 64 bytes encoded) String value during generating content or not.
    ***/

    //TODO: Uncomment the below line to enable CHECK_SHARED_STRING_VALUES. This can further reduce the size of smile format based on repetitiveness in your values
    // You can clearly see the difference by converting Sample_back_referencing.json to Smile.
    // Comment/uncomment this feature flag and observe the difference in Smile output
    //smileFactory.enable(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES);
    mapper = new ObjectMapper();
    smileMapper = new ObjectMapper(smileFactory);
  }

  public byte[] jsonToSmile(String jsonValue) throws JsonProcessingException {
    try {
       return smileMapper.writeValueAsBytes(jsonValue);
    } catch (JsonProcessingException e) {
      log.error("Some error occurred in json parsing", e);
      throw e;
    }
  }

  public byte[] jsonToSmile(File jsonFile) throws IOException {
    try {
      JsonNode jsonNode = mapper.readValue(jsonFile, JsonNode.class);
      return smileMapper.writeValueAsBytes(jsonNode);
    } catch (IOException e) {
      log.error("Some error occurred in json parsing", e);
      throw e;
    }
  }

  public JsonNode smileToJSON(byte[] smileBytes) throws IOException {
    try {
      return smileMapper.readValue(smileBytes, JsonNode.class);
    } catch (IOException e) {
      log.error("Some error occurred in smile parsing", e);
      throw e;
    }
  }

  public JsonNode smileToJSON(File smileFile) throws IOException {
    try {
      return smileMapper.readValue(smileFile, JsonNode.class);
    } catch (IOException e) {
      log.error("Some error occurred in smile parsing", e);
      throw e;
    }
  }

  public void writeToFile(byte[] bytes, String fileDest) {
    try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
      fileOuputStream.write(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
