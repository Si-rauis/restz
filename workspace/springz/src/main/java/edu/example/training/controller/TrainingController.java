package edu.example.training.controller;

import edu.example.training.entity.Training;
import edu.example.training.service.TrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/training")
public class TrainingController {
    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/display-details")
    public String displayDetails(@RequestParam String trainingId, Model model){
        System.out.println("/training/display-details id: " + trainingId);

        Training training = trainingService.findById(trainingId);
        model.addAttribute("training", training);

        return "training/trainingDetails";
    }

    @GetMapping("/display-list")
    public String displayList(Model model){
        List<Training> list = trainingService.findByTraining();
            model.addAttribute("trainingList", list);

        return "training/trainingList";
    }
}
