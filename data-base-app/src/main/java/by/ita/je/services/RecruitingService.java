package by.ita.je.services;

import by.ita.je.models.Recruiting;
import by.ita.je.repository.RecruitingCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RecruitingService {
    private final RecruitingCrudRepository recruitingCrudRepository;

    public Recruiting insert(Recruiting recruiting) {
        return recruitingCrudRepository.save(recruiting);
    }


}
