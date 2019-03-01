package pl.wiktor.customexceptionmessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CustomController {

    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> getMessage() throws Exception {

        HttpUriRequest request = new HttpGet("http://192.168.20.30:8080/users/3");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        UserDTO userDTO = RetrieveUtil.retrieveResourceFromResponse(response, UserDTO.class);


        return new ResponseEntity<>(userDTO, HttpStatus.OK);


    }


    @PostMapping()
    public @ResponseBody
    ResponseEntity<?> setMessage(@RequestBody @Validated(CustomValidation.class) CustomDTO customDTO) {

        if (customDTO.getName().equals("aaa")) {
            throw new CustomException();
        }

        return new ResponseEntity<>(new ResponseMessage("GOOD VALIDATION!"), HttpStatus.OK);
    }


}
