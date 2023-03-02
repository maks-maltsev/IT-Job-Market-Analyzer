package com.maltsev.vacanciesparser.service.datahandler;

import com.maltsev.vacanciesparser.entity.Framework;
import com.maltsev.vacanciesparser.entity.Requirement;
import com.maltsev.vacanciesparser.entity.Vacancy;
import com.maltsev.vacanciesparser.entity.enums.*;
import com.maltsev.vacanciesparser.repository.FrameworkRepository;
import com.maltsev.vacanciesparser.repository.RequirementRepository;
import com.maltsev.vacanciesparser.repository.VacancyRepository;
import com.maltsev.vacanciesparser.service.date.DateService;
import org.springframework.stereotype.Service;

import static com.maltsev.vacanciesparser.service.datahandler.VacancyMatchCounter.getMatchCount;

/**
 * The VacancyDataInserter class is responsible for inserting new data into the database.
 * This class also utilizes the VacancyDataFetcher class to get the latest data from a remote source.
 * The data that is fetched is then used to create new instances of the Framework, Requirement, and Vacancy classes.
 * These instances are then saved to the database via their respective repositories.
 */
@Service
public class VacancyDataInserter {

    private final FrameworkRepository frameworkRepository;
    private final RequirementRepository requirementRepository;
    private final VacancyRepository vacancyRepository;

    private final String[] frameworks;
    private final String[] vacancies;
    private final String[] requirements;

    private final VacancyDataFetcher vacancyDataFetcher;

    public VacancyDataInserter(FrameworkRepository frameworkRepository,
                               RequirementRepository requirementRepository,
                               VacancyRepository vacancyRepository,
                               VacancyDataFetcher vacancyDataFetcher) {
        this.frameworkRepository = frameworkRepository;
        this.requirementRepository = requirementRepository;
        this.vacancyRepository = vacancyRepository;

        this.vacancyDataFetcher = vacancyDataFetcher;

        this.frameworks = Frameworks.getFrameworksArray();
        this.vacancies = Vacancies.getVacanciesArray();
        this.requirements = Requirements.getRequirementsArray();
    }

    public void insertNewDataIntoDB() throws Exception {
        refreshVacanciesSet();
        insertFrameworksIntoDB();
        insertRequirementsIntoDB();
        insertVacanciesIntoDB();
    }

    private void insertFrameworksIntoDB() {
        for (String frameworkName : frameworks) {
            Framework framework = new Framework(
                    frameworkName,
                    getMatchCount(frameworkName, vacancyDataFetcher.getVacanciesSet()),
                    DateService.getFormattedDate()
            );
            frameworkRepository.save(framework);
        }
    }

    private void insertRequirementsIntoDB() {
        for (int i = 0; i < requirements.length; i++){
            Requirement requirement = new Requirement(
                    RequirementsForChart.getAllRequirementsForChart()[i],
                    getMatchCount(requirements[i], vacancyDataFetcher.getVacanciesSet()),
                    DateService.getFormattedDate()
            );
            requirementRepository.save(requirement);
        }
    }

    private void insertVacanciesIntoDB() {
        for (int i = 0; i < vacancies.length; i++){
            Vacancy vacancy = new Vacancy(
                    VacanciesForChart.getAllVacanciesForChart()[i],
                    getMatchCount(vacancies[i], vacancyDataFetcher.getVacanciesSet()),
                    DateService.getFormattedDate()
            );
            vacancyRepository.save(vacancy);
        }
    }

    private void refreshVacanciesSet() throws Exception {
        vacancyDataFetcher.getAllVacanciesData();
    }
}