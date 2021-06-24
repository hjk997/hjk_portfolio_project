package com.hjkportfolio.hjk.error;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorController implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        if (status.is4xxClientError()) {
            return new ModelAndView("4xx", "errorStatus", status.value());
        }else if(status.is5xxServerError()){
            return new ModelAndView("5xx", "errorStatus", status.value());
        }

        return new ModelAndView("error");
    }
}
