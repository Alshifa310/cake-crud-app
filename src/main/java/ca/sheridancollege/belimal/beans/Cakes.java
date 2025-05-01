package ca.sheridancollege.belimal.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor

public class Cakes {
	private int id;
    private String name;
    private double price;
    private String mainflavour;
    private String dietary;
    private double calories;
    private String theme;
    private double size;
}
