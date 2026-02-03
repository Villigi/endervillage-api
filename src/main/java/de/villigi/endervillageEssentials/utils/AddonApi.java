package de.villigi.endervillageEssentials.utils;

import de.villigi.endervillageEssentials.Essentials;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddonApi {

    private String addon;

    public AddonApi(String addon) {
        this.addon = addon;
    }

    public void create() {
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("INSERT INTO addon_availability (Addon, Availability) VALUES ('" + addon + "', '" + AddonAvailability.UNCLEAR.toString() + "');");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("DELETE FROM addon_availability WHERE Addon = '" + addon + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAvailability(AddonAvailability availability) {
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("UPDATE addon_availability SET Availability = '" + availability + "' WHERE Addon = '" + addon + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AddonAvailability getAvailability() {
        String availabilityString = "";
        AddonAvailability availability = null;
        try {
            PreparedStatement statement = Essentials.getInstance().getDatabaseManager().getConnection().prepareStatement("SELECT * FROM `addon_availability` WHERE Addon = ` " + addon + "`;");
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                availabilityString = resultSet.getString("Availability");
            }else{
                availability = AddonAvailability.UNCLEAR;
                System.out.println("The Addon" + addon + " is not founded ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        availability = AddonAvailability.valueOf(availabilityString);
        return availability;
    }

}
