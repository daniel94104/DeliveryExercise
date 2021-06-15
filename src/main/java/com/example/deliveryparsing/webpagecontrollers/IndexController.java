package com.example.deliveryparsing.webpagecontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
  private final String INDEX_VIEW = "index";

  @GetMapping("/index")
  public String index(Model model) {
    return INDEX_VIEW;
  }
}
