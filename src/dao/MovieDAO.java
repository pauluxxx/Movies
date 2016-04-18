package dao;


import entities.Movie;
import util.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private static final String GET_RECENT_MOVIE = "select * FROM movie where movie.year=Year(now()) order by movie.releaseDate  desc limit 1 ; ";
    private static String GET_MOVIE_BY_ID = "SELECT * FROM movie WHERE movie.movie_ID=?;";
    private static String GET_MOVIE_BY_YEAR = "SELECT * FROM movie WHERE movie.year=?;";
    private static String GET_MOVIE_ALL = "SELECT * FROM movie;";
    private static String SEARCH_BY_TITLE = "SELECT * FROM movie WHERE movie.title LIKE  ?'%';"; // В таблице movie нет title!?
    Connection connection;

    public Movie getRecent() throws SQLException {
        Movie movie = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_RECENT_MOVIE);
            while (resultSet.next()) {
                movie = obtain(resultSet);
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return movie;
    }

    public Movie getByPK(int pk) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Movie movie = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(GET_MOVIE_BY_ID);
            statement.setInt(1, pk);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie = obtain(resultSet);
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return movie;
    }

    public List<Movie> searchByTitle(String word) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(SEARCH_BY_TITLE);
            statement.setString(1, word);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//Исправлено
                movies.add(obtain(resultSet));
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return movies;
    }

    public List<Movie> getMoviesByYear(int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(GET_MOVIE_BY_YEAR);
            statement.setInt(1, year);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {//Исправлено
                movies.add(obtain(resultSet));
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return movies;
    }

    public List<Movie> getAllMovie() throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            connection = Connector.getConnection();
            resultSet = statement.executeQuery(GET_MOVIE_ALL);
            while (resultSet.next()) {//Исправлено
                movies.add(obtain(resultSet));
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return movies;
    }

    public Movie obtain(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setMovie_id(resultSet.getInt("movie_ID"));
        movie.setRuntime(resultSet.getTime("runtime"));
        movie.setDescription(resultSet.getString("description"));
        //todo поввнимательней кто писал !!!   movie.setImage_id(resultSet.getInt("year"));
        movie.setYear(resultSet.getInt("year"));
        movie.setReleaseDate(resultSet.getDate("releaseDate"));
        movie.setImage_id(resultSet.getInt("image_ID"));
        return movie;
    }

}
