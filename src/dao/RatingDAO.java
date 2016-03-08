package dao;

import entities.Rating;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Станислав on 08.03.16.
 */
public class RatingDAO {
    private static String GET_RATING_BY_ID_MOVIE="SELECT *  FROM rating WHERE rating.movie_ID=?";
    private static String ADD_NEW_RATING="INSERT INTO rating (`rating_ID`,`user_ID`,`movie_ID`,`score`)VALUES (NULL,?,?,?)";
    Connection connection;

    public Rating getRatingByIdMovie(int idMovie)throws SQLException{
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Rating rating = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(GET_RATING_BY_ID_MOVIE);
            statement.setInt(1, idMovie);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rating = obtain(resultSet);
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return rating;
    }

    

    private Rating obtain(ResultSet resultSet) throws SQLException {
       Rating rating= new Rating();
        rating.setRating_ID(resultSet.getInt("rating_ID"));
        rating.setUser_ID(resultSet.getInt("user_ID"));
        rating.setMovie_ID(resultSet.getInt("movie_ID"));
        rating.setScore(resultSet.getInt("score"));
        return rating;
    }


}
