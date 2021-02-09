package dk.experis.fakeitunes.data_access;

import dk.experis.fakeitunes.models.Track;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TrackRepository {
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<Track> showcaseTracks(){
        ArrayList<Track> tracks = new ArrayList<>();
        try{
            // connect
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Name FROM Track ORDER BY RANDOM() LIMIT 5");
            ResultSet set = prep.executeQuery();
            while(set.next()){
                tracks.add( new Track(set.getString("Name")));
            }
            System.out.println("Get all went well!");

        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        // ---
        return tracks;
    }
    public ArrayList<Track> showcaseComposers(){
        ArrayList<Track> composers = new ArrayList<>();
        try{
            // connect
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Composer FROM Track ORDER BY RANDOM() LIMIT 5");
            ResultSet set = prep.executeQuery();
            while(set.next()){
                composers.add( new Track(set.getString("Composer")));
            }
            System.out.println("Get all went well!");

        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        // ---
        return composers;
    }
    public ArrayList<Track> showcaseGenres(){
        ArrayList<Track> genres = new ArrayList<>();
        try{
            // connect
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Name FROM Genre ORDER BY RANDOM() LIMIT 5");
            ResultSet set = prep.executeQuery();
            while(set.next()){
                genres.add( new Track(set.getString("Name")));
            }
            System.out.println("Get all went well!");

        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        // ---
        return genres;
    }


    /*public Boolean search(Track track){
        Boolean success = false;
        try{
            // connect
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT TrackId, Name, AlbumId, GenreId, Composer, Milliseconds FROM Track WHERE UPPER(Name) LIKE UPPER('%trackName%')");
            prep.setString(1,"Name");
            ResultSet set = prep.executeQuery();
            while(set.next()){
                track = new Track (
                        set.getString("TrackId"),
                        set.getString("Name"),
                        set.getString("AlbumId"),
                        set.getString("GenreId"),
                        set.getString("Composer"),
                        set.getString("Milliseconds")

                );
            }


            int result = prep.executeUpdate();
            success = (result != 0); // if res = 1; true

        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        // ---
        return success;
    }*/
}
