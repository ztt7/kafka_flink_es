package com.it;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {
    public static void main(String[] args) {
        System.out.println("ztt分支开发的代码");

        System.out.println("尝试reset功能");


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        ObjectNode insNode = objectMapper.createObjectNode();
        insNode.put("pers",123);
        insNode.put("txdate","20230405");

        objectNode.put("indeName","input_es_2023");
        objectNode.put("routingField","input_es_2023");
        objectNode.put("docId",100);
        objectNode.set("source",insNode);

        JsonNode source = objectNode.get("source");
        String sourceString = source.toString();
        System.out.println(sourceString.toString());


    }
}
