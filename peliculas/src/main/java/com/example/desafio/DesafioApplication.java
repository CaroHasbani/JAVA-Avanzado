package com.example.desafio;

import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {
	@Autowired
    private JdbcTemplate jdbcTemplate;


	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
	@Override
    public void run(String... args) throws Exception {
        this.createTables();

        this.insertData();

        this.listData();

}

	private void createTables() {
        
		// Si ya existe la tabla la eliminamos
		jdbcTemplate.execute("DROP TABLE IF EXISTS movie_actor");
        jdbcTemplate.execute("DROP TABLE IF EXISTS movies");
        jdbcTemplate.execute("DROP TABLE IF EXISTS actors");
        jdbcTemplate.execute("DROP TABLE IF EXISTS categories");
		
		// Tabla de categorias
        String CreateSQLTableCategories = "CREATE TABLE categories(id serial NOT NULL, category varchar NULL, CONSTRAINT category_pkey PRIMARY KEY(id))";
        jdbcTemplate.execute(CreateSQLTableCategories);
        
		// Tabla de peliculas
        String CreateSQLTableMovie = "CREATE TABLE movies(id serial NOT NULL, movie_name varchar NULL, " +
                " category_id Integer, CONSTRAINT movie_pkey PRIMARY KEY(id), FOREIGN KEY (category_id) REFERENCES categories(id))";
        jdbcTemplate.execute(CreateSQLTableMovie);

        // Tabla de actores
        String CreateSQLTableActors = "CREATE TABLE actors(id serial NOT NULL, actor_name varchar NULL, CONSTRAINT actor_pkey PRIMARY KEY(id))";
        jdbcTemplate.execute(CreateSQLTableActors);

        // Tabla actoresXpelicula
        String CreateSQLTableMovieActor = "CREATE TABLE movie_actor(id_movie INTEGER NOT NULL, id_actor INTEGER NOT NULL, " +
                "PRIMARY KEY(id_movie,id_actor), FOREIGN KEY(id_movie) REFERENCES movies(id), FOREIGN KEY(id_actor) REFERENCES actors(id))";
        jdbcTemplate.execute(CreateSQLTableMovieActor);
    }

    private void insertData(){
        // Insertamos registros en la tabla de categorias
        String insertFirstCategory = "INSERT INTO categoriesVALUES(1,'Action')";
        jdbcTemplate.execute(insertFirstCategory);

        String insertSecondCategory = "INSERT INTO categories VALUES(2,'Drama')";
        jdbcTemplate.execute(insertSecondCategory);

        // Insertamos registros en la tabla de peliculas
        String insertFirstMovie = "INSERT INTO movies VALUES(1,'Red notice','2021-11-4', 1)";
        jdbcTemplate.execute(insertFirstMovie);

        String insertSecondMovie = "INSERT INTO movies VALUES(2,'Army of the dead','2021-05-21', 2)";
        jdbcTemplate.execute(insertSecondMovie);

        // Insertamos registros en la tabla de actores
        String insertFirstActor = "INSERT INTO actors VALUES(1,'Gal Gadot')";
        jdbcTemplate.execute(insertFirstActor);

        String insertSecondActor = "INSERT INTO actors VALUES(2, 'Wesley Coller')";
        jdbcTemplate.execute(insertSecondActor);

        // Insertamos registros en la tabla actoresXpelicula
        String insertFirstMovieActor = "INSERT INTO movie_actor VALUES(1,1)";
        jdbcTemplate.execute(insertFirstMovieActor);

        String insertSecondMovieActor = "INSERT INTO movie_actor VALUES(2,2)";
        jdbcTemplate.execute(insertSecondMovieActor);
    }

    @SuppressWarnings("deprecation")
	private void listData(){
        String SQLView = "SELECT m.name_movie as movie, c.category as category, a.name_actor as actor " +
                "FROM movie_actor ma, movies m, actors a, categories c " +
                "WHERE m.id = ma.id_movie and a.id = ma.id_actor and m.category_id = c.id";

        List<String> data = new ArrayList<>();

        jdbcTemplate.query(SQLView, new Object[] {}, (rs, row) ->
                                        "Pelicula: " + rs.getString("movie") +
                                        ", Categoria: "+ rs.getString("category") +
                                        ", Actor: " + rs.getString("actor"))
                .forEach(movie -> data.add(movie));
        System.out.println(data);
    }}