package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.handler.RestExceptionHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public final class MockMvcCreator {

    private MockMvcCreator() {}

    public static MockMvc create(Object ...controllers) {
        return MockMvcBuilders.standaloneSetup(controllers)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }
}
