package com.ivanbasic.learnspring.controler;

import com.ivanbasic.learnspring.dto.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiParametersController {

    @GetMapping("/rest-api-parameters/{pathId}")
    String showParameters(
            @PathVariable Long pathId,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size,
            @RequestHeader("head1") int head1,
            @RequestHeader("head2") int head2,
            @RequestBody Greeting greeting
    ) {

        return
                "REST API PARAMETERS:" +
                        "\npath @PathVariable pathId=" + pathId +
                        "\nquery @RequestParam page=" + page +
                        "\nquery @RequestParam size=" + size +
                        "\nhead @RequestHeader head1=" + head1 +
                        "\nhead @RequestHeader head2=" + head2 +
                        "\nbody @RequestBody greeting=" + greeting;
    }
}
