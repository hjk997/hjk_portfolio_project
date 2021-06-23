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
        // Use the request or status to optionally return a ModelAndView
        if (status.is4xxClientError()) {
            // We could add custom model values here
            ModelAndView modelAndView = new ModelAndView("4xx", "errorStatus", status.value());
            //modelAndView.setViewName("4xx");
            //request.setAttribute("error-status", status.value());

            return modelAndView;
        }else if(status.is5xxServerError()){
            ModelAndView modelAndView = new ModelAndView("5xx", "errorStatus", status.value());
            //modelAndView.setViewName("5xx");
            //request.setAttribute("error-status", status.value());

            return modelAndView;
        }

        return new ModelAndView("error");
    }
}
