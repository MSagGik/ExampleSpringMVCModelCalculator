package ru.msaggik.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first") // для задания у класса уникального ветвления url
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              Model model) {
        //System.out.println("GoodBye, " + name + " " + surname);
        model.addAttribute("message", "GoodBye, " + name + " " + surname); // отображение пользователю
        // в представлении предыдущей строки
        return "first/goodbye";
    }

    // реализация калькулятора
    @GetMapping("/calculator")
    // от пользователя нужно получить три параметра GET запроса и нужно задать модель для представления
    public String calculator(@RequestParam(value = "a", required = false) int a,
                             @RequestParam(value = "b", required = false) int b,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        // подсчёт математических операций
        double result;
        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "addition":
                result = a + b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "division":
                result = a / (double)b;
                break;
            default:
                result = 0;
                break;
        }
        model.addAttribute("result", result);
        return "first/calculator";
    }
}
