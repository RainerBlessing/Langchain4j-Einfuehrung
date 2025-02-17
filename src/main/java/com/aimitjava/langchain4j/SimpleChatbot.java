package com.aimitjava.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SimpleChatbot {
    public static void main(String[] args) {
        String apiKey = loadApiKeyFromProperties("config.properties");

        ChatLanguageModel model = OpenAiChatModel.builder().apiKey(apiKey).defaultRequestParameters(ChatRequestParameters.builder()
                        .modelName("gpt-4o-mini")
                        .build())
                .build();

        String response = model.chat("Was ist das Ergebnis von 2+2?");

        System.out.println("Antwort: " + response);
    }

    private static String loadApiKeyFromProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            return properties.getProperty("openai.api.key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
