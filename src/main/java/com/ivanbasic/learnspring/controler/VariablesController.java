package com.ivanbasic.learnspring.controler;

import com.ivanbasic.learnspring.dto.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
public class VariablesController {

    @GetMapping("/variables/{pathId}")
    String showVariables(
            @PathVariable Long pathId,
            @RequestParam(value = "page", required = false) Long page,
            @RequestParam(value = "size", required = false) Long size,
            @RequestHeader("head1") int head1,
            @RequestHeader("head2") int head2,
            @RequestBody Greeting greeting
    ) {

        return
                "VARIABLES:" +
                        "\nPathVariable pathId=" + pathId +
                        "\nQuery Variable (@RequestParam) page=" + page +
                        "\nQuery Variable (@RequestParam) size=" + size +
                        "\nHead var (@RequestHeader) head1=" + head1 +
                        "\nHead var (@RequestHeader) head2=" + head2 +
                        "\nBody var (@RequestBody) greeting=" + greeting;
    }
}
