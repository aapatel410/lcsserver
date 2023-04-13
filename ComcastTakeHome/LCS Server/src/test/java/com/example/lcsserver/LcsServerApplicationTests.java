package com.example.lcsserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.lcsserver.controller.ComputeLCSController;
import com.example.lcsserver.model.SetOfStrings;
import com.example.lcsserver.model.StringValue;
import com.example.lcsserver.service.ComputeLCSServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import java.util.*;

public class LcsServerApplicationTests {
    @Test
    public void testComputeLCS() {

        ComputeLCSServiceImpl lcsService = new ComputeLCSServiceImpl();
        ComputeLCSController controller = new ComputeLCSController(lcsService);

        // Initialize test data
        StringValue s1 = new StringValue("comcast");
        StringValue s2 = new StringValue("comcastic");
        StringValue s3 = new StringValue("broadcaster");
        List<StringValue> list = new ArrayList<>(Arrays.asList(s1,s2,s3));
        SetOfStrings setOfStrings = new SetOfStrings(list);

        ResponseEntity<Map<String, List<String>>> response = controller.computeLCS(setOfStrings);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of("cast"), response.getBody().get("lcs"));
    }

    @Test
    public void testNoPostBody() {
        // Initialize test data
        ComputeLCSServiceImpl lcsService = new ComputeLCSServiceImpl();
        ComputeLCSController controller = new ComputeLCSController(lcsService);

        // Call the controller method with no post body
        ResponseEntity<Map<String, List<String>>> response = controller.computeLCS(null);

        // Assert the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(List.of("Missing Post Body"), response.getBody().get("error"));
    }

    @Test
    public void testEmptySetOfStrings() {
        // Initialize test data
        SetOfStrings setOfStrings = new SetOfStrings(List.of());
        ComputeLCSServiceImpl lcsService = new ComputeLCSServiceImpl();
        ComputeLCSController controller = new ComputeLCSController(lcsService);

        // Call the controller method with an empty set of strings
        ResponseEntity<Map<String, List<String>>> response = controller.computeLCS(setOfStrings);

        // Assert the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(List.of("setOfStrings should not be empty"), response.getBody().get("error"));
    }

    @Test
    public void testNonUniqueSetOfStrings() {
        // Initialize test data
        StringValue s1 = new StringValue("comcast");
        StringValue s2 = new StringValue("comcast");
        SetOfStrings setOfStrings = new SetOfStrings(List.of(s1, s2));
        ComputeLCSServiceImpl lcsService = new ComputeLCSServiceImpl();
        ComputeLCSController controller = new ComputeLCSController(lcsService);

        // Call the controller method with a non-unique set of strings
        ResponseEntity<Map<String, List<String>>> response = controller.computeLCS(setOfStrings);

        // Assert the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(List.of("setOfStrings must contain unique values"), response.getBody().get("error"));
    }
}

