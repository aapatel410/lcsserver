package com.example.lcsserver.controller;

import com.example.lcsserver.model.SetOfStrings;
import com.example.lcsserver.model.StringValue;
import com.example.lcsserver.service.ComputeLCSServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class ComputeLCSController {


    private ComputeLCSServiceImpl computeLCSService;
    @Autowired
    public ComputeLCSController(ComputeLCSServiceImpl lcsService){
        computeLCSService = lcsService;
    }

    @PostMapping("/lcs")
    public ResponseEntity<Map<String, List<String>>> computeLCS(@RequestBody(required = false) SetOfStrings setOfStrings){

        //if post body missing
        if(setOfStrings == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", List.of("Missing Post Body")));
        }
        List<StringValue> stringValues =  setOfStrings.getSetOfStrings();
        /*If setOfStrings is empty the server should respond with an HTTP an appropriate status code
        with a response body explaining that setOfStrings should not be empty.*/
        if (stringValues == null || stringValues.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", List.of("setOfStrings should not be empty")));
        }

        /*if the setOfStrings supplied is not a set (i.e. all strings are not unique) the server should
        respond with an appropriate HTTP status code, and a response
        body explaining that "setOfStrings" must be a Set*/
        Set<String> setValues = stringValues.stream().map(StringValue::getValue).collect(Collectors.toSet());
        if (setValues.size() < stringValues.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", List.of("setOfStrings must contain unique values")));
        }

        //Call LCS method of computeLCSService to find LCS
        List<String> lcs = computeLCSService.findLCS(stringValues);

        Map<String, List<String>> responseBody = Map.of("lcs",lcs);
        return ResponseEntity.ok(responseBody);
    }



}
