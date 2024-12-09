package by.ita.je.controllers;

import by.ita.je.models.Recruiting;
import by.ita.je.services.RecruitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/database/recruiting")
public class RecruitingController {
    private final RecruitingService recruitingService;

    @PostMapping("/create")
    public Recruiting create(@RequestBody Recruiting recruiting) {
        return recruitingService.insert(recruiting);
    }
}
