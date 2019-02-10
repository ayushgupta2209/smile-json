package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * @author Ayush Gupta
 */

@SpringBootApplication
public class DemoApplication {

  private static String resourceFolderPath = "./src/main/resources/";

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
    SmileParser smileParser = applicationContext.getBean(SmileParser.class);

    try {
      File directory = new File(resourceFolderPath + "Sample_100KB.json");

      //Converting Json to Smile
      byte[] smileBytes = smileParser.jsonToSmile(directory);
      //Copying to file to verify
      smileParser.writeToFile(smileBytes, resourceFolderPath + "Sample_100KB_json_converted.sml");

      //Converting Smile to Json
      JsonNode node = smileParser.smileToJSON(smileBytes);

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}
