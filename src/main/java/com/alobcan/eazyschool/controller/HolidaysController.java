package com.alobcan.eazyschool.controller;


import com.alobcan.eazyschool.model.Holiday;
import com.alobcan.eazyschool.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display,
                                  Model model) {
        if (Objects.nonNull(display)) {
            switch (display) {
                case "all":
                    model.addAttribute("festival", true);
                    model.addAttribute("federal", true);
                    break;
                case "federal":
                    model.addAttribute("federal", true);
                    break;
                case "festival":
                    model.addAttribute("festival", true);
                    break;
                default:
                    break;
            }
        }

        List<Holiday> holidays = holidaysRepository.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
