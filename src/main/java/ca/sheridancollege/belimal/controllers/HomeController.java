package ca.sheridancollege.belimal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.belimal.beans.Cakes;
import ca.sheridancollege.belimal.repository.CakeRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	CakeRepository cakerepo;
	
	@GetMapping("/")
    public String home() {
        return "home.html"; // Home page
    }
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("cakes", new Cakes());
		
		return "add.html";
	}
	
	@PostMapping("/add")
	public String addtodb(Model model, @ModelAttribute Cakes cake) {
		
		cakerepo.addCake(cake);
		return "redirect:/add";
	}
	
	@GetMapping("/view")
	public String viewdrink(Model model) {
			model.addAttribute("cakeList", cakerepo.getCake());	
		return "view.html";
}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
	    Cakes cake = cakerepo.getCakeById(id);
	    model.addAttribute("cake", cake);  // Using 'cake' here
	    return "edit.html";
	}

	@PostMapping("/edit")
	public String editDrinkProcess(Model model, @ModelAttribute Cakes cake) {
	    cakerepo.editCake(cake);
	    return "redirect:/view";
	}

	@GetMapping("/delete/{id}")
	public String deletePage(@PathVariable int id, Model model) {
	cakerepo.deleteCake(id);
	return "redirect:/view";
	}
	
	@GetMapping("/stats")
    public String stats(Model model) {
        long totalCakes = cakerepo.getTotalCakes();
        double averagePrice = cakerepo.getAveragePrice();
        double maxCalories = cakerepo.getMaxCalories();
        double minSize = cakerepo.getMaxSize();
        long veganCakeCount = cakerepo.getCountByDietary("Vegan");

        
        model.addAttribute("totalCakes", totalCakes);
        model.addAttribute("averagePrice", averagePrice);
        model.addAttribute("maxCalories", maxCalories);
        model.addAttribute("minSize", minSize);
        model.addAttribute("veganCakeCount", veganCakeCount);

        return "stats.html"; 
    }
	
	
}
