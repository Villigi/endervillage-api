package de.villigi.endervillageEssentials.utils;

import de.villigi.endervillageEssentials.Essentials;
import de.villigi.endervillageEssentials.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageApi {

    private String placeholder;
    public MessageApi(String placeholder) {
        this.placeholder = placeholder;
    }

    public void addMessage(String message) {
        //messages      Placeholder    Messages
        if(!containsMessage()){
            try {
                PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("INSERT INTO messages (Placeholder, Message) VALUES ('" + placeholder + "', '" + message + "');");
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean containsMessage() {
        boolean exists = false;
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("SELECT * FROM `messages` WHERE Placeholder = '" + placeholder + "';");
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                exists = true;
            }else{
                exists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public String getMessage() {
        String message = "";
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("SELECT * FROM `messages` WHERE Placeholder = '" + placeholder + "';");
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                message = resultSet.getString("Message");
            }else{
                message = "§cDie Nachricht mit dem Namen ("+placeholder+") wurde nicht in der Datenbank gefunden!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }


    public static HashMap<String, String> getMessages() throws SQLException {
        HashMap<String, String> messages = new HashMap<>();
        String sql = "SELECT Placeholder, Message FROM messages";
        Connection connection = Essentials.getInstance().getDatabaseManager().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String placeholder = resultSet.getString("Placeholder");
            String message = resultSet.getString("Message");
            messages.put(placeholder, message);
        }

        resultSet.close();
        statement.close();

        return messages;
    }


    public void changeMessage(String message) {
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("UPDATE messages SET Message = '" + message + "' WHERE Placeholder = '" + placeholder + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
