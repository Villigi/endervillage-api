package de.villigi.endervillageEssentials.utils;

import de.villigi.endervillageEssentials.Essentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlApi {
    public static void updatePreparedStatement(String statement) {
        try {
            PreparedStatement statemnt1 = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement(statement);
            statemnt1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnectionOfApi() {
        return Essentials.getInstance().getDatabaseManager().getConnection();
    }
}
