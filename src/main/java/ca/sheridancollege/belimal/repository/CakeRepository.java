package ca.sheridancollege.belimal.repository;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.belimal.beans.Cakes;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CakeRepository {
    
    private NamedParameterJdbcTemplate jdbc;
    
    public void addCake(Cakes cake) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        
        String query = "INSERT INTO cakes (name, price, mainflavour, dietary, calories, theme, size) "
                     + "VALUES (:na, :pr, :mf, :die, :cal, :th, :si)";
        
        params.addValue("na", cake.getName());
        params.addValue("pr", cake.getPrice());
        params.addValue("mf", cake.getMainflavour());
        params.addValue("die", cake.getDietary());
        params.addValue("cal", cake.getCalories());
        params.addValue("th", cake.getTheme());
        params.addValue("si", cake.getSize());
        
        jdbc.update(query, params);
    }
    
    public ArrayList<Cakes> getCake() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String query = "SELECT * FROM cakes";
        ArrayList<Cakes> cakes = (ArrayList<Cakes>) jdbc.query(query, params, new BeanPropertyRowMapper<Cakes>(Cakes.class));
        return cakes;
    }
    
    public Cakes getCakeById(int id) {
    	MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "SELECT * FROM cakes WHERE id=:meow ";
		params.addValue("meow", id);
		
		ArrayList<Cakes> cakes = (ArrayList<Cakes>) jdbc.query(query, params, new BeanPropertyRowMapper<Cakes>(Cakes.class));
		
		if (cakes.size() > 0) {
			return cakes.get(0);
		}
		else {
			return null;
		}
    }
    
    public void editCake (Cakes cake) {
    	 MapSqlParameterSource params = new MapSqlParameterSource();
         
    	 String query = "UPDATE cakes SET name =:na, price=:pr, mainflavour=:mf , dietary=:die , calories=:cal, theme=:th, size=:si WHERE id=:id";
    	 params.addValue("id", cake.getId());

         
         params.addValue("na", cake.getName());
         params.addValue("pr", cake.getPrice());
         params.addValue("mf", cake.getMainflavour());
         params.addValue("die", cake.getDietary());
         params.addValue("cal", cake.getCalories());
         params.addValue("th", cake.getTheme());
         params.addValue("si", cake.getSize());
         
         jdbc.update(query, params);
    }
    
    public void deleteCake(int id) {
    	MapSqlParameterSource params = new MapSqlParameterSource();
		
		String query = "DELETE FROM cakes WHERE id=:id";
		
		params.addValue("id", id);
		
		jdbc.update(query, params);
    }
    
    // For the Satats Page :
    public long getTotalCakes() {
        String query = "SELECT COUNT(*) FROM cakes";
        MapSqlParameterSource params = new MapSqlParameterSource(); 

        return jdbc.queryForObject(query, params, Long.class);
    }
    
    public double getAveragePrice() {
        String query = "SELECT AVG(price) FROM cakes";
        MapSqlParameterSource params = new MapSqlParameterSource(); 

        return jdbc.queryForObject(query, params, Double.class);
    }
    
    public double getMaxCalories() {
        String query = "SELECT MAX(calories) FROM cakes";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbc.queryForObject(query, params, Double.class);
    }
    
    public double getMaxSize() {
        String query = "SELECT MAX(size) FROM cakes";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbc.queryForObject(query, params, Double.class);
    }
    
    public long getCountByDietary(String dietary) {
        String query = "SELECT COUNT(*) FROM cakes WHERE dietary = 'Vegan'";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dietary", dietary);
        return jdbc.queryForObject(query, params, Long.class);
    }
    


       
  

}
