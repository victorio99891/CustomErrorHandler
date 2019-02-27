package pl.wiktor.customexceptionmessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CustomController {

    @GetMapping()
    public @ResponseBody
    ResponseEntity<?> getMessage() {
        return new ResponseEntity<>(new ResponseMessage("GOOD ANSWER!"), HttpStatus.OK);
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
